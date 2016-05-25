package nudelsquad.nudelcalendar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sathearo on 25.05.2016.
 */

public class VoiceRecord extends Fragment {
    private static final String LOG_TAG = "RECORDER";
    private static final int PERMISSION_VOICE_RECORD = 1;
    private View rootView;

    private ImageButton btnRecord;
    private ImageButton btnPlay;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private static boolean mStartRecording = true;
    private static boolean mStartPlaying = true;
    private DBHandler dbHandler;
    private String mFileName;
    private boolean hasRecorded=false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.voicerecord_fragment, container, false);
        findViewsbyID();
        dbHandler=new DBHandler(getContext());

        mFileName = Environment.getExternalStorageDirectory().toString() + "/data/com.nudelsquad.Nudelcalendar/";
        File file = new File(mFileName);
        try{
            file.mkdir();
        }
        catch(SecurityException se){
            //handle it
        }

        mFileName += System.currentTimeMillis() + ".3gp";

        Log.i(LOG_TAG, mFileName);

        Button addTaskButton = (Button) rootView.findViewById(R.id.addTaskAct);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CreateTaskView(1), "NewFragmentTag");
                ft.commit();
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {


                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            PERMISSION_VOICE_RECORD);

                    return;
                }

                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                mStartPlaying = !mStartPlaying;
            }
        });

        return rootView;
    }

    private void findViewsbyID(){
        btnRecord = (ImageButton) rootView.findViewById(R.id.btn_record);
        btnPlay = (ImageButton) rootView.findViewById(R.id.btn_play);
    }

    private void onRecord(boolean start) {

        hasRecorded=true;
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_black_24dp));
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        mPlayer = null;
    }

    private void startRecording() {
        btnRecord.setBackgroundColor(Color.RED);
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
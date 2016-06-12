package nudelsquad.nudelcalendar.search;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nudelsquad.nudelcalendar.R;

public class search_entrys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_entrys);

/*
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            mySearch(query);
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
        }
        */


    }

    public void mySearch(String querr)
    {
        int a=5;
        int b=3;
    }
}

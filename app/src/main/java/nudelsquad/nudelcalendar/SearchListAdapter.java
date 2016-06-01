package nudelsquad.nudelcalendar;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by waser2 on 01.06.2016.
 */
public class SearchListAdapter extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlist);

/*
        Cursor mCursor;
        mCursor = this.getContentResolver().query(Contacts.People.CONTENT_URI, null, null, null, null);
        startManagingCursor(mCursor);


        ListAdapter adapter = new SimpleCursorAdapter(
                this, // Context.
                android.R.layout.two_line_list_item,  // Specify the row template to use (here, two columns bound to the two retrieved cursor
                rows).
                mCursor,                                              // Pass in the cursor to bind to.
        new String[] {People.NAME, People.COMPANY},           // Array of cursor columns to bind to.
                new int[] {android.R.id.text1, android.R.id.text2});  // Parallel array of which template objects to bind to those columns.

        // Bind to our new adapter.
        setListAdapter(adapter);

        */



    }


}

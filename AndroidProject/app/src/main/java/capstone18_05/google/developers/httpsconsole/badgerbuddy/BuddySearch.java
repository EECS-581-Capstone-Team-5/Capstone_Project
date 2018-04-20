package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;
import capstone18_05.google.developers.httpsconsole.badgerbuddy.ListViewAdapter;
import capstone18_05.google.developers.httpsconsole.badgerbuddy.BuddyNames;

import java.util.ArrayList;

public class BuddySearch extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //variables declared
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] BuddyList;
    ArrayList<BuddyNames> arrList = new ArrayList<BuddyNames>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddysearch);
        Intent intent = getIntent();

        //test list
        BuddyList = new String[]{"Ashli Mosiman", "James Muoghalu", "Colby Hughes", "Richard Aviles", "Alex Pechin", "Giovanni Artavia", "Archer Lammey", "Benjamin Cabrera"};

        list = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < BuddyList.length; i++) {
            BuddyNames BuddyNames = new BuddyNames(BuddyList[i]);
            // Binds all strings into an array
            arrList.add(BuddyNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    //cancel button
    public void onCancel(View view) {
        finish();
    }
}

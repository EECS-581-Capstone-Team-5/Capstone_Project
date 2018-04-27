package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BuddyScreen extends AppCompatActivity
{
    ArrayList<Integer> buddy_ids = new ArrayList<Integer>();
    ArrayList<Integer> pending_ids = new ArrayList<Integer>();
    ArrayList<String> buddyNames = new ArrayList<String>();
    ArrayList<String> pendingNames = new ArrayList<String>();
    ArrayAdapter<String> buddyAdapter;
    ArrayAdapter<String> pendingAdapter;
    ListView buddy_list;
    ListView pending_list;
    Button acceptButton;
    Button deleteButton;
    Button homeButton;
    boolean buddySelected = false;
    int selectedId = 0;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_screen);

        buddy_list = (ListView) findViewById(R.id.buddy_list);
        pending_list = (ListView) findViewById(R.id.pending_list);
        acceptButton = (Button) findViewById(R.id.right_button);
        deleteButton = (Button) findViewById(R.id.left_button);
        homeButton = (Button) findViewById(R.id.home_button);

        acceptButton.setText("Accept");
        acceptButton.setEnabled(false);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptClicked();
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteClicked();
            }
        });

        homeButton.setText("Return to Homepage");

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        buddyAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                buddyNames);

        pendingAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                pendingNames);

        buddy_list.setAdapter(buddyAdapter);
        pending_list.setAdapter(pendingAdapter);

        buddy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position,
                                    long id)
            {
                buddySelected = true;

                if(acceptButton.isEnabled()) acceptButton.setEnabled(false);
                if(!deleteButton.isEnabled()) deleteButton.setEnabled(true);

                for (int i = 0; i < buddy_list.getChildCount(); i++) {
                    if(position == i )
                    {
                        buddy_list.getChildAt(i).setBackgroundColor(Color.rgb(0, 204, 153));
                        selectedId = buddy_ids.get(i);
                        selectedIndex = i;
                    }
                    else
                    {
                        buddy_list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                for(int i = 0; i < pending_list.getChildCount(); i++)
                {
                    pending_list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        pending_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position,
                                    long id)
            {
                buddySelected = false;

                if(!acceptButton.isEnabled()) acceptButton.setEnabled(true);
                if(!deleteButton.isEnabled()) deleteButton.setEnabled(true);

                for (int i = 0; i < pending_list.getChildCount(); i++) {
                    if(position == i )
                    {
                        pending_list.getChildAt(i).setBackgroundColor(Color.rgb(0, 204, 153));
                        selectedId = pending_ids.get(i);
                        selectedIndex = i;
                    }
                    else
                    {
                        pending_list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                for(int i = 0; i < buddy_list.getChildCount(); i++)
                {
                    buddy_list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        populateBuddies();
    }

    private void populateBuddies()
    {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    buddyNames.clear();
                    pendingNames.clear();
                    buddy_ids.clear();
                    pending_ids.clear();

                    JSONObject j_Object = new JSONObject(response);

                    boolean success = false;

                    if(j_Object.has("success"))
                        success = j_Object.getBoolean("success");

                    if(success)
                    {
                        int rows = j_Object.getInt("num_rows");

                        for(int i = 0; i < rows; i++)
                        {
                            String buddyName = j_Object.getString("first_name" + i) +
                                    " " + j_Object.getString("last_name" + i);

                            int buddyId = j_Object.getInt("buddy_id" + i);

                            if(j_Object.getInt("confirmed" + i) != 0)
                            {
                                buddy_ids.add(buddyId);
                                buddyNames.add(buddyName);
                            }
                            else
                            {
                                pending_ids.add(buddyId);
                                pendingNames.add(buddyName);
                            }
                        }
                    }
                    else
                    {
                        AlertDialog.Builder b = new AlertDialog.Builder(BuddyScreen.this);
                        b.setMessage("No buddies to display.").setNegativeButton("Ok", null).create().show();
                    }
                    buddyAdapter.notifyDataSetChanged();
                    pendingAdapter.notifyDataSetChanged();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    AlertDialog.Builder b = new AlertDialog.Builder(BuddyScreen.this);
                    b.setMessage(e.getMessage()).setNegativeButton("Ok", null).create().show();
                }
            }
        };

        UserBuddyRequest l_Rquest = new UserBuddyRequest(r_Listener);

        RequestQueue r_queue = Volley.newRequestQueue(BuddyScreen.this);

        r_queue.add(l_Rquest);
    }

    void AcceptClicked()
    {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                populateBuddies();
            }
        };

        AcceptBuddyRequest d_request = new AcceptBuddyRequest(selectedId, r_Listener);

        RequestQueue r_queue = Volley.newRequestQueue(BuddyScreen.this);

        r_queue.add(d_request);
    }

    void DeleteClicked()
    {
        AlertDialog.Builder b = new AlertDialog.Builder(BuddyScreen.this);
        String buddyName = (buddySelected) ? buddyNames.get(selectedIndex) : pendingNames.get(selectedIndex);
        String prompt = (buddySelected) ? " from your buddy list?" : "'s buddy request?";
        b.setTitle("Delete " + buddyName + prompt);
        b.setItems(new CharSequence[]{"Yes", "No"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                DeleteBuddy();
                                break;

                            case 1:
                                dialog.cancel();
                                break;
                        }
                    }
                });
        b.create().show();
    }

    void DeleteBuddy()
    {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                populateBuddies();
            }
        };

        DeleteBuddyRequest d_request = new DeleteBuddyRequest(selectedId, r_Listener);

        RequestQueue r_queue = Volley.newRequestQueue(BuddyScreen.this);

        r_queue.add(d_request);
    }
}

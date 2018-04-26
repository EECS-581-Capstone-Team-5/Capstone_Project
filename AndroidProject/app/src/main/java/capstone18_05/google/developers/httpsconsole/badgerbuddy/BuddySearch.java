package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.app.LauncherActivity;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class User
{
    public String firstName;
    public String lastName;
    public String uName;
    public int userId;

    public User(String first_name, String last_name, String username, int id)
    {
        firstName = first_name;
        lastName = last_name;
        uName = username;
        userId = id;
    }
}

public class BuddySearch extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //variables declared
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    final ArrayList<User> all_users = new ArrayList<User>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<BuddyNames> arrList = new ArrayList<BuddyNames>();
    boolean buddySelected;
    Button addBuddyButton;
    int selectedId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddysearch);
        Intent intent = getIntent();

        buddySelected = false;

        list = (ListView) findViewById(R.id.listView);
        addBuddyButton = (Button)findViewById(R.id.add_buddy);

        addBuddyButton.setEnabled(false);
        addBuddyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBuddy();
            }
        });

        GetAllNames();

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position,
                                    long id)
            {
                buddySelected = true;

                if(!addBuddyButton.isEnabled()) addBuddyButton.setEnabled(true);

                for (int i = 0; i < list.getChildCount(); i++)
                {
                    if (position == i) {
                        list.getChildAt(i).setBackgroundColor(Color.rgb(0, 204, 153));

                        String displayString = ((BuddyNames)list.getItemAtPosition(i)).getBuddyName();

                        for(int j = 0; j < all_users.size(); j++)
                        {
                            User current = all_users.get(j);
                            String display = current.firstName + " " + current.lastName + " (" +
                                    current.uName + ")";

                            if(display.equals(displayString))
                            {
                                selectedId = current.userId;
                                break;
                            }
                        }
                    }
                    else
                    {
                        list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
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

    public void GetAllNames()
    {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject j_Object = new JSONObject(response);

                    boolean success = false;

                    if(j_Object.has("success"))
                        success = j_Object.getBoolean("success");

                    if(success)
                    {
                        int rows = j_Object.getInt("num_rows");

                        for(int i = 0; i < rows; i++)
                        {
                            User current = new User(j_Object.getString("first_name" + i),
                                    j_Object.getString("last_name" + i), j_Object.getString("username" + i),
                                    j_Object.getInt("user_id" + i));

                            all_users.add(current);

                            String displayString = current.firstName + " " + current.lastName + " (" +
                                    current.uName + ")";
                            BuddyNames BuddyNames = new BuddyNames(displayString);

                            arrList.add(BuddyNames);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    AlertDialog.Builder b = new AlertDialog.Builder(BuddySearch.this);
                    b.setMessage(e.getMessage()).setNegativeButton("Ok", null).create().show();
                }
            }
        };

        AllUsersRequest u_Rquest = new AllUsersRequest(r_Listener);

        RequestQueue r_queue = Volley.newRequestQueue(BuddySearch.this);

        r_queue.add(u_Rquest);
    }

    public void AddBuddy()
    {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    if(response.equals("true"))
                    {
                        AlertDialog.Builder b = new AlertDialog.Builder(BuddySearch.this);
                        b.setMessage("Buddy request sent :)").setNegativeButton("Ok", null).create().show();
                    }
                    else
                    {
                        AlertDialog.Builder b = new AlertDialog.Builder(BuddySearch.this);
                        b.setMessage("Buddy request failed.").setNegativeButton("Ok", null).create().show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    AlertDialog.Builder b = new AlertDialog.Builder(BuddySearch.this);
                    b.setMessage(e.getMessage()).setNegativeButton("Ok", null).create().show();
                }
            }
        };

        AddBuddyRequest b_Rquest = new AddBuddyRequest(Current_User.user_id, selectedId, Current_User.first_name,
                Current_User.last_name, Current_User.username, r_Listener);

        RequestQueue r_queue = Volley.newRequestQueue(BuddySearch.this);

        r_queue.add(b_Rquest);
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
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
    ArrayList<String> first_names = new ArrayList<String>();
    ArrayList<String> last_names = new ArrayList<String>();
    ArrayList<String> usernames = new ArrayList<String>();
    LinearLayout buddies_view;
    LinearLayout pending_buddies_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_screen);

        buddies_view = (LinearLayout) findViewById(R.id.buddies_layout);
        pending_buddies_view = (LinearLayout) findViewById(R.id.pending_buddies_layout);

        populateBuddies();
    }

    private void populateBuddies()
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
                            String buddyName = j_Object.getString("first_name" + i) +
                                    " " + j_Object.getString("last_name" + i);
                            TextView nameView = new TextView(BuddyScreen.this);
                            nameView.setText(buddyName);

                            if(j_Object.getInt("confirmed" + i) != 0)
                            {
                                buddies_view.addView(nameView);
                            }
                            else
                            {
                                pending_buddies_view.addView(nameView);
                            }
                        }
                    }
                    else
                    {
                        AlertDialog.Builder b = new AlertDialog.Builder(BuddyScreen.this);
                        b.setMessage("Failed to retrieve buddy list.").setNegativeButton("Ok", null).create().show();
                    }
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
}

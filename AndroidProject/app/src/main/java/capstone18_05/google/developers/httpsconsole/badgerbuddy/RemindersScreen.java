package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemindersScreen extends AppCompatActivity {

    private Button create_rem, back_to_home, forward_button, back_button;
    private JSONObject returnObject = null;
    private JSONObject remObject = null;
    boolean getRemResult = false;
    private RequestQueue r_queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_screen);

        create_rem = findViewById(R.id.allRem_add_reminders);
        back_to_home = findViewById(R.id.allRem_back_to_home);
        forward_button = findViewById(R.id.allRem_ForwardButton);
        back_button = findViewById(R.id.allRem_BackButton);

        getCurrentUsersReminders();

        if(remObject != null)
        {
            /*

                STACK OVERFLOW JSON JAVA PARSING

                                {
                   "pageInfo": {
                         "pageName": "abc",
                         "pagePic": "http://example.com/content.jpg"
                    }
                    "posts": [
                         {
                              "post_id": "123456789012_123456789012",
                              "actor_id": "1234567890",
                              "picOfPersonWhoPosted": "http://example.com/photo.jpg",
                              "nameOfPersonWhoPosted": "Jane Doe",
                              "message": "Sounds cool. Can't wait to see it!",
                              "likesCount": "2",
                              "comments": [],
                              "timeOfPost": "1234567890"
                         }
                    ]
                }



                JSONObject obj = new JSONObject(" .... ");
                String pageName = obj.getJSONObject("pageInfo").getString("pageName");

                JSONArray arr = obj.getJSONArray("posts");
                for (int i = 0; i < arr.length(); i++)
                {
                    String post_id = arr.getJSONObject(i).getString("post_id");
                    ......
                }
                */
        }

        create_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateReminders.class));
            }
        });

        back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void getCurrentUsersReminders() {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    returnObject = new JSONObject(response);

                    if(returnObject.has("success")) {
                        if (returnObject.getBoolean("success")) {
                            getRemResult = true;
                            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                            b.setMessage("Query Success").setNeutralButton("Confirm", null).create().show();

                            remObject = returnObject.getJSONObject("rem_object");
                        }
                        else {
                            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                            b.setMessage("Query Failed").setNeutralButton("Confirm", null).create().show();
                        }
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                    b.setMessage(e.getMessage()).setNeutralButton("Confirm", null).create().show();
                }

            }
        };

        String self_or_buddy = "SELF";
        GetReminderRequest get_request = new GetReminderRequest(self_or_buddy, r_Listener);
        r_queue = Volley.newRequestQueue(getApplicationContext());
        r_queue.add(get_request);
    }

    public void onAdd(View view)
    {
        startActivity(new Intent(getApplicationContext(), CreateReminders.class));
    }

    public void onHome(View view)
    {
        finish();
    }
}

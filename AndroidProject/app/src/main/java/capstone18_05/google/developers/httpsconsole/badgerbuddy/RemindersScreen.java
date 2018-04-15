package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class RemindersScreen extends AppCompatActivity {
    public static JSONArray remArray = null;

    private Button create_rem, back_to_home, forward_button, back_button;
    boolean getRemResult = false;
    private RequestQueue r_queue;

    private int number_of_reminders_on_screen = 1;
    private int current_start_index = 0;
    private int current_end_index = number_of_reminders_on_screen;
    private int rem_result_size = 0;
    private LinearLayout[] myrem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_screen);

        myrem = new LinearLayout[] {findViewById(R.id.myrem_01)};
        for(int i = 0; i < myrem.length; i++)
        {
            myrem[i].setVisibility(View.INVISIBLE);
        }

        create_rem = findViewById(R.id.allRem_add_reminders);
        back_to_home = findViewById(R.id.allRem_back_to_home);

        forward_button = findViewById(R.id.allRem_ForwardButton);
        forward_button.setClickable(false);
        back_button = findViewById(R.id.allRem_BackButton);
        back_button.setClickable(false);

        getCurrentUsersReminders();
        if(remArray != null) {
            clickabilityUpdate();
            updateRemindersDisplay();
        }
        else {
            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
            b.setMessage("You have no reminders.\n" + remArray).setNeutralButton("OK",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RemindersScreen.this.finish();
                }
            }).create().show();
        }



        create_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                finish();
                startActivity(new Intent(getApplicationContext(), CreateReminders.class));
                startActivity(i);
            }
        });

        back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(back_button.isClickable()) {
                    current_start_index -= number_of_reminders_on_screen;
                    current_end_index -= number_of_reminders_on_screen;
                    clickabilityUpdate();
                }
            }
        });

        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(forward_button.isClickable()) {
                    current_start_index += number_of_reminders_on_screen;
                    current_end_index += number_of_reminders_on_screen;
                    clickabilityUpdate();
                }
            }
        });

    }



    public void clickabilityUpdate() {
        if(current_end_index <= 0) {
            back_button.setClickable(false);
        }
        if(current_end_index >= remArray.length()) {
            forward_button.setClickable(false);
        }
    }

    public void getCurrentUsersReminders() {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject returnObject = new JSONObject(response);

                    if(returnObject.has("success")) {
                        if (returnObject.getBoolean("success")) {
                            getRemResult = true;
                            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                            //b.setMessage("Query Success: (Size = " + returnObject.getInt("result_size") + ")" ).setNeutralButton("Confirm", null).create().show();

                            RemindersScreen.remArray = returnObject.getJSONArray("rem_object");
                            rem_result_size = returnObject.getInt("result_size");
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



    public void updateRemindersDisplay() {

        try {

            int j = current_start_index;
            for(int i = 0; i < myrem.length; i++) {
                JSONObject current_reminder = remArray.getJSONObject(j++);

                // indices: id, user, title, description, time, location
                int curRem_id = current_reminder.getInt("id");
                String curRem_user = current_reminder.getString("user"); // should equal Current_User.username
                String curRem_title = current_reminder.getString("title");
                final String curRem_desc = current_reminder.getString("description");
                String curRem_time = current_reminder.getString("time");
                String curRem_loc = current_reminder.getString("location");

                TextView title_slot_i = (TextView) myrem[i].getChildAt(0);
                TextView time_slot_i = (TextView) myrem[i].getChildAt(1);
                title_slot_i.setText(curRem_title);
                time_slot_i.setText(time_slot_i.getText() + "\t" + curRem_time);

                // the second child of the current reminder layout is a layout for the buttons
                LinearLayout button_layout_i = (LinearLayout) myrem[i].getChildAt(2);
                Button expand_i = (Button) button_layout_i.getChildAt(0);
                Button delete_i = (Button) button_layout_i.getChildAt(1);

                expand_i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                        b.setMessage(curRem_desc);
                        b.setPositiveButton("See Location",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // map click;
                            }
                        });
                        b.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user closed the description view box
                            }
                        });
                        b.create().show();
                    }
                });

                delete_i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // delete this reminder
                        // post the curRem_id to the delete reminders PHP file
                    }
                });

                myrem[i].setClickable(true);
                myrem[i].setVisibility(View.VISIBLE);
            }

        }
        catch (JSONException e)
        {
            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
            b.setMessage("A problem occurred.\n" + e.getMessage()).setNeutralButton("Confirm", null).create().show();
        }

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

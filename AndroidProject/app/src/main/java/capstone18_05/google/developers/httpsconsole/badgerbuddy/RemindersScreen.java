package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemindersScreen extends AppCompatActivity {
    public JSONArray remArray = new JSONArray();

    // used to detect whether new reminders were created after the instantiation of this screen
    public static int NEW_REMINDER_SAVED;

    private Button create_rem, back_to_home, forward_button, back_button;
    protected RequestQueue r_queue;

    private int number_of_reminders_on_screen = 3;
    private int current_start_index = 0;
    private int current_end_index = number_of_reminders_on_screen-1;
    private int rem_result_size = 0;
    private LinearLayout[] myrem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_screen);

        myrem = new LinearLayout[] {findViewById(R.id.myrem_01), findViewById(R.id.myrem_02), findViewById(R.id.myrem_03)};
        for (LinearLayout rem_i : myrem) {
            rem_i.setVisibility(View.INVISIBLE);
        }

        create_rem = findViewById(R.id.allRem_add_reminders);
        back_to_home = findViewById(R.id.allRem_back_to_home);

        forward_button = findViewById(R.id.allRem_ForwardButton);
        forward_button.setBackgroundColor(Color.DKGRAY);
        back_button = findViewById(R.id.allRem_BackButton);
        back_button.setBackgroundColor(Color.DKGRAY);


        create_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateReminders.class));
            }
        });

        back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindersScreen.this.finish();
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(back_button.isClickable()) {
                    if((current_start_index - number_of_reminders_on_screen) <= 0)
                    {
                        current_start_index = 0;
                        current_end_index = number_of_reminders_on_screen;
                    }
                    else {
                        current_start_index -= number_of_reminders_on_screen;
                        current_end_index -= number_of_reminders_on_screen;
                    }
                    clickabilityUpdate();
                    updateRemindersDisplay();

                }
            }
        });

        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(forward_button.isClickable()) {
                    if((current_end_index + number_of_reminders_on_screen) >= remArray.length())
                    {
                        current_end_index = remArray.length() - 1;
                        current_start_index = current_end_index - number_of_reminders_on_screen + 1;
                    }
                    else {
                        current_start_index += number_of_reminders_on_screen;
                        current_end_index += number_of_reminders_on_screen;
                    }
                    clickabilityUpdate();
                    updateRemindersDisplay();
                }
            }
        });

        forward_button.setClickable(false);
        back_button.setClickable(false);
        getCurrentUserReminders();


    }


    @Override
    protected void onResume() {
        super.onResume();

        getCurrentUserReminders();
    }



    public void clickabilityUpdate() {
        if(current_start_index <= 0) {
            back_button.setClickable(false);
            back_button.setBackgroundColor(Color.DKGRAY);
        }
        else {
            back_button.setClickable(true);
            back_button.setBackgroundColor(Color.LTGRAY);
        }

        if((current_start_index + number_of_reminders_on_screen) >= remArray.length()) {
            forward_button.setClickable(false);
            forward_button.setBackgroundColor(Color.DKGRAY);
        }
        else{
            forward_button.setClickable(true);
            forward_button.setBackgroundColor(Color.LTGRAY);
        }
    }



    public void getCurrentUserReminders() {
        Response.Listener<String> r_Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject returnObject = new JSONObject(response);

                    AlertDialog.Builder b;
                    if(returnObject.has("success")) {

                        switch(returnObject.getInt("success")) {

                            case 0:
                                b = new AlertDialog.Builder(RemindersScreen.this);
                                b.setMessage("DATABASE ERROR").setNeutralButton("OK",  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        RemindersScreen.this.finish();
                                    }
                                }).create().show();
                                break;

                            case 1:
                                RemindersScreen.this.remArray = returnObject.getJSONArray("rem_array");
                                rem_result_size = returnObject.getInt("result_size");
                                clickabilityUpdate();
                                updateRemindersDisplay();
                                break;

                            case 2:
                                b = new AlertDialog.Builder(RemindersScreen.this);
                                b.setMessage("You have no reminders.").setNeutralButton("OK",  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { /* do nothing else */ }
                                });
                                //b.create().show();
                                break;

                            case 3:
                                b = new AlertDialog.Builder(RemindersScreen.this);
                                b.setMessage("There was a problem saving the result.").setNeutralButton("OK",  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        RemindersScreen.this.finish();
                                    }
                                }).create().show();
                                break;
                        }

                    }
                    else {
                        b = new AlertDialog.Builder(RemindersScreen.this);
                        b.setMessage("PHP ERROR").setNeutralButton("OK",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RemindersScreen.this.finish();
                            }
                        }).create().show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                    b.setMessage(e.getMessage()).setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RemindersScreen.this.finish();
                        }
                    }).create().show();
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
            for (LinearLayout myrem_i : myrem) {
                JSONObject current_reminder = remArray.getJSONObject(j++);

                // indices: id, user, title, description, time, location
                final int curRem_id = current_reminder.getInt("id");
                //String curRem_user = current_reminder.getString("user"); // should equal Current_User.username
                String curRem_title = current_reminder.getString("title");
                final String curRem_desc = current_reminder.getString("description");
                String curRem_time = "Time     " + current_reminder.getString("time");
                String curRem_loc = current_reminder.getString("location");

                TextView title_slot_i = (TextView) myrem_i.getChildAt(0);
                TextView time_slot_i = (TextView) myrem_i.getChildAt(1);
                title_slot_i.setText(curRem_title);
                time_slot_i.setText(curRem_time);

                // the second child of the current reminder layout is a layout for the buttons
                LinearLayout button_layout_i = (LinearLayout) myrem_i.getChildAt(2);
                Button expand_i = (Button) button_layout_i.getChildAt(0);
                Button delete_i = (Button) button_layout_i.getChildAt(1);

                expand_i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                        b.setMessage(curRem_desc);
                        b.setPositiveButton("See Location", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // find a way to pass curRem_loc to the map activity and focus on that location
                                // perhaps make a new Java class for focusing on a specific location,
                                    // and allow the string representation of that location to be passed
                                    // in via the constructor
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
                        Response.Listener<String> r_Listener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    if(response.equals("success")) {
                                        AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                                        b.setMessage("Reminder Deleted.").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                RemindersScreen.this.finish();
                                            }
                                        }).create().show();
                                    }
                                    else if(response.equals("failure")) {
                                        AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                                        b.setMessage("An Error Occurred.").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                RemindersScreen.this.finish();
                                            }
                                        }).create().show();
                                    }
                                }

                                catch (Exception e) {
                                    AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
                                    b.setMessage(e.getMessage()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            RemindersScreen.this.finish();
                                        }
                                    }).create().show();
                                }

                            }
                        };


                        String self_or_buddy = "SELF";
                        DeleteReminderRequest del_request = new DeleteReminderRequest(self_or_buddy, Integer.toString(curRem_id), r_Listener);
                        r_queue = Volley.newRequestQueue(getApplicationContext());
                        r_queue.add(del_request);
                    }
                });

                myrem_i.setClickable(true);
                myrem_i.setVisibility(View.VISIBLE);

                if (j >= remArray.length()) {
                    break;
                }
            }

        }
        catch (JSONException e)
        {
            AlertDialog.Builder b = new AlertDialog.Builder(RemindersScreen.this);
            b.setMessage("A problem occurred.\n" + e.getMessage()).setNeutralButton("Confirm", null).create().show();
        }

    }


}

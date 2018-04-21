package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CreateReminders extends AppCompatActivity {


    private EditText mRemName, mRemDesc;
    private RequestQueue r_queue;
    private Button save_button, cancel_button, time_button;
    //private StringRequest stringRequest;

    // the database columns for self reminders and buddy reminders
    String by_user, for_user, title, description, time, location, approved = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrem);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mRemName = findViewById(R.id.newRemNameField);
        mRemDesc = findViewById(R.id.newRemDescField);
        save_button = findViewById(R.id.saveButton);
        cancel_button = findViewById(R.id.cancelButton);
        time_button = findViewById(R.id.newRemTimeButton);

        // TODO: add buddy reminder functionality
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String self_or_buddy = "SELF";
                by_user = Current_User.username;
                for_user = ""; // irrelevant for self reminders
                title = mRemName.getText().toString();
                description = mRemDesc.getText().toString();
                time = "None Specified";
                location = "None Specified";
                approved = "Yes"; // irrelevant for self reminders

                Response.Listener<String> r_listener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            /*These alert boxes, if uncommented, will crash the app.*/
                            if(response.equals("true")) {
                                String successMessage = "Successfully Saved Reminder";
                                AlertDialog.Builder b = new AlertDialog.Builder(CreateReminders.this);
                                b.setMessage(successMessage).setNeutralButton("", null);
                                CreateReminders.this.finish();
                            }
                            else {
                                String errorMessage = "The Reminder Could Not Be Saved.";
                                AlertDialog.Builder b = new AlertDialog.Builder(CreateReminders.this);
                                b.setMessage(errorMessage).setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (Exception e) {
                            //e.printStackTrace();
                            AlertDialog.Builder b = new AlertDialog.Builder(CreateReminders.this);
                            b.setMessage(e.getMessage()).setNegativeButton("Error", null).create().show();
                        }
                    }
                };

                SaveReminderRequest save_request = new SaveReminderRequest(self_or_buddy, by_user, for_user,
                        title, description, time, location, approved, r_listener);
                r_queue = Volley.newRequestQueue(getApplicationContext());
                r_queue.add(save_request);

            }
        });


        cancel_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder cancel_dialog = new AlertDialog.Builder(CreateReminders.this);
                cancel_dialog.setMessage(R.string.cancel_prompt);
                cancel_dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         RemindersScreen.NEW_REMINDER_SAVED = 1;
                         CreateReminders.this.finish();
                     }
                });
                cancel_dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         // the user cancelled the dialog
                         RemindersScreen.NEW_REMINDER_SAVED = 0;
                     }
                });
                cancel_dialog.create().show();
            }
        });

        time_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }

        });

    }

}

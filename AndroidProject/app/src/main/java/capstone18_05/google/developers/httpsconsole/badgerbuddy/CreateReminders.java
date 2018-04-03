package capstone18_05.google.developers.httpsconsole.badgerbuddy;

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
        Button save_button = findViewById(R.id.saveButton);
        //Button cancel_button = findViewById(R.id.cancel_button);

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
                                finish();
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


        //cancel_button.setOnClickListener();

    }


    public void onCancel(View view) {
        finish();
    }
}

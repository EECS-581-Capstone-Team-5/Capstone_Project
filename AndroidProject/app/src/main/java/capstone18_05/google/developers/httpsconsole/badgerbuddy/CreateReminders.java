package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CreateReminders extends AppCompatActivity {


    private EditText mRemName, mRemDesc;
    private RequestQueue r_queue;
    private StringRequest stringRequest;
    private Button save_button, cancel_button;

    // the database columns for self reminders and buddy reminders
    String by_user, for_user, user, title, description, time, location, approved = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrem);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mRemName = (EditText) findViewById(R.id.newRemNameField);
        mRemDesc = (EditText) findViewById(R.id.newRemDescField);
        save_button = (Button) findViewById(R.id.saveButton);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        r_queue = Volley.newRequestQueue(this);


        // TODO: add buddy reminder functionality
        /*
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String self_or_buddy = "SELF";
                by_user = getIntent().getStringExtra("username");
                for_user = "";
                title = mRemName.getText().toString();
                description = mRemDesc.getText().toString();
                time = "None Specified";
                location = "None Specified";
                approved = "Yes";

                Response.Listener<String> r_listener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            if(response.equals("true"))
                            {
                                String successMessage = "Successfully Saved Reminder";
                                AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
                                b.setMessage(successMessage).setNegativeButton("Confirm", null).create().show();
                                finish();
                            }
                            else
                            {
                                String errorMessage = "The Reminder Could Not Be Saved.";
                                AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
                                b.setMessage(errorMessage).setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (Exception e)
                        {
                            //e.printStackTrace();
                            AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
                            b.setMessage(e.getMessage()).setNegativeButton("Error", null).create().show();
                        }
                    }
                };

                SaveReminderRequest save_request = new SaveReminderRequest(self_or_buddy, by_user, for_user,
                        title, description, time, location, approved, r_listener);
                //     public SaveReminderRequest(String self_or_buddy, String by_user, String for_user, String title,
                //                               String description, String time, String location, String approved,
                //                               Response.Listener<String> listener)


                r_queue.add(save_request);



            }
        });

        */

        //cancel_button.setOnClickListener();

    }


    public void onSave(View view) {
        finish();
    }

    public void onCancel(View view) {
        finish();
    }
}

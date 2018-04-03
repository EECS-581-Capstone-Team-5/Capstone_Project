package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateAccount extends AppCompatActivity {

    private View registerProgressView;
    private View registrationForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acccreate);

        final EditText first_name_text = (EditText) findViewById(R.id.first_name_input);
        final EditText last_name_text = (EditText) findViewById(R.id.last_name_input);
        final EditText username_text = (EditText) findViewById(R.id.username_input);
        final EditText password_text = (EditText) findViewById(R.id.password_input);
        final Button register_button = (Button) findViewById(R.id.register_button);
        final Button cancel_button = (Button) findViewById(R.id.cancel_button);

        registrationForm = findViewById(R.id.register_form);
        registerProgressView = findViewById(R.id.register_progress);

        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                final String firstName = first_name_text.getText().toString();
                final String lastName = last_name_text.getText().toString();
                final String username = username_text.getText().toString();
                final String password = password_text.getText().toString();

                showProgress(true);

                Response.Listener<String> r_Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            //JSONObject j_Object = new JSONObject(response);
                            //boolean success = j_Object.getBoolean("success");

                            if(response.equals("true"))
                            {
                                Intent intent = new Intent(CreateAccount.this, AccountLogin.class);
                                getApplicationContext().startActivity(intent);
                                showProgress(false);
                                finish();
                            }
                            else
                            {
                                String errorMessage = "Username already in use.";
                                AlertDialog.Builder b = new AlertDialog.Builder(CreateAccount.this);
                                b.setMessage(errorMessage).setNegativeButton("Retry", null).create().show();
                                showProgress(false);
                            }
                        }
                        catch (Exception e)
                        {
                            //e.printStackTrace();
                            AlertDialog.Builder b = new AlertDialog.Builder(CreateAccount.this);
                            b.setMessage(e.getMessage()).setNegativeButton("Retry", null).create().show();
                            showProgress(false);
                        }
                    }
                };

                RegisterRequest r_Rquest = new RegisterRequest(firstName, lastName, username, password,
                        r_Listener);

                RequestQueue r_queue = Volley.newRequestQueue(CreateAccount.this);

                r_queue.add(r_Rquest);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CreateAccount.this, StartPage.class);
                CreateAccount.this.startActivity(intent);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            registrationForm.setVisibility(show ? View.GONE : View.VISIBLE);
            registrationForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    registrationForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            registerProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            registerProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    registerProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            registerProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            registrationForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

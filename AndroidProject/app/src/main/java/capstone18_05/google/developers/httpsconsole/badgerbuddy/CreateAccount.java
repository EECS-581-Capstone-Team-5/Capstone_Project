package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
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

        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                final String firstName = first_name_text.getText().toString();
                final String lastName = last_name_text.getText().toString();
                final String username = username_text.getText().toString();
                final String password = password_text.getText().toString();

                Response.Listener<String> r_Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject j_Object = new JSONObject(response);
                            boolean success = j_Object.getBoolean("success");

                            if(success)
                            {
                                Intent intent = new Intent(CreateAccount.this, AccountLogin.class);
                                CreateAccount.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder b = new AlertDialog.Builder(CreateAccount.this);
                                b.setMessage("Registration Failed").setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
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
}

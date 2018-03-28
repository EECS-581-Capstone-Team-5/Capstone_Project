package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class CreateReminders extends AppCompatActivity {


    private EditText mRemName, mRemDesc;
    private RequestQueue r_queue;
    private StringRequest stringRequest;
    //String database_url = "http://badgerbuddy.atwebpages.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrem);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mRemName = (EditText) findViewById(R.id.newRemNameField);
        mRemDesc = (EditText) findViewById(R.id.newRemDescField);

    }


    public void onSave(View view) {

        // String name = mRemName.getText().toString();
        // String description = mRemDesc.getText().toString();
        finish();
    }

    public void onCancel(View view) {

        finish();
    }
}

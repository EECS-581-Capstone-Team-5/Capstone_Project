package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class CreateReminders extends AppCompatActivity {


    private EditText newRemName, newRemDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrem);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        newRemName = (EditText) findViewById(R.id.newRemNameField);
        newRemDesc = (EditText) findViewById(R.id.newRemDescField);

    }


    public void onSave(View view) {

        // String name = newRemName.getText().toString();
        // String description = newRemDesc.getText().toString();
        finish();
    }

    public void onCancel(View view) {

        finish();
    }
}

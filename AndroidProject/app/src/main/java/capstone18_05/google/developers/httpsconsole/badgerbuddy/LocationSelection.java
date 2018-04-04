package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LocationSelection extends AppCompatActivity {

    Button confirm_button, cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);

        confirm_button = findViewById(R.id.confirm_button);
        cancel_button = findViewById(R.id.cancel_button);

    }

    public void onConfirm(View view) {
        finish();
    }

    public void onCancel(View view) {
        finish();
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemindersScreen extends AppCompatActivity {

        private Button create_rem, back_to_home;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_reminders_screen);
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

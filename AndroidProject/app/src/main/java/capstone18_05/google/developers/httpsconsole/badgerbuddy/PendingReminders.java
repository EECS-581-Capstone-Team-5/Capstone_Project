package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by colby on 2/27/2018.
 */

public class PendingReminders extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingrem);

        Button reminders_button = findViewById(R.id.reminder_button);
        Button back_to_home = findViewById(R.id.pending_back_to_home);

        reminders_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RemindersScreen.class);
                startActivity(intent);
            }
        });

        back_to_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

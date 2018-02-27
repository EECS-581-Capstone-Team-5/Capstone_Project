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
        setContentView(R.layout.activity_pending_reminders);

        Button reminders_button = findViewById(R.id.reminder_button);
        Button buddy_button = findViewById(R.id.buddy_button);

        reminders_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PendingReminders.this, RemindersScreen.class);
                PendingReminders.this.startActivity(intent);
            }
        });

        buddy_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PendingReminders.this, Buddies_Screen.class);
                PendingReminders.this.startActivity(intent);
            }
        });
    }
}

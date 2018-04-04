package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Buddies_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybuddies);
    }

    public void onReminder(View view)
    {
        startActivity(new Intent(getApplicationContext(), RemindersScreen.class));
    }

    public void onSearch(View view)
    {
        startActivity(new Intent(getApplicationContext(), BuddySearch.class));
    }

    public void onRemForYou(View view)
    {
        // open screen of reminders for the current user that were created by the selected buddy
    }

    public void onHome(View view)
    {
        finish();
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acchome);
    }

    public void onBuddies(View view) { startActivity(new Intent(this, BuddySearch.class)); }

    public void myBuddies(View view) { startActivity(new Intent(this, Buddies_Screen.class)); }

    public void onMakeNewRem(View view)
    {
        startActivity(new Intent(this, CreateReminders.class));
    }

    public void onReminders(View view) { startActivity(new Intent(this, RemindersScreen.class)); }
    
    public void onLogOut(View view) { finish(); }



}

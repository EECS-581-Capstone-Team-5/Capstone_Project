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

    public void onMakeNewRem(View view)
    {
        startActivity(new Intent(this, CreateReminders.class));
    }


}

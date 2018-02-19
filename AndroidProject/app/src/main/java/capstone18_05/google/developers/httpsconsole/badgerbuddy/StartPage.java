package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void onCreateClick(View view)
    {
        startActivity(new Intent(this, CreateAccount.class));
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, AccountLogin.class));
    }


}

package capstone18_05.google.developers.httpsconsole.badgerbuddy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class PendingReminders : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_reminders)

        val buddy_button = findViewById<View>(R.id.buddy_button) as Button
        val reminder_button = findViewById<View>(R.id.reminder_button) as Button

        buddy_button.setOnClickListener(){
            startActivity(Intent(this, Buddies_Screen::class.java))
        }

        reminder_button.setOnClickListener(){
            startActivity(Intent(this, RemindersScreen::class.java))
        }
    }
}

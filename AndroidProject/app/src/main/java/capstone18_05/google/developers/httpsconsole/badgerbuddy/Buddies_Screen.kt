package capstone18_05.google.developers.httpsconsole.badgerbuddy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Buddies_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mybuddies)
    }

    fun onReminder(view: View)
    {
        startActivity(Intent(applicationContext, RemindersScreen::class.java))
    }

    fun onSearch(view: View)
    {
        startActivity(Intent(applicationContext, BuddySearch::class.java))
    }

    fun onRemForYou(view: View)
    {
        // open screen of reminders for the current user that were created by the selected buddy
    }

    fun onHome(view: View)
    {
        this.finish()
    }
}

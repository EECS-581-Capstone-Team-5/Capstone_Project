package capstone18_05.google.developers.httpsconsole.badgerbuddy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Buddies_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buddies__screen)
    }

    fun onReminder(view: View)
    {
        startActivity(Intent(this, RemindersScreen::class.java))
    }

    fun onSearch(view: View)
    {
        startActivity(Intent(this, BuddySearch::class.java))
    }
}

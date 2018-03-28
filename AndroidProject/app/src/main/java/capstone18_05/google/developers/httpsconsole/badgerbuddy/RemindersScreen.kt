package capstone18_05.google.developers.httpsconsole.badgerbuddy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RemindersScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders_screen)
    }

    fun onAdd(view: View)
    {
        startActivity(Intent(applicationContext, CreateReminders::class.java))
    }

    fun onHome(view: View)
    {
        finish()
    }
}

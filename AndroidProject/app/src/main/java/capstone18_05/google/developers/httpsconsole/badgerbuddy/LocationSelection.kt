package capstone18_05.google.developers.httpsconsole.badgerbuddy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LocationSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_selection)

        val confirm_button = findViewById<View>(R.id.confirm_button) as Button
        val cancel_button = findViewById<View>(R.id.cancel_button) as Button

        confirm_button.setOnClickListener(){
            this.finish();
        }

        cancel_button.setOnClickListener(){
            this.finish();
        }
    }
}

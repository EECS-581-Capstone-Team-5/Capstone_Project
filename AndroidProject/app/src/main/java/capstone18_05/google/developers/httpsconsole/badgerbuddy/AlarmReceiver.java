package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by r556a812 on 4/21/18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, RemindersScreen.class));

        //NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification notification = intent.getParcelableExtra(NOTIFICATION);
        //int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        //notificationManager.notify(notificationId, notification);


    }

}
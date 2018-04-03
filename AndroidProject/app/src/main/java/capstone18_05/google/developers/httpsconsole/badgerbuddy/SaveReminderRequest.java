package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class SaveReminderRequest extends StringRequest {
    public static final String DATABASE_URL = "https://people.eecs.ku.edu/~j286m692/Project_Testcode/add_reminders.php";
    private Map<String, String> params;


    public SaveReminderRequest(String self_or_buddy, String by_user, String for_user, String title,
                               String description, String time, String location, String approved,
                               Response.Listener<String> listener)
    {
        super(Method.POST, DATABASE_URL, listener, null);

        params = new HashMap<>();
        params.put("self_or_buddy", self_or_buddy);
        params.put("by_user", by_user);
        params.put("for_user", for_user); // irrelevant for self-reminders
        params.put("title", title);
        params.put("description", description);
        params.put("time", time);
        params.put("location", location);
        params.put("approved", approved); // irrelevant for self-reminders
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

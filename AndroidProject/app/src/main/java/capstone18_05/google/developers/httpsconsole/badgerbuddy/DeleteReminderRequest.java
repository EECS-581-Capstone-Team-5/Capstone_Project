package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class DeleteReminderRequest extends StringRequest {
    private static final String DATABASE_URL = "https://people.eecs.ku.edu/~j286m692/Project_Testcode/delete_reminders.php";
    private Map<String, String> params;


    DeleteReminderRequest(String self_or_buddy, String rem_id, Response.Listener<String> listener)
    {
        super(Method.POST, DATABASE_URL, listener, null);

        params = new HashMap<>();
        params.put("self_or_buddy", self_or_buddy);
        params.put("rem_id", rem_id);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetReminderRequest extends StringRequest {

    public static final String DATABASE_URL = "https://people.eecs.ku.edu/~j286m692/Project_Testcode/get_reminders.php";
    private Map<String, String> params;

    public GetReminderRequest(String self_or_buddy, Response.Listener<String> listener) {
        super(Method.POST, DATABASE_URL, listener, null);

        params = new HashMap<>();
        params.put("self_or_buddy", self_or_buddy);
        params.put("by_user", Current_User.username);
        params.put("for_user", Current_User.username);
    }

    @Override
    public Map<String, String> getParams() { return params; }
}

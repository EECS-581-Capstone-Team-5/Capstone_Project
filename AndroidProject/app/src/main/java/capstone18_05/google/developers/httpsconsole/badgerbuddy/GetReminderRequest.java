package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetReminderRequest extends JsonRequest {

    public static final String DATABASE_URL = "https://people.eecs.ku.edu/~j286m692/Project_Testcode/get_reminders.php";
    private Map<String, String> params;

    public GetReminderRequest(String url, String requestBody, Response.Listener listener, Response.ErrorListener errorListener) {
        super(DATABASE_URL, requestBody, listener, errorListener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 4/18/2018.
 */

public class AcceptBuddyRequest extends StringRequest
{
    private static final String Accept_Buddy_URL = "http://badgerbuddy.atwebpages.com/AcceptBuddy.php";
    private Map<String, String> params;

    public AcceptBuddyRequest(int buddyId, Response.Listener<String> listener)
    {
        super(Request.Method.POST, Accept_Buddy_URL, listener, null);

        params = new HashMap<>();
        params.put("user_id", (Integer.toString(Current_User.user_id)));
        params.put("buddy_id", (Integer.toString(buddyId)));
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 4/21/2018.
 */

public class AddBuddyRequest extends StringRequest
{
    private static final String Accept_Buddy_URL = "http://badgerbuddy.atwebpages.com/AddBuddy.php";
    private Map<String, String> params;

    public AddBuddyRequest(int buddyId, int userId, String firstName, String lastName, String uName, Response.Listener<String> listener)
    {
        super(Request.Method.POST, Accept_Buddy_URL, listener, null);

        params = new HashMap<>();
        params.put("user_id", (Integer.toString(userId)));
        params.put("buddy_id", (Integer.toString(buddyId)));
        params.put("first_name", firstName);
        params.put("last_name", lastName);
        params.put("username", uName);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

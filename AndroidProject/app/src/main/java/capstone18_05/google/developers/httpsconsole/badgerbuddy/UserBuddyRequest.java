package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 3/26/2018.
 */

public class UserBuddyRequest extends StringRequest {
    private static final String User_Buddy_Request_URL = "http://badgerbuddy.atwebpages.com/UserBuddies.php";
    private Map<String, String> params;

    public UserBuddyRequest(Response.Listener<String> listener)
    {
        super(Method.POST, User_Buddy_Request_URL, listener, null);

        params = new HashMap<>();
        params.put("user_id", (Integer.toString(Current_User.user_id)));
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

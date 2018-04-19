package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 4/18/2018.
 */

public class UserSearchRequest extends StringRequest {
    private static final String User_Search_Request_URL = "http://badgerbuddy.atwebpages.com/SearchUsers.php";
    private Map<String, String> params;

    public UserSearchRequest(String uName, Response.Listener<String> listener)
    {
        super(Method.POST, User_Search_Request_URL, listener, null);

        params = new HashMap<>();
        params.put("search_text", uName);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

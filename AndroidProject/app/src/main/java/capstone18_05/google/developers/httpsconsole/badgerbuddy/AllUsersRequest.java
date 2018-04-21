package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 4/21/2018.
 */

public class AllUsersRequest extends StringRequest
{
    private static final String All_Users_Request_URL = "http://badgerbuddy.atwebpages.com/GetAllUsers.php";
    private Map<String, String> params;

    public AllUsersRequest(Response.Listener<String> listener)
    {
        super(Method.POST, All_Users_Request_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

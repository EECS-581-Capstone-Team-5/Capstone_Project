package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 2/27/2018.
 */

public class LoginRequest extends StringRequest {
    private static final String Login_Request_URL = "http://badgerbuddy.atwebpages.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String uName, String pWord, Response.Listener<String> listener) {
        super(Method.POST, Login_Request_URL, listener, null);

        params = new HashMap<>();
        params.put("username", uName);
        params.put("password", pWord);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

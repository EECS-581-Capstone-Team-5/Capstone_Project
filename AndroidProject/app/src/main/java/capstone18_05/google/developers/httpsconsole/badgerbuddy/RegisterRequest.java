package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colby on 2/26/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String Register_Request_URL = "http://badgerbuddy.atwebpages.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String fName, String lName, String uName, String pWord, Response.Listener<String> listener) {
        super(Method.POST, Register_Request_URL, listener, null);

        params = new HashMap<>();
        params.put("first_name", fName);
        params.put("last_name", lName);
        params.put("username", uName);
        params.put("password", pWord);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}

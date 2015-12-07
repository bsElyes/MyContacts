package interact.io.mycontacts.Utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import interact.io.mycontacts.Activities.Authentification;

/**
 * Created by ElyesL on 06/12/2015.
 */
public class RequestHeader extends  JsonObjectRequest{

        public RequestHeader(int method, String url, JSONObject jsonRequest, Listener listener, ErrorListener errorListener)
        {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map getHeaders() throws AuthFailureError {
            Map headers = new HashMap();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("authToken", Authentification.user.getAuthToken());
            return headers;
        }


}

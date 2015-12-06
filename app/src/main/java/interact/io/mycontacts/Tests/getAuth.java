package interact.io.mycontacts.Tests;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import interact.io.mycontacts.Entities.User;
import interact.io.mycontacts.R;
import interact.io.mycontacts.Utils.AppController;

public class getAuth extends AppCompatActivity {
    public static final String urlAuth="https://api.mycontacts.io/v2/login";
    public TextView jsonContainer;
    public final  Context CONTEXT = this;
    public static User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_auth);
        jsonContainer = (TextView) findViewById(R.id.jsonContainer);
        Button btnAuthJson  = (Button ) findViewById( R.id.btnGetAuth);

        btnAuthJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getAuthVolley(jsonContainer,CONTEXT,user); // TEST VOLLEY
                    jsonContainer.setText(user.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void getAuthVolley( final TextView textView, final Context context,final User user){
        JSONObject params = new JSONObject();
        try {
            params.put("username", "admin@test.com");
            params.put("password", "inte2ract");
            params.put("client", "Apiary");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
                urlAuth,params ,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject userObject = null;
                        JSONObject tokenObject=null;
                        try {
                            userObject = new JSONObject(response.getString("user"));
                            tokenObject = new JSONObject(response.getString("token"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            try {
                                String t = response.getString("message");
                                if(t.trim().equals("login.user.not_found")){
                                    Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Wrong Password !", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        try {
                            //private String id ;
                            user.setId(userObject.getString("id"));
                            //private String username;
                            user.setUsername(userObject.getString("username"));
                            //private String accountStatus;
                            user.setAccountStatus(userObject.getString("accountStatus"));
                            //private Date expirationDate;
                            user.setExpirationDate(userObject.getLong("expirationDate"));
                            //private String email;
                            user.setEmail(userObject.getString("email"));
                            //private String firstName;
                            user.setFirstName(userObject.getString("firstName"));
                            //private String lastName;
                            user.setLastName(userObject.getString("lastName"));
                            //private String phoneNumber;
                            user.setPhoneNumber(userObject.getString("phoneNumber"));
                            //private String timeZone;
                            user.setTimeZone(userObject.getString("timeZone"));
                            //private String organizationId;
                            user.setOrganizationId(userObject.getString("organizationId"));
                            //private String organizationName;
                            user.setOrganizationName(userObject.getString("organizationName"));
                            //private String signupClient;
                            user.setSignupClient(userObject.getString("signupClient"));
                            //private Date dateCreated;
                            user.setDateCreated(userObject.getLong("dateCreated"));
                            //private Boolean active;
                            user.setActive(userObject.getBoolean("active"));

                            //private String firebaseAuthToken;
                            user.setSignupClient(response.getString("firebaseAuthToken"));

                            //private String authToken;
                            user.setSignupClient(tokenObject.getString("authToken"));
                            //private Long authTokenexpires;
                            user.setAuthTokenexpires(tokenObject.getLong("expires"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                textView.setText(error.getMessage());
                Toast.makeText(context, "onErrorResponse", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}

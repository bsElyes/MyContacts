package interact.io.mycontacts.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import interact.io.mycontacts.Entities.User;
import interact.io.mycontacts.R;
import interact.io.mycontacts.Utils.AppController;

public class Authentification extends AppCompatActivity {
    public static final String REQUEST_TAG = "RequestAuth";
    public static String MyPREFERENCES = "MyContactPref";

    private EditText usernameEditText;
    private EditText passwordEditText;
    public static final String urlAuth="https://api.mycontacts.io/v2/login";
    public final  Context CONTEXT = this;
    public static User user = new User();
    Button loginBtn;
    Button signinBtn;
    ProgressDialog pDialog;
    SharedPreferences contactsPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        contactsPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = contactsPref.edit();

        usernameEditText = (EditText) findViewById(R.id.username_login);
        usernameEditText.setText(contactsPref.getString("LastLogin",""));
        passwordEditText = (EditText) findViewById(R.id.password_login);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.edittext_action_login ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    login();
                    return true;
                }
                return false;
            }
        });

        loginBtn = (Button) findViewById(R.id.action_button);
        signinBtn = (Button) findViewById(R.id.action_button_signin);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppController.getInstance().cancelPendingRequests(REQUEST_TAG);
    }

    private void login() {
        final String username = usernameEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        // Validate the log in data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(Authentification.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        getAuthVolley(CONTEXT,user,username,password);
    }

    private void signin(){
        //Intent i = new Intent(this,SignUpActivity.class);
        //startActivity(i);
    }


    public  void getAuthVolley(final Context context, final User user ,final String username,final String password){
        JSONObject params = new JSONObject();
        try {
            params.put("username",username);
            params.put("password", password);
            params.put("client", "Apiary");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest auth = new JsonObjectRequest(Request.Method.POST,
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
                                pDialog.dismiss();
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
                            if (userObject != null) {
                                user.setId(userObject.getString("id"));
                                user.setUsername(userObject.getString("username"));
                                user.setAccountStatus(userObject.getString("accountStatus"));
                                user.setExpirationDate(userObject.getLong("expirationDate"));
                                user.setEmail(userObject.getString("email"));
                                user.setFirstName(userObject.getString("firstName"));
                                user.setLastName(userObject.getString("lastName"));
                                user.setPhoneNumber(userObject.getString("phoneNumber"));
                                user.setTimeZone(userObject.getString("timeZone"));
                                user.setOrganizationId(userObject.getString("organizationId"));
                                user.setOrganizationName(userObject.getString("organizationName"));
                                user.setSignupClient(userObject.getString("signupClient"));
                                user.setDateCreated(userObject.getLong("dateCreated"));
                                user.setActive(userObject.getBoolean("active"));
                                user.setFirebaseAuthToken(response.getString("firebaseAuthToken"));
                            }
                            if (tokenObject != null) {
                                user.setAuthToken(tokenObject.getString("authToken"));
                                user.setAuthTokenexpires(tokenObject.getLong("expires"));
                            }
                            pDialog.dismiss();
                            if(user.getId()!=null){
                                Intent i = new Intent(CONTEXT,MainActivity.class);
                                editor.putString("LastLogin",user.getEmail());
                                editor.commit();
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            pDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(context, "onErrorResponse", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(auth);
    }


}

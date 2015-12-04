package interact.io.mycontacts.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import interact.io.mycontacts.R;
import interact.io.mycontacts.Utils.AppController;

public class Authentification extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    public static final String TAG="Authentification";
    public static final String urlAuth="https://api.mycontacts.io/v2/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);



        usernameEditText = (EditText) findViewById(R.id.username_login);
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

        Button loginBtn = (Button) findViewById(R.id.action_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });

        Button signinBtn = (Button) findViewById(R.id.action_button_signin);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signin();
            }
        });
    }

    private void login() {
        final String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

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

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(Authentification.this);
        dialog.setMessage(getString(R.string.progress_login));
        dialog.show();
        authMyContact(username,password);
    }

    private void signin(){
        //Intent i = new Intent(this,SignUpActivity.class);
        //startActivity(i);
    }


    void authMyContact(final String username , final String password){

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                urlAuth,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Toast.makeText(getApplicationContext(),"Login" + username+" "+response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //pDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("client", "Apiary");

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


}

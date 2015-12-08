package interact.io.mycontacts.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.HashMap;
import java.util.Map;

import interact.io.mycontacts.Entities.User;
import interact.io.mycontacts.Fragments.ContactsFragment;
import interact.io.mycontacts.Fragments.UserActivities;
import interact.io.mycontacts.R;
import interact.io.mycontacts.Utils.AppController;

public class MainActivity extends AppCompatActivity {
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("My Contacts").withIcon(R.drawable.ico_enterprise).withIdentifier(1);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("User Activities").withIcon(R.drawable.ico_useractivities).withIdentifier(2);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Call / SMS").withIcon(R.drawable.ico_telephony).withIdentifier(3);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("About").withIcon(R.drawable.ico_about).withIdentifier(4);
    PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.ico_about).withIdentifier(5);

    public Drawer shDrawer;
    ProgressDialog pDialog;

    //Fragment
    FragmentManager fg;
    FragmentTransaction ft;
    public final Context CONTEXT_MAIN= this;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit MyContacts");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to quit the application !");
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                supportFinishAfterTransition();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //fg = getFragmentManager();
        //ft = fg.beginTransaction().replace(R.id.container, new HelloFragment());
        //ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User u = Authentification.user;
        new DrawerBuilder().withActivity(this).build();


        final IProfile profile = new ProfileDrawerItem().withName(u.getFirstName()).withEmail(u.getEmail()).withTextColorRes(R.color.md_black_1000).withIcon(Uri.parse("")).withIdentifier(100);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.material_drawer_badge)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        shDrawer=new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (drawerItem.getIdentifier()){
                            case 1:{
                                fg = getFragmentManager();
                                ft = fg.beginTransaction().replace(R.id.container, new ContactsFragment());
                                ft.commit();
                                shDrawer.closeDrawer();
                            }break;
                            case 2:{
                                fg = getFragmentManager();
                                ft = fg.beginTransaction().replace(R.id.container, new UserActivities());
                                ft.commit();
                                shDrawer.closeDrawer();
                            }break;
                            case 3:{
                                Toast.makeText(getApplicationContext(), "In Progress...", Toast.LENGTH_LONG).show();
                                shDrawer.closeDrawer();
                            }break;
                            case 4:{
                                Toast.makeText(getApplicationContext(), "In Progress...", Toast.LENGTH_LONG).show();
                                shDrawer.closeDrawer();
                            }break;
                            case 5:{
                                Logout(CONTEXT_MAIN);
                                shDrawer.closeDrawer();
                            }break;

                        }
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .build();
        shDrawer.openDrawer();


    }

    private void Logout(Context context) {
        String url = "https://api.mycontacts.io/v2/logout";

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Intent i = new Intent(MainActivity.this,Authentification.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "onErrorResponse "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map params = new HashMap();
                params.put("authToken", Authentification.user.getAuthToken());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        AppController.getInstance().cancelPendingRequests(AppController.class);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


}

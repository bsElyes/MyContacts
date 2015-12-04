package interact.io.mycontacts.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import interact.io.mycontacts.Fragments.HelloFragment;
import interact.io.mycontacts.R;

public class MainActivity extends AppCompatActivity {
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("My Contacts").withIcon(R.drawable.ico_enterprise).withIdentifier(1);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("User Activities").withIcon(R.drawable.ico_useractivities).withIdentifier(2);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Call / SMS").withIcon(R.drawable.ico_telephony).withIdentifier(3);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("About").withIcon(R.drawable.ico_about).withIdentifier(4);

    public Drawer shDrawer;

    //Fragment
    FragmentManager fg;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg = getFragmentManager();
        ft = fg.beginTransaction().replace(R.id.container, new HelloFragment());
        ft.commit();
        new DrawerBuilder().withActivity(this).build();


        final IProfile profile = new ProfileDrawerItem().withName("Elyes").withEmail("Elyes.bensalah@esprit.tn").withTextColorRes(R.color.md_black_1000).withIcon(Uri.parse("")).withIdentifier(100);

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
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (drawerItem.getIdentifier()){
                            case 1:{
                                Toast.makeText(getApplicationContext(), "In Progress...", Toast.LENGTH_LONG).show();
                                shDrawer.closeDrawer();
                            }break;
                            case 2:{
                                Toast.makeText(getApplicationContext(), "In Progress...", Toast.LENGTH_LONG).show();
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

                        }
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .build();

    }


}

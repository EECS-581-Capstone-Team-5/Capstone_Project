package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class AccountHomepage extends AppCompatActivity {

    private DrawerLayout accHomeDrawer;
    private Toolbar accHomeToolbar;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acchome);

        accHomeDrawer = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                    {
                        menuItem.setCheckable(true); // highlight the item that has been pressed
                        accHomeDrawer.closeDrawers(); // close the nav drawer when the item is pressed

                        switch (menuItem.getItemId()) {
                            case R.id.my_buddies:
                                startActivity(new Intent(getApplicationContext(), BuddySearch.class));
                            case R.id.makeNewRem:
                                startActivity(new Intent(getApplicationContext(), CreateReminders.class));
                            case R.id.buddySearchButton:
                                startActivity(new Intent(getApplicationContext(), Buddies_Screen.class));
                            case R.id.map_button:
                                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                            case R.id.reminders:
                                startActivity(new Intent(getApplicationContext(), RemindersScreen.class));
                            case R.id.pendingRemindersButton:
                                startActivity(new Intent(getApplicationContext(), PendingReminders.class));
                            case R.id.logoutButton:
                                finish();
                        }

                        menuItem.setCheckable(false);
                        return true;
                    }
                }
        );


        accHomeDrawer.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                        // respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                        // respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {
                        // respond when the drawer is closed
                        accHomeDrawer.closeDrawers(); // close the nav drawer when the item is pressed

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // respond when the drawer motion state changes
                        accHomeDrawer.closeDrawers(); // close the nav drawer when the item is pressed
                    }
                }
        );

        accHomeToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(accHomeToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                accHomeDrawer.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(menuItem);
    }


}

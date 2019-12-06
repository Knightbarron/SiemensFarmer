package com.github.tenx.xcom.ui.main;

import android.os.Bundle;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.main.about.AboutFragment;
import com.github.tenx.xcom.ui.main.events.EventsFragment;
import com.github.tenx.xcom.ui.main.home.HomeFragment;
import com.github.tenx.xcom.ui.main.schedule.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener{


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @BindView(R.id.act_main_bnv)
    BottomNavigationView botNav;

    @Inject
    MainViewModel mainViewModel;


//    frag mans
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
//        this is neccesary bind call for BindView decorators
        ButterKnife.bind(this);

//        set callback as implemented interface
        botNav.setOnNavigationItemSelectedListener(this);

        if(mainViewModel == null){
            Timber.d("View model NOT ok");
        }else{
            Timber.d("View model is locked and loaded!");
        }



        //        initialize home fragment in main activity

        loadFragment(new HomeFragment());


//        user logger like this
        Timber.d("Welcome to my main application");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            @TODO delete this
            Toast.makeText(this, "You just clicked settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    handle bottom navigation clicks
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        int colorID;
        Fragment frag;
        switch (id){
            case R.id.nav_home:
                frag = new HomeFragment();
                colorID = R.color.nav_home;
                break;
            case R.id.nav_events:
                frag = new EventsFragment();
                colorID = R.color.nav_events;
                break;
            case R.id.nav_schedule:
                frag = new ScheduleFragment();
                colorID = R.color.nav_schedule;
                break;
            case R.id.nav_about:
                frag = new AboutFragment();
                colorID = R.color.nav_about;
                break;
                default:
                    return  false;
        }

        botNav.setBackgroundColor(getResources().getColor(colorID));
        loadFragment(frag);
        return true;
    }



    private void loadFragment(Fragment frag){
            fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.act_main_fl_container, frag).commit();


    }
}

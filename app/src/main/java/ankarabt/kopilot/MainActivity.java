package ankarabt.kopilot;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.serialization.PropertyInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import AdapterLayer.DirectoryAdapter;
import AdapterLayer.LanguageAdapter;
import DataLayer.ConfigData;
import DataLayer.Directory_Data;
import DataLayer.Language_Data;
import EntityLayer.Directory;
import EntityLayer.GirisYazi;
import EntityLayer.Language;
import EntityLayer.Nokta;
import ToolLayer.DefaultException;
import ToolLayer.MessageBox;
import ToolLayer.RSOperator;
import ToolLayer.StateReceiver;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


   public String speed_text_instant;
    public String speed_text_average;
    public String gidilen_km_text_total;
    public String kalan_km_text_total;
    public String varis_suresi_text_ortalama;
    public String varis_suresi_text_instant;
    public String gecen_sure_text_total;



    DrawerLayout drawer;
    NavigationView navigationView;
    private Handler mHandler;
    public static int navItemIndex = 0;
    ListView language_popup_list_view;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private String TAG_ANA_SAYFA = "Hac";
    private String TAG_GUZERGAH = "Umre";
    private String TAG_ISTATISTIK = "Dualar";
    private String TAG_HARITA = "Diyanet Hac Organizasyonu";
    private String TAG_AYARLAR = "Ziyaret";

    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    private boolean first_login = false;
    ConfigData configData;
    int ana_menuden_nav_item_index  = 0;

    MenuItem menu_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        mHandler = new Handler();

        ana_menuden_nav_item_index = getIntent().getIntExtra("nav_item_index",0);


        configData = new ConfigData(this);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        //drawer.openDrawer(GravityCompat.START);




       /* if(configData.getFirsttime().equals(""))
        {
            Log.v("first ", "login");
            first_login = true;
            if (StateReceiver.isNetworkAvailable(MainActivity.this)) {
                //new getLanguageViaService().execute();
            }
            else
            {
                MessageBox.showAlert(MainActivity.this,getResources().getString(R.string.internet_error),false);
            }
        }
        else
        {*/
            Log.v("old","login");
            first_login = false;

          /*  if(ana_menuden_nav_item_index == 0)
            {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HAC;
                loadHomeFragment();
            }
            else if(ana_menuden_nav_item_index == 1)
            {
                navItemIndex = 1;
                CURRENT_TAG = TAG_UMRE;
                loadHomeFragment();
            }
            else if(ana_menuden_nav_item_index == 2)
            {
                navItemIndex = 2;
                CURRENT_TAG = TAG_DUALAR;
                loadHomeFragment();
            }
            */

            navItemIndex = 0;
            CURRENT_TAG = TAG_ANA_SAYFA;
            loadHomeFragment();
       // }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu_item = menu.findItem(R.id.action_settings);
        menu_item.setVisible(false);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    void init()
    {
        TAG_ANA_SAYFA = getResources().getString(R.string.nav_ana_sayfa);
        TAG_GUZERGAH = getResources().getString(R.string.nav_guzergahlar);
        TAG_HARITA = getResources().getString(R.string.nav_harita);
        TAG_ISTATISTIK =  getResources().getString(R.string.nav_istatistik);
        TAG_AYARLAR = getResources().getString(R.string.nav_ayarlar);
    }







    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_ana_sayfa) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
        } else if (id == R.id.nav_guzergahlar) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_GUZERGAH;
        } else if (id == R.id.nav_harita) {
            navItemIndex = 2;
            CURRENT_TAG = TAG_HARITA;
        } else if (id == R.id.nav_istatistik) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_ISTATISTIK;
        } else if (id == R.id.nav_ayarlar) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_AYARLAR;
        }

        drawer.closeDrawer(GravityCompat.START);
        loadHomeFragment();


        return true;
    }




    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private Fragment getFragment() {
       // option_menu_set_invisible();
        switch (navItemIndex) {
            case 0:
                // home
                Log.v("ana sayfa frag","selected");
                TestFragment testFragment = new TestFragment();
                return testFragment;
            case 1:
                GuzergahFragment guzergahFragment = new GuzergahFragment();
                return guzergahFragment;
            case 2:
                Log.v("case 2","harita");
                Intent mint = new Intent(this , MapFragment.class);
                mint.putExtra("guzergah_mid","0");
                startActivity(mint);
                return null;

            case 3:
               // HacDirectoryFragment ziyaretFragment = new HacDirectoryFragment();
               // return ziyaretFragment;
            case 4:
                AyarlarFragment ayarlarfragment = new AyarlarFragment();
                return ayarlarfragment;

            default:
                return null;
                //return new HacDirectoryFragment();

        }
    }


    void option_menu_set_visible()
    {
        if(menu_item != null)
            menu_item.setVisible(true);
    }
    void option_menu_set_invisible()
    {
        if(menu_item != null)
            menu_item.setVisible(false);
    }


    Fragment previous_fragment = null;

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();
        // set toolbar title
        setToolbarTitle();
        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            Log.v("mevcut tıklandı","---");
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments


                if(previous_fragment != null)
                {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.remove(previous_fragment).commit();
                }

                    Fragment fragment = getFragment();

                    if(fragment != null) {

                        previous_fragment = fragment;
                        Bundle args = new Bundle();
                        args.putInt("menuId", navItemIndex + 1);
                        if(navItemIndex == 0) {
                            args.putString("totalMesafe", "0");
                            Log.v("total mesafe send","0");
                        }
                        fragment.setArguments(args);

                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame_layout, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }


            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        //Closing drawer on item click
     //   drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }




    public void fragmentChange(String mesafe)
    {
        TestFragment testFrag = new TestFragment();
        Fragment fragment = testFrag;

        if(fragment != null) {
            Bundle args = new Bundle();
            args.putString("totalMesafe",mesafe);
            fragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }






}

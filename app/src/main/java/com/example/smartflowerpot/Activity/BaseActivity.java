package com.example.smartflowerpot.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartflowerpot.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class BaseActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;
    private MaterialToolbar topbar;
    private RecyclerView recycledViewPlants;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity);

        initViews();
        setupNavigation();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navController.getCurrentDestination().getId() != R.id.createFragment) {
                    navController.navigate(R.id.createFragment);
                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    private void setupNavigation() {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navController = navHostFragment.getNavController();

        setSupportActionBar(topbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.overviewFragment,
                R.id.accountFragment,
                R.id.friendsFragment,
                R.id.settingsFragment)
                .build();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(topbar, navController, appBarConfiguration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        topbar = findViewById(R.id.topbar);
        fab = findViewById(R.id.fab);
    }

    public void setTopbarTitle(String title){
        topbar.setTitle(title);
    }
}
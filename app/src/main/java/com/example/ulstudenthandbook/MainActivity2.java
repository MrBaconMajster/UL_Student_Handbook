package com.example.ulstudenthandbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    View navHostFragment;


    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        navHostFragment = findViewById(R.id.navHostFragmentContainer);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setNavigationViewListener();
        setSupportActionBar(toolbar);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_nav_logout)
        {
         Navigation.findNavController(navHostFragment).navigate(R.id.action_mapsFrag_to_timetableFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_mainbuilding)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
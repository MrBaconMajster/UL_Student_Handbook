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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    View navHostFragment;
    TimetableInfo timetableInfo;


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

        timetableInfo = new TimetableInfo();

        timetableInfo = ReadTimetableInfoFromFile("timetablePrototype.txt");
        saveTimetableInfoToJSON("timetablePrototype.txt");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Reads which fragment the navHost is currently holding. eg.fragment_maps
        CharSequence currentFragmentLabel =  Navigation.findNavController(navHostFragment).getCurrentDestination().getLabel();
        String currentFragmentName = currentFragmentLabel.toString();

        //Changes the screen from the current fragment to the transition fragment
        if (currentFragmentName.equals("fragment_maps"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_mapsFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_timetable"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_timetableFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_main_building_map"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_mainBuildingMapFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_add_module"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_addModuleFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_building"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_buildingFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_edit_timetable"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_editTimetableFrag_to_transitionFrag);
        }
        else if (currentFragmentName.equals("fragment_add_module"))
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_addModuleFrag_to_transitionFrag);
        }

        // Goes from transition fragment to the selected fragment

        if(item.getItemId() == R.id.menu_nav_timetable)
        {
         Navigation.findNavController(navHostFragment).navigate(R.id.action_transitionFrag_to_timetableFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_map)
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_transitionFrag_to_mapsFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_mainbuilding)
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_transitionFrag_to_mainBuildingMapFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_buildings)
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_transitionFrag_to_buildingFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_addModule)
        {
            Navigation.findNavController(navHostFragment).navigate(R.id.action_transitionFrag_to_addModuleFrag);
        }
        else if (item.getItemId() == R.id.menu_nav_logout)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void saveTimetableInfoToJSON(String filename) {
        String TimetableInformationJSONString = new Gson().toJson(timetableInfo);

        //Write JSON file
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(TimetableInformationJSONString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TimetableInfo ReadTimetableInfoFromFile(String filename) {
        TimetableInfo t = new TimetableInfo();
        FileInputStream fis = null;
        try {
            fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();
            Gson gson = new Gson();
            t = gson.fromJson(json, TimetableInfo.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

}
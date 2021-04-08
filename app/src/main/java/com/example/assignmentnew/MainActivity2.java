package com.example.assignmentnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupNavigationView();
    }
    public void setupNavigationView() {
        //setup toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigationView);
        navController= Navigation.findNavController(this,R.id.host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.host_fragment),drawerLayout);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        drawerLayout.closeDrawers();
        int id=item.getItemId();
        switch(id)
        {

            case R.id.dashboard:
                Toast.makeText(getApplicationContext(),"dashboard Fragment Clicked",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.dashboardFragment);
                break;
                case R.id.first:
                Toast.makeText(getApplicationContext(),"First Fragment Clicked",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.firstFragment);
                break;
            case R.id.second:
                Toast.makeText(getApplicationContext(),"Second Fragment Clicked",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.secondFragment);
                break;
        }
        return true;

    }
}
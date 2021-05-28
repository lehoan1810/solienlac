package com.hoanle.solienlaccc;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomehocsinhActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homehocsinh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_hocsinh);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setDrawerMemu();
    }

    private void setDrawerMemu() {
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_dangxuat:
                    auth.signOut();
                    drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(this,Login.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.nav_thongtincanhan:
                    navController.navigate(R.id.action_hocsinhFragment_to_nav_thongtincanhan);
                    drawer.closeDrawer(GravityCompat.START);
                    break;
            }
            return false;
        });
    }

}
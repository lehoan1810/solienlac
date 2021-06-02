package com.hoanle.solienlaccc;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
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
    FirebaseFirestore fStore;
    NavController navController;
    TextView txtTenHocSinh, txtLophocsinh;
    String IDUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homehocsinh);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_hocsinh);

        IDUser=auth.getCurrentUser().getUid();
        fStore          = FirebaseFirestore.getInstance();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setDrawerMemu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_hocsinh);
        View headerView = navigationView.getHeaderView(0);
        txtTenHocSinh = (TextView) headerView.findViewById(R.id.txtTenHocSinh);

        getNameUser();

    }
    public void getNameUser(){
        DocumentReference documentReference = fStore.collection("HocSinh").document(IDUser);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name =(String) document.getString("HoTen");
                        txtTenHocSinh.setText(name);
                    }
                    else {
                        Log.d("TAG", "No such document");
                    }
                } else
                {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
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
                    navController.navigate(R.id.nav_thongtincanhan);
                    drawer.closeDrawer(GravityCompat.START);
                    break;
            }
            return false;
        });
    }

}
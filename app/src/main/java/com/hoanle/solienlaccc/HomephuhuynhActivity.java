package com.hoanle.solienlaccc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomephuhuynhActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore;
    NavController navController;
    TextView txttenphuhuynh;
    String IDUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homephuhuynh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        IDUser=auth.getCurrentUser().getUid();
        fStore          = FirebaseFirestore.getInstance();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setDrawerMemu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txttenphuhuynh = (TextView) headerView.findViewById(R.id.txttenphuhuynh);

        getNameUser();

    }

    public void getNameUser(){
        DocumentReference documentReference = fStore.collection("PhuHuynh").document(IDUser);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name =(String) document.getString("HoTen");
                        txttenphuhuynh.setText(name);
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
            if(item.getItemId()==R.id.nav_dangxuat){
                auth.signOut();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }
}
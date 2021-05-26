package com.hoanle.solienlaccc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.fragments.HocPhiFragment;
import com.hoanle.solienlaccc.fragments.PhuHuynhFragment;

public class Login extends Activity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText username = findViewById(R.id.edtUserName);
        EditText pass = findViewById(R.id.edtPass);
        EditText role = findViewById(R.id.edtRole);

        Button login = findViewById(R.id.btnLogin);
        login.setOnClickListener(view -> {
            Login(username.getText().toString(),pass.getText().toString());
        });

    }
    private void Login(String username, String password){
        auth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(authResult -> {
                    firestore.collection("roles").document(authResult.getUser().getUid())
                            .get().addOnSuccessListener(documentSnapshot -> {
                        Intent intent;
                                switch (documentSnapshot.getString("role")) {
                                    case "PhuHuynh":
                                        intent = new Intent(this, HomephuhuynhActivity.class);
                                        startActivity(intent);
                                        break;
                                    case "Admin":
                                        intent = new Intent(this, HomephuhuynhActivity.class);
                                        startActivity(intent);
                                        break;
                                    default:
                                        intent = new Intent(this, HomehocsinhActivity.class);
                                        startActivity(intent);
                                }
                    });
                }).addOnFailureListener(e -> {
            Log.d("",e.getMessage());
            Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        });
    }

}

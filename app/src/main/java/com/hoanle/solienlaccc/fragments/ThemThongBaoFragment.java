package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;

import java.util.ArrayList;
import java.util.Calendar;

public class ThemThongBaoFragment extends Fragment {
    Button Them;
    EditText NoiDung;
    EditText TieuDe;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addthongbao, container, false);
        Them = view.findViewById(R.id.buttonAddNotiSend);
        TieuDe = view.findViewById(R.id.editTextNotiTitle);
        NoiDung = view.findViewById(R.id.editTextTextMultiLine);

        Them.setOnClickListener(view1 -> {
            Timestamp NgayGui = Timestamp.now();
            String NguoiGui = auth.getCurrentUser().getEmail();
            ArrayList<DocumentReference> NguoiNhan = new ArrayList<>();
            xemThongBao thongBao = new xemThongBao(NgayGui, NguoiGui, NguoiNhan,
                    NoiDung.getText().toString(),
                    TieuDe.getText().toString());
            firestore.collection("ThongBao").add(thongBao).addOnCompleteListener(documentReference -> {
                Toast.makeText(getContext(), "Đã gửi", Toast.LENGTH_SHORT).show();
                NoiDung.setText("");
                TieuDe.setText("");
            });
        });
        return view;
    }
}

package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;

import java.util.ArrayList;

public class ChiTietDiemFragment extends Fragment {
    TextView name;
    TextView Tiet;
    TextView GiuaKy;
    TextView CuoiKy;
    TextView MuoiLam;
    TextView TrungBinh;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitietdiem, container, false);
        MuoiLam = view.findViewById(R.id.textView15point);
        Tiet = view.findViewById(R.id.textView1hourpoint);
        GiuaKy = view.findViewById(R.id.textViewmidTerm);
        CuoiKy = view.findViewById(R.id.textViewEndofTerm);
        TrungBinh = view.findViewById(R.id.textViewAvg);
        name = view.findViewById(R.id.textViewSubjectName);
        Bundle arguments = getArguments();
        String Id = arguments.getString("Id");
        String Name = arguments.getString("TenMon");
        name.setText(Name);
        MyFirebase fb = MyFirebase.getInstance();
        fb.getCurrentHocSinh().addOnCompleteListener(task -> {
            DocumentReference hocSinhRef = task.getResult().getReference();
            DocumentReference monRef = firestore.collection("MonHoc").document(Id);
            firestore.collection("Diem").whereEqualTo("Mon", monRef)
                .whereEqualTo("HocSinh", hocSinhRef).get()
                .addOnSuccessListener(documentSnapshots -> {
                    for(DocumentSnapshot doc : documentSnapshots) {
                        ArrayList<String> fifteen = (ArrayList<String>) doc.get("15Phut");
                        String muoilam = "";
                        for (String diem : fifteen) {
                            muoilam += diem;
                            MuoiLam.setText(muoilam);
                            muoilam += ", ";
                        }
                        ArrayList<String> hour = (ArrayList<String>)doc.get("1Tiet");
                        String tiet = "";
                        for (String diem : hour) {
                            tiet += diem;
                            Tiet.setText(tiet);
                            tiet += ", ";
                        }
                        String giuaky = doc.getString("GiuaKy");
                        String cuoiky = doc.getString("CuoiKy");
                        String avg = doc.getString("TrungBinh");
                        MuoiLam.setText(muoilam);
                        GiuaKy.setText(giuaky);
                        CuoiKy.setText(cuoiky);
                        TrungBinh.setText(avg);
                    }

                });
        });

        return view;
    }
}

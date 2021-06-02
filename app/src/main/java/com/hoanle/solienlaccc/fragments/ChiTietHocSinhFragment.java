package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.R;

public class ChiTietHocSinhFragment extends Fragment {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    TextView studentName;
    TextView studentEmail;
    TextView studentParent;
    TextView studentPhone;
    TextView studentId;
    TextView studentClassroom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chitiethocsinh, container, false);
        String email = auth.getCurrentUser().getEmail();
        studentName = root.findViewById(R.id.textViewStudentName);
        studentClassroom = root.findViewById(R.id.textViewClassroom);
        studentPhone = root.findViewById(R.id.textViewStudentPhone);
        studentParent = root.findViewById(R.id.textViewParentName);
        studentEmail = root.findViewById(R.id.textViewEmail);
        studentId = root.findViewById(R.id.textViewMSSV);

        fireStore.collection("HocSinh").whereEqualTo("Email", email).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for(DocumentSnapshot hocSinh : task.getResult().getDocuments()) {
                    studentEmail.setText(hocSinh.getString("Email"));
                    studentName.setText(hocSinh.getString("HoTen"));
                    hocSinh.getDocumentReference("Lop").get().addOnSuccessListener(documentSnapshot -> {
                        studentClassroom.setText(documentSnapshot.getString("TenLop"));
                    });
                    studentPhone.setText(hocSinh.getString("SDT"));
                    hocSinh.getDocumentReference("PhuHuynh").get().addOnSuccessListener(documentSnapshot -> {
                        studentParent.setText(documentSnapshot.getString("HoTen"));
                    });
                    studentId.setText(hocSinh.getString("Email"));
                }
            }
        });

        fireStore.collection("HocSinh").whereEqualTo("HoTen", qlhocsinhFragment.Ten).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for(DocumentSnapshot hocSinh : task.getResult().getDocuments()) {
                    studentEmail.setText(hocSinh.getString("Email"));
                    studentName.setText(hocSinh.getString("HoTen"));
                    hocSinh.getDocumentReference("Lop").get().addOnSuccessListener(documentSnapshot -> {
                        studentClassroom.setText(documentSnapshot.getString("TenLop"));
                    });
                    studentPhone.setText(hocSinh.getString("SDT"));
                    hocSinh.getDocumentReference("PhuHuynh").get().addOnSuccessListener(documentSnapshot -> {
                        studentParent.setText(documentSnapshot.getString("HoTen"));
                    });
                    studentId.setText(hocSinh.getString("Email"));
                }
            }
        });
        return root;
    }
}

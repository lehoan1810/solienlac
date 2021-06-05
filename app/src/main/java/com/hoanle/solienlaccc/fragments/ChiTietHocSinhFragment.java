package com.hoanle.solienlaccc.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.R;

public class ChiTietHocSinhFragment extends Fragment {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    DocumentReference washingtonRef = fireStore.collection("HocSinh").document("H8Wfi5yA2PP6AvASctQUiyJqo2G3");

    View root;
    TextView studentName;
    TextView studentEmail;
    TextView studentParent;
    TextView studentPhone;
    TextView studentId;
    TextView studentClassroom;
    Button btnEditProfile;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_chitiethocsinh, container, false);
        String email = auth.getCurrentUser().getEmail();

        Mapping(root);

        // My code
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

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

    private void Mapping(View root)
    {
        studentName         =   root.findViewById(R.id.textViewStudentName);
        studentClassroom    =   root.findViewById(R.id.textViewClassroom);
        studentPhone        =   root.findViewById(R.id.textViewStudentPhone);
        studentParent       =   root.findViewById(R.id.textViewParentName);
        studentEmail        =   root.findViewById(R.id.textViewEmail);
        studentId           =   root.findViewById(R.id.textViewMSSV);
        btnEditProfile      =   root.findViewById(R.id.buttonEditProfileStudent);
    }

    private  void showDialog()
    {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_profile);


        //EditText edtEmail       = (EditText) dialog.findViewById(R.id.editTextEmailEdit);
        EditText edtPhone       = (EditText) dialog.findViewById(R.id.editTextPhoneEdit);
        EditText edtAddress     = (EditText) dialog.findViewById(R.id.editTextAddressEdit);
        Button btnXacNhan       = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit       = (Button) dialog.findViewById(R.id.buttonHuyEdit);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email        = edtEmail.getText().toString();
                String phone        = edtPhone.getText().toString();
                String address      = edtAddress.getText().toString();

                if(phone.equals(" ") || address.equals(""))
                {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    washingtonRef
                            .update("SDT", phone, "DiaChi", address)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    studentPhone.setText(phone);
                                    Toast.makeText(getActivity(), "Successfully updated!", Toast.LENGTH_SHORT).show();
                                    //Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Error updating document!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Toast.makeText(getActivity(), "Đã cập nhật!.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        btnHuyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}

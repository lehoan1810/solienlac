package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.hocsinh.HocSinh;
import com.hoanle.solienlaccc.fragments.lop.Lop;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;

import java.util.ArrayList;
import java.util.Calendar;

import static com.google.firebase.messaging.Constants.MessagePayloadKeys.SENDER_ID;

public class ThemThongBaoFragment extends Fragment {
    Button Them;
    EditText NoiDung;
    EditText TieuDe;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    Spinner lop;
    Spinner nguoinhan;
    ArrayList<HocSinh> dsHocSinh;
    ArrayList<Lop> dsLop;
    ArrayAdapter<HocSinh> hocSinhAdapter;
    ArrayAdapter<Lop> lopAdapter;

    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addthongbao, container, false);
        Them = view.findViewById(R.id.buttonAddNotiSend);
        TieuDe = view.findViewById(R.id.editTextNotiTitle);
        NoiDung = view.findViewById(R.id.editTextTextMultiLine);

        setSpinner(view);

        Them.setOnClickListener(view1 -> {
            Timestamp NgayGui = Timestamp.now();
            String NguoiGui = auth.getCurrentUser().getEmail();
            DocumentReference hsRef = firestore.collection("HocSinh")
                    .document(((HocSinh)nguoinhan.getSelectedItem()).getId());
            ArrayList<DocumentReference> hss = new ArrayList<>();
            hss.add(hsRef);
            xemThongBao thongBao = new xemThongBao(NgayGui, NguoiGui, hss
                    ,
                    NoiDung.getText().toString(),
                    TieuDe.getText().toString());
            firestore.collection("ThongBao").add(thongBao).addOnCompleteListener(documentReference -> {
                Toast.makeText(getContext(), "Đã gửi", Toast.LENGTH_SHORT).show();
                NoiDung.setText("");
                TieuDe.setText("");
//                documentReference.getResult().get().addOnCompleteListener(task -> {
//                    FirebaseMessaging fm = FirebaseMessaging.getInstance();
//                    fm.send(new RemoteMessage.Builder(SENDER_ID + "@fcm.googleapis.com")
//                            .setMessageId(task.getResult().getId())
//                            .addData(task.getResult().getString("tieuDe"), task.getResult().getString("noiDung"))
//                            .addData("my_action","SAY_HELLO")
//                            .build());
//
//                });
            });

        });
        return view;
    }

    private void setSpinner(View view) {
        lop = view.findViewById(R.id.spinnerLop);
        nguoinhan = view.findViewById(R.id.spinnerMSHS);
        dsHocSinh = new ArrayList<>();
        dsLop = new ArrayList<>();
        MyFirebase.getAllLop().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for(DocumentSnapshot lop : task.getResult()){
                    com.hoanle.solienlaccc.fragments.lop.Lop l = new Lop();
                    l.setMaLop(lop.getId());
                    l.setTenLop(lop.getString("TenLop"));
                    dsLop.add(l);
                }
                lopAdapter = new ArrayAdapter<Lop>(getContext(),R.layout.support_simple_spinner_dropdown_item,dsLop);
                lop.setAdapter(lopAdapter);
                lop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        MyFirebase.getHocSinhOfLop(lopAdapter.getItem(i).getMaLop()).addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                dsHocSinh.clear();
                                for(QueryDocumentSnapshot hocSinh : task.getResult()){
                                    HocSinh hs = new HocSinh();
                                    hs.setHoTen(hocSinh.getString("HoTen"));
                                    hs.setId(hocSinh.getId());
                                    dsHocSinh.add(hs);
                                }
                                hocSinhAdapter = new ArrayAdapter<HocSinh>(getContext(),R.layout.support_simple_spinner_dropdown_item,dsHocSinh);
                                nguoinhan.setAdapter(hocSinhAdapter);
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        hocSinhAdapter.clear();
                    }
                });
            }
        });
    }
}

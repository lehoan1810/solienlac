package com.hoanle.solienlaccc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import java.util.List;

public class ChiTietTKBFragment extends Fragment {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<String> monTrongNgay;
    MyFirebase myFirebase = MyFirebase.getInstance();
    LinearLayout linearLayout;
    TextView tv_thu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiettkb, container, false);

        linearLayout        = view.findViewById(R.id.ll_ChiTietTKB);
        tv_thu              = view.findViewById(R.id.tv_TKBThu);

        Bundle arguments = getArguments();
        String Thu = arguments.getString("Thu");
        String Mon = arguments.getString("Mon");

        ArrayList<DocumentReference> mons = (ArrayList<DocumentReference>)arguments.getSerializable("Mons");
        tv_thu.setText("Thứ "+Thu);
        for(DocumentReference mon : mons){
            mon.get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    if(task.getResult().get("Thu").equals(Thu)){
                        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = vi.inflate(R.layout.dong_chitiettkb, null);

                        TextView tenMon = v.findViewById(R.id.tv_TenMonTKB);
                        tenMon.setText(task.getResult().getString("TenMon"));

                        TextView tiet = v.findViewById(R.id.tv_TietHoc);
                        tiet.setText("Tiết: " + task.getResult().getString("Tiet"));

                        linearLayout.addView(v);
                    }
                }
            });
        }
        return view;
    }
}

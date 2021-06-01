package com.hoanle.solienlaccc.lophoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.hocsinh.HocSinh;
import com.hoanle.solienlaccc.fragments.lop.Lop;

import java.util.ArrayList;

public class dslophoc extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lophoc, container, false);


        return view;
    }
}

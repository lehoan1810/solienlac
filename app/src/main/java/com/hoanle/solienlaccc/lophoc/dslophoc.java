package com.hoanle.solienlaccc.lophoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hoanle.solienlaccc.R;

public class dslophoc extends Fragment {
    Spinner spinnerLop;
    Spinner spinnerMaso;
    private static final String[] Lop = new String[0];
    private static final String[] Maso = new String[0];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lophoc, container, false);
        spinnerLop = root.findViewById(R.id.spinnerLop);
        spinnerMaso = root.findViewById(R.id.spinnerMSHS);

        return root;
    }
}

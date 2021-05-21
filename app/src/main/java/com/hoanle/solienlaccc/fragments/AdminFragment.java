package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.hoanle.solienlaccc.R;

public class AdminFragment extends Fragment {

    CardView cardqlhocsinh;
    CardView cardqlthongbao;
    CardView cardqllophoc;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);

        cardqlhocsinh = root.findViewById(R.id.cardQLHocSinh);
        cardqlthongbao = root.findViewById(R.id.cardQLThongBao);
        cardqllophoc = root.findViewById(R.id.cardQLLopHoc);

        NavController navController = Navigation.findNavController(container);

        cardqlhocsinh.setOnClickListener(v -> {
            navController.navigate(R.id.action_adminFragment_to_qlhocsinhFragment);
        });
        cardqlthongbao.setOnClickListener(v -> {
            navController.navigate(R.id.action_adminFragment_to_qlthongbaoFragment);
        });
        cardqllophoc.setOnClickListener(v -> {
            navController.navigate(R.id.action_adminFragment_to_qllophocFragment);
        });
        return root;
    }
}

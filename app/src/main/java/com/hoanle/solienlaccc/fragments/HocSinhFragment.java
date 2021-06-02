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

public class HocSinhFragment extends Fragment {

    CardView cardhsxemdiem;
    CardView cardhsthongbao;
    CardView cardhsthoikhoabieu;
    CardView cardhshocphi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hocsinh_home, container, false);

        cardhsxemdiem       = root.findViewById(R.id.cardhsXemDiem);
        cardhsthongbao      = root.findViewById(R.id.cardhsThongBao);
        cardhsthoikhoabieu  = root.findViewById(R.id.cardhsTKB);
        cardhshocphi        = root.findViewById(R.id.cardhsHocPhi);

        NavController navController = Navigation.findNavController(container);

        cardhsxemdiem.setOnClickListener(v -> {
            navController.navigate(R.id.action_hocsinhFragment_to_xemdiemFragment);
        });
        cardhsthongbao.setOnClickListener(v -> {
            navController.navigate(R.id.action_hocsinhFragment_to_thongbaoFragment);
        });
        cardhsthoikhoabieu.setOnClickListener(v -> {
            navController.navigate(R.id.action_hocsinhFragment_to_tkbFragment);
        });
        cardhshocphi.setOnClickListener(v -> {
            navController.navigate(R.id.action_hocsinhFragment_to_hocPhiFragment);
        });
        return root;
    }
}

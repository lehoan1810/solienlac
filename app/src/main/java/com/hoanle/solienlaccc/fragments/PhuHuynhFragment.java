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

public class PhuHuynhFragment extends Fragment {

    CardView cardXemDiem;
    CardView cardThongBao;
    CardView cardTKB;
    CardView cardHocPhi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_phuhuynh_home, container, false);

        cardXemDiem = root.findViewById(R.id.cardXemDiem);
        cardThongBao = root.findViewById(R.id.cardThongBao);
        cardTKB = root.findViewById(R.id.cardTKB);
        cardHocPhi = root.findViewById(R.id.cardHocPhi);

        NavController navController = Navigation.findNavController(container);

        cardThongBao.setOnClickListener(v -> {
            navController.navigate(R.id.action_phuHuynhFragment_to_thongBaoFragment);
        });
        cardHocPhi.setOnClickListener(v -> {
            navController.navigate(R.id.action_phuHuynhFragment_to_hocPhiFragment);
        });
        cardTKB.setOnClickListener(v -> {
            navController.navigate(R.id.action_phuHuynhFragment_to_TKBFragment);
        });
        cardXemDiem.setOnClickListener(v -> {
            navController.navigate(R.id.action_phuHuynhFragment_to_xemDiemFragment);
        });
        return root;
    }
}

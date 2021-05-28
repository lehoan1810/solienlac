package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoanle.solienlaccc.R;

public class ChiTietThongBaoFragment extends Fragment {

    TextView textViewNgayGui;
    TextView textViewNguoiGui;
    TextView textViewNoiDung;
    TextView textViewTieuDe;
    TextView tvBig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitietthongbao, container, false);
        Bundle arguments = getArguments();
        String NgayGui = arguments.getString("NgayGui");
        String NguoiGui = arguments.getString("NguoiGui");
        String NoiDung = arguments.getString("NoiDung");
        String TieuDe = arguments.getString("TieuDe");

        textViewNgayGui = view.findViewById(R.id.textViewDateSend);
        textViewNguoiGui = view.findViewById(R.id.textViewSender);
        textViewNoiDung = view.findViewById(R.id.textViewContentContent);
        textViewTieuDe = view.findViewById(R.id.textViewTitleContent);
        tvBig = view.findViewById(R.id.textViewNotiName);

        textViewNoiDung.setText(NoiDung);
        textViewTieuDe.setText(TieuDe);
        textViewNguoiGui.setText(NguoiGui);
        textViewNgayGui.setText(NgayGui);
        tvBig.setText(TieuDe);

        return view;
    }

}

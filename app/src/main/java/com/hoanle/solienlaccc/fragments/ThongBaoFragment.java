package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBaoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoFragment extends Fragment {
    ListView listViewThongBao;
    xemThongBaoAdapter adapter;
    List<xemThongBao> thongbao;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thongbao, container, false);
        Init(root);
        return root;
    }
    private void Init(View view){
        thongbao= new ArrayList<xemThongBao>();
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        thongbao.add(new xemThongBao("Thong báo 1","28/02/2000",R.drawable.imgthongbao));
        listViewThongBao=view.findViewById(R.id.listViewThongBao);
        adapter = new xemThongBaoAdapter(getActivity(),R.layout.dong_thongbao, thongbao);
        listViewThongBao.setAdapter(adapter);
    }
}

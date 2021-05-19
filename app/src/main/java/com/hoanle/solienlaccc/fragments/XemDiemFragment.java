package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMonAdapter;

import java.util.ArrayList;
import java.util.List;

public class XemDiemFragment extends Fragment {
    ListView listViewMonhoc;
    XemdiemMonAdapter adapter;
    List<XemdiemMon> xemdiemMons;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_xem_diem, container, false);
        Init(root);
        return root;
    }
    private void Init(View view){
        xemdiemMons = new ArrayList<XemdiemMon>();
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        xemdiemMons.add(new XemdiemMon("Thiết kế OOP",R.drawable.monhoc));
        listViewMonhoc=view.findViewById(R.id.listViewMonhoc);
        adapter = new XemdiemMonAdapter(getActivity(),R.layout.dong_monhoc, xemdiemMons);
        //arrayAdapter=new ArrayAdapter(getActivity(), R.layout.dong_monhoc, list);
//        xemdiemMons=new ArrayList<>();

        listViewMonhoc.setAdapter(adapter);
        adapter.notifyDataSetChanged();
 //       listViewMonhoc.setAdapter(arrayAdapter);
    }
}

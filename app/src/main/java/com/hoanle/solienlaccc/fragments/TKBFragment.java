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
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKB;
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKBAdapter;
import java.util.ArrayList;
import java.util.List;

public class TKBFragment extends Fragment {
    ListView listViewTKB;
    xemTKBAdapter adapter;
    List<xemTKB> thoikhoabieu;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tkb, container, false);
        Init(root);
        return root;
    }
    private void Init(View view){
        thoikhoabieu = new ArrayList<xemTKB>();
        thoikhoabieu.add(new xemTKB("Thứ 2",R.drawable.imgthu));
        thoikhoabieu.add(new xemTKB("Thứ 3",R.drawable.imgthu));
        thoikhoabieu.add(new xemTKB("Thứ 4",R.drawable.imgthu));
        thoikhoabieu.add(new xemTKB("Thứ 5",R.drawable.imgthu));
        thoikhoabieu.add(new xemTKB("Thứ 6",R.drawable.imgthu));
        thoikhoabieu.add(new xemTKB("Thứ 7",R.drawable.imgthu));
        listViewTKB=view.findViewById(R.id.listViewTKB);
        adapter = new xemTKBAdapter(getActivity(),R.layout.dong_tkb, thoikhoabieu);
        listViewTKB.setAdapter(adapter);
    }
}

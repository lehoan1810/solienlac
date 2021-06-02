package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKB;
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKBAdapter;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;

import java.util.ArrayList;
import java.util.List;

public class TKBFragment extends Fragment {
    ListView listViewTKB;
    xemTKBAdapter adapter;
    List<xemTKB> thoikhoabieu;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    MyFirebase myFirebase = MyFirebase.getInstance();
    ArrayList<DocumentReference> mons;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tkb, container, false);
        Init(root);
        return root;
    }


    private void Init(View view){
        mons = new ArrayList<>();
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
        listViewTKB.setOnItemClickListener((adapterView, view1, i1, l) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Mons", mons);
            bundle.putInt("Thu",i1+2);

            Navigation.findNavController(view).navigate(R.id.chiTietTKBFragment, bundle);
        });

        myFirebase.getCurrentHocSinh()
                .continueWith(new MyFirebase.getLopRef())
                .continueWithTask(new MyFirebase.getLop())
                .continueWith(new MyFirebase.getMonRefs())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(DocumentReference mon : task.getResult()){
                            mons.add(mon);
                        }
                    }
        });
    }
}

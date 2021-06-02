package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.util.FloatProperty;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMonAdapter;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class XemDiemFragment extends Fragment {
    ListView listViewMonhoc;
    XemdiemMonAdapter adapter;
    List<XemdiemMon> xemdiemMons;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_xem_diem, container, false);
        Init(root);
        return root;
    }
    private void Init(View view){
        xemdiemMons = new ArrayList<XemdiemMon>();
        listViewMonhoc=view.findViewById(R.id.listViewMonhoc);
        adapter = new XemdiemMonAdapter(getActivity(),R.layout.dong_monhoc, xemdiemMons);
        listViewMonhoc.setAdapter(adapter);
        String email = auth.getCurrentUser().getEmail();
        setDanhSachMonHocCuaHocSinh(email);
        listViewMonhoc.setOnItemClickListener((adapterView, view1, i, l) -> {
            Bundle arguments = new Bundle();
            XemdiemMon diem = xemdiemMons.get(i);
            arguments.putString("TenMon",diem.getTenMon());
            arguments.putString("Id",diem.getId());

            Navigation.findNavController(view).navigate(R.id.chiTietDiemFragment, arguments);
        });
    }

    private void setDanhSachMonHocCuaHocSinh(String email){
        MyFirebase fb = MyFirebase.getInstance();
        Task<DocumentSnapshot> getHocSinh = fb.getCurrentHocSinh();
        getHocSinh.continueWith(new getLop())
                .continueWithTask(new getDanhSachMon())
                .continueWith(new setAdapter());
    }

    public class getLop implements Continuation<DocumentSnapshot, DocumentReference> {
        @Override
        public DocumentReference then(@NonNull Task<DocumentSnapshot> hocSinh) throws Exception {
            return hocSinh.getResult().getDocumentReference("Lop");
        }
    }

    public class getDanhSachMon implements Continuation<DocumentReference, Task<DocumentSnapshot>> {
        @Override
        public Task<DocumentSnapshot> then(@NonNull Task<DocumentReference> task) throws Exception {
            return task.getResult().get();
        }
    }

    public class setAdapter implements  Continuation<DocumentSnapshot, ArrayList<DocumentReference>> {
        @Override
        public ArrayList<DocumentReference> then(@NonNull Task<DocumentSnapshot> task) throws Exception {
            ArrayList<DocumentReference> dsmon = (ArrayList<DocumentReference>) task.getResult().get("DanhSachMonHoc");
            for(DocumentReference mon: dsmon){
                mon.get().addOnCompleteListener(task2 -> {
                    String tenMon = task2.getResult().getString("TenMon");
                    String id = task2.getResult().getId();
                    xemdiemMons.add(new XemdiemMon(tenMon, id, R.drawable.monhoc));
                    adapter.notifyDataSetChanged();
                });
            }
            return dsmon;
        }
    }
}

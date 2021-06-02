package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.MyFirebase;
import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.hocphi.XemHocPhi;
import com.hoanle.solienlaccc.fragments.hocphi.XemHocPhiAdapter;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMonAdapter;

import java.util.ArrayList;
import java.util.List;

public class HocPhiFragment extends Fragment {

    ListView listViewHocPhi;
    TextView txtTotal;
    XemHocPhiAdapter adapter;
    List<XemHocPhi> xemHocPhis;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hocphi, container, false);

        Init(view);


        return view;
    }

    private void Init(View view){
        xemHocPhis = new ArrayList<XemHocPhi>();
        //xemHocPhis.add(new XemHocPhi("1", "Toan", "10000000"));
        //xemHocPhis.add(new XemHocPhi("2", "Toan", "10000000"));
        //xemHocPhis.add(new XemHocPhi("3", "Toan", "10000000"));
        //xemHocPhis.add(new XemHocPhi("4", "Toan", "10000000"));
        listViewHocPhi=view.findViewById(R.id.listViewHocPhi);
        txtTotal = view.findViewById(R.id.tv_hpTotal);
        adapter = new XemHocPhiAdapter(getActivity(),R.layout.dong_hocphi, xemHocPhis);
        listViewHocPhi.setAdapter(adapter);
        String email = auth.getCurrentUser().getEmail();
        Toast.makeText(getActivity(), email, Toast.LENGTH_SHORT).show();
        setDanhSachMonHocCuaHocSinh(email);
        /*listViewHocPhi.setOnItemClickListener((adapterView, view1, i, l) -> {
            Bundle arguments = new Bundle();
            XemHocPhi diem = xemHocPhis.get(i);
            arguments.putString("TenMon",diem.getTenMon());
            arguments.putString("Id",diem.getId());

            Navigation.findNavController(view).navigate(R.id.chiTietDiemFragment, arguments);
        });*/
    }

    private void setDanhSachMonHocCuaHocSinh(String email){
        MyFirebase fb = MyFirebase.getInstance();
        Task<DocumentSnapshot> getHocSinh = fb.getCurrentHocSinh();
        getHocSinh.continueWith(new HocPhiFragment.getLop())
                .continueWithTask(new HocPhiFragment.getDanhSachMon())
                .continueWith(new HocPhiFragment.setAdapter());
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
            //long count = 0;
            ArrayList<DocumentReference> dsmon = (ArrayList<DocumentReference>) task.getResult().get("DanhSachMonHoc");
            for(DocumentReference mon: dsmon){
                mon.get().addOnCompleteListener(task2 -> {
                    String tenMon = task2.getResult().getString("TenMon");
                    String hocphi = task2.getResult().getString("Tien");
                    //long count = Long.parseLong(hocphi);
                    String id = task2.getResult().getId();
                    xemHocPhis.add(new XemHocPhi(id, tenMon, hocphi));
                    adapter.notifyDataSetChanged();
                    txtTotal.setText(1 + "");
                });
            }
            return dsmon;
        }
    }
}

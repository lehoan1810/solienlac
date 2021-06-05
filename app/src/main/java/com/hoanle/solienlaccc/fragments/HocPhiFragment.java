package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    int count = 0;
    int total=0;

    ListView listViewHocPhi;
    TextView txtTotal, txtThutien, txtNotien;
    XemHocPhiAdapter adapter;
    List<XemHocPhi> xemHocPhis;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    EditText editnamhoc;
    EditText edithocki;
    String namhoc="";
    String hocki="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hocphi, container, false);

        Init(view);

        setChonNamHoc(view);

        return view;
    }

    private void Init(View view){
        xemHocPhis      = new ArrayList<XemHocPhi>();

        listViewHocPhi      =   view.findViewById(R.id.listViewHocPhi);
        txtTotal            =   view.findViewById(R.id.tv_hpTotal);
        txtThutien          =   view.findViewById(R.id.tv_hpThu);
        txtNotien           =   view.findViewById(R.id.tv_hpNo);

        adapter         = new XemHocPhiAdapter(getActivity(),R.layout.dong_hocphi, xemHocPhis);
        listViewHocPhi.setAdapter(adapter);
        String email    = auth.getCurrentUser().getEmail();

        setDanhSachMonHocCuaHocSinh(email);
    }
    // My new code
    private void setChonNamHoc(View view) {
        editnamhoc          =(EditText) view.findViewById(R.id.edtNam);
        edithocki           =(EditText) view.findViewById(R.id.edtHocki);

        editnamhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPopup();
            }
        });

        edithocki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuHockiPopup();
            }
        });
    }
    private void MenuPopup()
    {
        PopupMenu popupMenu=new PopupMenu(getContext(),editnamhoc);
        popupMenu.getMenuInflater().inflate(R.menu.menu_namhoc,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.namhoc1:
                        editnamhoc.setText("2018-2019");
                        namhoc = "2018-2019";
                        xemHocPhis.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                    case R.id.namhoc2:
                        editnamhoc.setText("2019-2020");
                        namhoc = "2019-2020";
                        xemHocPhis.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                    case R.id.namhoc3:
                        editnamhoc.setText("2020-2021");
                        namhoc = "2020-2021";
                        xemHocPhis.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void MenuHockiPopup()
    {
        PopupMenu popupMenu=new PopupMenu(getContext(),edithocki);
        popupMenu.getMenuInflater().inflate(R.menu.menu_hocki,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hk1:
                        edithocki.setText("1");
                        hocki = "1";
                        xemHocPhis.clear();
                        setDanhSachMonHocCuaHocSinh("");

                        break;
                    case R.id.hk2:
                        edithocki.setText("2");
                        hocki = "2";
                        xemHocPhis.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
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
            xemHocPhis.clear();
            count = 0;
            total = 0;
            ArrayList<DocumentReference> dsmon = (ArrayList<DocumentReference>) task.getResult().get("DanhSachMonHoc");
            for(DocumentReference mon: dsmon){
                mon.get().addOnCompleteListener(task2 -> {
                    if(task2.getResult().getString("NamHoc").equals(namhoc) &&
                            task2.getResult().getString("HocKy").equals(hocki)) {
                        String tenMon       = task2.getResult().getString("TenMon");
                        String hocphi       = task2.getResult().getString("Tien");
                        String dongtien     = task2.getResult().getString("DongTien");
                        String id           = task2.getResult().getId();

                        xemHocPhis.add(new XemHocPhi(id, tenMon, hocphi));
                        adapter.notifyDataSetChanged();

                        total       +=Integer.parseInt(hocphi);
                        count       += Integer.parseInt(dongtien);

                        txtTotal.setText("Tổng tiền: "+total+"");
                        txtThutien.setText("Đã Đóng: " + count);
                        txtNotien.setText("Còn Nợ: " + (total - count));
                    }
                });
            }
            return dsmon;
        }
    }
}

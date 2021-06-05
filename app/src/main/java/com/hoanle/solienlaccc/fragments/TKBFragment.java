package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

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
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKB;
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKBAdapter;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;

import java.util.ArrayList;
import java.util.List;

public class TKBFragment extends Fragment {
    EditText editnamhoc;
    EditText edithocki;
    String namhoc="";
    String hocki="";
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

        setChonNamHoc(root);

        return root;
    }


    private void Init(View view){

        mons = new ArrayList<>();
        thoikhoabieu = new ArrayList<xemTKB>();
//        thoikhoabieu.add(new xemTKB("Thứ 2",R.drawable.imgthu));
//        thoikhoabieu.add(new xemTKB("Thứ 3",R.drawable.imgthu));
//        thoikhoabieu.add(new xemTKB("Thứ 4",R.drawable.imgthu));
//        thoikhoabieu.add(new xemTKB("Thứ 5",R.drawable.imgthu));
//        thoikhoabieu.add(new xemTKB("Thứ 6",R.drawable.imgthu));
//        thoikhoabieu.add(new xemTKB("Thứ 7",R.drawable.imgthu));

        listViewTKB=view.findViewById(R.id.listViewTKB);
        adapter = new xemTKBAdapter(getActivity(),R.layout.dong_tkb, thoikhoabieu);
        listViewTKB.setAdapter(adapter);

        String email = auth.getCurrentUser().getEmail();
        setDanhSachMonHocCuaHocSinh(email);
        listViewTKB.setOnItemClickListener((adapterView, view1, i1, l) -> {
            Bundle arguments = new Bundle();
            xemTKB tkb = thoikhoabieu.get(i1);
            arguments.putString("Thu",tkb.getThu());
            arguments.putSerializable("Mons",mons);
            Navigation.findNavController(view).navigate(R.id.chiTietTKBFragment, arguments);
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
    private void setChonNamHoc(View view) {
        editnamhoc=view.findViewById(R.id.edtNam);
        edithocki=view.findViewById(R.id.edtHocki);
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
                        thoikhoabieu.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                    case R.id.namhoc2:
                        editnamhoc.setText("2019-2020");
                        namhoc = "2019-2020";
                        thoikhoabieu.clear();
                        setDanhSachMonHocCuaHocSinh("");
                        break;
                    case R.id.namhoc3:
                        editnamhoc.setText("2020-2021");
                        namhoc = "2020-2021";
                        thoikhoabieu.clear();
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
                        thoikhoabieu.clear();
                        setDanhSachMonHocCuaHocSinh("");

                        break;
                    case R.id.hk2:
                        edithocki.setText("2");
                        hocki = "2";
                        thoikhoabieu.clear();
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
                    if(task2.getResult().getString("NamHoc").equals(namhoc) &&
                            task2.getResult().getString("HocKy").equals(hocki)) {

                        String thu = task2.getResult().getString("Thu");
                        thoikhoabieu.add(new xemTKB(thu, R.drawable.imgthu));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            return dsmon;
        }
    }
}

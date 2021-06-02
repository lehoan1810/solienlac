package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.R;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class qlLopHocFragment extends Fragment {
    EditText editnamhoc;
    EditText edithocki;
    String namhoc="";
    String hocki="";
    public static String tenmon;
    ListView lstmonhoc;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qllophoc, container, false);

        Init(view);
        setChonNamHoc(view);

        return view;
    }

    private void Init(View view){

        lstmonhoc=view.findViewById(R.id.lstQLMonhoc);
        list = new ArrayList<String>();
        //DocumentReference monRef = firestore.collection("MonHoc").document(Id);
        //firestore.collection("MonHoc").document("DocumentId").get().addOnCompleteListener();

        firestore.collection("MonHoc").get().addOnCompleteListener(task -> {
            for(DocumentSnapshot hocsinh : task.getResult().getDocuments()){
                list.add(hocsinh.getString("TenMon"));
            }
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
            lstmonhoc.setAdapter(adapter);
        });
        lstmonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenmon=list.get(position);
                Navigation.findNavController(view).navigate(R.id.chiTietMonhocFragment);

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
                        break;
                    case R.id.namhoc2:
                        editnamhoc.setText("2019-2020");
                        namhoc = "2019-2020";
                        break;
                    case R.id.namhoc3:
                        editnamhoc.setText("2020-2021");
                        namhoc = "2020-2021";
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
                        break;
                    case R.id.hk2:
                        edithocki.setText("2");
                        hocki = "2";
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void setChonNamHoc(View view) {
        editnamhoc=view.findViewById(R.id.edtQlnam);
        edithocki=view.findViewById(R.id.edtQlhk);
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
}

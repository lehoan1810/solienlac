package com.hoanle.solienlaccc.namhoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hoanle.solienlaccc.R;

public class dsnamhoc extends Fragment {
    EditText editnamhoc;
    EditText edithocki;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_namhoc, container, false);
        editnamhoc=root.findViewById(R.id.edtNam);
        edithocki=root.findViewById(R.id.edtHocki);
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
        return root;
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
                        editnamhoc.setText("2019-2020");
                        break;
                    case R.id.namhoc2:
                        editnamhoc.setText("2020-2021");
                        break;
                    case R.id.namhoc3:
                        editnamhoc.setText("2021-2022");
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
                        break;
                    case R.id.hk2:
                        edithocki.setText("2");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}

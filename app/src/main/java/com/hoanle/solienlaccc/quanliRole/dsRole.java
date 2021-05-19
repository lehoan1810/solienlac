package com.hoanle.solienlaccc.quanliRole;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.hoanle.solienlaccc.R;

public class dsRole extends AppCompatActivity {
    EditText editRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editRole=findViewById(R.id.edtRole);
        editRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPopup();
            }
        });

    }
    private void MenuPopup()
    {
        PopupMenu popupMenu=new PopupMenu(this,editRole);
        popupMenu.getMenuInflater().inflate(R.menu.menu_role,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hocsinh:
                        editRole.setText("Học Sinh");
                        break;
                    case R.id.phuhuynh:
                        editRole.setText("Phụ Huynh");
                        break;
                    case R.id.admin:
                        editRole.setText("Admin");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}

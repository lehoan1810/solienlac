package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

import com.hoanle.solienlaccc.R;


public class SearchHocSinh extends AppCompatActivity {
    SearchView searchView;
    ListView listView;

    String[] nameList = {"Hoan", "Quốc", "Việt"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qlhocsinh);
        searchView = findViewById(R.id.search_bar);
        listView = findViewById(R.id.list_item);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
        listView.setAdapter(arrayAdapter);
    }
}

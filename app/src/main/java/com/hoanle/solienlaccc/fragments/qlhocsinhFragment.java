package com.hoanle.solienlaccc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hoanle.solienlaccc.R;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class qlhocsinhFragment extends Fragment {
    public static String Ten;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_qlhocsinh, container, false);
        NavController navController = Navigation.findNavController(container);
        searchView = root.findViewById(R.id.search_bar);
        listView = root.findViewById(R.id.list_item);

        list = new ArrayList<String>();

        firestore.collection("HocSinh").get().addOnCompleteListener(task -> {
            for(DocumentSnapshot hocsinh : task.getResult().getDocuments()){
                list.add(hocsinh.getString("HoTen"));
            }
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        //Nhấn vào listItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ten=list.get(position);
//                navController.navigate(R.id.action_qllophocFragment_ChitiethocsinhFragment);
                Navigation.findNavController(view).navigate(R.id.chiTietHocSinhFragment);
            }
        });
        return root;
    }
}

package com.hoanle.solienlaccc.fragments;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
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
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBaoAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThongBaoFragment extends Fragment {
    ListView listViewThongBao;
    xemThongBaoAdapter adapter;
    List<xemThongBao> thongbao;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    SimpleDateFormat sfd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thongbao, container, false);
        Init(root);
        return root;
    }
    private void Init(View view){
        thongbao= new ArrayList<xemThongBao>();
        MyFirebase fb = MyFirebase.getInstance();
        fb.getCurrentHocSinh().addOnCompleteListener(task -> {
            DocumentReference hocSinhRef = fireStore.collection("HocSinh").document(task.getResult().getId());
            fireStore.collection("ThongBao")
                    .whereArrayContains("nguoiNhan", hocSinhRef).get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for(DocumentSnapshot thonBao : queryDocumentSnapshots) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                sfd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                thongbao.add(new xemThongBao(
                                        thonBao.getString("tieuDe"),
                                        thonBao.getString("noiDung"),
                                        sfd.format(thonBao.getTimestamp("ngayGui").toDate()),
                                        R.drawable.imgthongbao));
                            }
                        }
                        listViewThongBao=view.findViewById(R.id.listViewThongBao);
                        adapter = new xemThongBaoAdapter(getActivity(),R.layout.dong_thongbao, thongbao);
                        listViewThongBao.setAdapter(adapter);
                        listViewThongBao.setOnItemClickListener((adapterView, view1, i, l) -> {
                            ChiTietThongBaoFragment fragment = new ChiTietThongBaoFragment();
                            Bundle arguments = new Bundle();
                            xemThongBao tb = thongbao.get(i);
                            arguments.putString("NgayGui",tb.getThoiGian());
                            arguments.putString("NguoiGui","");
                            arguments.putString("NoiDung",tb.getNoiDung());
                            arguments.putString("TieuDe",tb.getThongBao());

                            fragment.setArguments(arguments);
                            Navigation.findNavController(view).navigate(R.id.chiTietThongBaoFragment, arguments);
                        });
                    });
        });

    }
}

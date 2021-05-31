package com.hoanle.solienlaccc;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyFirebase {
    private static MyFirebase fb = new MyFirebase();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static MyFirebase getInstance() {
        return fb;
    }

    public Task<DocumentSnapshot> getCurrentHocSinh() {
        return firestore.collection("HocSinh").document(auth.getCurrentUser().getUid()).get();
    }

    public Task<DocumentSnapshot> getHocSinhFromParent() {
        return firestore.collection("PhuHuynh").document(auth.getCurrentUser().getUid()).get()
                .continueWith(new getHocSinhCuaPhuHuynh())
                .continueWithTask(new getHocSinhFromReference());
    }

    public String getHocSinhId() {
        return auth.getCurrentUser().getUid();
    }

    public class getHocSinhCuaPhuHuynh implements Continuation<DocumentSnapshot, DocumentReference> {
        @Override
        public DocumentReference then(@NonNull Task<DocumentSnapshot> phuHuynh) throws Exception {
            return phuHuynh.getResult().getDocumentReference("HocSinh");
        }
    }

    public class getHocSinhFromReference implements Continuation<DocumentReference, Task<DocumentSnapshot>> {
        @Override
        public Task<DocumentSnapshot> then(@NonNull Task<DocumentReference> hocSinh) throws Exception {
            return firestore.document(hocSinh.getResult().toString()).get();
        }
    }

    public static class getLopRef implements Continuation<DocumentSnapshot, DocumentReference> {
        @Override
        public DocumentReference then(@NonNull Task<DocumentSnapshot> hocSinh) throws Exception {
            return hocSinh.getResult().getDocumentReference("Lop");
        }
    }

    public static class getLop implements Continuation<DocumentReference, Task<DocumentSnapshot>> {
        @Override
        public Task<DocumentSnapshot> then(@NonNull Task<DocumentReference> lopRef) throws Exception {
            return lopRef.getResult().get();
        }
    }

    public static class getMonRefs implements Continuation<DocumentSnapshot, ArrayList<DocumentReference>> {
        @Override
        public ArrayList<DocumentReference> then(@NonNull Task<DocumentSnapshot> lop) throws Exception {
            return (ArrayList<DocumentReference>)lop.getResult().get("DanhSachMonHoc");
        }
    }

    public static class getMon implements Continuation<DocumentReference, Task<DocumentSnapshot>> {
        @Override
        public Task<DocumentSnapshot> then(@NonNull Task<DocumentReference> monRef) throws Exception {
            return monRef.getResult().get();
        }
    }

    public static class getTietFromLop implements Continuation<DocumentSnapshot, String> {
        @Override
        public String then(@NonNull Task<DocumentSnapshot> lop) throws Exception {
            return lop.getResult().getString("Tiet");
        }
    }
}

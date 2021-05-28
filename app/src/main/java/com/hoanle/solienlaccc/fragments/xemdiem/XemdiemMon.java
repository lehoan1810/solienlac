package com.hoanle.solienlaccc.fragments.xemdiem;

import com.google.firebase.firestore.DocumentReference;

public class XemdiemMon {
    private DocumentReference Ref;
    private String TenMon;
    private int Hinh;

    public XemdiemMon(String tenMon, int hinh) {
        TenMon = tenMon;
        Hinh = hinh;
    }

    public DocumentReference getRef() {
        return Ref;
    }

    public void setRef(DocumentReference ref) {
        Ref = ref;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }
}

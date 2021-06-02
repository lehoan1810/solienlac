package com.hoanle.solienlaccc.fragments.qlmon;

import com.google.firebase.firestore.DocumentReference;

public class XemMon {
    private String Id;
    private String TenMon;
    private int Hinh;

    public XemMon(String tenMon, String id,int hinh) {
        TenMon = tenMon;
        Hinh = hinh;
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

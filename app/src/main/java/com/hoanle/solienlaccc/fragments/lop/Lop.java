package com.hoanle.solienlaccc.fragments.lop;

import androidx.annotation.NonNull;

public class Lop {
    String TenLop;
    String GVChuNhiem;
    String NamHoc;
    String MaLop;

    @NonNull
    @Override
    public String toString() {
        return TenLop;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    public String getGVChuNhiem() {
        return GVChuNhiem;
    }

    public void setGVChuNhiem(String GVChuNhiem) {
        this.GVChuNhiem = GVChuNhiem;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String namHoc) {
        NamHoc = namHoc;
    }
}

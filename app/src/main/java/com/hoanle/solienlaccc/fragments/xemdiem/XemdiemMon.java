package com.hoanle.solienlaccc.fragments.xemdiem;

public class XemdiemMon {
    private String TenMon;
    private int Hinh;

    public XemdiemMon(String tenMon, int hinh) {
        TenMon = tenMon;
        Hinh = hinh;
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

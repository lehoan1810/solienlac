package com.hoanle.solienlaccc.fragments.thongbao;

public class xemThongBao {
    private String ThongBao;
    private String ThoiGian;
    private int Hinh;

    public xemThongBao(String thongbao,String thoigian, int hinh) {
        ThongBao = thongbao;
        ThoiGian = thoigian;
        Hinh = hinh;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getThongBao() {
        return ThongBao;
    }

    public void setThongBao(String thongbao) {
        ThongBao = thongbao;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoigian) {
        ThoiGian = thoigian;
    }
}

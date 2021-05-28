package com.hoanle.solienlaccc.fragments.thongbao;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class xemThongBao {
    private String NgayGui;
    private String NguoiGui;
    private ArrayList<DocumentReference> NguoiNhan;
    private String NoiDung;
    private String TieuDe;

    private String ThongBao;
    private String ThoiGian;
    private int Hinh;

    public xemThongBao(String ngayGui, String nguoiGui, ArrayList<DocumentReference> nguoiNhan, String noiDung, String tieuDe) {
        NgayGui = ngayGui;
        NguoiGui = nguoiGui;
        NguoiNhan = nguoiNhan;
        NoiDung = noiDung;
        TieuDe = tieuDe;
    }

    public xemThongBao(String thongbao, String noiDung, String thoigian, int hinh) {
        ThongBao = thongbao;
        ThoiGian = thoigian;
        Hinh = hinh;
        NoiDung = noiDung;
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

    public String getNoiDung() {
        return NoiDung;
    }
}

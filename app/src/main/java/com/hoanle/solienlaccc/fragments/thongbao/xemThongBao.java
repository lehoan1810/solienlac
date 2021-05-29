package com.hoanle.solienlaccc.fragments.thongbao;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.sql.Time;
import java.util.ArrayList;

public class xemThongBao {
    private Timestamp NgayGui;
    private String NguoiGui;
    private ArrayList<DocumentReference> NguoiNhan;
    private String NoiDung;
    private String TieuDe;

    private String ThongBao;
    private String ThoiGian;
    private int Hinh;

    public xemThongBao(Timestamp ngayGui, String nguoiGui, ArrayList<DocumentReference> nguoiNhan, String noiDung, String tieuDe) {
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

    public Timestamp getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(Timestamp NgayGui) {
        this.NgayGui = NgayGui;
    }

    public String getNguoiGui() {
        return NguoiGui;
    }

    public void setNguoiGui(String NguoiGui) {
        this.NguoiGui = NguoiGui;
    }

    public ArrayList<DocumentReference> getNguoiNhan() {
        return NguoiNhan;
    }

    public void setNguoiNhan(ArrayList<DocumentReference> NguoiNhan) {
        this.NguoiNhan = NguoiNhan;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String TieuDe) {
        this.TieuDe = TieuDe;
    }
}

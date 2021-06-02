package com.hoanle.solienlaccc.fragments.hocphi;

public class XemHocPhi {
    private String Id;
    private String TenMon;
    private String hocphi;
    //private int Hinh;

    public XemHocPhi(String id, String tenMon, String hocphi) {
        Id = id;
        TenMon = tenMon;
        this.hocphi = hocphi;
        //Hinh = hinh;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getHocphi() {
        return hocphi;
    }

    public void setHocphi(String hocphi) {
        this.hocphi = hocphi;
    }

}

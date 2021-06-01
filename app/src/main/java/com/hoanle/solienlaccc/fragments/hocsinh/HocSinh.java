package com.hoanle.solienlaccc.fragments.hocsinh;

import androidx.annotation.NonNull;

public class HocSinh {
    String id;
    String hoTen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @NonNull
    @Override
    public String toString() {
        return hoTen;
    }
}

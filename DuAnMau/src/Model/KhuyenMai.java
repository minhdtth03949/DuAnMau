/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author asus
 */
public class KhuyenMai {
    
    private int MaKM;
    private int PhanTramKM;
    private Date ThoiGianBatDau;
    private Date ThoiGianKetThuc;
    private int MaNV;

    public KhuyenMai() {
    }

    public KhuyenMai(int MaKM, int PhanTramKM, Date ThoiGianBatDau, Date ThoiGianKetThuc, int MaNV) {
        this.MaKM = MaKM;
        this.PhanTramKM = PhanTramKM;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.MaNV = MaNV;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public int getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(int PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }

    public Date getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(Date ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
    
}

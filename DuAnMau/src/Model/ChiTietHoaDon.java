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
public class ChiTietHoaDon {
   
    private int MaCTHD;
    private int MaHD;
    private int MaKM;
    private int MaCTSP;
    private String TenSP;
    private float DonGia;
    private float GiaApDungMaKM;
    private Boolean TrangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int MaCTHD, int MaHD, int MaKM, int MaCTSP, String TenSP, float DonGia, float GiaApDungMaKM, Boolean TrangThai) {
        this.MaCTHD = MaCTHD;
        this.MaHD = MaHD;
        this.MaKM = MaKM;
        this.MaCTSP = MaCTSP;
        this.TenSP = TenSP;
        this.DonGia = DonGia;
        this.GiaApDungMaKM = GiaApDungMaKM;
        this.TrangThai = TrangThai;
    }

    public int getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(int MaCTHD) {
        this.MaCTHD = MaCTHD;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public float getGiaApDungMaKM() {
        return GiaApDungMaKM;
    }

    public void setGiaApDungMaKM(float GiaApDungMaKM) {
        this.GiaApDungMaKM = GiaApDungMaKM;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}

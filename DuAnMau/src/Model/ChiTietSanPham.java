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
public class ChiTietSanPham {
    
    private int MaCTSP;
    private int MaSP;
    private String TenSP;
    private float DonGia;
    private Date NgayNhap;
    private Date HanSD;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int MaCTSP, int MaSP, String TenSP, float DonGia, Date NgayNhap, Date HanSD) {
        this.MaCTSP = MaCTSP;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.DonGia = DonGia;
        this.NgayNhap = NgayNhap;
        this.HanSD = HanSD;
    }

    public int getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(int MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
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

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public Date getHanSD() {
        return HanSD;
    }

    public void setHanSD(Date HanSD) {
        this.HanSD = HanSD;
    }
    
    
    
}

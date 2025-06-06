/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package Model;
import java.util.Date;
/**
 *
 * @author asus
 */
public class SanPham {

    private int MaSP;
    private String TenSP;
    private float DonGia;
    private Date NgayNhap;
    private int MaNV;

    public SanPham(int MaSP, String TenSP, float DonGia, Date NgayNhap, int MaNV) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.DonGia = DonGia;
        this.NgayNhap = NgayNhap;
        this.MaNV = MaNV;
    }

    public SanPham() {
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

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
    
}

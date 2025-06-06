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
public class HoaDon {
     
    private int MaHD;
    private int MaNV;
    private Boolean TrangThai;
    private Date NgayThanhToan;
    private int GioThanhToan;

    public HoaDon() {
    }

    public HoaDon(int MaHD, int MaNV, Boolean TrangThai, Date NgayThanhToan, int GioThanhToan) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.TrangThai = TrangThai;
        this.NgayThanhToan = NgayThanhToan;
        this.GioThanhToan = GioThanhToan;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getGioThanhToan() {
        return GioThanhToan;
    }

    public void setGioThanhToan(int GioThanhToan) {
        this.GioThanhToan = GioThanhToan;
    }
    
    
}

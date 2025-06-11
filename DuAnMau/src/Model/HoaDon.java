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

    public HoaDon() {
    }

    public HoaDon(int MaHD, int MaNV, Boolean TrangThai, Date NgayThanhToan) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.TrangThai = TrangThai;
        this.NgayThanhToan = NgayThanhToan;
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

    
}

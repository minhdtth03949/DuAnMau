/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class NhanVien {
    
    private int MaNV;
    private String TenDangNhap;
    private String Email;
    private String MatKhau;
    private Boolean VaiTro;

    public NhanVien() {
    }

    public NhanVien(int MaNV, String TenDangNhap, String Email, String MatKhau, Boolean VaiTro) {
        this.MaNV = MaNV;
        this.TenDangNhap = TenDangNhap;
        this.Email = Email;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public Boolean getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(Boolean VaiTro) {
        this.VaiTro = VaiTro;
    }
    
    
}

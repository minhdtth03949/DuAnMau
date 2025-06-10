/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COntroller;

import Model.ChiTietHoaDon;
import Model.ChiTietSanPham;
import Model.HoaDon;
import Model.SanPham;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class QuanLyBanHang {

    MyConnection mc;
    public QuanLyBanHang() {
    
    }
    
    public List<HoaDon> getAllHoaDon() throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "SELECT * FROM HOADON";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<HoaDon> lst = new ArrayList<HoaDon>();
        while(rs.next()){
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(rs.getInt(1));
            hd.setNgayLap(rs.getDate(2));
            hd.setTenKhachHang(rs.getString(3));
            hd.setTongTien(rs.getFloat(4));
            lst.add(hd);
        }
        return lst;
    }
    
    public List<String[]> getChiTietHoaDon(int maHoaDon) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "SELECT        ChiTietHoaDon.MaChiTietHoaDon, ChiTietHoaDon.MaHoaDon, ChiTietHoaDon.MaChiTiet, SanPham.TenSanPham, ChiTietSanPham.MauSac, ChiTietSanPham.KichThuoc, ChiTietHoaDon.SoLuong, ChiTietHoaDon.DonGia\n" +
                        "FROM            ChiTietHoaDon INNER JOIN\n" +
                        "                         ChiTietSanPham ON ChiTietHoaDon.MaChiTiet = ChiTietSanPham.MaChiTiet INNER JOIN\n" +
                        "                         HoaDon ON ChiTietHoaDon.MaHoaDon = HoaDon.MaHoaDon INNER JOIN\n" +
                        "                         SanPham ON ChiTietSanPham.MaSanPham = SanPham.MaSanPham\n" +
                        "WHERE ChiTietHoaDon.MaHoaDon = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, String.valueOf(maHoaDon));
        ResultSet rs = stmt.executeQuery();
        List<String[]> lst = new ArrayList<>();
        while(rs.next()){
            String[] object= new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
            lst.add(object);
        }
        return lst;
    }

    public List<ChiTietHoaDon> getAllChiTietHoaDon(int mahd) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "SELECT * FROM CHITIETHOADON WHERE MAHOADON = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, String.valueOf(mahd));
        ResultSet rs = stmt.executeQuery();
        List<ChiTietHoaDon> lst = new ArrayList<ChiTietHoaDon>();
        while(rs.next()){
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaChiTietHoaDon(rs.getInt(1));
            ct.setMaHoaDon(rs.getInt(2));
            ct.setMaChiTet(rs.getInt(3));
            ct.setSoLuong(rs.getInt(4));
            ct.setDonGia(rs.getFloat(5));
            lst.add(ct);
        }
        return lst;
    }
    
    public List<ChiTietSanPham> getChiTietSanPham(int maSP) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "SELECT * FROM CHITIETSANPHAM WHERE MASANPHAM = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, maSP);
        ResultSet rs = stmt.executeQuery();
        List<ChiTietSanPham> lst = new ArrayList<ChiTietSanPham>();
        while(rs.next()){
            ChiTietSanPham ctsp = new ChiTietSanPham();
            ctsp.setMaChiTiet(rs.getInt(1));
            ctsp.setMaSanPham(rs.getInt(2));
            ctsp.setMauSac(rs.getString(3));
            ctsp.setKichThuoc(rs.getString(4));
            ctsp.setSoLuongTon(rs.getInt(5));
            lst.add(ctsp);
        }
        return lst;
    }
    public List<SanPham> getAllSanPham() throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "SELECT * FROM SANPHAM";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<SanPham> lst = new ArrayList<SanPham>();
        while(rs.next()){
            SanPham sp = new SanPham();
            sp.setMaSanPham(rs.getInt(1));
            sp.setTenSanPham(rs.getString(2));
            sp.setLoaiSanPham(rs.getString(3));
            sp.setGia(rs.getFloat(4));
            lst.add(sp);
        }
        return lst;
    }
    
    public boolean ThemHoaDon(HoaDon hd) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "INSERT INTO HOADON VALUES (?,?,?)";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDate(1, hd.getNgayLap());
        stmt.setString(2, hd.getTenKhachHang());
        stmt.setFloat(3, hd.getTongTien());
        int rowAdded = stmt.executeUpdate();
        if(rowAdded > 0){
            return true;
        }
        return false;
    }
    
        public boolean ThemChiTietHD(ChiTietHoaDon cthd) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "INSERT INTO ChiTietHoaDon VALUES (?,?,?,?)";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cthd.getMaHoaDon());
        stmt.setInt(2, cthd.getMaChiTet());
        stmt.setInt(3, cthd.getSoLuong());
        stmt.setFloat(4, cthd.getDonGia());
        int rowAdded = stmt.executeUpdate();
        if(rowAdded > 0){
            return true;
        }
        return false;
    }
        
    public boolean UpdateChiTietHD(int soluong, int mactHD) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "UPDATE CHITIETHOADON SET SOLUONG = ? WHERE MACHITIETHOADON = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, soluong);
        stmt.setInt(2, mactHD);
        int rowUpdated = stmt.executeUpdate();
        if(rowUpdated > 0){
            return true;
        }
        return false;
    }
    
        public boolean UpdateHoaDon(float tongtien, int mahoadon) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "UPDATE HOADON SET tongtien = ? WHERE mahoadon = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setFloat(1, tongtien);
        stmt.setInt(2, mahoadon);
        int rowUpdated = stmt.executeUpdate();
        if(rowUpdated > 0){
            return true;
        }
        return false;
    }
        
    public boolean XoaChiTietHD(int maHoaDonChiTiet) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query = "DELETE FROM CHITIETHOADON WHERE MACHITIETHOADON = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, maHoaDonChiTiet);
        int rowDeleted = stmt.executeUpdate();
        if(rowDeleted > 0){
            return true;
        }
        return false;
    }
    
    public boolean XoaHoaDon(HoaDon hd) throws ClassNotFoundException, SQLException{
        mc = new MyConnection();
        String query1 = "DELETE FROM CHITIETHOADON WHERE MAHOADON = ?";
        String query2 = "DELETE FROM HOADON WHERE MAHOADON = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt1 = conn.prepareStatement(query1);
        stmt1.setInt(1, hd.getMaHoaDon());
        stmt1.executeUpdate();
        PreparedStatement stmt2 = conn.prepareStatement(query2);
        stmt2.setInt(1, hd.getMaHoaDon());
        int rowDeleted = stmt2.executeUpdate();
        if(rowDeleted > 0){
            return true;
        }
        return false;
    }
}

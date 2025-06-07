/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDon;
import Model.ChiTietSanPham;
import Model.HoaDon;
import java.sql.*;
import Model.HoaDon;
import Model.SanPham;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class QLBanHang {
    
   MyConnection mc;
   
    public QLBanHang() {
    
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
            hd.setMaHD(rs.getInt(1));
            hd.setMaNV(rs.getInt(2));
            hd.setTrangThai(rs.getBoolean(3));
            hd.setNgayThanhToan(rs.getDate(4));
            hd.setGioThanhToan(rs.getInt(5));
            lst.add(hd);
        }
        return lst;
    }
    
    public List<ChiTietHoaDon> getAllChiTietHoaDon() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ChiTietHoaDon where MaHoaDon = ?";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<ChiTietHoaDon> lst = new ArrayList<>();
        while (rs.next()) {
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setMaCTHD(rs.getInt(1));
            cthd.setMaHD(rs.getInt(2));
            cthd.setMaKM(rs.getInt(3));
            cthd.setMaCTSP(rs.getInt(4));
            cthd.setTenSP(rs.getString(5));
            cthd.setDonGia(rs.getFloat(6));
            cthd.setGiaApDungMaKM(rs.getFloat(7));
            cthd.setTrangThai(rs.getBoolean(8));
            lst.add(cthd);
        }
 
        return lst;
    }
    
    public List<SanPham> getAllSanPham() throws SQLException, ClassNotFoundException {
        List<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
        Connection connect = mc.DBConnect();
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setMaSP(rs.getInt(1));
            sp.setTenSP(rs.getString(2));
            sp.setDonGia(rs.getFloat(3));
            sp.setNgayNhap(rs.getDate(4));
            sp.setMaNV(rs.getInt(5));
            listSP.add(sp);
        }
        return listSP;
    }
    
    public List<ChiTietSanPham> getAllChiTietSanPham() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ChiTietSanPham";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<ChiTietSanPham> lst = new ArrayList<>();
        while (rs.next()) {
            ChiTietSanPham ctsp = new ChiTietSanPham();
            ctsp.setMaCTSP(rs.getInt(1));
            ctsp.setMaSP(rs.getInt(2));
            ctsp.setTenSP(rs.getString(3));
            ctsp.setDonGia(rs.getFloat(4));
            ctsp.setNgayNhap(rs.getDate(5));
            ctsp.setHanSD(rs.getDate(6));
            lst.add(ctsp);
        }
        
        return lst;
    }
    
    
    }
    
        


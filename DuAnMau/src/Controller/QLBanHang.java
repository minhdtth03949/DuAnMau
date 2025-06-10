/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDon;
import Model.ChiTietSanPham;
import Model.HoaDon;
import Model.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asus 
 */
public class QLBanHang {
    
   MyConnection mc;

    public QLBanHang() {
        mc = new MyConnection();
    }

    public List<SanPham> getAllSanPham() throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "SELECT * FROM SanPham";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<SanPham> lst = new ArrayList<>();
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setMaSP(rs.getInt(1));
            sp.setTenSP(rs.getString(2));
            sp.setDonGia(rs.getFloat(3));
            sp.setNgayNhap(rs.getDate(4));
            sp.setMaNV(rs.getInt(5));
            lst.add(sp);
        }
        rs.close();
        stmt.close();
        conn.close();
        return lst;
    }

    public List<ChiTietSanPham> getChiTietSanPham(int maSP) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "SELECT * FROM ChiTietSanPham WHERE MaSP = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, maSP);
        ResultSet rs = stmt.executeQuery();
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
        rs.close();
        stmt.close();
        conn.close();
        return lst;
    }

    public List<HoaDon> getAllHoaDon() throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "SELECT * FROM HoaDon";
        Connection conn = mc.DBConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<HoaDon> lst = new ArrayList<>();
        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getInt(1));
            hd.setMaNV(rs.getInt(2));
            hd.setTrangThai(rs.getBoolean(3));
            hd.setNgayThanhToan(rs.getDate(4));
            hd.setGioThanhToan(rs.getInt(5)); // Giả định GioThanhToan là int (cần điều chỉnh nếu dùng Time)
            lst.add(hd);
        }
        rs.close();
        stmt.close();
        conn.close();
        return lst;
    }

    public List<ChiTietHoaDon> getAllChiTietHoaDon(int maHD) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "SELECT * FROM ChiTietHoaDon WHERE MaHD = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, maHD);
        ResultSet rs = stmt.executeQuery();
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
        rs.close();
        stmt.close();
        conn.close();
        return lst;
    }

    public boolean ThemHoaDon(HoaDon hd) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "INSERT INTO HoaDon (MaNV, TrangThai, NgayThanhToan, GioThanhToan) VALUES (?, ?, ?, ?)";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, hd.getMaNV());
        stmt.setBoolean(2, hd.getTrangThai());
        stmt.setDate(3, new java.sql.Date(hd.getNgayThanhToan().getTime()));
        stmt.setInt(4, hd.getGioThanhToan()); // Cần điều chỉnh nếu dùng Time
        int rowAdded = stmt.executeUpdate();
        return rowAdded > 0;
    }

    public boolean ThemChiTietHD(ChiTietHoaDon cthd) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "INSERT INTO ChiTietHoaDon (MaHD, MaKM, MaCTSP, TenSP, DonGia, GiaApDungMaKM, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cthd.getMaHD());
        stmt.setInt(2, cthd.getMaKM());
        stmt.setInt(3, cthd.getMaCTSP());
        stmt.setString(4, cthd.getTenSP());
        stmt.setFloat(5, cthd.getDonGia());
        stmt.setFloat(6, cthd.getGiaApDungMaKM());
        stmt.setBoolean(7, cthd.getTrangThai());
        int rowAdded = stmt.executeUpdate();
        return rowAdded > 0;
    }

    public boolean UpdateChiTietHD(int maCTHD, float donGia, float giaApDungMaKM, boolean trangThai) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "UPDATE ChiTietHoaDon SET DonGia = ?, GiaApDungMaKM = ?, TrangThai = ? WHERE MaCTHD = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setFloat(1, donGia);
        stmt.setFloat(2, giaApDungMaKM);
        stmt.setBoolean(3, trangThai);
        stmt.setInt(4, maCTHD);
        int rowUpdated = stmt.executeUpdate();
        return rowUpdated > 0;
    }

    public boolean UpdateHoaDon(int maHD, boolean trangThai, Date ngayThanhToan, int gioThanhToan) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "UPDATE HoaDon SET TrangThai = ?, NgayThanhToan = ?, GioThanhToan = ? WHERE MaHD = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setBoolean(1, trangThai);
        stmt.setDate(2, new java.sql.Date(ngayThanhToan.getTime()));
        stmt.setInt(3, gioThanhToan); // Cần điều chỉnh nếu dùng Time
        stmt.setInt(4, maHD);
        int rowUpdated = stmt.executeUpdate();
        return rowUpdated > 0;
    }

    public boolean XoaChiTietHD(int maCTHD) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query = "DELETE FROM ChiTietHoaDon WHERE MaCTHD = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, maCTHD);
        int rowDeleted = stmt.executeUpdate();
        return rowDeleted > 0;
    }

    public boolean XoaHoaDon(int maHD) throws ClassNotFoundException, SQLException {
        mc = new MyConnection();
        String query1 = "DELETE FROM ChiTietHoaDon WHERE MaHD = ?";
        String query2 = "DELETE FROM HoaDon WHERE MaHD = ?";
        Connection conn = mc.DBConnect();
        PreparedStatement stmt1 = conn.prepareStatement(query1);
        stmt1.setInt(1, maHD);
        stmt1.executeUpdate();
        PreparedStatement stmt2 = conn.prepareStatement(query2);
        stmt2.setInt(1, maHD);
        int rowDeleted = stmt2.executeUpdate();
        return rowDeleted > 0;
    }
    
    }
    
        


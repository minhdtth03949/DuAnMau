/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asus
 */
public class QLHoaDon {
    
    MyConnection conn;

    public QLHoaDon() {
        conn = new MyConnection();
    }

    // Lấy tất cả hóa đơn
    public List<HoaDon> getAll() {
        List<HoaDon> listHD = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    // Lấy 1 dòng dữ liệu dạng Object[] cho JTable
    public Object[] getRow(HoaDon hd) {
        return new Object[]{
            hd.getMaHD(),
            hd.getMaNV(),
            hd.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán",
            hd.getNgayThanhToan()
        };
    }

    // Thêm hóa đơn
    public int themHD(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaHD, MaNV, TrangThai, NgayThanhToan) VALUES (?, ?, ?, ?)";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaNV());
            pstm.setBoolean(3, hd.getTrangThai());
            pstm.setDate(4, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            if (pstm.executeUpdate() > 0) {
                System.out.println("Thêm hóa đơn thành công.");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Sửa hóa đơn
    public int suaHD(HoaDon hd, int theoMaHD) {
        String sql = "UPDATE HoaDon SET MaHD = ?, MaNV = ?, TrangThai = ?, NgayThanhToan = ? WHERE MaHD = ?";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaNV());
            pstm.setBoolean(3, hd.getTrangThai());
            pstm.setDate(4, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            pstm.setInt(5, theoMaHD);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Cập nhật hóa đơn thành công.");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Xóa hóa đơn theo mã
    public int xoaHD(int maHD) {
        String sql = "DELETE FROM HoaDon WHERE MaHD = ?";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maHD);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xóa hóa đơn thành công.");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tìm kiếm theo mã hóa đơn
    public List<HoaDon> timKiem(int maTim) {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maTim);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

}

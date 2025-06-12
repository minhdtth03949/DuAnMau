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
    
    MyConnection conn;

    public QLBanHang() {
        conn = new MyConnection();
    }

    // Lấy tất cả sản phẩm
    public List<SanPham> getAllSanPham() throws ClassNotFoundException {
        List<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM SANPHAM";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setNgayNhap(rs.getDate("NgayNhap"));
                sp.setMaNV(rs.getInt("MaNV"));
                listSP.add(sp);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        }
        return listSP;
    }

    // Lấy hàng sản phẩm
    public Object[] getRowSanPham(SanPham sp) {
        return new Object[]{
            sp.getMaSP(),
            sp.getTenSP(),
            sp.getDonGia(),
            sp.getNgayNhap(),
            sp.getMaNV()
        };
    }

    // Lấy tất cả hóa đơn
    public List<HoaDon> getAllHoaDon() throws ClassNotFoundException {
        List<HoaDon> listHD = new ArrayList<>();
        String query = "SELECT * FROM HOADON";
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
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
        }
        return listHD;
    }
    
    public List<ChiTietHoaDon> getAllChiTietHoaDon() throws ClassNotFoundException {
        List<ChiTietHoaDon> listCTHD = new ArrayList<>();
        String query = "SELECT * FROM ChiTietHoaDon";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaCTHD(rs.getInt("MaCTHD"));
                cthd.setMaHD(rs.getInt("MaHD"));
                cthd.setMaKM(rs.getInt("MaKM"));
                cthd.setMaCTSP(rs.getInt("MaCTSP"));
                cthd.setTenSP(rs.getString("TenSP"));
                cthd.setDonGia(rs.getFloat("DonGia"));
                cthd.setGiaApDungMaKM(rs.getFloat("GiaApDungMaKM"));
                cthd.setTrangThai(rs.getBoolean("TrangThai"));
                listCTHD.add(cthd);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
        }
        return listCTHD;
    }
    
    public List<ChiTietSanPham> getAllChiTietSanPham() throws ClassNotFoundException {
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        String query = "SELECT * FROM ChiTietSanPham";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setMaSP(rs.getInt("MaSP"));
                ctsp.setTenSP(rs.getString("TenSP"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                ctsp.setNgayNhap(rs.getDate("NgayNhap"));
                ctsp.setHanSD(rs.getDate("HanSD"));
                listCTSP.add(ctsp);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
        }
        return listCTSP;
    }

    // Lấy hàng hóa đơn
    public Object[] getRowHoaDon(HoaDon hd) {
        return new Object[]{
            hd.getMaHD(),
            hd.getMaNV(),
            hd.getTrangThai(),
            hd.getNgayThanhToan()
        };
    }

    // Lấy chi tiết sản phẩm theo mã sản phẩm
    public List<ChiTietSanPham> getChiTietSanPham(int maSP) throws ClassNotFoundException {
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        String query = "SELECT * FROM CHITIETSANPHAM WHERE MaSP = ?";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, maSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setMaCTSP(rs.getInt("MaCTSP"));
                ctsp.setMaSP(rs.getInt("MaSP"));
                ctsp.setTenSP(rs.getString("TenSP"));
                ctsp.setDonGia(rs.getFloat("DonGia"));
                ctsp.setNgayNhap(rs.getDate("NgayNhap"));
                ctsp.setHanSD(rs.getDate("HanSD"));
                listCTSP.add(ctsp);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết sản phẩm: " + e.getMessage());
        }
        return listCTSP;
    }

    // Lấy hàng chi tiết sản phẩm
    public Object[] getRowChiTietSanPham(ChiTietSanPham ctsp) {
        return new Object[]{
            ctsp.getMaCTSP(),
            ctsp.getMaSP(),
            ctsp.getTenSP(),
            ctsp.getDonGia(),
            ctsp.getNgayNhap(),
            ctsp.getHanSD()
        };
    }

    // Lấy chi tiết hóa đơn theo mã hóa đơn
    public List<ChiTietHoaDon> getChiTietHoaDon(int maHD) throws ClassNotFoundException {
        List<ChiTietHoaDon> listCTHD = new ArrayList<>();
        String query = "SELECT * FROM CHITIETHOADON WHERE MaHD = ?";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaCTHD(rs.getInt("MaCTHD"));
                cthd.setMaHD(rs.getInt("MaHD"));
                cthd.setMaKM(rs.getInt("MaKM"));
                cthd.setMaCTSP(rs.getInt("MaCTSP"));
                cthd.setTenSP(rs.getString("TenSP"));
                cthd.setDonGia(rs.getFloat("DonGia"));
                cthd.setGiaApDungMaKM(rs.getFloat("GiaApDungMaKM"));
                cthd.setTrangThai(rs.getBoolean("TrangThai"));
                listCTHD.add(cthd);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage());
        }
        return listCTHD;
    }

    // Lấy hàng chi tiết hóa đơn
    public Object[] getRowChiTietHoaDon(ChiTietHoaDon cthd) {
        return new Object[]{
            cthd.getMaCTHD(),
            cthd.getMaHD(),
            cthd.getMaKM(),
            cthd.getMaCTSP(),
            cthd.getTenSP(),
            cthd.getDonGia(),
            cthd.getGiaApDungMaKM(),
            cthd.getTrangThai()
        };
    }

    // Thêm hóa đơn
    public boolean ThemHoaDon(HoaDon hd) throws ClassNotFoundException {
        String query = "INSERT INTO HOADON (MaNV, TrangThai, NgayThanhToan) VALUES (?, ?, ?)";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, hd.getMaNV());
            stmt.setBoolean(2, hd.getTrangThai());
            stmt.setDate(3, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            connect.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
            return false;
        }
    }

    // Thêm chi tiết hóa đơn
    public boolean ThemChiTietHD(ChiTietHoaDon cthd) throws ClassNotFoundException {
        String query = "INSERT INTO CHITIETHOADON (MaHD, MaKM, MaCTSP, TenSP, DonGia, GiaApDungMaKM, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, cthd.getMaHD());
            stmt.setInt(2, cthd.getMaKM());
            stmt.setInt(3, cthd.getMaCTSP());
            stmt.setString(4, cthd.getTenSP());
            stmt.setFloat(5, cthd.getDonGia());
            stmt.setFloat(6, cthd.getGiaApDungMaKM());
            stmt.setBoolean(7, cthd.getTrangThai());
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            connect.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật chi tiết hóa đơn
    public boolean UpdateChiTietHD(ChiTietHoaDon cthd, int maCTHD) throws ClassNotFoundException {
        String query = "UPDATE CHITIETHOADON SET MaHD = ?, MaKM = ?, MaCTSP = ?, TenSP = ?, DonGia = ?, GiaApDungMaKM = ?, TrangThai = ? WHERE MaCTHD = ?";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, cthd.getMaHD());
            stmt.setInt(2, cthd.getMaKM());
            stmt.setInt(3, cthd.getMaCTSP());
            stmt.setString(4, cthd.getTenSP());
            stmt.setFloat(5, cthd.getDonGia());
            stmt.setFloat(6, cthd.getGiaApDungMaKM());
            stmt.setBoolean(7, cthd.getTrangThai());
            stmt.setInt(8, maCTHD);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            connect.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật chi tiết hóa đơn: " + e.getMessage());
            return false;
        }
    }

    // Xóa chi tiết hóa đơn
    public boolean XoaChiTietHD(int maCTHD) throws ClassNotFoundException {
        String query = "DELETE FROM CHITIETHOADON WHERE MaCTHD = ?";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, maCTHD);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            connect.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa chi tiết hóa đơn: " + e.getMessage());
            return false;
        }
    }

    // Xóa hóa đơn
    public boolean XoaHoaDon(int maHD) throws ClassNotFoundException {
        String query1 = "DELETE FROM CHITIETHOADON WHERE MaHD = ?";
        String query2 = "DELETE FROM HOADON WHERE MaHD = ?";
        try {
            Connection connect = conn.DBConnect();
            // Xóa chi tiết hóa đơn trước
            PreparedStatement stmt1 = connect.prepareStatement(query1);
            stmt1.setInt(1, maHD);
            stmt1.executeUpdate();
            // Xóa hóa đơn
            PreparedStatement stmt2 = connect.prepareStatement(query2);
            stmt2.setInt(1, maHD);
            int rowsAffected = stmt2.executeUpdate();
            stmt1.close();
            stmt2.close();
            connect.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
}

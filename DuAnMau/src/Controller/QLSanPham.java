/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.NhanVien;
import Model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author asus
 */
public class QLSanPham {

    MyConnection conn;

    public QLSanPham() {
        conn = new MyConnection();
    }

    // Get All Sản Phẩm
    public List<SanPham> getAll() {
        List<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
        try {
            Connection connect = conn.DBConnect();
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
        } catch (Exception e) {
        }

        return listSP;
    }

    // Get Row Sản Phẩm
    public Object[] getRow(SanPham sp) {
        int Masp = sp.getMaNV();
        String Tensp = sp.getTenSP();
        float DonGia = sp.getDonGia();
        Date NgayNhap = sp.getNgayNhap();
        int Manv = sp.getMaNV();
        Object[] Obj = new Object[]{Masp, Tensp, DonGia, NgayNhap, Manv};
        return Obj;
    }

    // Thêm Dữ Liệu Nhân Viên 
    public int Themsp(SanPham sp) {
        String SQL = "INSERT INTO SanPham VALUES\n"
                + "( ? ,  ?   ,   ? ,  ?  ,    ?  )";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setInt(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            pstm.setFloat(3, sp.getDonGia());
            java.sql.Date sqlDate = new java.sql.Date(sp.getNgayNhap().getTime());
            pstm.setDate(4, sqlDate);
            pstm.setInt(5, sp.getMaNV());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Xoá Dữ Liệu Sản Phẩm
    public int Xoasp(int TheoMa) {
        String SQL = "DELETE FROM SanPham WHERE MaSP = ?";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setInt(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Sửa Dữ Liệu Sản Phẩm
    public int Suasp(SanPham sp, int TheoMa) {
        String SQL = "UPDATE SanPham SET MaSP = ? ,\n"
                + "                   TenSP =  ? ,\n"
                + "		      DonGia =  ?  ,\n"
                + "		      NgayNhap =  ? ,\n"
                + "		      MaNV =   ? \n"
                + "		      WHERE MaSP = ? ";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setInt(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            pstm.setFloat(3, sp.getDonGia());
            java.sql.Date sqlDate = new java.sql.Date(sp.getNgayNhap().getTime());
            pstm.setDate(4, sqlDate);
            pstm.setInt(5, sp.getMaNV());
            pstm.setInt(6, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua SP. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Tìm Kiếm Sản Phẩm Theo Mã Sản Phẩm
    public List<SanPham> TimKiem(int TheoMa) {
        List<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setDonGia(rs.getFloat(3));
                sp.setNgayNhap(rs.getDate(4));
                sp.setMaNV(rs.getInt(5));
                if (sp.getMaSP() == TheoMa) {
                    System.out.println("Tim Kiem. Connect");
                    listSP.add(sp);
                }
            }
        } catch (Exception e) {
        }

        return listSP;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class QLNV {

    MyConnection conn;

    public QLNV() {
        conn = new MyConnection();
    }

    // Get All Nhân Viên
    public List<NhanVien> getAll() {
        List<NhanVien> listNV = new ArrayList<NhanVien>();
        String query = "SELECT * FROM NHANVIEN";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTenDangNhap(rs.getString(2));
                nv.setEmail(rs.getString(3));
                nv.setMatKhau(rs.getString(4));
                nv.setVaiTro(rs.getBoolean(5));
                listNV.add(nv);
            }
        } catch (Exception e) {
        }
        return listNV;
    }

    // Get Row Nhân Viên
    public Object[] getRow(NhanVien nv) {
        int Manv = nv.getMaNV();
        String Tendn = nv.getTenDangNhap();
        String Email = nv.getEmail();
        String MatKhau = nv.getMatKhau();
        String vaiTroStr = nv.getVaiTro() ? "Admin" : "Nhân Viên"; // Chuyển đổi boolean -> text
        Object[] obj = new Object[]{Manv, Tendn, Email, MatKhau, vaiTroStr};
        return obj;
    }

    // Thêm Dữ Liệu Nhân Viên
    public int ThemNV(NhanVien nv) {
        String SQL = "INSERT INTO NhanVien VALUES \n"
                + "(   ?   ,   ?  ,    ?   ,   ?  ,    ?   )";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setInt(1, nv.getMaNV());
            pstm.setString(2, nv.getTenDangNhap());
            pstm.setString(3, nv.getEmail());
            pstm.setString(4, nv.getMatKhau());
            pstm.setBoolean(5, nv.getVaiTro());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Xoá Dữ Liệu Nhân Viên
    public int XoaNV(int TheoMa) {
        String SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
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

    // Sửa Dữ Liệu Nhan Viên
    public int SuaDL(NhanVien nv, int Theoma) {
        String sql = "UPDATE NhanVien SET MaNV = ?  ,\n"
                + "                    TenDangNhap = ?  ,\n"
                + "		       Email = ? ,\n"
                + "		       MatKhau = ? ,\n"
                + "		       VaiTro = ? \n"
                + "		       WHERE MaNV = ?";
        try {
            Connection con = conn.DBConnect();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nv.getMaNV());
            pstm.setString(2, nv.getTenDangNhap());
            pstm.setString(3, nv.getEmail());
            pstm.setString(4, nv.getMatKhau());
            pstm.setBoolean(5, nv.getVaiTro());
            pstm.setInt(6, Theoma);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Tìm Kiếm Theo Mã Nhân Viên
    public List<NhanVien> TimKiem(int TheoMa) {
        List<NhanVien> listNV = new ArrayList<NhanVien>();
        String query = "SELECT * FROM NHANVIEN";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTenDangNhap(rs.getString(2));
                nv.setEmail(rs.getString(3));
                nv.setMatKhau(rs.getString(4));
                nv.setVaiTro(rs.getBoolean(5));
                if (nv.getMaNV() == TheoMa) {
                    System.out.println("Tim Kiem. Connect");
                    listNV.add(nv);
                }
            }
        } catch (Exception e) {
        }

        return listNV;
    }
}

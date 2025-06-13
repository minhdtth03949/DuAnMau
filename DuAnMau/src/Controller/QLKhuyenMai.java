/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author asus
 */
public class QLKhuyenMai {
    
        MyConnection conn;

    public QLKhuyenMai() {
        conn = new MyConnection();
    }

    public List<KhuyenMai> getAll()  {
        List<KhuyenMai> listKM = new ArrayList<>();
        String query = "SELECT * FROM KhuyenMai";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getInt(1));
                km.setPhanTramKM(rs.getInt(2));
                km.setThoiGianBatDau(rs.getDate(3));
                km.setThoiGianKetThuc(rs.getDate(4));
                km.setMaNV(rs.getInt(5));
                listKM.add(km);
            } 
        } catch (Exception e) {
        }
        
        return listKM;
    }
    
    public Object[] getRow(KhuyenMai km){
        int Makm = km.getMaKM();
        int PhanTram = km.getPhanTramKM();
        java.util.Date ThoiGianBD = km.getThoiGianBatDau();
        java.util.Date ThoiGianKT = km.getThoiGianKetThuc();
        int Manv = km.getMaNV();
        Object[] obj = new Object[]{Makm , PhanTram , ThoiGianBD , ThoiGianKT , Manv};
        return obj;
    }
    
    // Thêm Dữ Liệu Khuyến Mãi
    public int Themkm(KhuyenMai km){
        String sql = "INSERT INTO KhuyenMai () VALUES\n" +
                      "(  ?  ,    ?   ,   ?   ,   ?  ,   ?   ),";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(sql);
            pstm.setInt(1, km.getMaKM());
            pstm.setInt(2, km.getPhanTramKM());
            java.sql.Date TGBD = new java.sql.Date(km.getThoiGianBatDau().getTime());
            pstm.setDate(3, TGBD);
            java.sql.Date TGKT = new java.sql.Date(km.getThoiGianKetThuc().getTime());
            pstm.setDate(4, TGKT);
            pstm.setInt(5, km.getMaNV());
            if (pstm.executeUpdate() > 0) {
                System.out.println("Them. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    // Xoá Dữ Liệu Khuyến Mãi
    public int Xoakm(int TheoMa){
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaKM =  ?  ;\n" +
                     "DELETE FROM KhuyenMai WHERE MaKM =  ?   ;";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(sql);
            pstm.setInt(1, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Xoa. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    // Sửa Dữ Liệu Khuyến Mãi
    public int Suakm(KhuyenMai km , int TheoMa){
        String sql = "";
        try {
            Connection connect = conn.DBConnect();
            PreparedStatement pstm = connect.prepareStatement(sql);
            pstm.setInt(1, km.getMaKM());
            pstm.setInt(2, km.getPhanTramKM());
            java.sql.Date TGBD = new java.sql.Date(km.getThoiGianBatDau().getTime());
            pstm.setDate(3, TGBD);
            java.sql.Date TGKT = new java.sql.Date(km.getThoiGianKetThuc().getTime());
            pstm.setDate(4, TGKT);
            pstm.setInt(5, km.getMaNV());
            pstm.setInt(6, TheoMa);
            if (pstm.executeUpdate() > 0) {
                System.out.println("Sua. Connect");
                return 1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    // Tìm Kiếm Khuyến Mãi Theo Thời Gian
    public List<KhuyenMai> TimKiem(java.sql.Date TGBD , java.sql.Date TGKT)  {
        List<KhuyenMai> listKM = new ArrayList<>();
        String query = "SELECT * FROM KhuyenMai";
        try {
            Connection connect = conn.DBConnect();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getInt(1));
                km.setPhanTramKM(rs.getInt(2));
                km.setThoiGianBatDau(rs.getDate(3));
                km.setThoiGianKetThuc(rs.getDate(4));
                km.setMaNV(rs.getInt(5));
                if (TGBD.equals(km.getThoiGianBatDau()) && TGKT.equals(km.getThoiGianKetThuc())) {
                    
                    listKM.add(km);
                }
            } 
        } catch (Exception e) {
        }
        
        return listKM;
    }
    
}

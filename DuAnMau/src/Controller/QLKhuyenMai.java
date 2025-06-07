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

    public List<KhuyenMai> getAll() throws SQLException, ClassNotFoundException {
        List<KhuyenMai> listKM = new ArrayList<>();
        String query = "SELECT * FROM KhuyenMai";
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
        return listKM;
    }
    
}

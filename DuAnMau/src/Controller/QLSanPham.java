/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author asus
 */
public class QLSanPham {
    
    private MyConnection conn;

    public QLSanPham() {
        conn = new MyConnection();
    }

    public List<SanPham> getAll() throws SQLException, ClassNotFoundException {
        List<SanPham> listSP = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
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
        return listSP;
    }
    
}

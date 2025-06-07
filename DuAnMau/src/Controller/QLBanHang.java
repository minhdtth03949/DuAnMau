/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import java.sql.*;
import Model.HoaDon;
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
    
    }
    
        


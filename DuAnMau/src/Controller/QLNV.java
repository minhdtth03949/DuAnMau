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

    private MyConnection conn;
    
    public QLNV() {
        conn = new MyConnection();
    }
    
    public List<NhanVien> getAll() throws SQLException, ClassNotFoundException{
        List<NhanVien> listNV = new ArrayList<NhanVien>();
        String query = "SELECT * FROM NHANVIEN";
        Connection connect = conn.DBConnect();
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt(1));
            nv.setTenDangNhap(rs.getString(2));
            nv.setEmail(rs.getString(3));
            nv.setMatKhau(rs.getString(4));
            nv.setVaiTro(rs.getBoolean(5));
            listNV.add(nv);
            
            
        }
        return listNV;
    }
}

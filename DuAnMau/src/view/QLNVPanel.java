/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Controller.QLNV;
import Model.NhanVien;
import java.awt.JobAttributes;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLNVPanel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    int index = -1;
    /**
     * Creates new form QLNV
     */
    QLNV qlnv = new QLNV();

    public QLNVPanel() {
        qlnv = new QLNV();
        initComponents();
        Initable();
        FillToTable();
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Nhân Viên", "Tên Nhân Viên", "Email", "Mật Khẩu", "Vai Trò"};
        TableModel.setColumnIdentifiers(cols);
        tbl_Bang.setModel(TableModel);
    }

    public void FillToTable() {
        TableModel.setRowCount(0);
        for (NhanVien nv : qlnv.getAll()) {
            TableModel.addRow(qlnv.getRow(nv));
        }
    }

    // Làm Mới
    public void LamMoi() {
        txt_NhapID.setText("");
        txt_NhapTen.setText("");
        txt_MatKhau.setText("");
        txt_TimKiem.setText("");
        txt_email.setText("");
        btg_Vai_Tro.clearSelection();
    }

    // Thêm Nhân Viên
    public void ThemDL() {
        int Ma;       // Tạo Biến Đã Bao Gồm Cả Check Mã Theo Kiểu Nếu Mà Trống Thì Thông Báo Để Nhập Lại.
        if (txt_NhapID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã.");
            return;
        }
        try {
            Ma = Integer.parseInt(txt_NhapID.getText()); // Thông Báo Cho Người Dùng Mã Nhân Viên Phải Là Số Nguyên.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên Phải Là Số Nguyên.");
            return;
        }

        String Ten = txt_NhapTen.getText(); // Check Tên Nhân Viên Đã Điền Đầy Đủ Chưa.
        if (txt_NhapTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Nhân Viên.");
            return;
        }
        String MatKhau = txt_MatKhau.getText(); // Check Mật Khẩu Có Để Trống Không.
        if (txt_MatKhau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mật Khẩu.");
            return;
        }
        String Email = txt_email.getText(); // Check Email Người Dùng Không Được Để Chống.
        if (txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Email.");
            return;
        }
        String VaiTro = rdo_Admin.isSelected() ? "Admin" : "Nhân Viên"; // Người Dùng Bắt Buộc Phải Chọn Một Trong Hai Là Nhân Viên Hoặc Là Admin.
        if ((!rdo_Admin.isSelected() && !rdo_nv.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Vai Trò.");
        }
        boolean vaitro; // Vì Vai Trò Lưu Ở Nhân Viên Là Biến Boolean Nên Thêm Dữ Liệu Phải Thay Đổi
        if (VaiTro.equalsIgnoreCase("Admin")) {
            vaitro = true; // Nếu Vai Trò Ở Dao Diện Chọn Là Admin Thì Vai Trò Sẽ Là True
        } else {
            vaitro = false; // Ngược Lại Dao Diện Là Nhân Viên Thì Vai Trf Sẽ Thành False
        }
        NhanVien nv = new NhanVien(Ma, Ten, Email, MatKhau, vaitro);
        int Result = qlnv.ThemNV(nv);
        if (Result == 1) { // Nếu Result Bằng 1 Thì Sẽ Thêm Dược Dữ Liệu Nhân Viên Thành Công.
            JOptionPane.showMessageDialog(this, "Thêm DL Thành  Công.");
        } else { // Cong Không Thì Sẽ Thông Báo Lỗi Cho Người Dùng Kiểm Tra Lại
            JOptionPane.showMessageDialog(this, "Có Lỗi Sảy Ra.");
        }
    }

    // Xoá Dữ Liệu Nhân Viên
    public void XoaDL() {
        index = tbl_Bang.getSelectedRow(); // Lấy Mã Theo Vị Trí Trong Bảng
        if (index >= 0) {
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Nhân Viên Này Không ?", "Xác Nhận Xoá: ", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) { // Khi Người Dùng Chọn Xoá Thì Xẽ Được Thông Báo Có Sác Nhận Hay Không
                // Nếu Xác Nhận Thì Ấn Xoá.
                NhanVien nv = qlnv.getAll().get(index);
                int Ma = nv.getMaNV(); // Lấy Mã Để Xoá.
                int Result = qlnv.XoaNV(Ma);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Nhân Viên Thành Công.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nhân Viên Để Xoá.");
        }
    }

    // Sửa Dữ Liệu Liên Quan Đến Thông Tin Của Nhân Viên.
    public void SuaDL() {
        index = tbl_Bang.getSelectedRow();
        if (index >= 0) {
            int MaCu = qlnv.getAll().get(index).getMaNV(); // Lấy Dữ Liệu Mã Cũ Của Nhân Viên 

            int MaMoi;// Nếu Muốn Đổi Mã Còn Không Thì Vẫn Lẫy Mã Cũ
            if (txt_NhapID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã.");
                return;
            }
            try {
                MaMoi = Integer.parseInt(txt_NhapID.getText()); // Thông Báo Cho Người Dùng Mã Nhân Viên Phải Là Số Nguyên.
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Phải Là Số Nguyên.");
                return;
            }
            String Tennv = txt_NhapTen.getText(); // Thông Báo Cho Người Dùng Tên Nhân Viên Khi Sửa Không Được Để Trống.
            if (txt_NhapTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Nhân Viên.");
                return;
            }
            String Email = txt_email.getText(); // Thông Báo Cho Người Dùng Khi Sửa Email Không Được Để Trống.
            if (txt_email.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Email.");
                return;
            }
            String MatKhau = txt_MatKhau.getText(); // Thông Báo Mật Khẩu Nếu Muốn Đổi Thì Không Được Để Trống
            if (txt_MatKhau.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mật Khẩu.");
                return;
            }
            String VaiTro = rdo_Admin.isSelected() ? "Admin" : "Nhân Viên"; // Khi Muốn Đổi Thì Phải Chọn Một Trong Hai Admin Hoặc Nhân Viên
            if ((!rdo_Admin.isSelected() && !rdo_nv.isSelected())) { // Không Bao H Được Để Trống Không Chọn
                JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Vai Trò.");
            }
            boolean vaitro;
            if (VaiTro.equalsIgnoreCase("Admin")) {
                vaitro = true;
            } else {
                vaitro = false;
            }
            NhanVien nv = new NhanVien(MaCu, Tennv, Email, MatKhau, vaitro);

            int Result = qlnv.SuaDL(nv, MaCu);
            if (Result == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Dữ Liệu Nhân Viên Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Nhâ Viên Để Sửa");
            }
        }
    }

    // Showdetail Giúp Xem Chi Tiết Thông Tin Của Một Nhân Viên Chỉ Định Trong Bảng.
    public void Showdetail() {
        index = tbl_Bang.getSelectedRow();
        if (index >= 0) {
            NhanVien nv = qlnv.getAll().get(index);

            txt_NhapID.setText(String.valueOf(nv.getMaNV()));
            txt_NhapTen.setText(nv.getTenDangNhap());
            txt_email.setText(nv.getEmail());
            txt_MatKhau.setText(nv.getMatKhau());
            boolean vaitro = nv.getVaiTro();
            if (vaitro == true) {
                rdo_Admin.setSelected(true);
            } else {
                rdo_nv.setSelected(true);
            }
        }
    }

    public void TimKiem() { // Tìm Kiếm Theo Mã Nhân Viên
        TableModel.setRowCount(0); // Set Bảng Về Vị Trí Trống
        int TheoMa = Integer.valueOf(txt_TimKiem.getText());
        for (NhanVien nv : qlnv.TimKiem(TheoMa)) {
            TableModel.addRow(qlnv.getRow(nv));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btg_Vai_Tro = new javax.swing.ButtonGroup();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        btn_them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Bang = new javax.swing.JTable();
        btn_Show = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_NhapTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_NhapID = new javax.swing.JTextField();
        rdo_Admin = new javax.swing.JRadioButton();
        rdo_nv = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jFormattedTextField1.setText("jFormattedTextField1");

        btn_them.setBackground(new java.awt.Color(204, 255, 204));
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(204, 255, 204));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/edit.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setBackground(new java.awt.Color(204, 255, 204));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btn_Xoa.setText("Xoá");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        tbl_Bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Ten", "Email", "MatKhau", "VaiTro"
            }
        ));
        tbl_Bang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Bang);

        btn_Show.setBackground(new java.awt.Color(204, 255, 204));
        btn_Show.setText("Show");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Nhân Viên"));

        txt_NhapTen.setBackground(new java.awt.Color(255, 204, 204));

        jLabel5.setText("Chọn Vai Trò:");

        jLabel2.setText("Nhập Tên:");

        jLabel3.setText("Nhập Email: ");

        txt_email.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setText("Nhập Id:");

        txt_MatKhau.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setText("Nhập Mật khẩu:");

        txt_NhapID.setBackground(new java.awt.Color(255, 204, 204));

        btg_Vai_Tro.add(rdo_Admin);
        rdo_Admin.setText("Admin");

        btg_Vai_Tro.add(rdo_nv);
        rdo_nv.setText("Nhân Viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_NhapTen, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(rdo_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(rdo_nv))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(txt_NhapID, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_NhapID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_NhapTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdo_Admin)
                    .addComponent(rdo_nv))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        jLabel6.setText("Tìm kiếm");

        txt_TimKiem.setBackground(new java.awt.Color(255, 204, 204));

        btn_TimKiem.setBackground(new java.awt.Color(204, 255, 204));
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        ThemDL();
        LamMoi();
        FillToTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        SuaDL();
        LamMoi();
        FillToTable();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        XoaDL();
        LamMoi();
        FillToTable();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed

        FillToTable();
    }//GEN-LAST:event_btn_ShowActionPerformed

    private void tbl_BangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BangMouseClicked
        // TODO add your handling code here:
        Showdetail();
        FillToTable();
    }//GEN-LAST:event_tbl_BangMouseClicked

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        TimKiem();
    }//GEN-LAST:event_btn_TimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_Vai_Tro;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_them;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdo_Admin;
    private javax.swing.JRadioButton rdo_nv;
    private javax.swing.JTable tbl_Bang;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_NhapID;
    private javax.swing.JTextField txt_NhapTen;
    private javax.swing.JTextField txt_TimKiem;
    private javax.swing.JTextField txt_email;
    // End of variables declaration//GEN-END:variables
}

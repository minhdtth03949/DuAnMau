/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.table.DefaultTableModel;
import Model.SanPham;
import Controller.QLSanPham;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QLSPPanel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    int index = -1;
    QLSanPham qlsp = new QLSanPham();

    /**
     * Creates new form QLSPPanel
     */
    public QLSPPanel() {
        initComponents();
        Initable();
        FilltoTable();
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá", "Ngày Nhập", "Mã Nhân Viên"};
        TableModel.setColumnIdentifiers(cols);
        tbl_QLSP.setModel(TableModel);
    }

    public void FilltoTable() {
        TableModel.setRowCount(0);
        for (SanPham sp : qlsp.getAll()) {
            TableModel.addRow(qlsp.getRow(sp));
        }
    }

    // Làm Mới Sản Phẩm
    public void LamMoi() {
        txt_NhapMasp.setText("");
        txt_GiaTien.setText("");
        txt_NgayNhap.setText("");
        txt_NhapTen.setText("");
        txt_Manv.setText("");
        txt_TimKiem.setText("");
        FilltoTable();
    }

    // Thêm Dữ Liệu Sản Phẩm
    public void ThemDL() {
        int Masp;
        if (txt_NhapMasp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã Sản Phẩm.");
            return;
        }
        try {
            Masp = Integer.valueOf(txt_NhapMasp.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Phải Là Số.");
            return;
        }

        String Tensp = txt_NhapTen.getText();
        if (txt_NhapTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sản Phẩm.");
            return;
        }

        float DonGia = Float.valueOf(txt_GiaTien.getText());
        if (txt_GiaTien.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đơn Giá Cho Sản Phẩm.");
            return;
        }

        java.sql.Date sqlDate = null;
        try {
            String ngayNhapStr = txt_NgayNhap.getText().trim();
            if (ngayNhapStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Ngày Nhập Sản Phẩm.");
                return;
            }

            // Dùng định dạng chuẩn ISO 8601
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Không cho tự động sửa lỗi sai
            java.util.Date parsedDate = sdf.parse(ngayNhapStr);
            sqlDate = new java.sql.Date(parsedDate.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
            return;
        }

        //Mã Nhân Viên
        int Manv;
        if (txt_Manv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã Nhân Viên Thêm Sản Phẩm.");
            return;
        }
        try {
            Manv = Integer.valueOf(txt_Manv.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên Phải Là Số Nguyên.");
            return;
        }

        SanPham sp = new SanPham(Masp, Tensp, DonGia, sqlDate, Manv);

        int Result = qlsp.Themsp(sp);
        if (Result == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Sản Phẩm Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Có Lỗi Sảy Ra.");
        }
    }

    // Xoá Dữ Liệu Sản Phẩm
    public void XoaDL() {
        index = tbl_QLSP.getSelectedRow();
        if (index >= 0) {
            int Choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xoá Sản Phẩm Này Hay Không ?", "Xác Nhận Xoá Sản Phẩm.", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                SanPham sp = qlsp.getAll().get(index);
                int Masp = sp.getMaSP();
                int Result = qlsp.Xoasp(Masp);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Dữ Liệu Sản Phẩm Thành Công.");

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Để Xoá.");
        }
    }

    // Sửa Dữ Liệu Sản Phẩm
    public void SuaDL() {
        index = tbl_QLSP.getSelectedRow();
        if (index >= 0) {
            // Mã Sản Phẩm Cũ
            int Masp_Cu = qlsp.getAll().get(index).getMaSP();

            // Mã Sản Phẩm Mới
            int Masp_Moi;
            if (txt_NhapMasp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã Sản Phẩm.");
                return;
            }
            try {
                Masp_Moi = Integer.valueOf(txt_NhapMasp.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Phải Là Số Nguyên.");
                return;
            }

            String Tensp = txt_NhapTen.getText();
            if (txt_NhapTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sản Phẩm.");
                return;
            }

            float DonGia = Float.valueOf(txt_GiaTien.getText());
            if (txt_GiaTien.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đơn Giá Cho Sản Phẩm.");
                return;
            }

            java.sql.Date sqlDate = null;
            try {
                String ngayNhapStr = txt_NgayNhap.getText().trim();
                if (ngayNhapStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Ngày Nhập Sản Phẩm.");
                    return;
                }

                // Dùng định dạng chuẩn ISO 8601
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Không cho tự động sửa lỗi sai
                java.util.Date parsedDate = sdf.parse(ngayNhapStr);
                sqlDate = new java.sql.Date(parsedDate.getTime());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
                return;
            }

            //Mã Nhân Viên
            int Manv;
            if (txt_Manv.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Mã Nhân Viên Thêm Sản Phẩm.");
                return;
            }
            try {
                Manv = Integer.valueOf(txt_Manv.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Phải Là Số Nguyên.");
                return;
            }

            SanPham sp = new SanPham(Masp_Moi, Tensp, DonGia, sqlDate, Manv);

            int Resul = qlsp.Suasp(sp, Masp_Cu);

            if (Resul == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Dữ Liệu Sản Phẩm Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Để Sửa.");
            }
        }
    }

    // Show Detail Sản Phẩm
    public void ShowDetail() {
        index = tbl_QLSP.getSelectedRow();
        if (index >= 0) {
            SanPham sp = qlsp.getAll().get(index);

            txt_NhapMasp.setText(String.valueOf(sp.getMaSP()));
            txt_NhapTen.setText(sp.getTenSP());
            txt_GiaTien.setText(String.valueOf(sp.getDonGia()));
            txt_Manv.setText(String.valueOf(sp.getMaNV()));
            txt_NgayNhap.setText(String.valueOf(sp.getNgayNhap()));

        }
    }

    // Tìm Kiếm Sản Phẩm Theo Mã
    public void TimKiem() {
        TableModel.setRowCount(0);
        int TheoMa = Integer.valueOf(txt_TimKiem.getText());
        for (SanPham sp : qlsp.TimKiem(TheoMa)) {
            TableModel.addRow(qlsp.getRow(sp));
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

        btn_ThemDL = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_XoaDL = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_NgayNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Manv = new javax.swing.JTextField();
        txt_NhapTen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_NhapMasp = new javax.swing.JTextField();
        txt_GiaTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_LamMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_QLSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_TimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_TimKiem = new javax.swing.JButton();

        btn_ThemDL.setBackground(new java.awt.Color(204, 255, 255));
        btn_ThemDL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btn_ThemDL.setText("Thêm ");
        btn_ThemDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDLActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(204, 255, 255));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/edit.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_XoaDL.setBackground(new java.awt.Color(204, 255, 255));
        btn_XoaDL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btn_XoaDL.setText("Xoá");
        btn_XoaDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaDLActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Quản Lý Sản Phẩm"));

        txt_NgayNhap.setBackground(new java.awt.Color(255, 255, 204));

        jLabel5.setText("Mã Nhân Viên:");

        txt_Manv.setBackground(new java.awt.Color(255, 255, 204));

        txt_NhapTen.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("Tên Sản Phẩm:");

        jLabel2.setText("Mã Sản Phẩm:");

        jLabel3.setText("Giá Tiền:");

        txt_NhapMasp.setBackground(new java.awt.Color(255, 255, 204));

        txt_GiaTien.setBackground(new java.awt.Color(255, 255, 204));

        jLabel4.setText("Ngày Nhập:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Manv, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NhapTen, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NhapMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_GiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_NhapTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_NhapMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_GiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btn_LamMoi.setBackground(new java.awt.Color(204, 255, 255));
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        tbl_QLSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_QLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QLSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_QLSP);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm Kiếm Sản Phẩm "));

        txt_TimKiem.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setText("Mã Sản Phẩm:");

        btn_TimKiem.setBackground(new java.awt.Color(204, 255, 255));
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
                .addGap(19, 19, 19)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_ThemDL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_LamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_XoaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ThemDL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_XoaDL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemDLActionPerformed

        ThemDL();
        FilltoTable();
    }//GEN-LAST:event_btn_ThemDLActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
        FilltoTable();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        TimKiem();
        
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void tbl_QLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QLSPMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tbl_QLSPMouseClicked

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        SuaDL();
        FilltoTable();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaDLActionPerformed
        // TODO add your handling code here:
        XoaDL();
        FilltoTable();
    }//GEN-LAST:event_btn_XoaDLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_ThemDL;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_XoaDL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_QLSP;
    private javax.swing.JTextField txt_GiaTien;
    private javax.swing.JTextField txt_Manv;
    private javax.swing.JTextField txt_NgayNhap;
    private javax.swing.JTextField txt_NhapMasp;
    private javax.swing.JTextField txt_NhapTen;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}

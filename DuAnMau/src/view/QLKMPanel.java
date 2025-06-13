/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.table.DefaultTableModel;
import Controller.QLKhuyenMai;
import Model.KhuyenMai;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class QLKMPanel extends javax.swing.JPanel {

    DefaultTableModel TableModel;
    int index = -1;
    QLKhuyenMai qlkm = new QLKhuyenMai();

    /**
     * Creates new form QLKMPanel
     */
    public QLKMPanel() {
        initComponents();
        Initable();
        FilltoTable();
    }

    public void Initable() {
        TableModel = new DefaultTableModel();
        String[] cols = {"Mã Khuyến Mãi", "Phần Trăm KM", "Thời Gian BD", "Thời Gian KT", "Mã Nhân Viên"};
        TableModel.setColumnIdentifiers(cols);
        tbl_QLKM.setModel(TableModel);
    }

    public void FilltoTable() {
        TableModel.setRowCount(0);
        for (KhuyenMai km : qlkm.getAll()) {
            TableModel.addRow(qlkm.getRow(km));
        }
    }

    // Làm Mới Dữ Liệu Khuyến Mãi
    public void LamMoi() {
        txt_Makm.setText("");
        txt_Manv.setText("");
        txt_PhanTram.setText("");
        txt_TGBD.setText("");
        txt_TGKT.setText("");
        txt_TKBD.setText("");
        txt_TKKT.setText("");

        FilltoTable();
    }

    // Thêm Dữ Liệu Khuyến Mãi
    public void ThemDL() {
        int Makm;
        if (txt_Makm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Khuyến Mãi Không Được Để Trống.");
            return;
        }
        try {
            Makm = Integer.valueOf(txt_Makm.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Khuyến Mãi Là Phải Là Số Nguyên.");
            return;
        }

        int PhanTram;
        if (txt_PhanTram.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phần Trăm Khuyến Mãi Không Được Để Trống.");
            return;
        }
        try {
            PhanTram = Integer.valueOf(txt_PhanTram.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Phần Trăm Khuyến Mãi Là Phải Là Số Nguyên.");
            return;
        }

        java.sql.Date TGBD = null;
        try {
            String TGBatDau = txt_TGBD.getText().trim();
            if (TGBatDau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Bắt Đầu Khuyến Mãi.");
                return;
            }

            // Dùng định dạng chuẩn ISO 8601
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Không cho tự động sửa lỗi sai
            java.util.Date parsedDate = sdf.parse(TGBatDau);
            TGBD = new java.sql.Date(parsedDate.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
            return;
        }

        java.sql.Date TGKT = null;
        try {
            String TGKetThuc = txt_TGKT.getText().trim();
            if (TGKetThuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Kết Thúc Khuyến Mãi.");
                return;
            }

            // Dùng định dạng chuẩn ISO 8601
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Không cho tự động sửa lỗi sai
            java.util.Date parsedDate = sdf.parse(TGKetThuc);
            TGKT = new java.sql.Date(parsedDate.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
            return;
        }

        int Manv;
        if (txt_Makm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên Không Được Để Trống.");
            return;
        }
        try {
            Manv = Integer.valueOf(txt_Manv.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên Là Phải Là Số Nguyên.");
            return;
        }

        KhuyenMai km = new KhuyenMai(Makm, PhanTram, TGBD, TGKT, Manv);
        int Resut = qlkm.Themkm(km);
        if (Resut == 1) {
            JOptionPane.showMessageDialog(this, "Thêm Dữ Liệu Khuyến Mãi Thành Công.");
        } else {
            JOptionPane.showMessageDialog(this, "Có Lỗi Sảy Ra.");
        }
    }

    // Xoá Dữ Liệu Khuyến Mãi
    public void XoaDL() {
        index = tbl_QLKM.getSelectedRow();
        if (index >= 0) {
            int Choice = JOptionPane.showConfirmDialog(this, "Xác Nhận Xoá Khuyến Mãi ?", "Xác Nhận Xoá.", JOptionPane.YES_NO_OPTION);
            if (Choice == JOptionPane.YES_OPTION) {
                int TheoMa = qlkm.getAll().get(index).getMaKM();
                int Result = qlkm.Xoakm(TheoMa);
                if (Result == 1) {
                    JOptionPane.showMessageDialog(this, "Xoá Dữ Liệu Khuyến Mãi Thành Công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá Thất Bại.");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Khuyến Mãi Để Xoá.");
        }
    }

    // Sửa Dữ Liệu Khuyến Mãi
    public void SuaDL() {
        index = tbl_QLKM.getSelectedRow();
        if (index >= 0) {
            int MaCu = qlkm.getAll().get(index).getMaKM();

            int Makm;
            if (txt_Makm.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã Khuyến Mãi Không Được Để Trống.");
                return;
            }
            try {
                Makm = Integer.valueOf(txt_Makm.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Khuyến Mãi Là Phải Là Số Nguyên.");
                return;
            }

            int PhanTram;
            if (txt_PhanTram.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phần Trăm Khuyến Mãi Không Được Để Trống.");
                return;
            }
            try {
                PhanTram = Integer.valueOf(txt_PhanTram.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Phần Trăm Khuyến Mãi Là Phải Là Số Nguyên.");
                return;
            }

            java.sql.Date TGBD = null;
            try {
                String TGBatDau = txt_TGBD.getText().trim();
                if (TGBatDau.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Bắt Đầu Khuyến Mãi.");
                    return;
                }

                // Dùng định dạng chuẩn ISO 8601
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Không cho tự động sửa lỗi sai
                java.util.Date parsedDate = sdf.parse(TGBatDau);
                TGBD = new java.sql.Date(parsedDate.getTime());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
                return;
            }

            java.sql.Date TGKT = null;
            try {
                String TGKetThuc = txt_TGKT.getText().trim();
                if (TGKetThuc.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Kết Thúc Khuyến Mãi.");
                    return;
                }

                // Dùng định dạng chuẩn ISO 8601
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Không cho tự động sửa lỗi sai
                java.util.Date parsedDate = sdf.parse(TGKetThuc);
                TGKT = new java.sql.Date(parsedDate.getTime());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
                return;
            }

            int Manv;
            if (txt_Makm.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Không Được Để Trống.");
                return;
            }
            try {
                Manv = Integer.valueOf(txt_Manv.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Là Phải Là Số Nguyên.");
                return;
            }

            KhuyenMai km = new KhuyenMai(Makm, PhanTram, TGBD, TGKT, Manv);
            int Result = qlkm.Suakm(km, Makm);
            if (Result == 1) {
                JOptionPane.showMessageDialog(this, "Sửa Dữ Liệu Khuyến Mãi Thành Công.");
            } else {
                JOptionPane.showMessageDialog(this, "Có Lỗi Sảy Ra.");
            }

        }
    }

    // Showdetail Dữ Liệu Khuyến Mãi
    public void Showdetail() {
        index = tbl_QLKM.getSelectedRow();
        if (index >= 0) {
            KhuyenMai km = qlkm.getAll().get(index);
            txt_Makm.setText(String.valueOf(km.getMaKM()));
            txt_PhanTram.setText(String.valueOf(km.getPhanTramKM()));
            txt_TGBD.setText(String.valueOf(km.getThoiGianBatDau()));
            txt_TGKT.setText(String.valueOf(km.getThoiGianKetThuc()));
            txt_Manv.setText(String.valueOf(km.getMaNV()));
        }
    }

    // Tìm Kiếm Theo Thời Gian Tìm Kiếm
    public void TimKiem() {
        TableModel.setRowCount(0);
        java.sql.Date TGBD = null;
        try {
            String TGBatDau = txt_TKBD.getText().trim();
            if (TGBatDau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Bắt Đầu Khuyến Mãi.");
                return;
            }

            // Dùng định dạng chuẩn ISO 8601
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Không cho tự động sửa lỗi sai
            java.util.Date parsedDate = sdf.parse(TGBatDau);
            TGBD = new java.sql.Date(parsedDate.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
            return;
        }

        java.sql.Date TGKT = null;
        try {
            String TGKetThuc = txt_TKKT.getText().trim();
            if (TGKetThuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Thời Gian Kết Thúc Khuyến Mãi.");
                return;
            }

            // Dùng định dạng chuẩn ISO 8601
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Không cho tự động sửa lỗi sai
            java.util.Date parsedDate = sdf.parse(TGKetThuc);
            TGKT = new java.sql.Date(parsedDate.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng: yyyy-MM-dd (ví dụ: 2025-06-11)");
            return;
        }
        for (KhuyenMai km : qlkm.TimKiem(TGBD, TGKT)) {
            TableModel.addRow(qlkm.getRow(km));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_Makm = new javax.swing.JTextField();
        txt_PhanTram = new javax.swing.JTextField();
        txt_TGBD = new javax.swing.JTextField();
        txt_TGKT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Manv = new javax.swing.JTextField();
        btn_LamMoi = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_TKBD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_TKKT = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_QLKM = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Khuyến Mãi"));

        jLabel1.setText("Mã Khuyến Mãi:");

        jLabel2.setText("Phần Trăm Khuyến Mãi:");

        jLabel3.setText("Ngày Bắt Đầu:");

        jLabel4.setText("Ngày Kết Thúc:");

        txt_Makm.setBackground(new java.awt.Color(204, 255, 255));

        txt_PhanTram.setBackground(new java.awt.Color(204, 255, 255));

        txt_TGBD.setBackground(new java.awt.Color(204, 255, 255));

        txt_TGKT.setBackground(new java.awt.Color(204, 255, 255));

        jLabel5.setText("Mã Nhân Viên:");

        txt_Manv.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_Makm)
                    .addComponent(txt_PhanTram)
                    .addComponent(txt_TGBD)
                    .addComponent(txt_TGKT)
                    .addComponent(txt_Manv, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Makm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_PhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_TGBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_TGKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_Manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        btn_LamMoi.setBackground(new java.awt.Color(255, 255, 204));
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(255, 255, 204));
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setBackground(new java.awt.Color(255, 255, 204));
        btn_Xoa.setText("Xoá");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Them.setBackground(new java.awt.Color(255, 255, 204));
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm Kiếm Khuyến Mãi Theo Thời Gian"));

        jLabel6.setText("Thời Gian Bắt Đầu:");

        txt_TKBD.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setText("Thời Gian Kết Thúc:");

        txt_TKKT.setBackground(new java.awt.Color(204, 255, 255));

        btn_TimKiem.setBackground(new java.awt.Color(255, 255, 204));
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
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TKBD)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_TKKT))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_TKBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_TKKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tbl_QLKM.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_QLKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QLKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_QLKM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        ThemDL();
        LamMoi();
        FilltoTable();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        SuaDL();
        LamMoi();
        FilltoTable();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        XoaDL();
        LamMoi();
        FilltoTable();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void tbl_QLKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QLKMMouseClicked
        // TODO add your handling code here:
        Showdetail();
    }//GEN-LAST:event_tbl_QLKMMouseClicked

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        TimKiem();
    }//GEN-LAST:event_btn_TimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_QLKM;
    private javax.swing.JTextField txt_Makm;
    private javax.swing.JTextField txt_Manv;
    private javax.swing.JTextField txt_PhanTram;
    private javax.swing.JTextField txt_TGBD;
    private javax.swing.JTextField txt_TGKT;
    private javax.swing.JTextField txt_TKBD;
    private javax.swing.JTextField txt_TKKT;
    // End of variables declaration//GEN-END:variables
}

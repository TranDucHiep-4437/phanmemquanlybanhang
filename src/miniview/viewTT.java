package miniview;

import controller.ThanhToanListener;
import dao.KhachHangDAO;
import dao.LichSuGiaoDichDAO;
import dao.SanPhamDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.LichSuGiaoDich;
import model.SanPham;

public class viewTT {

    private JTextField nid, sl, tienkhach;
    private JTextField sdtkh, tenkh;
    private DefaultTableModel bspgh;
    private JLabel tongtien;
    private JButton tgh, tinhtien, thanhtoan;
    private double tongtient = 0;
    private double tientralai, tongtiennhap;
    private double tienlai = 0;
    private JButton themkh;

    private Time tggiaodich = new Time(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    SanPhamDAO spdao = new SanPhamDAO();
    LichSuGiaoDichDAO ls = new LichSuGiaoDichDAO();

    public JPanel TTPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setPreferredSize(new Dimension(1000, 700));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        topPanel.add(new JLabel("Nhập mã sản phẩm: "));
        nid = new JTextField(10);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(nid);

        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(new JLabel("Số lượng: "));
        sl = new JTextField("0", 10);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(sl);

        ActionListener ac = new ThanhToanListener(this);

        tgh = new JButton("Thêm vào giỏ hàng");
        tgh.addActionListener(ac);
        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(tgh);

        JButton dlgh = new JButton("Đặt lại giỏ hàng");
        dlgh.addActionListener(ac);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(dlgh);

        panel.add(topPanel, BorderLayout.NORTH);

        String[] gtbgh = {"ID", "Tên", "Số lượng", "Giá", "Thành tiền", "Giá nhập"};
        bspgh = new DefaultTableModel(gtbgh, 0);
        JTable bgh = new JTable(bspgh);
        bgh.setShowGrid(true);
        bgh.setGridColor(Color.BLACK);
        JScrollPane scrollPaneSale = new JScrollPane(bgh);
        scrollPaneSale.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));

        bgh.getColumnModel().getColumn(5).setMinWidth(0);
        bgh.getColumnModel().getColumn(5).setMaxWidth(0);
        bgh.getColumnModel().getColumn(5).setWidth(0);

        panel.add(scrollPaneSale, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(10, 10));

        JPanel paymentPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        paymentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        paymentPanel.add(new JLabel("Tổng tiền:"));
        tongtien = new JLabel("0.0");
        tongtien.setFont(new Font("Arial", Font.BOLD, 14));
        paymentPanel.add(tongtien);
        paymentPanel.add(new JLabel(""));

        paymentPanel.add(new JLabel("Tiền khách đưa:"));
        tienkhach = new JTextField();
        paymentPanel.add(tienkhach);
        tinhtien = new JButton("Tính tiền trả lại");
        tinhtien.addActionListener(ac);
        paymentPanel.add(tinhtien);

        bottomPanel.add(paymentPanel, BorderLayout.CENTER);

        thanhtoan = new JButton("THANH TOÁN");
        thanhtoan.setFont(new Font("Arial", Font.BOLD, 16));
        thanhtoan.setPreferredSize(new Dimension(200, 40));
        thanhtoan.addActionListener(ac);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(thanhtoan);

        bottomPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    int soluong = 0;

    public void themgiohang() {
        try {
            int masp = Integer.parseInt(nid.getText());
            soluong = Integer.parseInt(sl.getText());

            SanPham sp = spdao.timkiem(new SanPham(masp));

            if (sp != null) {
                if (soluong <= 0) {
                    soluong = 1;
                }
                if (sp.getSoluong() < soluong) {
                    JOptionPane.showMessageDialog(null, "Không đủ sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean daco = false;
                for (int i = 0; i < bspgh.getRowCount(); i++) {
                    int mspigh = (int) bspgh.getValueAt(i, 0);
                    if (masp == mspigh) {
                        int slm = (int) bspgh.getValueAt(i, 2) + soluong;
                        if (slm > sp.getSoluong()) {
                            JOptionPane.showMessageDialog(null, "Không đủ sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        bspgh.setValueAt(slm, i, 2);

                        double ttm = slm * (double) bspgh.getValueAt(i, 3);
                        bspgh.setValueAt(ttm, i, 4);
                        daco = true;
                        break;
                    }
                }
                if (!daco) {
                    SanPham spmua = new SanPham(sp.getId(), sp.getTensanpham(), sp.getGianhap(), sp.getGiaban(), soluong);
                    double ttigh = spmua.getSoluong() * spmua.getGiaban();
                    Object[] rowData = {
                        spmua.getId(),
                        spmua.getTensanpham(),
                        spmua.getSoluong(),
                        spmua.getGiaban(),
                        ttigh,
                        spmua.getGianhap()
                    };
                    bspgh.addRow(rowData);
                }
                tinhtongtien();

            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nhập sai!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inhoadon() {
        if (bspgh.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm trong giỏ hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int chon = JOptionPane.showConfirmDialog(null, "Có muốn thêm thông tin khách hàng không", "Thêm thông tin khách hàng", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.NO_OPTION) {
                LocalDate date = LocalDate.now();
                String ngaygiaodich = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                StringBuilder hoadon = new StringBuilder();
                hoadon.append("NGÀY GD: ").append(ngaygiaodich).append("\n");
                hoadon.append("THỜI GIAN: ").append(tggiaodich).append("\n");
                hoadon.append("-------------------------------------------------\n");
                hoadon.append(String.format("%-15s %-10s %-10s\n", "SẢN PHẨM ", "SỐ LƯỢNG", "THÀNH TIỀN"));

                for (int i = 0; i < bspgh.getRowCount(); i++) {
                    int msp = (int) bspgh.getValueAt(i, 0);
                    String tenSanPham = (String) bspgh.getValueAt(i, 1);
                    int soLuong = (int) bspgh.getValueAt(i, 2);
                    double thanhTien = (double) bspgh.getValueAt(i, 4);
                    SanPham sp1 = new SanPham(msp, soLuong);
                    spdao.giamsoluong(sp1);

                    hoadon.append(String.format("%-15s %-10d %-10.2f\n", tenSanPham, soLuong, thanhTien));
                }

                hoadon.append("-------------------------------------------------\n");
                hoadon.append("TỔNG TIỀN: ").append(tinhtongtien()).append(" VND\n");
                LichSuGiaoDich ls1 = new LichSuGiaoDich(ngaygiaodich, sdf.format(tggiaodich), tinhtongtien(), tinhtonglai());

                ls.themlichsu(ls1);

                JOptionPane.showMessageDialog(null, hoadon.toString(), "HÓA ĐƠN", JOptionPane.INFORMATION_MESSAGE);
                bspgh.setRowCount(0);
                tongtien.setText("0");
            }
            if (chon == JOptionPane.YES_OPTION) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setLocation(400, 300);
                JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

                inputPanel.add(new JLabel("Số điện thoại:"));
                sdtkh = new JTextField();
                inputPanel.add(sdtkh);

                inputPanel.add(new JLabel("Tên khách hàng:"));
                tenkh = new JTextField();
                inputPanel.add(tenkh);
                ActionListener ac = new ThanhToanListener(this);
                themkh = new JButton("Thêm");
                themkh.addActionListener(ac);
                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Nhập thông tin khách hàng", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    nhapttkh();
                } else {
                    LocalDate date = LocalDate.now();
                    String ngaygiaodich = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    StringBuilder hoadon = new StringBuilder();
                    hoadon.append("NGÀY GIAO DỊCH: ").append(ngaygiaodich).append("\n");
                    hoadon.append("THỜI GIAN: ").append(tggiaodich).append("\n");
                    hoadon.append("-------------------------------------------------\n");
                    hoadon.append(String.format("%-15s %-10s %-10s\n", "SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"));

                    for (int i = 0; i < bspgh.getRowCount(); i++) {
                        int msp = (int) bspgh.getValueAt(i, 0);
                        String tenSanPham = (String) bspgh.getValueAt(i, 1);
                        int soLuong = (int) bspgh.getValueAt(i, 2);
                        double thanhTien = (double) bspgh.getValueAt(i, 4);
                        SanPham sp1 = new SanPham(msp, soLuong);
                        spdao.giamsoluong(sp1);

                        hoadon.append(String.format("%-15s %-10d %-10.2f\n", tenSanPham, soLuong, thanhTien));
                    }

                    hoadon.append("-------------------------------------------------\n");
                    hoadon.append("TỔNG TIỀN: ").append(tinhtongtien()).append(" VND\n");
                    LichSuGiaoDich ls1 = new LichSuGiaoDich(ngaygiaodich, sdf.format(tggiaodich), tinhtongtien(), tinhtonglai());

                    ls.themlichsu(ls1);

                    JOptionPane.showMessageDialog(null, hoadon.toString(), "HÓA ĐƠN", JOptionPane.INFORMATION_MESSAGE);
                    bspgh.setRowCount(0);
                    tongtien.setText("0");
                }

            }
        }

    }

    public void nhapttkh() {
        KhachHangDAO khdao = new KhachHangDAO();
        String sdt = sdtkh.getText();
        String ten = tenkh.getText();
        if (ten.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Tên không được chỉ chứa số.");
            return;
        }
        KhachHang kh1 = new KhachHang(sdt, ten);
        khdao.them(kh1);
        LocalDate date = LocalDate.now();
        String ngaygiaodich = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        StringBuilder hoadon = new StringBuilder();
        hoadon.append("NGÀY GIAO DỊCH: ").append(ngaygiaodich).append("\n");
        hoadon.append("THỜI GIAN: ").append(tggiaodich).append("\n");
        hoadon.append("-------------------------------------------------\n");
        hoadon.append("TTKH---------------------------------------------\n");
        hoadon.append("SDT :").append(sdt).append("-------------\n");;
        hoadon.append("TÊN KHÁCH HÀNG :").append(ten).append("---\n");;
        hoadon.append(String.format("%-15s %-10s %-10s\n", "SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"));

        for (int i = 0; i < bspgh.getRowCount(); i++) {
            int msp = (int) bspgh.getValueAt(i, 0);
            String tenSanPham = (String) bspgh.getValueAt(i, 1);
            int soLuong = (int) bspgh.getValueAt(i, 2);
            double thanhTien = (double) bspgh.getValueAt(i, 4);
            SanPham sp1 = new SanPham(msp, soLuong);
            spdao.giamsoluong(sp1);

            hoadon.append(String.format("%-15s %-10d %-10.2f\n", tenSanPham, soLuong, thanhTien));
        }

        hoadon.append("-------------------------------------------------\n");
        hoadon.append("TỔNG TIỀN: ").append(tinhtongtien()).append(" VND\n");
        LichSuGiaoDich ls1 = new LichSuGiaoDich(ngaygiaodich, sdf.format(tggiaodich), sdt, ten, tinhtongtien(), tinhtonglai());
        int i = ls.themlichsuttkh(ls1);
        JOptionPane.showMessageDialog(null, hoadon.toString(), "Hoa đơn", JOptionPane.INFORMATION_MESSAGE);
        System.err.println(i);
        bspgh.setRowCount(0);
        tongtien.setText("0");

    }

    public double tinhtongtien() {
        tongtient = 0;
        for (int i = 0; i < bspgh.getRowCount(); i++) {
            tongtient += (double) bspgh.getValueAt(i, 4);
        }
        tongtien.setText(String.valueOf(tongtient));
        return tongtient;
    }

    public double tinhtonglai() {
        tienlai = 0;
        for (int i = 0; i < bspgh.getRowCount(); i++) {
            double thanhTien = (double) bspgh.getValueAt(i, 4);
            double giaNhap = (double) bspgh.getValueAt(i, 5);
            double tonggianhap = (int) bspgh.getValueAt(i, 2) * giaNhap;
            tienlai += (thanhTien - tonggianhap);
        }
        return tienlai;
    }

    public void tinhtientralai() {
        try {
            double tienKhachDua = Double.parseDouble(tienkhach.getText());
            tientralai = tienKhachDua - tinhtongtien();
            if (tientralai < 0) {
                JOptionPane.showMessageDialog(null, "Số tiền khách trả không đủ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Số tiền trả lại: " + tientralai, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nhập sai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void rs() {
        bspgh.setRowCount(0);
        tongtien.setText("0");
    }

}

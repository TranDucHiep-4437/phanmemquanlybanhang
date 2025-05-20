package miniview;

import controller.KhachHangListener;
import dao.KhachHangDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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

public class viewKH {

    private JTextField idkh, sdtkh, tenkh;
    private JButton themkh, suakh, timkh, dlkh;
    private DefaultTableModel bdskh;
    KhachHangDAO kh = new KhachHangDAO();
    public String sodienthoai;
    private String tenkhachhang;
    private int solanmua;

    public JPanel KHPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));

        inputPanel.add(new JLabel("Số điện thoại:"));
        sdtkh = new JTextField();
        inputPanel.add(sdtkh);

        inputPanel.add(new JLabel("Tên khách hàng:"));
        tenkh = new JTextField();
        inputPanel.add(tenkh);

        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        ActionListener ac = new KhachHangListener(this);

        themkh = new JButton("Thêm");
        themkh.addActionListener(ac);
        suakh = new JButton("Sửa");
        suakh.addActionListener(ac);
        timkh = new JButton("Tìm");
        timkh.addActionListener(ac);
        dlkh = new JButton("Đặt lại");
        dlkh.addActionListener(ac);

        buttonPanel.add(themkh);
        buttonPanel.add(suakh);
        buttonPanel.add(timkh);
        buttonPanel.add(dlkh);

        String[] columnNames = {"Số điện thoại", "Tên khách hàng", "Số lần mua"};
        bdskh = new DefaultTableModel(columnNames, 0);
        JTable tbsp = new JTable(bdskh);
        tbsp.setShowGrid(true);
        tbsp.setGridColor(Color.black);
        JScrollPane scrollPane = new JScrollPane(tbsp);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));


        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadkh();

        return panel;
    }

    public JPanel KHPanel1() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));


        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));

        inputPanel.add(new JLabel("Số điện thoại:"));
        sdtkh = new JTextField();
        inputPanel.add(sdtkh);

        inputPanel.add(new JLabel("Tên khách hàng:"));
        tenkh = new JTextField();
        inputPanel.add(tenkh);


        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        ActionListener ac = new KhachHangListener(this);

        themkh = new JButton("Thêm");
        themkh.addActionListener(ac);
        timkh = new JButton("Tìm");
        timkh.addActionListener(ac);
        dlkh = new JButton("Đặt lại");
        dlkh.addActionListener(ac);

        buttonPanel.add(themkh);
        buttonPanel.add(timkh);
        buttonPanel.add(dlkh);


        String[] columnNames = {"Số điện thoại", "Tên khách hàng", "Số lần mua"};
        bdskh = new DefaultTableModel(columnNames, 0);
        JTable tbsp = new JTable(bdskh);
        tbsp.setShowGrid(true);
        tbsp.setGridColor(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(tbsp);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));


        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadkh();

        return panel;
    }

    public void loadkh() {
        bdskh.setRowCount(0);
        ArrayList<KhachHang> danhSachKhachHang = kh.xuat();

        if (danhSachKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        for (KhachHang kh : danhSachKhachHang) {
            Object[] rowData = {
                kh.getSodienthoai(),
                kh.getTenkhachhang(),
                kh.getSolanmua()
            };
            bdskh.addRow(rowData);
        }
    }

    public void themkh() {
        if (sdtkh.getText().isEmpty() || tenkh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trống dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sodienthoai = sdtkh.getText();
        tenkhachhang = tenkh.getText();

        KhachHang kht = new KhachHang(sodienthoai, tenkhachhang);
        int kt = kh.them(kht);
        if (kt > 0) {
            sdtkh.setText("");
            tenkh.setText("");
            loadkh();
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        loadkh();
    }

    public void suakh() {
        if (sdtkh.getText().isEmpty() || tenkh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trống dữ liệu !", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        sodienthoai = sdtkh.getText();
        tenkhachhang = tenkh.getText();

        KhachHang kht = new KhachHang(sodienthoai, tenkhachhang);
        int kt = kh.sua(kht);
        if (kt > 0) {
            sdtkh.setText("");
            tenkh.setText("");
            loadkh();
            JOptionPane.showMessageDialog(null, "Sửa khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        loadkh();

    }

    public void timkiemkh() {

        sodienthoai = sdtkh.getText();
        tenkhachhang = tenkh.getText();
        

        if (sodienthoai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng !", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        KhachHang kht = new KhachHang(sodienthoai, tenkhachhang);
        KhachHang khh = kh.timkiem(kht);
        if (khh == null) {
            idkh.setText("");
            sdtkh.setText("");
            tenkh.setText("");
            loadkh();
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            bdskh.setRowCount(0);
            Object[] khachhang = {
                khh.getSodienthoai(),
                khh.getTenkhachhang(),
                khh.getSolanmua()
            };
            bdskh.addRow(khachhang);

        }

    }

    public void datlai() {
        sdtkh.setText("");
        tenkh.setText("");
        loadkh();
    }

}

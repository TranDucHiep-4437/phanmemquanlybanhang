package miniview;

import controller.SanPhamListener;
import dao.SanPhamDAO;
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
import javax.swing.table.DefaultTableModel;
import model.SanPham;

public class viewSP {

    private JTextField id, tensanpham, gianhap, giaban, soluong;
    private JButton themsp, suasp, xoasp, timsp, dlsp;
    private DefaultTableModel bdssp;
    private int idsp;
    private String tensp;
    private double gianhapsp;
    private double giabansp;
    private int soluongsp;
    SanPhamDAO sp = new SanPhamDAO();

    public JPanel SPPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        inputPanel.setBackground(new Color(245, 245, 245));

        id = new JTextField();
        tensanpham = new JTextField();
        giaban = new JTextField();
        gianhap = new JTextField();
        soluong = new JTextField();

        inputPanel.add(new JLabel("Mã sản phẩm:"));
        inputPanel.add(id);
        inputPanel.add(new JLabel("Tên sản phẩm:"));
        inputPanel.add(tensanpham);
        inputPanel.add(new JLabel("Giá bán:"));
        inputPanel.add(giaban);
        inputPanel.add(new JLabel("Giá nhập:"));
        inputPanel.add(gianhap);
        inputPanel.add(new JLabel("Số lượng:"));
        inputPanel.add(soluong);

        topPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        buttonPanel.setBackground(new Color(240, 248, 255));

        ActionListener ac = new SanPhamListener(this);

        themsp = new JButton("Thêm");
        suasp = new JButton("Sửa");
        xoasp = new JButton("Xóa");
        timsp = new JButton("Tìm");
        dlsp = new JButton("Đặt lại");

        themsp.addActionListener(ac);
        suasp.addActionListener(ac);
        xoasp.addActionListener(ac);
        timsp.addActionListener(ac);
        dlsp.addActionListener(ac);

        buttonPanel.add(themsp);
        buttonPanel.add(suasp);
        buttonPanel.add(xoasp);
        buttonPanel.add(timsp);
        buttonPanel.add(dlsp);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Mã", "Tên", "Giá bán", "Giá nhập", "Số lượng"};
        bdssp = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(bdssp);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        panel.add(scrollPane, BorderLayout.CENTER);

        loadsp();

        return panel;
    }

    public JPanel SPPanel1() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        inputPanel.setBackground(new Color(245, 245, 245));

        id = new JTextField();
        tensanpham = new JTextField();
        giaban = new JTextField();
        gianhap = new JTextField();
        soluong = new JTextField();

        inputPanel.add(new JLabel("Mã sản phẩm:"));
        inputPanel.add(id);
        inputPanel.add(new JLabel("Tên sản phẩm:"));
        inputPanel.add(tensanpham);
        inputPanel.add(new JLabel("Giá bán:"));
        inputPanel.add(giaban);
        inputPanel.add(new JLabel("Giá nhập:"));
        inputPanel.add(gianhap);
        inputPanel.add(new JLabel("Số lượng:"));
        inputPanel.add(soluong);

        topPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        buttonPanel.setBackground(new Color(240, 248, 255));

        ActionListener ac = new SanPhamListener(this);

        themsp = new JButton("Thêm");
        timsp = new JButton("Tìm");
        dlsp = new JButton("Đặt lại");
        themsp.addActionListener(ac);

        timsp.addActionListener(ac);
        dlsp.addActionListener(ac);

        buttonPanel.add(themsp);
        buttonPanel.add(timsp);
        buttonPanel.add(dlsp);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Mã", "Tên", "Giá bán", "Giá nhập", "Số lượng"};
        bdssp = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(bdssp);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        panel.add(scrollPane, BorderLayout.CENTER);

        loadsp();

        return panel;
    }

    public void loadsp() {
        bdssp.setRowCount(0);
        ArrayList<SanPham> danhSachSanPham = sp.xuat();
        if (danhSachSanPham.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có sản phẩm ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        for (SanPham sp : danhSachSanPham) {
            Object[] rowData = {
                sp.getId(),
                sp.getTensanpham(),
                sp.getGiaban(),
                sp.getGianhap(),
                sp.getSoluong()
            };
            bdssp.addRow(rowData);
        }
    }

    public void themsp() {
        if (id.getText().isEmpty() || tensanpham.getText().isEmpty() || giaban.getText().isEmpty() || gianhap.getText().isEmpty() || soluong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trống dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            idsp = Integer.parseInt(id.getText());
            tensp = tensanpham.getText();
            gianhapsp = Double.parseDouble(gianhap.getText());
            giabansp = Double.parseDouble(giaban.getText());
            soluongsp = Integer.parseInt(soluong.getText());
            if (giabansp < gianhapsp) {
                JOptionPane.showMessageDialog(null, "Giá bán nhỏ hơn giá nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nhập sai dữ liệu", "Lỗi ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SanPham spt = new SanPham(idsp, tensp, gianhapsp, giabansp, soluongsp);
        int kt = sp.them(spt);
        if (kt > 0) {
            id.setText("");
            tensanpham.setText("");
            gianhap.setText("");
            giaban.setText("");
            soluong.setText("");
            loadsp();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!", "thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        loadsp();

    }

    public void suasp() {
        try {

            if (id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Trống dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            idsp = Integer.parseInt(id.getText());
            SanPham spdtk = sp.timkiem(new SanPham(idsp));
            if (spdtk == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            tensp = tensanpham.getText().isEmpty() ? spdtk.getTensanpham() : tensanpham.getText();
            giabansp = giaban.getText().isEmpty() ? spdtk.getGiaban() : Double.parseDouble(giaban.getText());
            gianhapsp = gianhap.getText().isEmpty() ? spdtk.getGianhap() : Double.parseDouble(gianhap.getText().trim());
            soluongsp = soluong.getText().isEmpty() ? spdtk.getSoluong() : Integer.parseInt(soluong.getText());
            if (giabansp < gianhapsp) {
                JOptionPane.showMessageDialog(null, "Giá bán nhỏ hơn giá nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SanPham spt = new SanPham(idsp, tensp, gianhapsp, giabansp, soluongsp);

            int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa sản phẩm này không", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                sp.sua(spt);
                id.setText("");
                tensanpham.setText("");
                gianhap.setText("");
                giaban.setText("");
                soluong.setText("");
                loadsp();
                JOptionPane.showMessageDialog(null, "Đã sửa sản phẩm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Trống mã sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void xoasp() {
        try {
            idsp = Integer.parseInt(id.getText());

            int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                SanPham spXoa = new SanPham(idsp);
                int ixoa=sp.xoa(spXoa);
                if (ixoa==1) {
                    loadsp();
                id.setText("");
                tensanpham.setText("");
                gianhap.setText("");
                giaban.setText("");
                soluong.setText("");
                JOptionPane.showMessageDialog(null, "Đã xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID sản phẩm!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timkiemsp() {
        bdssp.setRowCount(0);

        String idText = id.getText().trim();
        String tenSPText = tensanpham.getText().trim();

        boolean idEmpty = idText.isEmpty();
        boolean tenEmpty = tenSPText.isEmpty();

        if (idEmpty && tenEmpty) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID hoặc tên sản phẩm!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!idEmpty) {
            try {
                int idsp = Integer.parseInt(idText);
                SanPham sptk = sp.timkiem(new SanPham(idsp));

                if (sptk != null) {
                    Object[] row = {
                        sptk.getId(),
                        sptk.getTensanpham(),
                        sptk.getGianhap(),
                        sptk.getGiaban(),
                        sptk.getSoluong()
                    };
                    bdssp.addRow(row);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm theo ID!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (!tenEmpty) {
            ArrayList<SanPham> ds = sp.timkiemten(tenSPText);
            if (ds.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm theo tên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (SanPham sp : ds) {
                    Object[] row = {
                        sp.getId(),
                        sp.getTensanpham(),
                        sp.getGianhap(),
                        sp.getGiaban(),
                        sp.getSoluong()
                    };
                    bdssp.addRow(row);
                }
            }
        }
    }

    public void datlai() {
        id.setText("");
        tensanpham.setText("");
        gianhap.setText("");
        giaban.setText("");
        soluong.setText("");
        loadsp();
    }
}

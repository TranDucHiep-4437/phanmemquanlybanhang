package miniview;

import controller.TaiKhoanListener;
import dao.TaiKhoanDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoan;

public class viewTK {

    private JTextField tentk, mk, idtk;
    private JComboBox<String> vt;
    private JButton themtk, suatk, xoatk, dltk;
    private DefaultTableModel bdstk;
    private int idtaikhoan;
    private String tentaikhoan;
    private String matkhau;
    private String vaitro;
    TaiKhoanDAO tk = new TaiKhoanDAO();

    public JPanel TKPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

 
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));

        inputPanel.add(new JLabel("ID tài khoản (bỏ trống khi thêm):"));
        idtk = new JTextField();
        inputPanel.add(idtk);

        inputPanel.add(new JLabel("Tên tài khoản:"));
        tentk = new JTextField();
        inputPanel.add(tentk);

        inputPanel.add(new JLabel("Nhập mật khẩu:"));
        mk = new JTextField();
        inputPanel.add(mk);

        inputPanel.add(new JLabel("Vai trò:"));
        vt = new JComboBox<>(new String[]{"Nhan vien", "Quan ly"});
        inputPanel.add(vt);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        ActionListener ac = new TaiKhoanListener(this);

        themtk = new JButton("Thêm");
        themtk.addActionListener(ac);

        suatk = new JButton("Sửa");
        suatk.addActionListener(ac);

        xoatk = new JButton("Xóa");
        xoatk.addActionListener(ac);

        dltk = new JButton("Đặt lại");
        dltk.addActionListener(ac);

        buttonPanel.add(themtk);
        buttonPanel.add(suatk);
        buttonPanel.add(xoatk);
        buttonPanel.add(dltk);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"Mã", "Tên", "Mật khẩu", "Vai trò"};
        bdstk = new DefaultTableModel(columnNames, 0);
        JTable tbsp = new JTable(bdstk);
        tbsp.setShowGrid(true);
        tbsp.setGridColor(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(tbsp);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));

        panel.add(scrollPane, BorderLayout.CENTER);

        loadtk();

        return panel;
    }

    public void loadtk() {
        bdstk.setRowCount(0);
        ArrayList<TaiKhoan> dstk = tk.xuat();
        if (dstk.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có tài khoản", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        for (TaiKhoan tks : dstk) {
            Object[] rowData = {
                tks.getIdtaikhoan(),
                tks.getTentaikhoan(),
                tks.getMatkhau(),
                tks.getVaitro()
            };
            bdstk.addRow(rowData);
        }
    }

    public void themtk() {
        if (tentk.getText().isEmpty() || mk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trống dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tentaikhoan = tentk.getText();
        matkhau = mk.getText();
        vaitro = (String) vt.getSelectedItem();

        TaiKhoan tkt = new TaiKhoan(tentaikhoan, matkhau, vaitro);
        int kt = tk.them(tkt);
        if (kt == 0) {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            tentk.setText("");
            mk.setText("");
            loadtk();
            JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void suatk() {
        if (idtk.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Trống dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        idtaikhoan = Integer.parseInt(idtk.getText());
        
        TaiKhoan tkdtk =tk.timkiem(new TaiKhoan(idtaikhoan));
        tentaikhoan = tentk.getText().isEmpty()?tkdtk.getTentaikhoan():tentk.getText();
        matkhau = mk.getText().isEmpty()?tkdtk.getMatkhau():mk.getText();
        vaitro = (String) vt.getSelectedItem();

        TaiKhoan tkt = new TaiKhoan(idtaikhoan, tentaikhoan, matkhau, vaitro);
        int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản này không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            int chon2 = tk.sua(tkt);
            if (chon2 > 0) {
                JOptionPane.showMessageDialog(null, "Đã sửa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tentk.setText("");
                mk.setText("");
                loadtk();
            } else {
                JOptionPane.showMessageDialog(null, "Sửa tài khoản thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            return;
        }

    }

    public void xoatk() {
        idtaikhoan = Integer.parseInt(idtk.getText());
        tentaikhoan = tentk.getText();
        matkhau = mk.getText();
        vaitro = (String) vt.getSelectedItem();
        if (idtk.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập id tài khảon càn xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaiKhoan tkt = new TaiKhoan(idtaikhoan, tentaikhoan, matkhau, vaitro);
        int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản nay không", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            int chon1 = tk.xoa(tkt);
            if (chon1 > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tentk.setText("");
                mk.setText("");
                loadtk();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            return;
        }

    }

    public void datlai() {
        tentk.setText("");
        mk.setText("");
        loadtk();
    }

}

package miniview;

import controller.LichSuListener;
import dao.BaoCaoDoanhThuDAO;
import dao.LichSuGiaoDichDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DoanhThu;
import model.LichSuGiaoDich;

public class viewLS {

    LichSuGiaoDichDAO lsdao = new LichSuGiaoDichDAO();
    private JLabel tdd, tln, tslm, tdd1, tln2, tslm3;
    private JComboBox<String> cnt, ct, cn, cnam;
    private JButton tk, rs;
    BaoCaoDoanhThuDAO bcdt = new BaoCaoDoanhThuDAO();

    private DefaultTableModel bdsls;

    public JPanel LSPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTop = new JPanel(new GridLayout(1, 2, 20, 0));

        JPanel pane2 = new JPanel(new GridLayout(4, 2, 10, 10));
        pane2.setBorder(BorderFactory.createTitledBorder("Tùy chọn thống kê"));

        pane2.add(new JLabel("Báo cáo theo:"));
        cnt = new JComboBox<>(new String[]{"Ngày", "Tháng"});
        pane2.add(cnt);

        pane2.add(new JLabel("Chọn thời gian:"));
        JPanel timeSelectPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        cnam = new JComboBox<>(new String[]{"2025", "2024"});
        ct = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
        cn = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
        timeSelectPanel.add(cnam);
        timeSelectPanel.add(ct);
        timeSelectPanel.add(cn);
        pane2.add(timeSelectPanel);

        ActionListener ac = new LichSuListener(this);
        tk = new JButton("Thống kê");
        tk.addActionListener(ac);
        rs = new JButton("Đặt lại");
        rs.addActionListener(ac);
        pane2.add(tk);
        pane2.add(rs);

        JPanel pane3 = new JPanel(new GridLayout(3, 2, 10, 10));
        pane3.setBorder(BorderFactory.createTitledBorder("Tổng kết"));
        tdd = new JLabel("Tổng doanh thu:");
        tln = new JLabel("Tổng lợi nhuận:");
        tslm = new JLabel("Tổng số lần mua:");
        tdd1 = new JLabel("");
        tln2 = new JLabel("");
        tslm3 = new JLabel("");
        pane3.add(tdd);
        pane3.add(tdd1);
        pane3.add(tln);
        pane3.add(tln2);
        pane3.add(tslm);
        pane3.add(tslm3);

        panelTop.add(pane2);
        panelTop.add(pane3);
        panel.add(panelTop, BorderLayout.NORTH);

        String[] columnNames = {"IDGD", "Ngày", "Thời gian", "SĐT Khách Hàng", "Tên Khách Hàng", "Tổng Tiền", "Tiền Lãi"};
        bdsls = new DefaultTableModel(columnNames, 0);
        JTable tbsp = new JTable(bdsls);
        tbsp.setShowGrid(true);
        tbsp.setGridColor(Color.BLACK);
        tbsp.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tbsp);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách giao dịch"));
        panel.add(scrollPane, BorderLayout.CENTER);

        loadls();

        return panel;
    }

    public JPanel LSPanel1() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        ActionListener ac = new LichSuListener(this);

        rs = new JButton("Đặt lại");
        rs.addActionListener(ac);
        panel.add(rs, BorderLayout.NORTH);

        JPanel pane3 = new JPanel(new GridLayout(3, 2, 10, 10));
        pane3.setBorder(BorderFactory.createTitledBorder("Tổng kết"));
        tdd = new JLabel("Tổng doanh thu:");
        tln = new JLabel("Tổng lợi nhuận:");
        tslm = new JLabel("Tổng số lần mua:");
        tdd1 = new JLabel("");
        tln2 = new JLabel("");
        tslm3 = new JLabel("");
        pane3.add(tdd);
        pane3.add(tdd1);
        pane3.add(tln);
        pane3.add(tln2);
        pane3.add(tslm);
        pane3.add(tslm3);

        String[] columnNames = {"IDGD", "Ngày", "Thời gian", "SĐT Khách Hàng", "Tên Khách Hàng", "Tổng Tiền", "Tiền Lãi"};
        bdsls = new DefaultTableModel(columnNames, 0);
        JTable tbsp = new JTable(bdsls);
        tbsp.setShowGrid(true);
        tbsp.setGridColor(Color.BLACK);
        tbsp.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tbsp);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách giao dịch"));
        panel.add(scrollPane, BorderLayout.CENTER);

        loadls();

        return panel;
    }

    public void loadls() {
        bdsls.setRowCount(0);
        Stack<LichSuGiaoDich> ls = lsdao.xuatls();
        if (ls.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có giao dịch", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            while (!ls.isEmpty()) {
                LichSuGiaoDich lsgd = ls.pop();
                Object[] rowData = {
                    lsgd.getIdgiaodich(),
                    lsgd.getNgaygiaodich(),
                    lsgd.getThoigiangiaodich(),
                    lsgd.getSodienthoai(),
                    lsgd.getTenkhachhang(),
                    lsgd.getTongtien(),
                    lsgd.getTienlai()
                };
                bdsls.addRow(rowData);
            }
        }
    }
    

    public void datlai() {
        loadls();
    }

    public void doangthu() {
        String day = cnam.getSelectedItem() + "-" + ct.getSelectedItem() + "-" + cn.getSelectedItem();
        String month = cnam.getSelectedItem() + "-" + ct.getSelectedItem();
        String chontg = (String) cnt.getSelectedItem();

        if (chontg.equals("Ngày")) {
            LichSuGiaoDich ls = new LichSuGiaoDich(day);
            DoanhThu dt = bcdt.baocaotheongay(ls);
            if (dt != null) {
                tdd.setText("Tổng doanh thu ngày " + day);
                tln.setText("Tổng tiền lãi ngày " + day);
                tslm.setText("Tổng số lần mua hàng ngày " + day);
                tdd1.setText("" + dt.getTongtien());
                tln2.setText("" + dt.getTienlai());
                tslm3.setText("" + dt.getSolanmua());
            } else {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu " + day, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (chontg.equals("Tháng")) {
            LichSuGiaoDich ls = new LichSuGiaoDich(month);
            DoanhThu dtt = bcdt.baocaotheothang(ls);
            if (dtt != null) {
                tdd.setText("Tổng doanh thu tháng " + month);
                tln.setText("Tổng tiền lãi tháng " + month);
                tslm.setText("Tổng số lần mua hàng tháng " + month);
                tdd1.setText("" + dtt.getTongtien());
                tln2.setText("" + dtt.getTienlai());
                tslm3.setText("" + dtt.getSolanmua());
            } else {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu " + month, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

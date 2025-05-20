package view;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import miniview.viewKH;
import miniview.viewLS;
import miniview.viewSP;
import miniview.viewTK;
import miniview.viewTT;

public class ViewNhanVien extends JFrame {

    viewSP sp = new viewSP();
    viewTT tt = new viewTT();
    viewTK tk = new viewTK();
    viewKH kh = new viewKH();
    viewLS ls = new viewLS();
    URL urli = ViewNhanVien.class.getResource("/img/iconthem.png");
    Image img = Toolkit.getDefaultToolkit().createImage(urli);

    public ViewNhanVien() {
        setTitle("Trang Nhân Viên");
        setIconImage(img);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelSanpham = sp.SPPanel1();
        tabbedPane.addTab("Sản phẩm", panelSanpham);

        JPanel panelThanhtoan = tt.TTPanel();
        tabbedPane.addTab("Thanh toán", panelThanhtoan);
        
        JPanel panelKhachHang = kh.KHPanel1();
        tabbedPane.addTab("Khách hàng", panelKhachHang);
        
        JPanel panelLichSu = ls.LSPanel1();
        tabbedPane.addTab("Lịch sử", panelLichSu);

        
        JPanel panelDangXuat = DXPanel();
        tabbedPane.addTab("Đăng xuất", panelDangXuat);

        add(tabbedPane);
  
        setVisible(true);
    }

   public void dangxuat(){
        int chon = new JOptionPane().showConfirmDialog(null,"Ban co muon dang xuat khong","Thong bao",JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            dispose();
            new ViewDangNhap();
        }else{
            
        }
    }
    public JPanel DXPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panellll = new JPanel(new GridLayout(3, 1,0,0));
        
    JLabel label = new JLabel("Thông tin tài khoản !!");
    JLabel label1 = new JLabel("Tên đăng nhâp  :*******");
    JLabel label2 = new JLabel("Mật khẩu       :*********");
    
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 16));
    label1.setHorizontalAlignment(SwingConstants.CENTER);
    label1.setFont(new Font("Arial", Font.BOLD, 16));
    label2.setHorizontalAlignment(SwingConstants.CENTER);
    label2.setFont(new Font("Arial", Font.BOLD, 16));
    panellll.add(label);
    panellll.add(label1);
    panellll.add(label2);

    JButton dx = new JButton("ĐĂNG XUẤT");
    dx.setFont(new Font("Arial", Font.BOLD, 14));
    dx.setPreferredSize(new Dimension(150, 40));
    dx.addActionListener(e -> dangxuat());

    JPanel btnPanel = new JPanel();
    btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    btnPanel.add(dx);

    panel.add(panellll, BorderLayout.CENTER);
    panel.add(btnPanel, BorderLayout.SOUTH);
    return panel;
}
public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
        System.err.println("Không thể khởi tạo FlatLaf");
    }

    SwingUtilities.invokeLater(ViewNhanVien::new);

}
}

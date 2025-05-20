package view;
import com.formdev.flatlaf.FlatIntelliJLaf;
import controller.DangNhapListener;
import dao.TaiKhoanDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import model.TaiKhoan;

public class ViewDangNhap extends JFrame {

    JTextField jtf1;
    JPasswordField jpass;
    URL urli = ViewDangNhap.class.getResource("/img/iconthem.png");
    Image img = Toolkit.getDefaultToolkit().createImage(urli);

    public ViewDangNhap() {
        setIconImage(img);
        setTitle("Đăng Nhập Hệ Thống");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Tên đăng nhập:"));
        jtf1 = new JTextField();
        formPanel.add(jtf1);

        formPanel.add(new JLabel("Mật khẩu:"));
        jpass = new JPasswordField();
        formPanel.add(jpass);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton loginBtn = new JButton("Đăng nhập");
        loginBtn.addActionListener(new DangNhapListener(this));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void dangnhap() {
    try {
        String tentaikhoan = jtf1.getText();
        String matkhau = new String(jpass.getPassword());

        if (tentaikhoan.isEmpty() || matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        TaiKhoan tk = tkDAO.dangnhap(tentaikhoan, matkhau);

        if (tk != null) {

            if (tk.getVaitro().equalsIgnoreCase("Quan Ly")) {
                new ViewQuanLy().setVisible(true);
                dispose();  
            } else if (tk.getVaitro().equalsIgnoreCase("Nhan vien")) {
                new ViewNhanVien().setVisible(true);
                dispose();  
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            new ViewDangNhap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


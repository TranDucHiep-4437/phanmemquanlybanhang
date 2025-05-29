package dao;

import static dao.DatabaseConnection.closeConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.TaiKhoan;

public class TaiKhoanDAO implements DAOInterface<TaiKhoan> {

    @Override
    public int them(TaiKhoan t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            TaiKhoan tk = timkiem(t);
            if (tk == null) {
                String sql = "INSERT INTO `taikhoan`(`tentaikhoan`, `matkhau`, `vaitro`) VALUES"
                        + " ('" + t.getTentaikhoan() + "','" + t.getMatkhau() + "','" + t.getVaitro() + "')";
                int kq = st.executeUpdate(sql);

                System.out.println("Da them tai khoan :" + t.getTentaikhoan());
                closeConnection(conn);
                return kq;
            } else {
                System.out.println("tai khoan da ton tai.");
                closeConnection(conn);
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int sua(TaiKhoan t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "UPDATE `taikhoan` SET "
                    + "`tentaikhoan`='" + t.getTentaikhoan() + "',`matkhau`='" + t.getMatkhau() + "',"
                    + "`vaitro`='" + t.getVaitro() + "' WHERE `idtaikhoan` ='" + t.getIdtaikhoan() + "'";
            int kq = st.executeUpdate(sql);
            closeConnection(conn);
            return kq;

        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int xoa(TaiKhoan t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "DELETE FROM `taikhoan` WHERE `idtaikhoan`='" + t.getIdtaikhoan() + "'";
            int kq = st.executeUpdate(sql);
            closeConnection(conn);
            return kq;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public ArrayList<TaiKhoan> xuat() {
        ArrayList<TaiKhoan> ketqua = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement st = conn.createStatement();

            String sql = "SELECT * FROM `taikhoan`";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idtaikhoan");
                String tentaikhoan = rs.getString("tentaikhoan");
                String matkhau = rs.getString("matkhau");
                String vaitro = rs.getString("vaitro");
                TaiKhoan taikhoan = new TaiKhoan(id, tentaikhoan, matkhau, vaitro);
                ketqua.add(taikhoan);

            }
            closeConnection(conn);

        } catch (Exception e) {
        }
        return ketqua;
    }

    @Override
    public TaiKhoan timkiem(TaiKhoan t) {
        TaiKhoan ketqua = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM `taikhoan` WHERE `idtaikhoan` = " + t.getIdtaikhoan();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) { 
                int id = rs.getInt("idtaikhoan");
                String tentk = rs.getString("tentaikhoan");
                String matkhau = rs.getString("matkhau");
                String vaitro = rs.getString("vaitro");

                ketqua = new TaiKhoan(id, tentk, matkhau, vaitro);
            }
            closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

   public TaiKhoan dangnhap(String tentaikhoan, String matkhau) {
    try {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM taikhoan WHERE tentaikhoan = ? AND matkhau = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, tentaikhoan);
        pst.setString(2, matkhau);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            TaiKhoan tk = new TaiKhoan(
                    rs.getInt("idtaikhoan"),
                    rs.getString("tentaikhoan"),
                    rs.getString("matkhau"),
                    rs.getString("vaitro")
            );
            closeConnection(conn);
            return tk;
        }
        closeConnection(conn);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {

    @Override
    public int them(KhachHang t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            KhachHang kh = timkiem(t);
            if (kh == null) {
                String sql = "INSERT INTO `khachhang`(`sodienthoai`, `tenkhachhang`, `solanmua`) "
                        + "VALUES ('" + t.getSodienthoai() + "','" + t.getTenkhachhang() + "','" + t.getSolanmua() + "')";
                int kq = st.executeUpdate(sql);
                DatabaseConnection.closeConnection(conn);

                System.out.println("Da them khach hang" + t.getTenkhachhang());
                return kq;
            } else {
                System.out.println("Da co khach hang");
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int sua(KhachHang t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();

            String sql = "UPDATE `khachhang` SET `tenkhachhang`='" + t.getTenkhachhang() + "' WHERE `sodienthoai`='" + t.getSodienthoai() + "'";
            int kq = st.executeUpdate(sql);
            
            DatabaseConnection.closeConnection(conn);
            return kq;

        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int xoa(KhachHang t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<KhachHang> xuat() {
        ArrayList<KhachHang> dskd = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT `sodienthoai`, `tenkhachhang`, `solanmua` FROM `khachhang`";
            ResultSet kq = st.executeQuery(sql);
            while (kq.next()) {
                String sdt = kq.getString("sodienthoai");
                String ten = kq.getString("tenkhachhang");
                int slm = kq.getInt("solanmua");
                KhachHang kh = new KhachHang(sdt, ten, slm);
                dskd.add(kh);
            }
            DatabaseConnection.closeConnection(conn);

        } catch (Exception e) {
        }
        return dskd;
    }

    @Override
    public KhachHang timkiem(KhachHang t) {
        KhachHang kh = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT `sodienthoai`,`tenkhachhang`, `solanmua` FROM `khachhang` WHERE `sodienthoai`=" + t.getSodienthoai() + "";
            ResultSet kq = st.executeQuery(sql);
            while (kq.next()) {
                String sdt = kq.getString("sodienthoai");
                String ten = kq.getString("tenkhachhang");
                int slm = kq.getInt("solanmua");
                kh = new KhachHang(sdt, ten, slm);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
        }
        return kh;
    }

    public int tanglanmua(KhachHang t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "UPDATE `khachhang` SET `solanmua`= solanmua+1 WHERE `sodienthoai`='" + t.getSodienthoai() + "'";
            st.executeUpdate(sql);
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
        }
        return 0;
    }

}

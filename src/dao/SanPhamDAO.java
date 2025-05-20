package dao;

import static dao.DatabaseConnection.closeConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.SanPham;

public class SanPhamDAO implements DAOInterface<SanPham> {

    @Override
    public int them(SanPham t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            SanPham sp = timkiem(t);
            if (sp == null) {
                String sql = "INSERT INTO `sanpham`(`idsanpham`, `tensanpham`, `gianhap`, `giaban`,`soluong`)"
                        + " VALUES ('" + t.getId() + "','" + t.getTensanpham() + "','" + t.getGianhap() + "','" + t.getGiaban() + "','" + t.getSoluong() + "')";
                int kq = st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm :" + t.getTensanpham(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                closeConnection(conn);
                return kq;
            } else {
                String sql = "UPDATE `sanpham` SET `soluong`=`soluong`+" + t.getSoluong() + " WHERE `idsanpham`=" + t.getId() + " ";
                int kq = st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Đã thêm " + t.getSoluong() + " Mã sản phẩm:" + t.getId(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                closeConnection(conn);
                return kq;
            }
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int sua(SanPham t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "UPDATE `sanpham` SET `tensanpham`='" + t.getTensanpham() + "', `gianhap`=" + t.getGianhap()
                    + ", `giaban`=" + t.getGiaban() + ", `soluong`=" + t.getSoluong() + " WHERE `idsanpham`=" + t.getId();

            int kq = st.executeUpdate(sql);
            closeConnection(conn);
            return kq;

        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public int xoa(SanPham t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "DELETE FROM `sanpham` WHERE `idsanpham`='" + t.getId() + "'";
            int kq = st.executeUpdate(sql);
            closeConnection(conn);
            return kq;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public ArrayList<SanPham> xuat() {
        ArrayList<SanPham> ketqua = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement st = conn.createStatement();

            String sql = "SELECT idsanpham, tensanpham, gianhap, giaban, soluong FROM sanpham";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idsanpham");
                String tensanpham = rs.getString("tensanpham");
                double gianhap = rs.getDouble("gianhap");
                double giaban = rs.getDouble("giaban");
                int soluong = rs.getInt("soluong");
                SanPham sanpham = new SanPham(id, tensanpham, gianhap, giaban, soluong);
                ketqua.add(sanpham);

            }

            closeConnection(conn);

        } catch (Exception e) {
        }
        return ketqua;
    }

    @Override
    public SanPham timkiem(SanPham t) {
        SanPham ketqua = null;
        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement st = conn.createStatement();

            String sql = "SELECT * FROM `sanpham` WHERE idsanpham='" + t.getId() + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idsanpham");
                String tensanpham = rs.getString("tensanpham");
                double gianhap = rs.getDouble("gianhap");
                double giaban = rs.getDouble("giaban");
                int soluong = rs.getInt("soluong");
                ketqua = new SanPham(id, tensanpham, gianhap, giaban, soluong);

            }
            closeConnection(conn);

        } catch (Exception e) {
        }
        return ketqua;
    }

    public ArrayList<SanPham> timkiemten(String tensp) {
        ArrayList<SanPham> ketqua = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE '%" + tensp + "%'";
        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("idsanpham");
                String tensanpham = rs.getString("tensanpham");
                double gianhap = rs.getDouble("gianhap");
                double giaban = rs.getDouble("giaban");
                int soluong = rs.getInt("soluong");

                ketqua.add(new SanPham(id, tensanpham, gianhap, giaban, soluong));
            }
            closeConnection(conn);
        } catch (Exception e) {

        }
        return ketqua;
    }

    public int giamsoluong(SanPham t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            SanPham sp1 = timkiem(t);

            if (sp1.getSoluong() < t.getSoluong()) {
                System.out.println("khong du so luong.so luong hien co: " + sp1.getSoluong());
                return -1;

            }
            if (sp1.getSoluong() >= t.getSoluong()) {
                String sql = "UPDATE `sanpham` SET `soluong`=`soluong`-" + t.getSoluong() + " WHERE `idsanpham`='" + t.getId() + "'";
                st.executeUpdate(sql);
                DatabaseConnection.closeConnection(conn);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int tangsoluong(SanPham t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "UPDATE `sanpham` SET `soluong`=`soluong`+" + t.getSoluong() + " WHERE `idsanpham`='" + t.getId() + "'";
            st.executeUpdate(sql);
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
        }
        return 0;
    }

}

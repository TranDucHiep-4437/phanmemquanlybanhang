package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Stack;
import model.KhachHang;
import model.LichSuGiaoDich;

public class LichSuGiaoDichDAO {

    KhachHangDAO tkh = new KhachHangDAO();

    public int themlichsu(LichSuGiaoDich t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "INSERT INTO `lichsugiaodich`(`ngaygiaodich`, `thoigiangiaodich`, `sodienthoai`, `tenkhachhang`, `tongtien`, `tienlai`)"
                    + " VALUES ('" + t.getNgaygiaodich() + "','" + t.getThoigiangiaodich() + "','0','0','" + t.getTongtien() + "','" + t.getTienlai() + "')";

            int kq = st.executeUpdate(sql);
            System.out.println("Da them lich su khong co thong tin khach hang");
            DatabaseConnection.closeConnection(conn);
            return kq;
        } catch (Exception e) {
            return 0;
        }
    }

    public int themlichsuttkh(LichSuGiaoDich t ) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();

            KhachHang kh = new KhachHang(t.getSodienthoai(), t.getTenkhachhang());
            KhachHang ttkh = tkh.timkiem(kh);

            if (ttkh == null) {
                tkh.them(kh);
                String sql = "INSERT INTO `lichsugiaodich`(`ngaygiaodich`, `thoigiangiaodich`, `sodienthoai`, `tenkhachhang`, `tongtien`, `tienlai`)"
                        + " VALUES ('" + t.getNgaygiaodich() + "','" + t.getThoigiangiaodich() + "','" + t.getSodienthoai() + "','" + t.getTenkhachhang() + "','" + t.getTongtien() + "','" + t.getTienlai() + "')";
                int kq =  st.executeUpdate(sql);
                DatabaseConnection.closeConnection(conn);
                return kq;
            } else {
                tkh.tanglanmua(kh);
                String sql = "INSERT INTO `lichsugiaodich`(`ngaygiaodich`, `thoigiangiaodich`, `sodienthoai`, `tenkhachhang`, `tongtien`, `tienlai`)"
                        + " VALUES ('" + t.getNgaygiaodich() + "','" + t.getThoigiangiaodich() + "','" + ttkh.getSodienthoai() + "','" + ttkh.getTenkhachhang() + "','" + t.getTongtien() + "','" + t.getTienlai() + "')";
                
                int kq = st.executeUpdate(sql);
                DatabaseConnection.closeConnection(conn);
                return kq;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public Stack xuatls() {
        Stack<LichSuGiaoDich> lsgd = new Stack<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT `idgiaodich`, `ngaygiaodich`, `thoigiangiaodich`, `sodienthoai`, `tenkhachhang`, `tongtien`, `tienlai` FROM `lichsugiaodich`";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("idgiaodich");
                String ngaygiaodich = rs.getString("ngaygiaodich");
                String thoigiangiaodich = rs.getString("thoigiangiaodich");
                String sodienthoai = rs.getString("sodienthoai");
                String tenkhachhang = rs.getString("tenkhachhang");
                double tongtien = rs.getDouble("tongtien");
                double tienlai = rs.getDouble("tienlai");
                LichSuGiaoDich ls = new LichSuGiaoDich(id, ngaygiaodich, thoigiangiaodich, sodienthoai, tenkhachhang, tongtien, tienlai);
                lsgd.push(ls);
            }
            DatabaseConnection.closeConnection(conn);
        } catch (Exception e) {
        }
        return lsgd;
    }

}

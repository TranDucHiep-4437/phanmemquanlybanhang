package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.DoanhThu;
import model.LichSuGiaoDich;

public class BaoCaoDoanhThuDAO {

    public DoanhThu baocaotheothang(LichSuGiaoDich t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM `lichsugiaodich` WHERE ngaygiaodich LIKE '" + t.getNgaygiaodich() + "%'";
            ResultSet rs = st.executeQuery(sql);

            int sogiaodich = 0;
            double tongdoanhthu = 0;
            double tongtienlai = 0;

            while (rs.next()) {
                sogiaodich++;
                double tongdt = rs.getDouble("tongtien");
                tongdoanhthu = tongdoanhthu + tongdt;
                double tongtl = rs.getDouble("tienlai");
                tongtienlai = tongtienlai + tongtl;
            }
            DoanhThu dt = new DoanhThu(tongdoanhthu, tongtienlai, sogiaodich);
            
            DatabaseConnection.closeConnection(conn);
            return dt;
        } catch (Exception e) {

        }
        return null;
    }

    public DoanhThu baocaotheongay(LichSuGiaoDich t) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM `lichsugiaodich` WHERE ngaygiaodich LIKE '" + t.getNgaygiaodich() + "'";
            ResultSet rs = st.executeQuery(sql);

            int sogiaodich = 0;
            double tongdoanhthu = 0;
            double tongtienlai = 0;

            while (rs.next()) {
                sogiaodich++;
                double tongdt = rs.getDouble("tongtien");
                tongdoanhthu = tongdoanhthu + tongdt;
                double tongtl = rs.getDouble("tienlai");
                tongtienlai = tongtienlai + tongtl;

            }
            DoanhThu dt = new DoanhThu(tongdoanhthu, tongtienlai, sogiaodich);

            DatabaseConnection.closeConnection(conn);
            return dt;
        } catch (Exception e) {

        }
        return null;
    }
}

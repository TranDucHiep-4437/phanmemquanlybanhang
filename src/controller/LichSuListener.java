package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miniview.viewLS;

public class LichSuListener implements ActionListener{
public viewLS vls;

    public LichSuListener(viewLS vls) {
        this.vls = vls;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sc = e.getActionCommand();
        if (sc.equals("Thống kê")) {
            vls.doangthu();
        }if (sc.equals("Đặt lại")) {
            vls.datlai();
        }
    }

}

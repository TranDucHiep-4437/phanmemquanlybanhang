package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miniview.viewSP;

public class SanPhamListener implements ActionListener{

    private viewSP vsp;

    public SanPhamListener(viewSP vsp) {
        this.vsp = vsp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String sc = e.getActionCommand();
        if (sc.equals("Thêm")) {
            vsp.themsp();
        } if (sc.equals("Sửa")) {
            vsp.suasp();
        }if (sc.equals("Xóa")) {
            vsp.xoasp();
        }if (sc.equals("Đặt lại")) {
            vsp.datlai();
        }if (sc.equals("Tìm")) {
            vsp.timkiemsp();
        }
        
    }

}

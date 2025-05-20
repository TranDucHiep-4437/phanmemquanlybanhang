package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miniview.viewKH;

public class KhachHangListener implements ActionListener{
    public viewKH vkh;

    public KhachHangListener(viewKH vkh) {
        this.vkh = vkh;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String sc = e.getActionCommand();
        if (sc.equals("Thêm")) {
            vkh.themkh();
        }if (sc.equals("Sửa")) {
            vkh.suakh();
        }if (sc.equals("Tìm")) {
            vkh.timkiemkh();
        }if (sc.equals("Đặt lại")) {
            vkh.datlai();
        }
    }
    

}

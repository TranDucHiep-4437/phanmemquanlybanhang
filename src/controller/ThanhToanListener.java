package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miniview.viewTT;

public class ThanhToanListener implements ActionListener{
    public viewTT vtt;

    public ThanhToanListener(viewTT vtt) {
        this.vtt = vtt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sc = e.getActionCommand();
        if (sc.equals("Thêm vào giỏ hàng")) {
            vtt.themgiohang();
        }if (sc.equals("Tính tiền trả lại")) {
            vtt.tinhtientralai();
        }if (sc.equals("THANH TOÁN")) {
            vtt.inhoadon();
        }if (sc.equals("Đặt lại giỏ hàng")) {
            vtt.rs();
        }if (sc.equals("Thêm")) {
            vtt.nhapttkh();
        }
    }
    

}

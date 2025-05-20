package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ViewDangNhap;

public class DangNhapListener implements ActionListener{
    
    private ViewDangNhap vdn;

    public DangNhapListener(ViewDangNhap vdn) {
        this.vdn = vdn;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
            vdn.dangnhap();
        
    }

}

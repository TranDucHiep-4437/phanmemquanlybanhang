package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miniview.viewTK;

public class TaiKhoanListener implements ActionListener {

    public viewTK vtk;

    public TaiKhoanListener(viewTK vtk) {
        this.vtk = vtk;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String sc = e.getActionCommand();
        if (sc.equals("Thêm")) {
            vtk.themtk();
        } if (sc.equals("Sửa")) {
            vtk.suatk();
        }if (sc.equals("Xóa")) {
            vtk.xoatk();
        }if (sc.equals("Đặt lại")) {
            vtk.datlai();
        }
        
    }
    }



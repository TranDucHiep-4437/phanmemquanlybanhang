package model;

public class LichSuGiaoDich {

    private int idgiaodich;
    private String ngaygiaodich;
    private String thoigiangiaodich;
    private String sodienthoai;
    private String tenkhachhang;
    private double tongtien;
    private double tienlai;
    
    public LichSuGiaoDich(int idgiaodich, String ngaygiaodich, String thoigiangiaodich, String sodienthoai, String tenkhachhang, double tongtien, double tienlai) {
        this.idgiaodich = idgiaodich;
        this.ngaygiaodich = ngaygiaodich;
        this.thoigiangiaodich = thoigiangiaodich;
        this.sodienthoai = sodienthoai;
        this.tenkhachhang = tenkhachhang;
        this.tongtien = tongtien;
        this.tienlai = tienlai;
    }

    public LichSuGiaoDich(String ngaygiaodich, String thoigiangiaodich, String sodienthoai, String tenkhachhang, double tongtien, double tienlai) {
        this.ngaygiaodich = ngaygiaodich;
        this.thoigiangiaodich = thoigiangiaodich;
        this.sodienthoai = sodienthoai;
        this.tenkhachhang = tenkhachhang;
        this.tongtien = tongtien;
        this.tienlai = tienlai;
    }

    public LichSuGiaoDich(String ngaygiaodich, String thoigiangiaodich, double tongtien, double tienlai) {
        this.ngaygiaodich = ngaygiaodich;
        this.thoigiangiaodich = thoigiangiaodich;
        this.tongtien = tongtien;
        this.tienlai = tienlai;
    }

    public LichSuGiaoDich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public LichSuGiaoDich() {
    }

    public int getIdgiaodich() {
        return idgiaodich;
    }

    public void setIdgiaodich(int idgiaodich) {
        this.idgiaodich = idgiaodich;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public String getThoigiangiaodich() {
        return thoigiangiaodich;
    }

    public void setThoigiangiaodich(String thoigiangiaodich) {
        this.thoigiangiaodich = thoigiangiaodich;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public double getTienlai() {
        return tienlai;
    }

    public void setTienlai(double tienlai) {
        this.tienlai = tienlai;
    }
    
}

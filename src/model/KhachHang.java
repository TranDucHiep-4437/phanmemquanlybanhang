package model;

public class KhachHang {
    public String sodienthoai; 
    private String tenkhachhang;
    private int solanmua;

    public KhachHang(String sodienthoai, String tenkhachhang, int solanmua) {
        this.sodienthoai = sodienthoai;
        this.tenkhachhang = tenkhachhang;
        this.solanmua = solanmua;
    }

    public KhachHang(String sodienthoai, String tenkhachhang) {
        this.sodienthoai = sodienthoai;
        this.tenkhachhang = tenkhachhang;
    }

    public KhachHang() {
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

    public int getSolanmua() {
        return solanmua;
    }

    public void setSolanmua(int solanmua) {
        this.solanmua = solanmua;
    }
}

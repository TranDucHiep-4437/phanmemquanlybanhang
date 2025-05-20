package model;

public class DoanhThu {
    private double tongtien;
    private double tienlai;
    private int solanmua;

    public DoanhThu(double tongtien, double tienlai, int solanmua) {
        this.tongtien = tongtien;
        this.tienlai = tienlai;
        this.solanmua = solanmua;
    }

    public DoanhThu() {
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

    public int getSolanmua() {
        return solanmua;
    }

    public void setSolanmua(int solanmua) {
        this.solanmua = solanmua;
    }
    

}

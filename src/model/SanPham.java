package model;

public class SanPham {
    private int id;
    private String tensanpham;
    private double gianhap;
    private double giaban;
    private int soluong;

    public SanPham(int id, String tensanpham, double gianhap, double giaban, int soluong) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public SanPham(int id, String tensanpham) {
        this.id = id;
        this.tensanpham = tensanpham;
    }

    public SanPham(int id, int soluong) {
        this.id = id;
        this.soluong = soluong;
    }

    public SanPham(int id) {
        this.id = id;
    }

    public SanPham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    

    public SanPham() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public double getGianhap() {
        return gianhap;
    }

    public void setGianhap(double gianhap) {
        this.gianhap = gianhap;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }



}

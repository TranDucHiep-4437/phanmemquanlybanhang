package model;

public class TaiKhoan {
    private int idtaikhoan;
    private String tentaikhoan;
    private String matkhau;
    private String vaitro;

    public TaiKhoan(int idtaikhoan, String tentaikhoan, String matkhau, String vaitro) {
        this.idtaikhoan = idtaikhoan;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
    }

    public TaiKhoan(String tentaikhoan, String matkhau, String vaitro) {
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
    }

    public TaiKhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }
    

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public TaiKhoan() {
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }
    

}

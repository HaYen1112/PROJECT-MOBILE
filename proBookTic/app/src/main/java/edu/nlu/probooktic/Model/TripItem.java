package edu.nlu.probooktic.Model;

public class TripItem {
    private String chuyenDi;
    private String gioKH;
    private String giaVe;
    private String bienSoXe;

    public TripItem(String chuyenDi, String gioKH, String giaVe, String bienSoXe) {
        this.chuyenDi = chuyenDi;
        this.gioKH = gioKH;
        this.giaVe = giaVe;
        this.bienSoXe = bienSoXe;
    }

    public void setChuyenDi(String chuyenDi) {
        this.chuyenDi = chuyenDi;
    }

    public void setGioKH(String gioKH) {
        this.gioKH = gioKH;
    }

    public void setGiaVe(String giaVe) {
        this.giaVe = giaVe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public String getChuyenDi() {
        return chuyenDi;
    }

    public String getGioKH() {
        return gioKH;
    }

    public String getGiaVe() {
        return giaVe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }
}

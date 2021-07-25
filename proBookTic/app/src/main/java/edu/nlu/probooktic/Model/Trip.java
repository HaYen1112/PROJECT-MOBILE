package edu.nlu.probooktic.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Trip {
    private String idtrip;
    private String diemKH;
    private String diemKT;
    private Date date;
    private String tgKH;
    private String tgKT;
    private String bienSo;

    public Trip() {
    }

    public Trip(String idtrip, String diemKH, String diemKT, Date date, String tgKH, String tgKT, String bienSo) {
        this.idtrip = idtrip;
        this.diemKH = diemKH;
        this.diemKT = diemKT;
        this.date = date;
        this.tgKH = tgKH;
        this.tgKT = tgKT;
        this.bienSo = bienSo;
    }

    public String getIdtrip() {
        return idtrip;
    }

    public void setIdtrip(String idtrip) {
        this.idtrip = idtrip;
    }

    public String getDiemKH() {
        return diemKH;
    }

    public void setDiemKH(String diemKH) {
        this.diemKH = diemKH;
    }

    public String getDiemKT() {
        return diemKT;
    }

    public void setDiemKT(String diemKT) {
        this.diemKT = diemKT;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTgKH() {
        return tgKH;
    }

    public void setTgKH(String tgKH) {
        this.tgKH = tgKH;
    }

    public String getTgKT() {
        return tgKT;
    }

    public void setTgKT(String tgKT) {
        this.tgKT = tgKT;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    @Override
    public String toString() {
        return diemKH + " - " + diemKT + "\t Thời gian: " + tgKH + " - " + tgKT + "\nNgày: " + dateToString(date);
    }

    public static Trip toTrip(String infotrip) throws ParseException {

        infotrip= infotrip.replaceAll("Thời gian:", "");
        infotrip= infotrip.replaceAll("Ngày:", "");

        StringTokenizer tokenizer = new StringTokenizer(infotrip, "\t");
        String benXe = tokenizer.nextToken();

        StringTokenizer tokenizer1 = new StringTokenizer(tokenizer.nextToken(), "\n");
        String TG = tokenizer1.nextToken();
        String date = tokenizer1.nextToken();

        StringTokenizer tokenizer2 = new StringTokenizer(TG, "-");
        StringTokenizer tokenizer3 = new StringTokenizer(benXe, "-");

        Trip trip1 = new Trip(null, tokenizer3.nextToken(), tokenizer3.nextToken(), stringToDate(date), tokenizer2.nextToken(), tokenizer2.nextToken(), null);

        return trip1;
        // System.out.println(tokenizer1+tokenizer2);

    }

    public static void main(String[] args) throws ParseException {
        System.out.println(Trip.toTrip("Tiền Giang - TP.HCM\t Thời gian: 9:00 - 13:00\nNgày: 2021-07-19").toString());
        ;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        return s;
    }


    public static Date stringToDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        d = format.parse(s.trim());
        return d;
    }
}

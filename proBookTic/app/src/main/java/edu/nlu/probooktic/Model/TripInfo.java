package edu.nlu.probooktic.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class TripInfo {

    private String diemKH;
    private String diemKT;
    private String tgKH;
    private String tgKT;
    private String bienSo;

    public TripInfo() {
    }

    public TripInfo(String diemKH, String diemKT, String tgKH, String tgKT, String bienSo) {

        this.diemKH = diemKH;
        this.diemKT = diemKT;
        this.tgKH = tgKH;
        this.tgKT = tgKT;
        this.bienSo = bienSo;
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

    public static ArrayList<TripInfo> createList(){
        ArrayList<TripInfo>ds= new ArrayList<>();
        ds.add(new TripInfo("TP HCM", "HA NOI",  "6H30", "11H30","59B-00156"));
        ds.add(new TripInfo("TP HCM", "HA NOI",  "7H30", "12H30","57B-85586"));
        ds.add(new TripInfo("TP HCM", "HA NOI",  "8H30", "13H30","59B-08964"));
        ds.add(new TripInfo( "TP HCM", "HA NOI",  "9H30", "14H30","56B-22545"));
        ds.add(new TripInfo( "TP HCM", "HA NOI",  "10H30", "15H30","59D-01578"));
        ds.add(new TripInfo( "TP HCM", "HA NOI",  "11H30", "16H30","59A-58814"));


        ds.add(new TripInfo( "HA NOI", "TP HCM",  "6H30", "11H30","51C-25896"));
        ds.add(new TripInfo( "HA NOI", "TP HCM", "7H30", "12H30","51C-25896"));
        ds.add(new TripInfo( "HA NOI", "TP HCM",  "8H30", "13H30","51C-25896"));
        ds.add(new TripInfo( "HA NOI", "TP HCM",  "9H30", "14H30","51C-25896"));
        ds.add(new TripInfo( "HA NOI", "TP HCM",  "10H30", "15H30","51C-25896"));
        ds.add(new TripInfo( "HA NOI", "TP HCM",  "11H30", "16H30","51C-25896"));

        ds.add(new TripInfo( "HUE", "NHA TRANG",  "6H30", "11H30","51C-25896"));
        ds.add(new TripInfo( "HUE", "NHA TRANG",  "7H30", "12H30","51C-25896"));
        ds.add(new TripInfo( "HUE", "NHA TRANG",  "8H30", "13H30","51C-25896"));
        ds.add(new TripInfo( "HUE", "NHA TRANG",  "9H30", "14H30","51C-25896"));
        ds.add(new TripInfo( "HUE", "NHA TRANG",  "10H30", "15H30","51C-25896"));
        ds.add(new TripInfo( "HUE", "NHA TRANG",  "11H30", "16H30","51C-25896"));

        ds.add(new TripInfo( "NHA TRANG", "HUE",  "6H30", "11H30","51C-25896"));
        ds.add(new TripInfo( "NHA TRANG", "HUE",  "7H30", "12H30","51C-25896"));
        ds.add(new TripInfo( "NHA TRANG", "HUE",  "8H30", "13H30","51C-25896"));
        ds.add(new TripInfo( "NHA TRANG", "HUE",  "9H30", "14H30","51C-25896"));
        ds.add(new TripInfo( "NHA TRANG", "HUE",  "10H30", "15H30","51C-25896"));
        ds.add(new TripInfo( "NHA TRANG", "HUE",  "11H30", "16H30","51C-25896"));

        ds.add(new TripInfo( "KHANH HOA", "TP HCM", "7H40", "10H40","51C-25896"));
        ds.add(new TripInfo( "TP HCM", "KHANH HOA", "7H50", "10H45","51C-25896"));
        ds.add(new TripInfo("KHANH HOA", "TP HCM",  "8H40", "10H40","51C-25896"));
        ds.add(new TripInfo( "TP HCM", "KHANH HOA","8H50", "10H45","51C-25896"));
        ds.add(new TripInfo( "KHANH HOA", "TP HCM",  "1H40", "10H40","51C-25896"));
        ds.add(new TripInfo( "TP HCM", "KHANH HOA", "4H50", "10H45","51C-25896"));
    return ds;
    }
    public static void insertDB(ArrayList<TripInfo> ds){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("TripInfomation");
        for (TripInfo tm:ds) {
         mDatabase.push().setValue(tm);
        }
    }

    public static void main(String[] args) {

    }
}

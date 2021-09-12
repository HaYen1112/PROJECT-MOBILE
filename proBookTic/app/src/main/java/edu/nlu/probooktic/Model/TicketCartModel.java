package edu.nlu.probooktic.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import edu.nlu.probooktic.View.Info_Cus_Get_Ticket;
import edu.nlu.probooktic.View.TicketCart;


public class TicketCartModel {
    private String idCus;
    private String idTicket;
    private String numSeat;
    private String nameOwner;
    private String idCard;
    private String expirePayment;
    private String info_trip;
    private long price;

    public TicketCartModel() {
    }

    public TicketCartModel(String idCus, String idTicket, String numSeat, String nameOwner, String idCard, String expirePayment, String info_trip,long price) {
        this.idCus = idCus;
        this.idTicket = idTicket;
        this.numSeat = numSeat;
        this.nameOwner = nameOwner;
        this.idCard = idCard;
        this.expirePayment = expirePayment;
        this.info_trip = info_trip;
        this.price=price;
    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getNumSeat() {
        return numSeat;
    }

    public void setNumSeat(String numSeat) {
        this.numSeat = numSeat;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getExpirePayment() {
        return expirePayment;
    }

    public void setExpirePayment(String expirePayment) {
        this.expirePayment = expirePayment;
    }

    public String getInfo_trip() {
        return info_trip;
    }

    public void setInfo_trip(String info_trip) {
        this.info_trip = info_trip;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public static   ArrayList<String>  getListSeat(ArrayList<TicketCartModel> arr){
        ArrayList<String> listSeat = new ArrayList<>();
        for (TicketCartModel tcm : arr) {
            listSeat.add(tcm.numSeat);
            }
        return listSeat;
    }
//    public ArrayList<TicketCartModel> createList() {
//         Trip trip = new TripInfo("TR0001", "Tiền Giang", "TP.HCM", new Date(), "9:00", "13:00", "63F-5236");
//
//        ArrayList<TicketCartModel> arr = new ArrayList<>();
//        arr.add(new TicketCartModel("C001", "IDVE002", "A2", "Nguyễn Ngọc Lan", "121455555", TicketDAO.dateExpire(), trip.toString(),125000));
//        arr.add(new TicketCartModel("C001", "IDVE016", "D4", "Lê Thị Diệu", "2465454545", TicketDAO.dateExpire(), trip.toString(),125000));
//        arr.add(new TicketCartModel("C001", "IDVE025", "G1", "Nguyễn Phước Toản", "214545454", TicketDAO.dateExpire(), trip.toString(),125000));
//        arr.add(new TicketCartModel("C001", "IDVE027", "G3", "Lê Quốc Khanh", "546455545", TicketDAO.dateExpire(), trip.toString(),125000));
//        return arr;
//    }
//
//    public static void writeToDB(ArrayList<TicketCartModel> arr) {
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        for (TicketCartModel tcm : arr) {
//            mDatabase.child("ticketCart").child(tcm.getIdTicket()).setValue(tcm);
//
//        }
//
//    }
//
//    public void saveTicketInCart1() {
//        //lưu vào giỏ
//        ArrayList<TicketCartModel> arr = createList();
//        for (TicketCartModel tm : arr) {
//            tm.setExpirePayment(TicketDAO.dateExpire());
//        }
//        TicketCartModel.writeToDB(arr);
//        //Lưu vào vé để cập nhật trạng thái của vé
//
//    }
//
//    public static void main(String[] args) {
//        TicketCartModel t = new TicketCartModel();
//        t.saveTicketInCart1();
//    }
}

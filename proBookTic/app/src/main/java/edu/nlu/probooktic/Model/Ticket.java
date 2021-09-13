package edu.nlu.probooktic.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Ticket implements Serializable {
    private String idTrip;
    private String idTic;
    private String numOfSeat;
    private long price;
    private String status;
    private String isPayment;

    public Ticket(String idTrip, String idTic, String numOfSeat, long price, String status, String isPayment) {
        this.idTrip = idTrip;
        this.idTic = idTic;
        this.numOfSeat = numOfSeat;
        this.price = price;
        this.status = status;
        this.isPayment = isPayment;
    }

    public Ticket(String idTrip, String idTic, String isPayment, String numOfSeat, long price, String status) {
        this.idTrip = idTrip;
        this.idTic = idTic;
        this.numOfSeat = numOfSeat;
        this.price = price;
        this.status = status;
        this.isPayment = isPayment;
    }

    public Ticket() {
    }

    public String getIdTic() {
        return idTic;
    }

    public void setIdTic(String idTic) {
        this.idTic = idTic;
    }

    public String getNumOfSeat() {
        return numOfSeat;
    }

    public void setNumOfSeat(String numOfSeat) {
        this.numOfSeat = numOfSeat;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(String isPayment) {
        this.isPayment = isPayment;
    }

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public static ArrayList createListTicket() {
        ArrayList<Ticket> list = new ArrayList<>();
        list.add(new Ticket("TR0001", "IDVE001", "A1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE002", "A2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE003", "A3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE004", "A4", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE005", "B1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE006", "B2", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE007", "B3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE008", "B4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE009", "C1", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE010", "C2", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE011", "C3", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE012", "C4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE013", "D1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE014", "D2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE015", "D3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE016", "D4", 125000, "Đang bán", "null"));
        list.add(new Ticket("TR0001", "IDVE017", "E1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE018", "E2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE019", "E3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE020", "E4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE021", "F1", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE022", "F2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE023", "F3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE024", "F4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE025", "G1", 125000, "Đang bán", "null"));
        list.add(new Ticket("TR0001", "IDVE026", "G2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE027", "G3", 125000, "Đang bán", "null"));
        list.add(new Ticket("TR0001", "IDVE028", "G4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE029", "H1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE030", "H2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE031", "H3", 125000, "Đã bán", "null"));
        list.add(new Ticket("TR0001", "IDVE032", "H4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE033", "I1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE034", "I2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE035", "I3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE036", "I4", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE037", "J1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE038", "J2", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE039", "J3", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE040", "J4", 125000, "Trống", "null"));


        return list;
    }


    public static ArrayList createListTicket1() {
        ArrayList<Ticket> list = new ArrayList<>();
        list.add(new Ticket("TR0001", "IDVE001", "A1", 125000, "Trống", "null"));
        list.add(new Ticket("TR0001", "IDVE002", "A2", 125000, "Trống", "null"));


        return list;
    }
    public String checkGiuVe() {
        String re = "";
        try {
            if (checkTime(isPayment))
                re = "A";
            else
                re = "R";
        } catch (Exception e) {
            re = "R";
        }
        return re;
    }

    public static String listToString(ArrayList<Ticket> list) {
        int count = 0;
        String result = "null";
        for (Ticket t : list) {
            count++;
            if (count == 2) {
                if (t.getStatus().equals("Trống")) result += "A_";
                else if (t.getStatus().equals("Đã bán")) result += "U_";
                else if (t.getStatus().equals("Đang bán")) result += t.checkGiuVe()+"_";
            } else if (count == 4) {
                if (t.getStatus().equals("Trống")) result += "A/";
                else if (t.getStatus().equals("Đã bán")) result += "U/";
                else if (t.getStatus().equals("Đang bán")) result +=t.checkGiuVe()+"/";
                count = 0;
            } else {
                if (t.getStatus().equals("Trống")) result += "A";
                else if (t.getStatus().equals("Đã bán")) result += "U";
                else if (t.getStatus().equals("Đang bán")) result += t.checkGiuVe();
            }

        }
        return result;
    }


    public boolean checkTime(String time) throws ParseException {
        Date d1 = TicketDAO.stringToDate(time);
        Date d2 = new Date();
        if (d2.getTime() - d1.getTime() > 0)
            return true;
        return false;
    }

    public static ArrayList<Ticket> getListSeatChoosed() {
        ArrayList<Ticket> listTic = Ticket.createListTicket();
        ArrayList<Ticket> re = new ArrayList<>();
        String selectedIds = "1,5,6,";
        char[] c = selectedIds.toCharArray();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < c.length - 1; i++) {
            str.append(c[i]);
        }
        StringTokenizer tokenizer = new StringTokenizer(str.toString(), ",");
        while (tokenizer.hasMoreTokens()) {
            re.add(listTic.get(Integer.parseInt(tokenizer.nextToken()) - 1));
        }
        return re;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTic='" + idTic + '\'' +
                ", numOfSeat='" + numOfSeat + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +

                '}';
    }

    public void inSertToDB() {
        ArrayList<Ticket> list = createListTicket();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference();
        for (Ticket t : list) {
            myRef.child("ticketing").child(t.getIdTic()).setValue(t);
        }

    }

    public static void main(String[] args) {
        // System.out.println(Ticket.listToString(Ticket.createListTicket()));
        // System.out.println(Ticket.getListSeatChoosed().toString());
        Ticket t = new Ticket();
        t.inSertToDB();
    }
}

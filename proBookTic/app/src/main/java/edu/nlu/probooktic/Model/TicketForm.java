package edu.nlu.probooktic.Model;

public class TicketForm {
    private String idTicket;
    private String nameOwner;
    private String numCar;
    private String numSeat;
    private long price;

    public TicketForm() {
    }

    public TicketForm(String idTicket, String nameOwner, String numCar, String numSeat, long price) {
        this.idTicket = idTicket;
        this.nameOwner = nameOwner;
        this.numCar = numCar;
        this.numSeat = numSeat;
        this.price = price;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNumCar() {
        return numCar;
    }

    public void setNumCar(String numCar) {
        this.numCar = numCar;
    }

    public String getNumSeat() {
        return numSeat;
    }

    public void setNumSeat(String numSeat) {
        this.numSeat = numSeat;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketForm{" +
                "idTicket='" + idTicket + '\'' +
                ", nameOwner='" + nameOwner + '\'' +
                ", numCar='" + numCar + '\'' +
                ", numSeat='" + numSeat + '\'' +
                ", price=" + price +
                '}';
    }
}

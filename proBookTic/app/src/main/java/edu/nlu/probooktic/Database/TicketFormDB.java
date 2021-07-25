package edu.nlu.probooktic.Database;

public class TicketFormDB {
    private String idTicket;
    private String nameOwner;
    private String idCard;
    private String idPayment;

    public TicketFormDB() {
    }

    public TicketFormDB(String idTicket, String nameOwner, String idCard, String idPayment) {
        this.idTicket = idTicket;
        this.nameOwner = nameOwner;
        this.idCard = idCard;
        this.idPayment = idPayment;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }
}

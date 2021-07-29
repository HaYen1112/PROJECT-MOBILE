package com.example.tbd.Ticket;

import java.sql.Time;

public class Ticket {
    private String id;
    private String nameRoute;
    private int priceTicket;
    private String category;
    private String numSeat;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;

    public Ticket(String id, String nameRoute, int priceTicket, String category, String numSeat, String departureDate, String arrivalDate, String departureTime) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.priceTicket = priceTicket;
        this.category = category;
        this.numSeat = numSeat;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public int getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumSeat() {
        return numSeat;
    }

    public void setNumSeat(String numSeat) {
        this.numSeat = numSeat;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", nameRoute='" + nameRoute + '\'' +
                ", priceTicket=" + priceTicket +
                ", category='" + category + '\'' +
                ", numSeat=" + numSeat +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
        "}";
    }
    }




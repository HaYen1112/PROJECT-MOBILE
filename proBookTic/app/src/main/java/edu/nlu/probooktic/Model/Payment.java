package edu.nlu.probooktic.Model;

import java.util.Date;

public class Payment {
    private String id;
    private String name;
    private Date date;

    public Payment() {
    }

    public Payment(String id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

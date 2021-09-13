package edu.nlu.probooktic.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Acount implements Serializable {
    private String name;
    private String passwors;
    private String mail;
    private String phone;

    public Acount(String name, String passwors, String mail, String phone) {
        this.name = name;
        this.passwors = passwors;
        this.mail = mail;
        this.phone = phone;
    }
    public void setName (String name){
        this.name = name;
    }

    public void setPasswors (String passwors){
        this.passwors = passwors;
    }

    public void setMail (String mail){
        this.mail = mail;
    }

    public void setPhone (String phone){
        this.phone = phone;
    }

    public String getName () {
        return name;
    }

    public String getPasswors () {
        return passwors;
    }

    public String getMail () {
        return mail;
    }

    public String getPhone () {
        return phone;
    }
    public void inSertToDB() {
        ArrayList<Acount> list = createListAcount();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference();
        for (Acount acount : list) {
            myRef.child("Acount").child(acount.getName()).setValue(acount);
            firebaseAuth.createUserWithEmailAndPassword(acount.getMail(), acount.getPasswors());

        }
    }
    public ArrayList<Acount> createListAcount() {
            ArrayList<Acount> arrayList = new ArrayList<>();
            arrayList.add(new Acount("Trương Văn Xinh", "123456", "xinh@gmail.com", "0844362969"));
            arrayList.add(new Acount("Lâm Hà Yến", "123456", "yen@gmail.com", "0844362969"));
            arrayList.add(new Acount("Nguyễn Ngọc Thanh Xuân", "123456", "xuan@gmail.com", "0844362969"));
            arrayList.add(new Acount("Nguyễn Trần Minh Hiếu", "123456", "hieu@gmail.com", "0844362969"));
            arrayList.add(new Acount("Nguyễn Hoàng Vinh", "123456", "vinh@gmail.com", "0844362969"));
        return arrayList;
    }

    public Acount() {
    }

    public static void main(String[] args) {
        Acount acount = new Acount();
        acount.inSertToDB();
        System.out.println(acount.createListAcount().get(1).name);
    }


    }

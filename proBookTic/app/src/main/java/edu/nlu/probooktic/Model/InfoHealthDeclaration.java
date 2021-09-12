package edu.nlu.probooktic.Model;

public class InfoHealthDeclaration {
    private String idCus;
    private String nameCus;
    private String dayOfBirth;
    private String gender;
    private String numCMND;
    private String address;
    private String phone;
    private String email;
    private String placesIn14Days;
    private String sot;
    private String ho;
    private String khoTho;
    private String dauHong;
    private String buonNon;
    private String tieuChay;
    private String xuatHuyet;
    private String noiBan;


    public InfoHealthDeclaration() {

    }

    public InfoHealthDeclaration(String idCus, String nameCus, String dayOfBirth, String gender, String numCMND, String address, String phone, String email, String placesIn14Days, String sot, String ho, String khoTho, String dauHong, String buonNon, String tieuChay, String xuatHuyet, String noiBan) {
        this.idCus = idCus;
        this.nameCus = nameCus;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
        this.numCMND = numCMND;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.placesIn14Days = placesIn14Days;
        this.sot = sot;
        this.ho = ho;
        this.khoTho = khoTho;
        this.dauHong = dauHong;
        this.buonNon = buonNon;
        this.tieuChay = tieuChay;
        this.xuatHuyet = xuatHuyet;
        this.noiBan = noiBan;

    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumCMND() {
        return numCMND;
    }

    public void setNumCMND(String numCMND) {
        this.numCMND = numCMND;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlacesIn14Days() {
        return placesIn14Days;
    }

    public void setPlacesIn14Days(String placesIn14Days) {
        this.placesIn14Days = placesIn14Days;
    }

    public String getSot() {
        return sot;
    }

    public void setSot(String sot) {
        this.sot = sot;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getKhoTho() {
        return khoTho;
    }

    public void setKhoTho(String khoTho) {
        this.khoTho = khoTho;
    }

    public String getDauHong() {
        return dauHong;
    }

    public void setDauHong(String dauHong) {
        this.dauHong = dauHong;
    }

    public String getTieuChay() {
        return tieuChay;
    }

    public void setTieuChay(String tieuChay) {
        this.tieuChay = tieuChay;
    }

    public String getXuatHuyet() {
        return xuatHuyet;
    }

    public void setXuatHuyet(String xuatHuyet) {
        this.xuatHuyet = xuatHuyet;
    }

    public String getNoiBan() {
        return noiBan;
    }

    public void setNoiBan(String noiBan) {
        this.noiBan = noiBan;
    }

    public String getBuonNon() {
        return buonNon;
    }

    public void setBuonNon(String buonNon) {
        this.buonNon = buonNon;
    }

    @Override
    public String toString() {
        return "InfoHealthDeclaration{" +
                "idCus='" + idCus + '\'' +
                ", nameCus='" + nameCus + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", gender='" + gender + '\'' +
                ", numCMND='" + numCMND + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", placesIn14Days='" + placesIn14Days + '\'' + "Symptom:[" +
                ", sot='" + sot + '\'' +
                ", ho='" + ho + '\'' +
                ", khoTho='" + khoTho + '\'' +
                ", dauHong='" + dauHong + '\'' +
                ", buonNon='" + buonNon + '\'' +
                ", tieuChay='" + tieuChay + '\'' +
                ", xuatHuyet='" + xuatHuyet + '\'' +
                ", noiBan='" + noiBan + "]" + '\'' +
                '}';
    }
}
package com.company;

public class Station {
    private String name;
    private String surname;
    private String patronymic;
    private int num;

    public Station(String surname, String name, String patronymic, int num){
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAll(Station s){
        this.name = s.getName();
        this.surname = s.getSurname();
        this.patronymic = s.getPatronymic();
        this.num = s.getNum();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString(){
        return String.format("%-30s|%-30s|%-30s|%-10d|%n", this.surname, this.name, this.patronymic, this.num);
    }
}

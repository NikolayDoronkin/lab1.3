package com.company;

import java.util.Scanner;

public class Main {

    static boolean checker = true;
    private static MyArrayList<Station> info = new MyArrayList<>();

    public static void main(String[] args) {
        info.add(new Station("доронкИН", "ИГОрь", "николаевИЧ", 8209912));
        info.add(new Station("ДОРОнкин", "Николай", "Степанович", 8209912));
        info.add(new Station("дОрОнкиН", "никОЛАЙ", "ИГОРЕВич", 8020165));
        menu();
        while (checker) {
            switch (choose()) {
                case 1://добавить в
                    int s;
                    info.add(create());
                    System.out.println("СОХРАНЕНО");
                    menu();
                    break;
                case 2://вывести список
                    sort(info);
                    if (!info.isEmpty()) {
                        show(info);
                    } else {
                        System.out.println("Список пуст");
                    }
                    s = 0;
                    if (s == selection()) menu();
                    else checker = false;
                    break;
                case 3://поиск по номеру телефона фамилии
                    int temp = Integer.parseInt(inputnum());
                    if(checkNum(temp, info)) temp = Integer.parseInt(inputnum());
                    boolean b = false;
                    for(int i = 0; i < info.size(); i++){
                        if(temp == info.get(i).getNum()){
                            System.out.println(info.get(i).toString());
                            b = true;
                        }
                    }
                    if(!b) System.out.println("Совпадений не найдено!");
                    s = 0;
                    if (s == selection()) menu();
                    else checker = false;
                    break;
                case 4://поиск по фамилии номера телефона
                    String namex = inputstr("Имя");
                    String surnamex = inputstr("Фамилию");
                    String patronymicx = inputstr("Отчество");
                    boolean a = true;
                    for(int i = 0; i < info.size(); i++){
                        if(surnamex.toLowerCase().equals(info.get(i).getSurname().toLowerCase())){
                            if(namex.toLowerCase().equals(info.get(i).getName().toLowerCase())){
                                if(patronymicx.toLowerCase().equals((info.get(i).getPatronymic().toLowerCase()))){
                                    System.out.println(info.get(i).toString());
                                } else a = false;
                            } else a = false;
                        } else a = false;
                    }
                    if(a) System.err.println("Совпадений не найдено!");
                    s = 0;
                    if (s == selection()) menu();
                    else checker = false;
                    break;
                case 5://закончить прогу
                    checker = false;
                    break;
            }
        }
    }


    private static void sort(MyArrayList<Station> l) {
        boolean check = true, c = false;
        while (check) {
            check = false;
            for (int i = 1; i < l.size(); i++) {
                if (l.get(i).getSurname().toLowerCase().equals(l.get(i - 1).getSurname().toLowerCase())){
                    if(l.get(i).getName().toLowerCase().equals(l.get(i - 1).getName().toLowerCase())){
                        c = compare(l.get(i).getPatronymic(), l.get(i - 1).getPatronymic());
                    } else
                        c = compare(l.get(i).getName(), l.get(i - 1).getName());
                } else
                    c = compare(l.get(i).getSurname(), l.get(i - 1).getSurname());
                if (c) {
                    swap(l.get(i), l.get(i - 1));
                    check = true;
                }
            }
        }
    }

    private static void swap(Station first, Station second) {
        Station temp = new Station("", "", "", 0);
        temp.setAll(first);
        first.setAll(second);
        second.setAll(temp);
    }

    private static boolean compare(String f, String s) {
        boolean b = true;
        if (f.toLowerCase().charAt(0) != s.toLowerCase().charAt(0)) {
            return b = f.toLowerCase().charAt(0) < s.toLowerCase().charAt(0);
        } else {
            for (int i = 1; i < Math.min(f.length(), s.length()); i++) {
                if (f.toLowerCase().charAt(i) != s.toLowerCase().charAt(i)) {
                    return b = f.toLowerCase().charAt(i) < s.toLowerCase().charAt(i);
                }
            }
            b = false;
        }
        return b;
    }

    private static void menu() {
        MyArrayList<String> menu = new MyArrayList<>();
        menu.add("Добавить объект в список");
        menu.add("Вывести список");
        menu.add("Поиск объекта по номеру телефона");
        menu.add("Поиск номера телефона по ФИО объекта");
        menu.add("Выход");
        for (int pos = 0; pos < menu.size(); pos++) {
            System.out.println(pos + 1 + " - " + menu.get(pos));
        }
        menu.clear();
    }

    private static int choose() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            if (str.matches("\\s*[1-8]{1}\\s*")) return Integer.parseInt(str.replaceAll("\\s+|, \\s*", ""));
            else System.err.println("Повторите попытку: ");
        }
    }

    private static String inputstr(String s) {
        System.out.println("Введите " + s + ":");
        while (true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            if (str.matches("[\\s*[a-zA-Zа-яА-Я]\\s+]+")) return str.replaceAll("\\s+|, \\s*", "");
            else System.err.println("Повторите попытку: ");
        }
    }

    private static String inputnum() {
        System.out.println("Введите номер телефона");
        while (true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            boolean b = true;
            if (str.matches("\\s*[0-9]{7}\\s*"))
                return str.replaceAll("\\s+|, \\s*", "");
            else System.err.println("Повторите попытку: ");
        }
    }

    private static boolean checkNum(int number, MyArrayList<Station> list){
        boolean b = true;
        for(int i = 0; i < list.size(); i++){
            if(number == list.get(i).getNum()) {
                b = false;
                break;
            }
        }
        return b;
    }

    private static Station create() {
        return new Station(inputstr("Фамилию"), inputstr("Имя"), inputstr("Отчество"), Integer.parseInt(inputnum()));
    }

    private static void show(MyArrayList<Station> s) {
        System.out.format("%-30s|%-30s|%-30s|%-10s|%n", "Surname", "Name", "Patronymic", "Number");
        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
        }
    }

    private static int selection() {
        int b = 0;
        System.out.println("\n0 - Вернуться в главное меню\n1 - Завершить программу");
        while (true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            if (str.matches("\\s*[0-1]\\s*")) {
                return Integer.parseInt(str.replaceAll("\\s+|, \\s*", ""));
            } else
                System.err.println("Повторите попытку: ");
        }
    }
}
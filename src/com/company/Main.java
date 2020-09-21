package com.company;

import java.util.Scanner;

public class Main {

    static boolean checker = true;
    private static MyArrayList<Station> info = new MyArrayList<>();

    public static void main(String[] args) {
        MyArrayList<String> myMenu = new MyArrayList<>();
        myMenu.add("Добавить объект в список");
        myMenu.add("Вывести список");
        myMenu.add("Поиск объекта по номеру телефона");
        myMenu.add("Поиск номера телефона по ФИО объекта");
        myMenu.add("Выход");
        info.add(new Station("доронкИН", "ИГОрь", "николаевИЧ", 8209912));
        info.add(new Station("ДОРОнкин", "Николай", "Степанович", 8209912));
        info.add(new Station("дОрОнкиН", "никОЛАЙ", "ИГОРЕВич", 8020165));
        menu(myMenu);
        while (checker) {
            switch (choose()) {
                case 1://добавить в
                    info.add(create());
                    System.out.println("СОХРАНЕНО");
                    menu(myMenu);
                    break;
                case 2://вывести список
                    sort(info);
                    show(info);
                    menu(myMenu);
                    break;
                case 3://поиск по номеру телефона фамилии
                    searchByNumber();
                    menu(myMenu);
                    break;
                case 4://поиск по фамилии номера телефона
                    searchByName();
                    menu(myMenu);
                    break;
                case 5://закончить прогу
                    checker = false;
                    break;
            }
        }
    }

    private static void searchByName() {
        if (!info.isEmpty()) {
            if (!equalityIn("Names")) System.err.println("Совпадений не найдено!");
        } else
            System.out.println("Cписок пуст!");
    }

    private static void searchByNumber() {
        if (!info.isEmpty()) {
            if (!equalityIn("Numbers")) System.out.println("Совпадений не найдено!");
        } else
            System.out.println("Cписок пуст!");
    }

    private static boolean equalityIn(String str) {
        boolean b;
        switch (str) {
            case "Numbers":
                int temp = 0;
                if (checkNum(temp, info)) temp = Integer.parseInt(inputnum());
                b = false;
                for (int i = 0; i < info.size(); i++) {
                    if (temp == info.get(i).getNum()) {
                        System.out.println(info.get(i).toString());
                        b = true;
                    }
                }
                break;
            case "Names":
                String namex = inputstr("Имя");
                String surnamex = inputstr("Фамилию");
                String patronymicx = inputstr("Отчество");
                b = true;
                for (int i = 0; i < info.size(); i++) {
                    if (surnamex.toLowerCase().equals(info.get(i).getSurname().toLowerCase())) {
                        if (namex.toLowerCase().equals(info.get(i).getName().toLowerCase())) {
                            if (patronymicx.toLowerCase().equals((info.get(i).getPatronymic().toLowerCase()))) {
                                System.out.println(info.get(i).toString());
                                b = true;
                            } else b = false;
                        } else b = false;
                    } else b = false;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
        return b;
    }

    private static void sort(MyArrayList<Station> l) {
        boolean check = true, c = false;
        while (check) {
            check = false;
            for (int i = 1; i < l.size(); i++) {
                if (l.get(i).getSurname().toLowerCase().equals(l.get(i - 1).getSurname().toLowerCase())) {
                    if (l.get(i).getName().toLowerCase().equals(l.get(i - 1).getName().toLowerCase())) {
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
        temp.copy(first);
        first.copy(second);
        second.copy(temp);
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

    private static void menu(MyArrayList<String> menu) {
        for (int pos = 0; pos < menu.size(); pos++) {
            System.out.println(pos + 1 + " - " + menu.get(pos));
        }
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
            String regex = "\\s";
            if (!str.matches("\\s*")) {
                if (str.matches("[\\s*[a-zA-Zа-яА-Я]\\s+]+")) return str.replaceAll("\\s+|, \\s*", "");
                else System.err.println("Повторите попытку: ");
            } else System.err.println("Повторите попытку: ");
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

    private static boolean checkNum(int number, MyArrayList<Station> list) {
        boolean b = true;
        for (int i = 0; i < list.size(); i++) {
            if (number == list.get(i).getNum()) {
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
        if (!s.isEmpty()) {
            System.out.format("%-30s|%-30s|%-30s|%-10s|%n", "Surname", "Name", "Patronymic", "Number");
            for (int i = 0; i < s.size(); i++) {
                System.out.print(s.get(i));
            }
        } else
            System.out.println("Cписок пуст!");
    }
}
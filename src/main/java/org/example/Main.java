package org.example;

import java.util.Scanner;

import static org.example.DataGathering.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите через пробел следующие данные:\n" +
                "Фамилия Имя Отчество, дата рождения (в формате dd.mm.yyyy), номер телефона (только цифры), пол (f/m)");

        String inputStr = scanner.nextLine();
        int checkKod = checkCountInput(inputStr);
        if (checkKod == -1) {
            System.out.println("Введено недостаточное кол-во данных.");
        } else if (checkKod == -2) {
            System.out.println("Введено данных больше, чем ожидалось");
        } else {

            try {
                saveData(inputStr);
            } catch (IllegalDataFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }


}
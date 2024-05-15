package org.example;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DataGathering {
    /**
     * Метод проверяет введенные данные по количеству. Если количество не совпадает с требуемым, возвращает код ошибки,
     *
     * @param inputStr строка для проверки
     * @return код результата: 0 - строка корректна, -1 - данных меньше 6, -2 - данных больше 6
     */
    public static int checkCountInput(String inputStr) {
        int count = inputStr.split(" ").length;
        if (count < 6) {
            return -1;
        } else if (count > 6) {
            return -2;
        }
        return 0;
    }

    /**
     * Метод проверяет переданные данные и записывает в файл
     *
     * @param inputStr
     * @throws IllegalDataFormatException
     */
    public static void saveData(String inputStr) throws IllegalDataFormatException {
        checkInput(inputStr.trim());
        saveToFile(inputStr.trim().split(" ")[0], inputStr);
    }

    /**
     * Служебный метод для записи данных в файл
     *
     * @param nameFile   - имя файла
     * @param dataString строка, которую добавляем в файл
     */
    private static void saveToFile(String nameFile, String dataString) {
        File file = new File(nameFile);
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write((dataString + "\n").getBytes());

        } catch (FileNotFoundException ex) {
            System.out.println("Не удалось открыть/создать файл с именем " + nameFile + ". ошибка: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    /**
     * Служебный метод проверяет корректность ввода
     *
     * @param inputStr строка с данными для проверки
     * @throws IllegalDataFormatException
     */

    private static void checkInput(String inputStr) throws IllegalDataFormatException {
        String[] dataInput = inputStr.split(" ");
        checkString(dataInput[0], "Фамилия содержит символы, отличные от букв");
        checkString(dataInput[1], "Имя содержит символы, отличные от букв");
        checkString(dataInput[2], "Отчество содержит символы, отличные от букв");
        checkData(dataInput[3]);
        checkPhone(dataInput[4]);
        checkGender(dataInput[5]);
    }

    /**
     * Служебный метод проверяет корректность строковых данных (фамилия, имя, отчество)
     *
     * @param string  строка для проверки
     * @param message сообщение об ошибке, которое передается пользователю
     * @throws IllegalDataFormatException
     */
    private static void checkString(String string, String message) throws IllegalDataFormatException {

        if (!string.matches("[A-Za-zА-Яа-я]+")) {
            throw new IllegalDataFormatException(message);
        }
    }

    /**
     * Служебный метод проверяет, что переданное дата рождения (в формате dd.mm.yyyy)
     *
     * @param dataStr строковое представвление даты
     * @throws IllegalDataFormatException - исключение в случае, если передан некорректный формат даты
     *                                    либо несуществующая  дата
     */
    private static void checkData(String dataStr) throws IllegalDataFormatException {
        if (!dataStr.matches("[0-9]{2}[.][0-9]{2}[.][0-9]{4}")) {
            throw new IllegalDataFormatException("формат даты '" + dataStr + "' - некорректен");
        }

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        df.setLenient(false);
        try {
            df.parse(dataStr);
        } catch (ParseException ex) {
            throw new IllegalDataFormatException("дата '" + dataStr + "' - некорректна");
        }
    }

    /**
     * Служебный метод проверяет, что телефон содержит только цифры
     *
     * @param phoneStr строковое представление телефона
     * @throws IllegalDataFormatException - если строка содержит символы, отличные от телефона
     */
    private static void checkPhone(String phoneStr) throws IllegalDataFormatException {
        if (!phoneStr.matches("[0-9]+")) {
            throw new IllegalDataFormatException("Некорректный номер телефона: номер содержит символы, отличные от цифр");
        }
    }

    /**
     * Служебный метод проверяет, что гендер передан корректно
     *
     * @param gender строка с гендером
     * @throws IllegalDataFormatException - если переданая строка не соответствует шаблону (f или m)
     */
    private static void checkGender(String gender) throws IllegalDataFormatException {

        if (!gender.matches("[fFmM]")) {
            throw new IllegalDataFormatException("некорректный гендер");
        }
    }
}

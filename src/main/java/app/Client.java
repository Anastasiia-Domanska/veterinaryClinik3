package app;

import app.controller.PetController;

import java.util.Scanner;

public class Client {

    private static PetController petController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            petController = new PetController();
            // ownerController
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Ветеринарная клиника - ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - операции с питомцами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    petOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
        }
    }
}

    private static void petOperations() {
        while (true) {
           try {
               System.out.println("Выберите желаемую операцию с питомцами:");
               System.out.println("1 - Сохранить питомца");
               System.out.println("2 - Показать всех животных");
               System.out.println("3 - Выбрать питомца по id");
               System.out.println("4 - Изменить данные питомца по id");
               System.out.println("5 - Удалить питомца по id");
               System.out.println("6 - Восстановить питомца по id");
               System.out.println("7 - Показать всех питомцев конкретного владельца по id владельца");
               System.out.println("8 - Показать историю всех визитов конкретного питомца");
               System.out.println("9 - Показать список всех врачей, лечивших данного питомца");
               System.out.println("10 - Удалить все визиты питомца по id питомца");
               System.out.println("0 - Сохранить питомца");

               String input = scanner.nextLine();

               switch (input) {

                   case "0":
                       return;
                   default:
                       System.out.println("Некорректный ввод!");
                       break;
               }
           } catch (Exception e) {
               System.err.println(e.getMessage());
           }
        }
    }
    }

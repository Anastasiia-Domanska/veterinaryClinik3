package app;

import app.controller.OwnerController;
import app.controller.PetController;
import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.petExceptions.PetNotFoundException;
import app.service.OwnerService;
import app.service.PetService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static OwnerController ownerController;
    private static PetController petController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            OwnerService ownerService = new OwnerService();
            PetService petService = new PetService();

            ownerService.setPetService(petService);
            petService.setOwnerService(ownerService);


        } catch (Exception e) {
            System.err.println("Ошибка - " + e.getMessage());
        }


        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            petController = new PetController();
            ownerController = new OwnerController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Ветеринарная клиника - ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - операции с питомцами");
            System.out.println("2 - операции с владельцами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    petOperations();
                    break;
                case "2":
                    ownerOperations();
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
                System.out.println("7 - Показать количество питомцев в клинике");
                System.out.println("8 - Показать историю всех визитов конкретного питомца");
                System.out.println("9 - Показать список всех врачей, лечивших данного питомца");
                System.out.println("10 - Удалить все визиты питомца по id питомца");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя питомца:");
                        String name = scanner.nextLine();
                        System.out.println("Введите породу питомца:");
                        String breed = scanner.nextLine();
                        System.out.println("Введите цвет питомца:");
                        String color = scanner.nextLine();
                        System.out.println("Введите возраст питомца:");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println(petController.save(name, breed, color, age));
                        break;

                    case "2":
                        petController.getAllActivePets().forEach(System.out::println);
                        break;

                    case "3":
                        System.out.println("Введите id питомца:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(petController.getActivePetById(id));
                        break;

                    case "4":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Обновленный возраст питомца:");
                        age = Integer.parseInt(scanner.nextLine());
                        petController.update(id, age);
                        break;

                    case "5":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.deleteById(id);
                        break;

                    case "6":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.restoreById(id);
                        break;

                    case "7":
                        System.out.println("Количество всех животных в клинике - " + petController.getActivePetsNumber());
                        break;

                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11":
                        break;
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

    private static void ownerOperations() {

        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с владельцами:");
                System.out.println("1 - Сохранить владельца");
                System.out.println("2 - Показать всех владельцев");
                System.out.println("3 - Показать владельца по id");
                System.out.println("4 - Изменить данные владельца по id");
                System.out.println("5 - Удалить владельца по id");
                System.out.println("6 - Удалить владельца по имени");
                System.out.println("7 - Восстановить владельца по id");
                System.out.println("8 - Показать количество владельцев зарегистрированных в клинике");
                System.out.println("9 - Показать всех животных владельца по id владельца");
                System.out.println("10 - Добавить нового питомца в список животных владельца");
                System.out.println("11 - Удалить питомца по id из списка животных владельца по id");
                System.out.println("12 - Удалить всех питомцев из списка животных владельца по id");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя владельца:");
                        String name = scanner.nextLine();
                        System.out.println("Введите mail владельца:");
                        String mail = scanner.nextLine();
                        System.out.println("Введите телефон владельца:");
                        String phone = scanner.nextLine();
                        ownerController.save(name, mail, phone);
                        break;

                    case "2":
                        ownerController.getAllActiveOwners().forEach(System.out::println);
                        break;

                    case "3":
                        System.out.println("Введите id владельца:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(ownerController.getActiveOwnerById(id));
                        break;

                    case "4":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Обновленный mail владельца:");
                        mail = scanner.nextLine();
                        System.out.println("Обновленный phone владельца:");
                        phone = scanner.nextLine();
                        ownerController.update(id, mail, phone);
                        break;

                    case "5":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.deleteById(id);
                        break;

                    case "6":
                        System.out.println("Введите имя владельца:");
                        name = scanner.nextLine();
                        ownerController.deleteByName(name);
                        break;

                    case "7":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.restoreById(id);
                        break;

                    case "8":
                        System.out.println("Количество владельцев зарегистрированных в клинике - "
                                + ownerController.getActiveOwnersNumber());
                        break;

                    case "9":
                        System.out.println("Количество всех животных владельца - ");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.getTotalPetsByOwnerId(id);
                        break;

                    case "10":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Добавленный питомец:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.addNewPetToOwner(id, id);
                        break;

                    case "11":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.removePetFromOwnersList(id, id);
                        break;

                    case "12":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.clearOwnersPetList(id);
                        break;

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

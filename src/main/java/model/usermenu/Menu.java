package model.usermenu;

import model.resolver.student.StudentResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static StudentResolver resolver = new StudentResolver();
    private static Scanner scanner = new Scanner(System.in);

    public static void printMainMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. Pokaż rekordy");
        System.out.println("2. Dodaj rekord");
        System.out.println("3. Usuń rekord [id=]");
        System.out.println("4. Nadpisz rekord [id=]");
        System.out.println("5. Koniec");
        System.out.println();
    }

    public static void showReconds() {
        System.out.println("--- Lista studentów ---");
        resolver.get().forEach(line -> {
            System.out.println(String.format("%d: %s %s", line.getId(), line.getFirstname(), line.getLastname()));
        });
        System.out.println();
    }

    public static void addRecord() {
        boolean end = false;
        String firstName;
        String lastName;
        while(true) {
            System.out.println("Podaj imię");
            firstName = scanner.nextLine();
            if(firstName.equals("5")) {
                end = true;
                break;
            }
            System.out.println("Podaj nazwisko");
            lastName = scanner.nextLine();
            if(firstName.equals("5")) {
                end = true;
                break;
            }

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("Pola nazwisko i imię muszą zostać określone.");
            } else break;
        }

        if(!end) {
            Map<String, String> studMap = new HashMap<>();
            if(resolver.insert(studMap)) {
                System.out.println("Użytkownik został dodany do listy");
            } else {
                System.out.println("Użytkownik nie został dodany do listy");
            }
        }
    }

    public static void deleteRecord(int id) {
        if(resolver.delete(id)) {
            System.out.println("Usunięto żądany wpis z listy");
        } else {
            System.out.println("Nie usunięto wpisu z listy");
        }
    }

    public static void updateRecord(int id) {
        boolean end = false;
        String firstName;
        String lastName;
        while(true) {
            System.out.println("Podaj imię");
            firstName = scanner.nextLine();
            if(firstName.equals("5")) {
                end = true;
                break;
            }
            System.out.println("Podaj nazwisko");
            lastName = scanner.nextLine();
            if(firstName.equals("5")) {
                end = true;
                break;
            }

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("Pola nazwisko i imię muszą zostać określone.");
            } else break;
        }

        if(!end) {
            Map<String, String> studMap = new HashMap<>();
            if(resolver.update(id, studMap)) {
                System.out.println("Dane użytkownika zostały zaktualizowane");
            } else {
                System.out.println("Dane użytkownika nie zostały zaktualizowane");
            }
        }
    }
}
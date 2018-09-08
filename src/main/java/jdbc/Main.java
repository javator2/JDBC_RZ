package jdbc;

import model.Student;
import model.resolver.AbstractResolver;
import model.resolver.student.StudentResolver;
import model.usermenu.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        String[] params;
        Menu.printMainMenu();
        System.out.println("Wpisz komendę 'end' aby zakończyć program.");

        while(true) {
            line = scanner.nextLine();
            if(line.equals("end")) break;
            else if(line.equals("menu")) {
                Menu.printMainMenu();
                continue;
            }
            params = line.split(" ");
            if(params.length > 0) {
                switch(params[0]) {
                    case "1": break;
                    case "2": break;
                    case "3": break;
                    case "4": break;
                    case "5": break;
                    default:
                        System.out.println("Niepoprawne polecenie. Wpisz 'menu' żeby wyświetlić listę poleceń");
                        break;
                }
            }
        }

    }

}

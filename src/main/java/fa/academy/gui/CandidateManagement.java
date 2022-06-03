package fa.academy.gui;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.controller.EditController;
import fa.academy.controller.FindController;
import fa.academy.utils.ClearConsole;
import fa.academy.utils.Validator;

public class CandidateManagement {

    private FindController findController;
    private EditController editController;

    public CandidateManagement() {
        this.findController = new FindController();
        this.editController = new EditController(this.findController);
    }

    public void start() {
        int choice = 0;
        do {
            choice = mainMenu();
            switch (choice) {
                case 1:
                    switch (findMenu()) {
                        case 1:
                            findCandidate();
                            break;
                        case 2:
                            findAllCandidate();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    switch (editMenu()) {
                        case 1:
                            editCandidate();
                            break;
                        case 2:
                            editCandidateWithRS();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (choice != 5);
        ClearConsole.clear();
        System.out.println("See you again!");
    }

    public int mainMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                    MAIN MENU                   ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Find candidate                               ");
        System.out.println("2. Add candidate                                ");
        System.out.println("3. Edit candidate                               ");
        System.out.println("4. Delete candidate                             ");
        System.out.println("5. Exit                                         ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public int findMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("              FIND CANDIDATE MENU               ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Find candidate                               ");
        System.out.println("2. Find all candidate                           ");
        System.out.println("3. Back to Main menu                            ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public int addMenu() {
        return 0;
    }

    public int editMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("              EDIT CANDIDATE MENU               ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Edit Candidate by ID                         ");
        System.out.println("2. Edit Candidate by ID with ResultSet          ");
        System.out.println("3. Back to Main menu                            ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public int deleteMenu() {
        return 0;
    }

    public void findCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("              FIND CANDIDATE MENU               ");
        System.out.println("------------------------------------------------");
        System.out.println();
        findController.findCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void findAllCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("              FIND CANDIDATE MENU               ");
        System.out.println("------------------------------------------------");
        System.out.println();
        findController.findAllCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void addCandidate() {}

    public void addCandidateFromFile() {}

    public void addCandidateWithRS() {}

    public void editCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("              EDIT CANDIDATE MENU               ");
        System.out.println("------------------------------------------------");
        System.out.println();
        editController.editCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void editCandidateWithRS() {}

    public void deleteCandidate() {}
}

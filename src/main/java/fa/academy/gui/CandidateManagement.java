package fa.academy.gui;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.controller.DeleteController;
import fa.academy.controller.EditController;
import fa.academy.controller.FindController;
import fa.academy.controller.InsertController;
import fa.academy.controller.OtherController;
import fa.academy.utils.ClearConsole;
import fa.academy.utils.Validator;

public class CandidateManagement {

    private FindController findController;
    private EditController editController;
    private DeleteController deleteController;
    private InsertController insertController;
    private OtherController otherController;

    public CandidateManagement() {
        this.otherController = new OtherController();
        this.findController = new FindController();
        this.editController = new EditController(this.findController);
        this.deleteController = new DeleteController(this.findController);
        this.insertController = new InsertController(this.findController);
    }

    public void start() {
        int choice = 0;
        do {
            choice = mainMenu();
            switch (choice) {
                // Find Menu
                case 1:
                    switch (findMenu()) {
                        case 1:
                            findCandidate();
                            break;
                        case 2:
                            findAllCandidate();
                            break;
                        case 3:
                            findCertification();
                            break;
                        case 4:
                            findCertificationByCid();
                            break;
                        case 5:
                            break;
                    }
                    break;
                // Add Menu
                case 2:
                    switch (addMenu()) {
                        case 1:
                            addCandidate();
                            break;
                        case 2:
                            addCertification();
                            break;
                        case 3:
                            addDataFromFile();
                            break;
                        case 4:
                            break;
                    }
                    break;
                // Update Menu
                case 3:
                    switch (editMenu()) {
                        case 1:
                            editCandidate();
                            break;
                        case 2:
                            editCertification();
                            break;
                        case 3:
                            break;
                    }
                    break;
                // Delete Menu
                case 4:
                    switch (deleteMenu()) {
                        case 1:
                            deleteCandidate();
                            break;
                        case 2:
                            deleteCertification();
                            break;
                        case 3:
                            deleteCertificationByCid();
                            break;
                        case 4:
                            break;
                    }
                    break;
                case 5:
                    switch (resultSetExampleMenu()) {
                        case 1:
                            updateCertificationWithRs();
                            break;
                        case 2:
                            insertCertificationWithRs();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    switch (otherMenu()) {
                        case 1:
                            candidateCount();
                            break;
                        case 2:
                            stringBuilderExcercise();
                            break;
                        case 3:
                            collectionsSortExcercise();
                            break;
                    }
                    break;
                case 7:
                    break;
            }
        } while (choice != 7);
        ClearConsole.clear();
        System.out.println("See you again!");
    }

    public int mainMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                    MAIN MENU                   ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Find menu                                    ");
        System.out.println("2. Add menu                                     ");
        System.out.println("3. Edit menu                                    ");
        System.out.println("4. Delete menu                                  ");
        System.out.println("5. ResultSet examples                           ");
        System.out.println("6. Other                                        ");
        System.out.println("7. Exit                                         ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 7) {
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
        System.out.println("                  FIND MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Find candidate by ID                         ");
        System.out.println("2. Find all candidate                           ");
        System.out.println("3. Find CERF by CERF-ID                         ");
        System.out.println("4. Find CERF by CDD-ID                         ");
        System.out.println("5. Back to Main menu                            ");
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

    public int addMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                   ADD MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Add New Candidate                            ");
        System.out.println("2. Add New CERF                                 ");
        System.out.println("3. Add Data from file                           ");
        System.out.println("4. Back to Main menu                            ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public int editMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  EDIT MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Edit Candidate by ID                         ");
        System.out.println("2. Edit CERF by CERF-ID                         ");
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
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 DELETE MENU                    ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Delete Candidate by ID                       ");
        System.out.println("2. Detele CERF by CERF-ID                       ");
        System.out.println("3. Detele CERF by CDD-ID                        ");
        System.out.println("4. Back to Main menu                            ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");

        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public int resultSetExampleMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("             ResultSet Example                  ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Update CERT with ResultSet                   ");
        System.out.println("2. Insert CERT with ResultSet                   ");
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

    public int otherMenu() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 Other Menu                     ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Candidate Count                              ");
        System.out.println("2. StringBuilder Excercise                      ");
        System.out.println("3. Collections sort                             ");
        System.out.println("4. Back to main menu                            ");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");
        int choice = 0;
        while (true) {
            String input = consoleScanner.nextLine();
            try {
                choice = Validator.validateNumber(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter valid number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    public void findCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  FIND MENU                     ");
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
        System.out.println("                  FIND MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        findController.findAllCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void findCertification() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  FIND MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        findController.findCerf();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void findCertificationByCid() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  FIND MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        findController.findCerfbyCandidateId();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void addCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                   ADD MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        insertController.insertCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void addDataFromFile() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                   ADD MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        insertController.insertDataFromFile();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void addCertificationWithRS() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                   ADD MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        insertController.insertCertificationWithRS();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void addCertification() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                   ADD MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        insertController.insertCertification();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void editCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  EDIT MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        editController.editCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void editCertification() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                  EDIT MENU                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        editController.editCertification();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void deleteCandidate() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 DELETE MENU                    ");
        System.out.println("------------------------------------------------");
        System.out.println();
        deleteController.deleteCandidate();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void deleteCertification() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 DELETE MENU                    ");
        System.out.println("------------------------------------------------");
        System.out.println();
        deleteController.deleteCertification();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void deleteCertificationByCid() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 DELETE MENU                    ");
        System.out.println("------------------------------------------------");
        System.out.println();
        deleteController.deleteCertificationByCid();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void updateCertificationWithRs() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("             ResultSet Example                  ");
        System.out.println("------------------------------------------------");
        System.out.println();
        editController.editCertificationWithRS();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void insertCertificationWithRs() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("             ResultSet Example                  ");
        System.out.println("------------------------------------------------");
        System.out.println();
        insertController.insertCertificationWithRS();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void candidateCount() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 Other Menu                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        otherController.getCandidateCount();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void stringBuilderExcercise() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 Other Menu                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        otherController.stringBuilderExcercise();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }

    public void collectionsSortExcercise() {
        ClearConsole.clear();
        System.out.println("  WELCOME TO CANDIDATE MANAGEMENT APPLICATION   ");
        System.out.println("------------------------------------------------");
        System.out.println("                 Other Menu                     ");
        System.out.println("------------------------------------------------");
        System.out.println();
        otherController.collectionsSortExcercise();
        System.out.println();
        System.out.println("Press Enter to continue.");
        consoleScanner.nextLine();
    }
}

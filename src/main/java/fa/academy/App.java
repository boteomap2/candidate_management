package fa.academy;

import fa.academy.config.DatabaseConfig;
import fa.academy.gui.CandidateManagement;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("fa.academy.utils.GlobalScanner");
        DatabaseConfig.openConnection();
        CandidateManagement cm = new CandidateManagement();
        cm.start();
        DatabaseConfig.closeConnection();
    }
}

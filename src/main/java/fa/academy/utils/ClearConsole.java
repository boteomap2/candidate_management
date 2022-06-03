package fa.academy.utils;

public class ClearConsole {

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

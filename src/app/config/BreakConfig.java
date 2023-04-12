package app.config;

/*\033[2J\033[2J1;1H*/
public final class BreakConfig {
    private static final String CSI = "\033[";//"\033[2J\033[2J1;1H"or "\u001b[2J\u001b[2J1;1H" for unicode

    private static final String CLEAR_SCREEN = CSI + "2J";

    private static String position(int row, int col) {
        return CSI + row + ";" + col + "H";
    }

    public static void clearScreen() {
        System.out.println(CLEAR_SCREEN + position(1, 1));
        System.out.flush();
    }
}
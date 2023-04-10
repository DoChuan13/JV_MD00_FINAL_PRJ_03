package app.config;

/*\033[H\033[2J*/
public final class BreakConfig {
    private static final String CSI = "\033[";//or "\u001b[" for unicode

    private static final String CLEAR_SCREEN = CSI + "2J";

    private static String position(int row, int col) {
        return CSI + row + ";" + col + "H";
    }

    public static void clearScreen() {
        System.out.println(CLEAR_SCREEN + position(1, 1));
        System.out.flush();
    }
}
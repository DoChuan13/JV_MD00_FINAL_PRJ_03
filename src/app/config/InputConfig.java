package app.config;

import java.util.Scanner;

/**
 * <p><b><u}>Description detail</u></b> </p>
 * <p>getString()       ==>> Return a string value from the user.</p>
 * <p>getChar()         ==>> Return a character value from the user</p>
 * <p>getBoolean()	    ==>> Return a boolean value from the user.</p>
 * <p>getByte()	        ==>> Return a byte value from the user.</p>
 * <p>getShort()	    ==>> Return a short value from the user.</p>
 * <p>getInteger()	    ==>> Return a integer value from the user.</p>
 * <p>getLong()	        ==>> Return a long value from the user.</p>
 * <p>getFloat()	    ==>> Return a float value from the user.</p>
 * <p>getDouble()	    ==>> Return a double value from the user.</p>
 */
public final class InputConfig {
    private static final String ERROR_ALERT = ColorConfig.ERROR_COLOR + "===>> Number format invalid or Out of Range! Please try again...\n" + ColorConfig.RESET;
    private static final String EMPTY_ALERT = ColorConfig.ERROR_COLOR + "===>> Please do not empty this field! Please try again...\n" + ColorConfig.RESET;
    private static final String CONTINUE = ColorConfig.ACTION_COLOR + "===>> ...Press any key to continue... <<===" + ColorConfig.RESET;
    /*========================================Input Method Start========================================*/

    /**
     * getString()  Return a String value from the user.
     */
    public static String getString() {
        String result = getInput().trim();
        if (result.equals("")) {
            System.out.print(EMPTY_ALERT);
            return getString();
        }
        return result;
    }

    /**
     * getChar()  Return a Character value from the user.
     */
    public static Character getChar() {
        return getString().charAt(0);
    }

    /**
     * getBoolean()  Return a Boolean value from the user.
     */
    public static Boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  Return a Byte value from the user.
     */
    public static Byte getByte() {
        try {
            return Byte.parseByte(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getByte();
        }
    }

    /**
     * getShort()  Return a Short value from the user.
     */
    public static Short getShort() {
        try {
            return Short.parseShort(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getShort();
        }
    }

    /**
     * getInteger()  Return a Integer value from the user.
     */
    public static Integer getInteger() {
        try {
            return Integer.parseInt(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getInteger();
        }
    }

    /**
     * getLong()  Return a Long value from the user.
     */
    public static Long getLong() {
        try {
            return Long.parseLong(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getLong();
        }
    }

    /**
     * getFloat()  Return a Float value from the user.
     */
    public static Float getFloat() {
        try {
            return Float.parseFloat(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getFloat();
        }
    }

    /**
     * getDouble()  Return a Double value from the user.
     */
    public static Double getDouble() {
        try {
            return Double.parseDouble(getString());
        } catch (NumberFormatException errException) {
            System.out.print(ERROR_ALERT);
            return getDouble();
        }
    }
    /*========================================Input Method End========================================*/

    /**
     * getInput()  Return any String value from the user.
     */
    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * pressAnyKey()  Press any key to continue....
     */
    public static void pressAnyKey() {
        //System.out.println(CONTINUE);
        getInput();
    }
    /*========================================Other Method========================================*/
}
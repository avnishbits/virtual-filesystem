package navi.filesystem.commons;

/**
 * @author avnishkg
 */
public class Utils {

    private static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +"0123456789"+"abcdefghijklmnopqrstuvxyz";

    public static boolean isEmpty(String input){
        return input == null || input.isEmpty();
    }

   public static String getRandomString(int n)
    {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}

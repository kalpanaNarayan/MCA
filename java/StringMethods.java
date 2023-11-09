import java.util.Arrays;

public class StringMethods {
    public static void main(String[] args) {
        // Original string
        String originalString = "We realizes that while our workers were thriving, the surrounding villages were still suffering. It became our goal to uplift their quality of life as well. I remember seeing a family of 4 on a motorbike in the heavy Bombay rain — I knew I wanted to do more for these families who were risking their lives for lack of an alternative";

        // charAt
        char firstChar = originalString.charAt(0);
        System.out.println("First character: " + firstChar);
        

        // compareTo
        String compareString = "We realizes that while our workers were thriving";
        int compareToResult = originalString.compareTo(compareString);
        System.out.println("Compare To result: " + compareToResult);

        // concat
        String concatString = originalString.concat("  the additional content is added at the end.");
        System.out.println("Concatenated string: " + concatString);

        // contains
        boolean containsResult = originalString.contains("workers");
        System.out.println("Contains 'workers': " + containsResult);

        // endsWith
        boolean endsWithResult = originalString.endsWith("thriving...");
        System.out.println("Ends with 'thriving...': " + endsWithResult);

        // equals
        boolean equalsResult = originalString.equals(compareString);
        System.out.println("Equals result: " + equalsResult);

        // equalsIgnoreCase
        boolean equalsIgnoreCaseResult = originalString.equalsIgnoreCase(compareString.toUpperCase());
        System.out.println("Equals Ignore Case result: " + equalsIgnoreCaseResult);

        // format
        String formattedString = String.format("Formatted string: %s", originalString);
        System.out.println(formattedString);

        // getBytes
        byte[] bytes = originalString.getBytes();
        System.out.println("String as bytes: " + Arrays.toString(bytes));

        // getChars
        char[] charArray = new char[10];
        originalString.getChars(0, 10, charArray, 0);
        System.out.println("First 10 characters: " + Arrays.toString(charArray));

        // indexOf
        int indexOfResult = originalString.indexOf("workers");
        System.out.println("Index of 'workers': " + indexOfResult);

        // intern
        String internedString = originalString.intern();
        System.out.println("Interned string: " + internedString);

        // isEmpty
        boolean isEmptyResult = originalString.isEmpty();
        System.out.println("Is empty: " + isEmptyResult);

        // join
        String joinedString = String.join("-", "We", "realizes", "that", "while");
        System.out.println("Joined string: " + joinedString);

        // lastIndexOf
        int lastIndexOfResult = originalString.lastIndexOf("workers");
        System.out.println("Last index of 'workers': " + lastIndexOfResult);

        // length
        int length = originalString.length();
        System.out.println("Length of the string: " + length);

        // replace
        String replacedString = originalString.replace("workers", "employees");
        System.out.println("Replaced string: " + replacedString);

        // replaceAll
        String replacedAllString = originalString.replaceAll("\\bworkers\\b", "employees");
        System.out.println("Replace All string: " + replacedAllString);

        // split
        String[] splitArray = originalString.split(" ");
        System.out.println("Split string: " + Arrays.toString(splitArray));

        // startsWith
        boolean startsWithResult = originalString.startsWith("We");
        System.out.println("Starts with 'We': " + startsWithResult);

        // substring
        String subString = originalString.substring(3, 10);
        System.out.println("Substring: " + subString);

        // toCharArray
        char[] charArrayFromString = originalString.toCharArray();
        System.out.println("Char array from string: " + Arrays.toString(charArrayFromString));

        // toLowerCase
        String lowerCaseString = originalString.toLowerCase();
        System.out.println("Lowercase string: " + lowerCaseString);

        // toUpperCase
        String upperCaseString = originalString.toUpperCase();
        System.out.println("Uppercase string: " + upperCaseString);

        // trim
        String trimmedString = "   Trimmed String   ";
        System.out.println("Original string: '" + trimmedString + "'");
        System.out.println("Trimmed string: '" + trimmedString.trim() + "'");

        // valueOf
        String valueOfString = String.valueOf(12345);
        System.out.println("Value Of string: " + valueOfString);
    }
}

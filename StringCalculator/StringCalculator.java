import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final Pattern PATTERN = Pattern.compile("\\\\(.)");

    public static int add(String input){
        if(Objects.requireNonNullElse(input, "").isBlank()){
            return 0;
        }
        String delimiteurs = allDelimiteursRegex(input);
        String[] splittedInput = input.split(delimiteurs);
        negativeNumbersCheck(splittedInput);
        return calculateNumbers(splittedInput);
    }

    protected static int calculateNumbers(String[] splittedInput) {
        return Arrays.stream(splittedInput)
                .filter(StringCalculator::isNumeric)
                .mapToInt(Integer::parseInt)
                .filter(i -> i < 1000)
                .sum();
    }

    protected static void negativeNumbersCheck(String[] splittedInput) {
        List<String> tab = Arrays.stream(splittedInput)
                .filter(StringCalculator::isNumeric)
                .map(Integer::parseInt)
                .filter(i -> i < 0)
                .map(Object::toString)
                .toList();
        int count = tab.size();
        if (count == 0) {
            return;
        }
        String errorMessage = "Les nombres négatifs ne sont pas autorisés"
                + (count == 1 ? "" : " : " + String.join(",", tab));
        throw new RuntimeException(errorMessage);
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    protected static String allDelimiteursRegex(String input) {
        Matcher matcher = PATTERN.matcher(input);

        String delimiter = "";
        if(matcher.find()){
            delimiter = matcher.group(1);
        }
        String pattern = "[%s,\n]";
        return String.format(pattern, delimiter);
    }
}

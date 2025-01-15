import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input){
        if(input.isBlank()){
            return 0;
        }
        String delimiteurs = allDelimiteursRegex(input);
        String[] splittedInput = input.split(delimiteurs);
        negativeNumbersCheck(splittedInput);
        return calculateNumbers(splittedInput);
    }

    protected static int calculateNumbers(String[] splittedInput) {
        int total = 0;
        List<Integer> parsedSplittedIntegers = Arrays.stream(splittedInput)
                .filter(StringCalculator::isNumeric)
                .map(Integer::parseInt)
                .toList();
        for(Integer splittedIntegers : parsedSplittedIntegers){
            if(splittedIntegers < 1000){
                total += splittedIntegers;
            }
        }
        return total;
    }

    protected static void negativeNumbersCheck(String[] splittedInput) {
        List<String> negativesList = Arrays.stream(splittedInput).filter(s -> s.contains("-")).toList();
        String exceptionText;
        long negativesCount = negativesList.size();
        if(negativesCount > 0){
            exceptionText = "Les nombres négatifs ne sont pas autorisés";
            if(negativesCount > 1){
                String nombresNegatifs = String.join(",", negativesList);
                exceptionText = exceptionText + " : " + nombresNegatifs;
            }
            throw new RuntimeException(exceptionText);
        }
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
        String delimiter = "";
        Pattern pattern = Pattern.compile("\\\\(.)");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            delimiter = matcher.group(1);
        }
        return "[" + delimiter + ",\n]";
    }
}

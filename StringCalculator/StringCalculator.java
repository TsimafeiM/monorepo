public class StringCalculator {

    public static int Add(String input){
        if(input.isBlank()){
            return 0;
        }
        String delimiteur = getDelimiteur(input);
        String normalizedInput = normalizeInput(input, !delimiteur.isBlank());
        String[] splittedInput = normalizedInput.split(",");
        negativeNumbersCheck(splittedInput, delimiteur);
        return calculateNumbers(splittedInput, delimiteur);
    }

    protected static int calculateNumbers(String[] splittedInput, String delimiteur) {
        int total = 0;
        for(String splittedString : splittedInput){
            //gestion du delimiteur custom
                if(notNumeric(splittedString) && !delimiteur.isBlank()){
                    String[] resplittedString = splittedString.split(delimiteur);
                    total = addStringToTotal(resplittedString[0], total);
                    total = addStringToTotal(resplittedString[1], total);
                }else {
                    total = addStringToTotal(splittedString, total);
                }
        }
        return total;
    }

    private static int addStringToTotal(String splittedString, int total) {
        int parsedInt = Integer.parseInt(splittedString);
        if(parsedInt < 1000){
            total += parsedInt;
        }
        return total;
    }

    protected static void negativeNumbersCheck(String[] splittedInput, String delimiteur) {
        StringBuilder negativeNumbers = new StringBuilder();
        int negativesCount = 0;;
        for(String splittedString : splittedInput){
            //gestion du delimiteur custom
            if(notNumeric(splittedString) && !delimiteur.isBlank()){
                for(String resplittedString : splittedString.split(delimiteur)){
                    negativesCount = handleNegatives(resplittedString, negativesCount, negativeNumbers);
                }
            }else {
                negativesCount = handleNegatives(splittedString, negativesCount, negativeNumbers);
            }
        }
        String exceptionText;
        if(negativesCount > 0){
            exceptionText = "Les nombres négatifs ne sont pas autorisés";
            if(negativesCount > 1){
                exceptionText = exceptionText + " : " + negativeNumbers;
            }
            throw new RuntimeException(exceptionText);
        }
    }

    private static int handleNegatives(String resplittedString, int countNegatives, StringBuilder negativeNumbers) {
        // "" donne 0
        int parsedInt = Integer.parseInt(resplittedString);
        if(parsedInt <= 0){
            appendNegativeNumbersWithCommas(resplittedString, countNegatives, negativeNumbers);
            countNegatives++;
        }
        return countNegatives;
    }

    private static void appendNegativeNumbersWithCommas(String resplittedString, int countNegatives, StringBuilder negativeNumbers) {
        if(countNegatives == 0){
            negativeNumbers.append(resplittedString);
        }else {
            negativeNumbers.append(",");
            negativeNumbers.append(resplittedString);
        }
    }

    private static boolean notNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch(NumberFormatException e){
            return true;
        }
    }

    private static String normalizeInput(String input, boolean hasDelimiter) {
        if(hasDelimiter){
            input = input.substring(3);
        }
        return input.replaceAll("\n", ",");
    }

    protected static String getDelimiteur(String input) {
        int indexOfDelim = input.indexOf("\\");
        String delimiteur = "";
        if (indexOfDelim != -1){
            delimiteur = input.substring(indexOfDelim+1,indexOfDelim+2);
        }
        return delimiteur;
    }
}

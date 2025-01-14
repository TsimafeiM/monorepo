public class StringCalculator {

    public static int Add(String input){
        int total = 0;
        String delimiteur = getDelimiteur(input);
//        int indexDelimiteur = input.lastIndexOf(delimiteur);
//        String customDelimiteurNumber = input.substring(indexDelimiteur, indexDelimiteur+1);
//        total += Integer.parseInt(customDelimiteurNumber);
//        input.replace

        input = input.replaceAll("\n", ",");
        for(String s : input.split(",")){
            total += s.isBlank() ? 0 : Integer.parseInt(s);
        }
        return total;
    }

    protected static String[] splitWith2Delim(String firstDelim, String secondDelim, String string){
        String[] splittedNumbers;
        int lastCharFirstDelim = string.lastIndexOf(firstDelim);
        int lastCharSecondDelim = string.lastIndexOf(secondDelim);
        while(!string.isBlank()){

        }
    }

    protected static String getDelimiteur(String input) {
        int indexOfDelim = input.indexOf("\\");
        String delimiteur = null;
        if (indexOfDelim != -1){
            delimiteur = input.substring(indexOfDelim+1,indexOfDelim+2);
        }
        return delimiteur;
    }

//    private static String replaceLineSkipWithSeparator(String input){
//        int lastIndexOfSkip = input.lastIndexOf("\n");
//        while (lastIndexOfSkip != -1){
//            input.replaceAll()
//        }
//    }
}

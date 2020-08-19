import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String numbers) {
        double result = 0.0;
        int stringNumbersIndex;

        if (numbers.isBlank()) {
            return "0.0";
        }


        stringNumbersIndex = illegalSeparatorFound(",\\n", numbers);
        if (stringNumbersIndex > -1){
            return "Number expected but '\\n' found at position " + (stringNumbersIndex+1) + ".";

        }

        stringNumbersIndex = illegalSeparatorFound("\\n,", numbers);
        if (stringNumbersIndex > -1){
            return "Number expected but ',' found at position " + (stringNumbersIndex+1) + ".";

        }

        stringNumbersIndex = illegalSeparatorFound(",$", numbers);
        if (stringNumbersIndex > -1){
            return "Number expected but EOF found";

        }

        String NegativeNumbersList = isNegativeNumbers(numbers);
        if (!NegativeNumbersList.isBlank()){
            return "Negative not allowed :" + NegativeNumbersList;
        }


        Pattern p = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher m = p.matcher(numbers);
        while (m.find()){
            result += Double.parseDouble(m.group());
            System.out.println(m.group());
        }
        return result + "";
    }

    private int illegalSeparatorFound(String pattern, String numbers){

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(numbers);
        if (m.find()){
            return m.start();
        }
        return -1;
    }

    private String isNegativeNumbers(String numbers){
        StringBuilder NegativeNumbersList = new StringBuilder();
        Pattern p = Pattern.compile("-\\d+\\.\\d+|-\\d+");
        Matcher m = p.matcher(numbers);
        boolean first = true;
        while (m.find()){
            if (first){
                NegativeNumbersList.append(" ").append(m.group());
                first = false;
            }else {
                NegativeNumbersList.append(", ").append(m.group());
            }


        }
        return NegativeNumbersList.toString();
    }
}

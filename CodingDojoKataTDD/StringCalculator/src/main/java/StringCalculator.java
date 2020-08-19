import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String numbers) {
        double result = 0.0;
        int stringNumbersIndex;
                ;
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
        while (m.find()){
            return m.start();
        }
        return -1;
    }
}

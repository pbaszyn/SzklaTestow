import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String numbers) {
        double result = 0.0;
        if (numbers.isBlank()) {
            return "0.0";
        }

        Pattern p_ = Pattern.compile(",\\n|\\n,");
        Matcher m_ = p_.matcher(numbers);
        while (m_.find()){

            String message = "Number expected but '\\n' found at position " + (m_.start()+1) + ".";
            return message;


        }

        Pattern p = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher m = p.matcher(numbers);
        while (m.find()){
            result += Double.parseDouble(m.group());
            System.out.println(m.group());
        }
        return result + "";

    }
}

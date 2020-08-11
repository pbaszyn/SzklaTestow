import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String numbers){
        int result = 0;
        if (numbers.isBlank()) {
            return "0";
        }

        LinkedList<String> ListOfNumbers = new LinkedList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(numbers);
        while (m.find()){
            result += Integer.parseInt(m.group());

        }
        return result + "";

    }
}

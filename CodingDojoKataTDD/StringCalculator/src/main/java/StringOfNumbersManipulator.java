import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOfNumbersManipulator {

    private String numbers;
    private String illegalSeparator = "";
    private int illegalSeparatorPosition = -1;
    private boolean eofFoundInsteadOfNumber = false;
    private String negativeNumbersList = "";
    private String customSeparator = "";
    private String wrongSeparator = "";
    private int wrongSeparatorPosition = -1;

    public String getCustomSeparator() {
        return customSeparator;
    }
    public String getWrongSeparator() {
        return wrongSeparator;
    }
    public int getWrongSeparatorPosition() {
        return wrongSeparatorPosition;
    }
    public String getNegativeNumbersList() {
        return negativeNumbersList;
    }
    public boolean foundNegativeNumbers() {
        return !negativeNumbersList.isEmpty();
    }
    public StringOfNumbersManipulator(String numbers) {
        this.numbers = numbers;
    }
    public String getIllegalSeparator() {
        return illegalSeparator;
    }
    public int getIllegalSeparatorPosition() { return illegalSeparatorPosition; }
    public boolean isEmpty(){
        return numbers.isBlank();
    }
    public boolean isDoubledSeparators(){
        return illegalSeparatorPosition > -1;
    }
    public boolean eofFoundInsteadOfNumber(){
        return eofFoundInsteadOfNumber;
    }
    public boolean foundWrongCustomSeparator() {
        return wrongSeparatorPosition > -1;
    }
    public void analyzeString(){
        searchForCustomSeparator();
        if (searchForDoubledSeparators("\\d+,\\n")){
            illegalSeparator = "\\n";
            illegalSeparatorPosition += 2;
        }
        if (searchForDoubledSeparators("\\n,")){
            illegalSeparator = ",";
            illegalSeparatorPosition += 1;
        }if (!customSeparator.isBlank()) {
            searchForWrongSeparator();
        }
        if (searchForMissingNumberAtTheEnd()){ eofFoundInsteadOfNumber = true;}
        searchForNegativeNumbers();

    }

    public String getSumOfNumbers(){
        Pattern p = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher m = p.matcher(numbers);
        double result = 0.0;
        while (m.find()){
            result += Double.parseDouble(m.group());
        }
        return result + "";
    }

    private boolean searchForWrongSeparator(){
        Pattern p = Pattern.compile("\\d+(?<wrongSeparator>[^;.])\\d+");
        Matcher m = p.matcher(numbers);
        if (m.find()){
            wrongSeparator = m.group("wrongSeparator");
            wrongSeparatorPosition = m.start()+ 2 + 3 + customSeparator.length();
            return true;
        }
        return false;
    }
    private boolean searchForDoubledSeparators(String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(numbers);
        if (m.find()){
            illegalSeparatorPosition = m.start();
            return true;
        }
        return false;
    }
    private boolean searchForMissingNumberAtTheEnd(){
        Pattern p = Pattern.compile(",$");
        Matcher m = p.matcher(numbers);
        return m.find();
    }

    private void searchForNegativeNumbers (){
        StringBuilder negativeNumbers = new StringBuilder();
        Pattern p = Pattern.compile("-\\d+\\.\\d+|-\\d+");
        Matcher m = p.matcher(numbers);
        boolean first = true;
        while (m.find()){
            if (first){
                negativeNumbers.append(" ").append(m.group());
                first = false;
            }else {
                negativeNumbers.append(", ").append(m.group());
            }
        }
        negativeNumbersList = negativeNumbers.toString();
    }

    private void searchForCustomSeparator (){
        Pattern p = Pattern.compile("//(?<customSeparator>[^\n]+)\n");
        Matcher m = p.matcher(numbers);
        while (m.find()){
            customSeparator = m.group("customSeparator");
            numbers = numbers.substring(m.group().length());
        }
    }
}

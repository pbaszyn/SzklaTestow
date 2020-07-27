public class StringCalculator {
    int add (String input){
        if (isEmpty(input)){
            return 0;
        }
        else {
            int result = 0;
            String[] numbers = input.split(",");
            for (String number: numbers){
                result += Integer.parseInt(number);
            }
            return result;

        }

    }

    private boolean isEmpty(String input) {
        if (input != null && !input.isEmpty())
            return false;
        return true;
    }
}

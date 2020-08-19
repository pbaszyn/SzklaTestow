public class StringCalculator {

    public String add(String numbers) {

        StringOfNumbersManipulator stringOfNumbers = new StringOfNumbersManipulator(numbers);
        stringOfNumbers.analyzeString();

        if (stringOfNumbers.isEmpty()) {
            return "0.0";
        }else if (stringOfNumbers.isIllegalSeparator()){
            return "Number expected but '" + stringOfNumbers.getIllegalSeparator() + "' found at position " + stringOfNumbers.getIllegalSeparatorPosition() + ".";
        }else if (stringOfNumbers.eofFoundInsteadOfNumber()){
            return "Number expected but EOF found";
        }else if (stringOfNumbers.foundNegativeNumbers()){
            return "Negative not allowed :" + stringOfNumbers.getNegativeNumbersList();
        }

            return stringOfNumbers.getSum();

    }

}

public class StringCalculator {

    public String add(String numbers) {

        StringOfNumbersManipulator stringOfNumbers = new StringOfNumbersManipulator(numbers);
        stringOfNumbers.analyzeString();

        if (stringOfNumbers.isEmpty()) {
            return "0.0";
        }else if (stringOfNumbers.isDoubledSeparators()){
            return "Number expected but '" + stringOfNumbers.getIllegalSeparator() + "' found at position " + stringOfNumbers.getIllegalSeparatorPosition() + ".";
        }else if (stringOfNumbers.eofFoundInsteadOfNumber()){
            return "Number expected but EOF found";
        }else if (stringOfNumbers.foundNegativeNumbers()){
            return "Negative not allowed :" + stringOfNumbers.getNegativeNumbersList();
        }else if (stringOfNumbers.foundWrongCustomSeparator()){
            return stringOfNumbers.getCustomSeparator() + " expected but '"+stringOfNumbers.getWrongSeparator()+"' found at position "+stringOfNumbers.getWrongSeparatorPosition()+".";
        }

            return stringOfNumbers.getSumOfNumbers();

    }

}

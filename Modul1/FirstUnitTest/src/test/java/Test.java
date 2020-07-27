public class Test {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        System.out.println(areEqual(5, calculator.add("5"), "Calculator should return number"));
        System.out.println(areEqual(7, calculator.add("5,2"), "Calculator should return sum of numbers"));
    }
    private static String areEqual(int expected, int actual, String s){
        if (actual == expected){
            return s + " - Passed";
        }
        return s + " - Failed";
    }
}

package umg.edu.gt.stackhandler;

public class App {

    public static void main(String[] args) {
        SymbolValidator validator = new SymbolValidator();

        if (args.length > 0) {
            String expr = args[0];
            System.out.println("Expression: " + expr);
            System.out.println("Valid: " + validator.isValid(expr));
            return;
        }

        String validCase = "(a+b) * [c-d]";
        String invalidCase = "([)]";

        System.out.println("=== Required Tests ===");
        System.out.println(validCase + " -> " + validator.isValid(validCase));
        System.out.println(invalidCase + " -> " + validator.isValid(invalidCase));
    }
}
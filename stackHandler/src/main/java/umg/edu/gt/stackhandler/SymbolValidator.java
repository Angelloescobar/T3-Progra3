package umg.edu.gt.stackhandler;

import umg.edu.gt.stack.LinkedStack;

public class SymbolValidator {

    public boolean isValid(String expr) {
        if (expr == null) return false;

        LinkedStack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (isOpen(ch)) {
                stack.push(ch);
            } else if (isClose(ch)) {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if (!matches(open, ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isClose(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }
}

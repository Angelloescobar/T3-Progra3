package umg.edu.gt.stack;

public class LinkedStack<T> {

    private Node<T> top;      // cabeza (top)
    private Node<T> bottom;   // inicio (fondo)
    private int count;

    public void push(T value) {
        Node<T> newNode = new Node<>(value, top);
        top = newNode;
        if (bottom == null) bottom = newNode;
        count++;
    }

    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        T value = top.getValue();
        top = top.getNext();
        count--;
        if (top == null) bottom = null;
        return value;
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return top.getValue();
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // Requisito: cuántos elementos hay
    public int getCount() { return count; }
    public int getSize()  { return count; }
    public int length()   { return count; }

    // Requisito: nodo inicial (fondo), no el top
    public Node<T> getNodeInit() {
        return bottom;
    }
}
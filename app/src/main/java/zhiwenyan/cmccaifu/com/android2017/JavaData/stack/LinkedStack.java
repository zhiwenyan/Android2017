package zhiwenyan.cmccaifu.com.android2017.JavaData.stack;

/**
 * Description:
 * Data：2018/12/22
 * Author:Steven
 */
public class LinkedStack<E> {
    private Node<E> top;


    public LinkedStack() {
    }

    public void add(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (top == null) {
            top = newNode;
        } else {
            System.out.println(top);
            newNode.next = top;
            top = newNode;
        }
    }

    public E remove() {
        System.out.println(top);
        E value = top.value;
        top = top.next;
        return value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        System.out.println(stack.remove());
        System.out.println(stack.remove());
        System.out.println(stack.remove());
        System.out.println(stack.remove());
        System.out.println(stack.remove());
        if (stack.isEmpty()) {
            System.out.println("stack is Empty");
        }


    }
}

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

    /**
     * 添加
     *
     * @param e
     */
    public void push(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * 弹出
     *
     * @return
     */
    public E pop() {
        E value = top.value;
        top = top.next;
        return value;
    }

    /**
     * 获取某个元素
     *
     * @return
     */
    public E get(int index) {
        E value = node(index).value;
        return value;
    }

    public Node<E> node(int index) {
        Node<E> t = top;
        for (int i = 0; i < index; i++) {
            t = t.next;
        }
        return t;
    }


    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.get(3));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        if (stack.isEmpty()) {
            System.out.println("stack is Empty");
        }


    }
}

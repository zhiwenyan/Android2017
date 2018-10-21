package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList1;

/**
 * Description:
 * Data：10/9/2018-9:45 AM
 *
 * @author yanzhiwen
 */
public class Node<E> {
    public E value;

    public Node<E> prev;
    public Node<E> next;

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }

    public Node(E value, Node<E> prev, Node<E> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

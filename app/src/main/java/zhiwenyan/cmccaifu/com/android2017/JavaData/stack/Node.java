package zhiwenyan.cmccaifu.com.android2017.JavaData.stack;

/**
 * Description:
 * Data：2018/12/22
 * Author:Steven
 */
public class Node<E> {
    E value;

    Node<E> next;

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }
}

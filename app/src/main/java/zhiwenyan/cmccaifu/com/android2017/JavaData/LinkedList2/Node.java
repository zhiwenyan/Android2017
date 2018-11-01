package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList2;

/**
 * Description:
 * Data：11/1/2018-4:00 PM
 *
 * @author yanzhiwen
 */
public class Node<E> {
    public E value;
    //下一个节点
    public Node<E> next;
    //上一个节点
    public Node<E> prev;

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }

    public Node(E value,  Node<E> prev,Node<E> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

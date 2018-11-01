package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList2;

/**
 * Description:
 * Data：11/1/2018-5:56 PM
 *
 * @author yanzhiwen
 */
public class DoubleLinkedList<E> {
    //头节点
    private Node<E> head;
    //尾节点
    private Node<E> last;
    //链表的长度
    private int length;

    /**
     * 添加数据
     *
     * @param value
     */
    public void add(E value) {
        linkLast(value);
        length++;

    }


    public E get(int index) {
        if (index >= 0 && index < length) {
            return node(index).value;
        }
        return null;
    }

    public E remove(int index) {
        if (index == 0) {
            Node<E> h = head;
            head = h.next;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> cur = prev.next;
            //之前的节点指向当前节点的后一个节点
            prev.next = cur.next;
        }
        length--;
        return null;
    }

    public void insert(int index, E value) {
        if (index == length) {
            linkLast(value);
        } else {
            linkBefore(node(index), value);
        }
        length++;
    }

    private void linkBefore(Node<E> cur, E value) {
        Node<E> prev = cur.prev;
        Node<E> new_node = new Node<>(value, prev, cur);
        if (prev == null) {
            head = new_node;
        } else {
            prev.next = new_node;
        }
    }

    private void linkLast(E value) {
        Node<E> l = last;
        Node<E> new_node = new Node<>(value, l, null);
        last = new_node;
        if (head == null) {
            head = new_node;
        } else {
            l.next = new_node;
        }
    }


    private Node<E> node(int index) {
        Node<E> h = head;
        for (int i = 0; i < index; i++) {
            h = h.next;
        }

        if (index < length >> 1) {

        } else {

        }
        return h;
    }

    public int size() {
        return length;
    }


    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(0);
        linkedList.add(-1);

    }
}

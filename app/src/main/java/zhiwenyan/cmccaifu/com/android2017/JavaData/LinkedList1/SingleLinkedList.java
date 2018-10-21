package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList1;

/**
 * Description:
 * Data：10/9/2018-9:46 AM
 *
 * @author yanzhiwen
 */
public class SingleLinkedList<E> {
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
        Node<E> new_Node = new Node<>(value, null);
        Node<E> l = last; //添加一个尾节点,时间复杂度O(1)
        last = new_Node;
        if (head == null) {
            head = new_Node;
        } else {
            //找到尾节点 时间复杂度四是O(n)
            // Node<E> last = node(length - 1);
            l.next = new_Node;
        }
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
            linkBefore(node(index - 1), value);
        }
        length++;
    }

    private void linkBefore(Node<E> prev, E value) {
        Node<E> new_node = new Node<>(value, null);
        if (prev == null) {
            head = new_node;
        } else {
            Node<E> cur = prev.next;
            prev.next = new_node;
            new_node.next = cur;
        }
    }

    private void linkLast(E value) {
        Node<E> l = last;
        Node<E> new_node = new Node<>(value, null);
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
        return h;
    }

    public int size() {
        return length;
    }


    public static void main(String[] args) {
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(0);
        linkedList.add(-1);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.remove(2);
        System.out.println("****************************");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        System.out.println("*****************************");
        linkedList.insert(2, 10);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}

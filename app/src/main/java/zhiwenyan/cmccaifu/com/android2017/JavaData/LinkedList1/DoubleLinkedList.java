package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList1;

/**
 * Description:
 * Data：10/9/2018-9:46 AM
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
       /* if (index == 0) {
            Node<E> h = head;
            head = h.next;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> cur = prev.next;
            //之前的节点指向当前节点的后一个节点
            prev.next = cur.next;
        }*/
        Node<E> cur = node(index);
        Node<E> prev = cur.prev;
        Node<E> next = cur.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
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
        cur.prev = new_node;
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
        //   Node<E> h = head;
//        for (int i = 0; i < index; i++) {
//            h = h.next;
//        }
        //优化一下，双向链表，时间复杂度为O(n/2)
        if (index < length >> 1) {
            Node<E> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else {
            Node<E> cur = last;
            for (int i = length - 1; i > index; i--) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    private void unLink(Node<E> cur) {

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
//        for (int i = 0; i < linkedList.size(); i++) {
//            System.out.println(linkedList.get(i));
//        }
//        linkedList.remove(2);
//        System.out.println("****************************");
//        for (int i = 0; i < linkedList.size(); i++) {
//            System.out.println(linkedList.get(i));
//        }
//        System.out.println("*****************************");
        linkedList.insert(0, 10);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}

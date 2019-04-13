package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList2;

/**
 * Description:
 * Data：11/1/2018-4:02 PM
 *
 * @author yanzhiwen
 */
public class LinkedList<E> {
    //头节点
    private Node<E> head;
    //尾节点
    private Node<E> last;

    private int length;

    public void add(E value) {
        linkedLast(value);
        length++;
    }

    private void linkedLast(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (head == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
    }


    private E get(int index) {
        if (index >= 0 || index <= length) {
            return node(index).value;
        }
        return null;
    }

    private Node<E> node(int index) {
        Node<E> h = head;
        for (int i = 0; i < index; i++) {
            h = h.next;
        }
        return h;
    }

    public void insert(int index, E value) {
        Node<E> newNode = new Node<>(value, null);
        if (index == 0) {
            Node<E> h = head;
            head = newNode;
            newNode.next = h;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> cur = prev.next;
            prev.next = newNode;
            newNode.next = cur;
        }
        length++;
    }


    public void remove(int index) {
        if (index >= 0 && index <= length) {
            unLinked(index);
        }
        length--;
    }

    private void unLinked(int index) {
        if (index == 0) {
            Node<E> h = head;
            head = h.next;
            h = null;
        } else {
            //前一个节点
            Node<E> prev = node(index - 1);
            //当前节点
            Node<E> cur = prev.next;
            //前一个节点的next指向当前节点的next
            prev.next = cur.next;
            cur = null;
        }
    }

    public int size() {
        return length;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        linkedList.remove(2);
        linkedList.insert(0, 10);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}

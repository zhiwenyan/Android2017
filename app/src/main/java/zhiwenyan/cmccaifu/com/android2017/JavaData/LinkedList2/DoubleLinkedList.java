package zhiwenyan.cmccaifu.com.android2017.JavaData.LinkedList2;

/**
 * Description: 双向链表 node(index) 查找某个结点为时间复杂度是o/2级别
 * Data：11/1/2018-5:56 PM
 *
 * @author yanzhiwen
 */
public class DoubleLinkedList<E> {
    //头节点  记录下头结点
    private Node<E> head;
    //尾节点 记录下尾结点
    private Node<E> last;
    //链表的长度
    private int length;

    /**
     * 添加元素
     *
     * @param value value
     */
    public void add(E value) {
        linkedLast(value);
        length++;
    }

    private void linkedLast(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(value, l, null);
        last = newNode;
        if (head == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
    }


    /**
     * 获取当前节点的值
     *
     * @param index 索引
     * @return value
     */
    private E get(int index) {
        if (index >= 0 || index <= length) {
            return node(index).value;
        }
        return null;
    }

    /**
     * 获取当前节点
     *
     * @param index 索引
     * @return cur
     */
    private Node<E> node(int index) {
        //位运算效率高
        if (index < (length >> 1)) {
            //从前往后遍历
            Node<E> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else {
            //从后往前遍历
            Node<E> cur = last;
            for (int i = length - 1; i > index; i--) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    /**
     * 插入元素
     *
     * @param index 索引
     * @param value value
     */
    public void insert(int index, E value) {
        if (index == length) {
            linkedLast(value);
        } else {
            linkedBefore(node(index), value);
        }

//        if (index == 0) {
//            Node<E> h = head;
//            head = newNode;
//            newNode.next = h;
//        } else {
//            Node<E> prev = node(index - 1);
//            Node<E> cur = prev.next;
//            prev.next = newNode;
//            newNode.next = cur;
//        }
        length++;
    }

    /**
     * 插入结点
     *
     * @param cur   当前结点
     * @param value value
     */
    private void linkedBefore(Node<E> cur, E value) {
        Node<E> prev = cur.prev;
        //插入结点
        Node<E> newNode = new Node<>(value, prev, cur);
        cur.prev = newNode;
        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
    }


    /**
     * 移除元素
     *
     * @param index 索引
     */
    public void remove(int index) {
        if (index >= 0 && index <= length) {
            unLinked(node(index));
        }
        length--;
    }

    private void unLinked(Node<E> cur) {
//        if (index == 0) {
//            Node<E> h = head;
//            head = h.next;
//            h = null;
//        } else {
//            //前一个节点
//            Node<E> prev = node(index - 1);
//            //当前节点
//            Node<E> cur = prev.next;
//            //前一个节点的next指向当前节点的next
//            prev.next = cur.next;
//            cur = null;
//        }

        Node<E> prev = cur.prev;
        Node<E> next = cur.next;
        //考虑左右两边为空的情况，严谨，思维灵活
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
        cur = null;
    }

    public int size() {
        return length;
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            doubleLinkedList.add(i);
        }
        doubleLinkedList.remove(0);
        doubleLinkedList.insert(0, 10);
        for (int i = 0; i < doubleLinkedList.size(); i++) {
            System.out.println(doubleLinkedList.get(i));
        }
    }
}

package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

/**
 * Description:
 * Data：8/10/2018-5:52 PM
 *
 * @author yanzhiwen
 */

class Node<E> {
    Node<E> next;
    E value;

    public Node(E value, Node<E> next) {
        this.next = next;
        this.value = value;
    }
}

class LinkedList<E> {
    //头节点
    Node<E> head;
    int length;

    public void push(E e) {
        //添加一个数据
        Node<E> new_node = new Node(e, null);
        if (head == null) {
            Node<E> last = node(length - 1);
            last.next = new_node;
        } else {
            head = new_node;
        }
        length++;
    }

    //找到尾节点
    private Node<E> node(int length) {
        Node<E> h = head;
        for (int i = 0; i < length; i++) {
            h = h.next;
        }
        return h;
    }

}


public class SingleLinkListTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.push(1);
    }
}

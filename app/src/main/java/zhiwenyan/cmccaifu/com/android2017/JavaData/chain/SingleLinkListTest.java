package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

import java.util.LinkedList;

/**
 * Description:
 * Data：8/10/2018-5:52 PM
 *
 * @author yanzhiwen
 */
public class SingleLinkListTest {
    private Node head;
    private int size;

    private class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    private Object add(Object obj) {
        Node newHead = new Node(obj);
        if (head == null) {
            head = newHead;
        } else {
            newHead.next = head;
            head = newHead;
        }
        size++;
        return obj;
    }

    private void remove() {

    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.remove(1);
    }
}

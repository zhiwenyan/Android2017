package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

/**
 * Description:
 * Data：2018/8/5
 * Author:Steven
 */
public class LinkList1 {
    private int size;
    private Node head;

    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    private Object addHead(Object obj) {
        Node newHead = new Node(obj);  // A   B
        if (size == 0) {
            head = newHead; // A
        } else {
            newHead.next = head; // A
            head = newHead; //B
        }
        size++;
        return obj;
    }

    private Object delete() {
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    private Node find(Object obj) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }
}

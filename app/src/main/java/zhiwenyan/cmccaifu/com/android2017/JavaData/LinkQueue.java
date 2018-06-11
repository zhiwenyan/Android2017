package zhiwenyan.cmccaifu.com.android2017.JavaData;

/**
 * Description:
 * Data：6/1/2018-9:43 AM
 *
 * @author yanzhiwen
 */
public class LinkQueue<T> {

    private class Node {
        //当前节点的数据
        private T data;
        //指向下一个节点
        private Node next;

        private Node() {

        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public void offer(T element) {
        if (front == null) {
            front = new Node();
            rear = front;
        } else {
            Node newDode = new Node(element, null);
            //让尾节点的next指向新增的节点
            rear.next = newDode;
            //以新节点作为新的尾节点
            rear = newDode;
        }
        size++;
    }

    // 出队
    public T poll() {
        Node oldFront = front;
        if (!isEmpty()) {
            // 指向下一个节点
            front = front.next;
            oldFront.next = null;
            size--;
        }
        return oldFront.data;

    }

    /**
     * @return
     * @Title: peek
     * @Description: 返回队列顶元素，但不删除队列顶元素
     */
    public T peek() {
        return rear.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

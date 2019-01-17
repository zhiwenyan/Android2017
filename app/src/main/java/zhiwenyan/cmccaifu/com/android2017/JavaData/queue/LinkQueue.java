package zhiwenyan.cmccaifu.com.android2017.JavaData.queue;

import java.io.Serializable;

/**
 * Description: 链式队列
 * Data：2018/8/19
 * Author:Steven
 */

public class LinkQueue<T> implements Serializable {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -6726728595616312615L;

    //定义一个内部类Node，Node实例代表链队列的节点。
    private class Node {

        private T data;//保存节点的数据

        private Node next;//指向下个节点的引用

        //无参数的构造器
        public Node() {
        }

        //初始化全部属性的构造器
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;//保存该链队列的头节点

    private Node rear;//保存该链队列的尾节点

    private int size;//保存该链队列中已包含的节点数

    /**
     * <p>Title: LinkQueue </p>
     * <p>Description: 创建空链队列 </p>
     */
    public LinkQueue() {
        //空链队列，front和rear都是null
        front = null;
        rear = null;
    }

    /**
     * <p>Title: LinkQueue </p>
     * <p>Description: 以指定数据元素来创建链队列，该链队列只有一个元素</p>
     */
    public LinkQueue(T element) {
        front = new Node(element, null);
        //只有一个节点，front、rear都指向该节点
        rear = front;
        size++;
    }

    /**
     * @return
     * @Title: size
     * @Description: 获取顺序队列的大小
     */
    public int size() {
        return size;
    }

    /**
     * @param element
     * @Title: offer
     * @Description: 入队
     */
    public void offer(T element) {
        //如果该链队列还是空链队列
        if (front == null) {
            front = new Node(element, null);
            rear = front;//只有一个节点，front、rear都指向该节点
        } else {
            Node newNode = new Node(element, null);//创建新节点
            rear.next = newNode;//让尾节点的next指向新增的节点
            rear = newNode;//以新节点作为新的尾节点
        }
        size++;
    }

    /**
     * @return
     * @Title: poll
     * @Description: 出队
     */
    public T poll() {
        //头结点
        Node oldFront = front;
        front = front.next;
        oldFront.next = null;
        size--;
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

    /**
     * @return
     * @Title: isEmpty
     * @Description: 判断顺序队列是否为空队列
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @Title: clear
     * @Description: 清空顺序队列
     */
    public void clear() {
        //将front、rear两个节点赋为null
        front = null;
        rear = null;
        size = 0;
    }

    public String toString() {
        //链队列为空链队列时
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = front; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    public static void main(String[] args) {
        LinkQueue<String> queue = new LinkQueue<String>("aaaa");
        //添加两个元素
        queue.offer("bbbb");
        queue.offer("cccc");
        System.out.println(queue);
        //删除一个元素后
        queue.poll();
        System.out.println("删除一个元素后的队列：" + queue);
        //再次添加一个元素
        queue.offer("dddd");
        System.out.println("再次添加元素后的队列：" + queue);
        //删除一个元素后，队列可以再多加一个元素
        queue.poll();
        //再次加入一个元素
        queue.offer("eeee");
        System.out.println(queue);
    }
}

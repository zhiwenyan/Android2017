package zhiwenyan.cmccaifu.com.android2017.JavaData.queue;

import java.util.ArrayDeque;

/**
 * Description:循环队列
 * Data：6/11/2018-10:30 AM
 *
 * @author yanzhiwen
 */
public class LoopQueue {
    //底层是数组
    private int[] arr;
    private int elements;
    private int front;
    private int end;

    public LoopQueue() {
        this.arr = new int[10];
        this.elements = 0;
        this.front = 0;
        this.end = -1;
    }

    public LoopQueue(int maxSize) {
        this.arr = new int[maxSize];
    }

    /**
     * 插入数据：从队尾插入
     *
     * @param value
     */
    public void insert(int value) {
        if (end == arr.length - 1) {
            end = -1;
        }
        arr[++end] = value;
        elements++;
    }

    /**
     * 移除数据：从队头删除
     *
     * @return
     */
    public int remove() {
        int value = arr[front++];
        if (front == arr.length) {
            front = 0;
        }
        elements--;
        return value;
    }

    /**
     * 查看数据，从队头查看
     *
     * @return
     */
    public int peek() {
        return arr[front];
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return elements == 0;
    }

    public boolean isFull() {
        return this.elements == arr.length;
    }

    public static void main(String[] args) {
        LoopQueue queue = new LoopQueue(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());

        System.out.println(queue.peek() + "---");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());

        System.out.println(queue.peek());
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());

        ArrayDeque<Integer> arrayDeque=new ArrayDeque<>();
        arrayDeque.add(1);
        arrayDeque.size();
    }
}

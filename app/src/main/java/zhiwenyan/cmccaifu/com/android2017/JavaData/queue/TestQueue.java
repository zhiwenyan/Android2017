package zhiwenyan.cmccaifu.com.android2017.JavaData.queue;

/**
 * Description: 先进先出 后进后出
 * Data：6/11/2018-9:59 AM
 *
 * @author yanzhiwen
 */
public class TestQueue {
    //底层是数组
    private int[] arr;
    private int elements;
    private int front;
    private int end;

    public TestQueue() {
        this.arr = new int[10];
        this.elements = 0;
        this.front = 0;
        this.end = -1;
    }

    public TestQueue(int maxSize) {
        this.arr = new int[maxSize];
    }

    /**
     * 插入数据：从队尾插入
     *
     * @param value
     */
    public void insert(int value) {
        arr[++end] = value;
        elements++;
    }

    /**
     * 移除数据：从队头删除
     *
     * @return
     */
    public int remove() {
        elements--;
        return arr[front++];
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
        TestQueue queue = new TestQueue(5);
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
        queue.insert(23);
    }
}

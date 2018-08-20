package zhiwenyan.cmccaifu.com.android2017.JavaData.queue;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Description: 顺序队列
 * Data：2018/8/19
 * Author:Steven
 */

public class ArrayQueue<T> implements Serializable {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 7333344126529379197L;

    private int DEFAULT_SIZE = 10;

    private int capacity;//保存数组的长度

    private Object[] elementData;//定义一个数组用于保存顺序队列的元素

    private int front = 0;//队头

    private int rear = 0;//队尾

    //以默认数组长度创建空顺序队列
    public ArrayQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    //以一个初始化元素来创建顺序队列
    public ArrayQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    public ArrayQueue(int initSize) {
        elementData = new Object[initSize];
    }

    /**
     * 以指定长度的数组来创建顺序队列
     *
     * @param element  指定顺序队列中第一个元素
     * @param initSize 指定顺序队列底层数组的长度
     */
    public ArrayQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    /**
     * @return
     * @Title: size
     * @Description: 获取顺序队列的大小
     */
    public int size() {
        return rear - front;
    }

    /**
     * @param element
     * @Title: offer
     * @Description: 入队  -->对尾入队
     */
    public void offer(T element) {
        ensureCapacity(rear + 1);
        elementData[rear++] = element;
    }

    private void ensureCapacity(int minCapacity) {
        //如果数组的原有长度小于目前所需的长度
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;  //16
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            // minCapacity is usually close to size, so this is a win:
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

    }

    /**
     * return
     * Title: poll
     * Description: 出队 -->对头
     */
    public T poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        //保留队列的front端的元素的值
        T oldValue = (T) elementData[front];
        //释放队列的front端的元素
        elementData[front++] = null;
        return oldValue;
    }

    /**
     * @return
     * @Title: peek
     * @Description: 返回队列顶元素，但不删除队列顶元素
     */
    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常");
        }
        return (T) elementData[front];
    }

    /**
     * @return
     * @Title: isEmpty
     * @Description: 判断顺序队列是否为空队列
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * @Title: clear
     * @Description: 清空顺序队列
     */
    public void clear() {
        //将底层数组所有元素赋为null
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    public static void main(String[] args) {

    }
}

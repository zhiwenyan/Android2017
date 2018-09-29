package zhiwenyan.cmccaifu.com.android2017.JavaData.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description:
 * Data：8/9/2018-10:43 AM
 *
 * @author yanzhiwen
 */
public class TestArrayList<T> {
    private Object[] elementData = new Object[3];
    private int size;

    public TestArrayList() {
    }

    public void add(T t) {
        elementData[size++] = t;
    }

    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("超出了边界");
        return ( T ) elementData[index];
    }

    public T remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("超出了边界");

        T oldValue = ( T ) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            // src:源数组；srcPos:源数组要复制的起始位置；
            //  dest:目的数组；	destPos:目的数组放置的起始位置；	length:复制的长度。
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null;

        return oldValue;
    }

    public boolean remove(Object o) {
        if (o != null) {
            for (int index = 0; index < elementData.length; index++) {
                if (o.equals(elementData[index])) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0)
                        System.arraycopy(elementData, index + 1, elementData, index,
                                numMoved);
                    elementData[--size] = null; // clear to let GC do its work
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrs = new ArrayList<>();
        arrs.add(1);
        arrs.add(2);
        arrs.add(3);
        arrs.get(1);
        arrs.remove(0);

        TestArrayList<String> testArrayList = new TestArrayList<>();
        testArrayList.add("1");
        testArrayList.add("2");
        testArrayList.add("3");

        System.out.println(testArrayList.get(1));
        System.out.println(testArrayList.remove(0));
        System.out.println(testArrayList.remove("3"));

        int[] is = new int[]{1, 2, 3, 4, 5};
        int[] is2 = new int[is.length];
        System.arraycopy(is, 0, is2, 0, is.length);
        System.out.println(Arrays.toString(is2));

        Vector<Integer> vector = new Vector<>();
        vector.add(1);

        Queue<Integer> queue=new ArrayBlockingQueue<Integer>(128);
        queue.add(1);

    }
}

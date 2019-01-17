package zhiwenyan.cmccaifu.com.android2017.JavaData.stack;

import java.util.Arrays;

/**
 * Description:数组实现栈
 * Data：2018/12/22
 * Author:Steven
 */
public class ArrayStack<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int top = 0;

    public ArrayStack() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int size) {
        elementData = new Object[size];
    }

    /**
     * 入栈
     *
     * @param e
     */
    public void add(E e) {
        //扩容
        ensureCapacityInternal(top + 1);
        elementData[top++] = e;
    }


    /**
     * 出栈
     */
    public void remove() {
        top = top - 1;
        System.out.println(elementData[top]);
        elementData[top] = null;
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    public E getTopElementData() {
        return (E) elementData[top - 1];
    }


    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 20; i++) {
            stack.add(i);
        }
        System.out.println(stack.getTopElementData());


        for (int i = 0; i < 20; i++) {
            stack.remove();
        }

    }

}

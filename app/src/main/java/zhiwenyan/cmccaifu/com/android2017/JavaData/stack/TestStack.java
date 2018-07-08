package zhiwenyan.cmccaifu.com.android2017.JavaData.stack;

import java.util.Stack;

/**
 * Description:栈 先进后出  后进先出
 * Data：6/11/2018-9:48 AM
 *
 * @author yanzhiwen
 */
public class TestStack {
    //底层是一个数组
    private int[] arr;
    private int top;

    public TestStack() {
        this.arr = new int[10];
        this.top = -1;
    }

    public TestStack(int maxSize) {
        arr = new int[maxSize];
        top = -1;
    }

    /**
     * 添加数据
     *
     * @param value
     */
    public void push(int value) {
        arr[++top] = value;
    }

    /**
     * 移除数据
     *
     * @return
     */
    public int pop() {
        return arr[top--];
    }

    /**
     * 查找数据
     *
     * @return
     */
    public int peek() {
        return arr[top];
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断是否满了
     *
     * @return
     */
    public boolean isFull() {
        return top == arr.length - 1;
    }

    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);
        testStack.push(4);
        testStack.push(5);
        System.out.println(testStack.isEmpty());
        System.out.println(testStack.isFull());
        System.out.println(testStack.peek());
        while (!testStack.isEmpty()) {
            System.out.println(testStack.pop() + "");
        }
        Stack<Integer> stack=new Stack<>();
        stack.add(1);
    }
}

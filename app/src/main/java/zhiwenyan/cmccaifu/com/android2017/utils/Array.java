package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class Array {
    private int[] mArray;

    /**
     * 构造函数  为数组赋值
     *
     * @param array
     */
    public Array(int[] array) {
        this.mArray = array;
    }

    /**
     * 给数组求和
     * @return
     */
    public int sum() {
        int sum = 0;
        //循环数组求和 一个一个叠加
        for (int i = 0; i < mArray.length; i++) {
            sum += mArray[i];
        }
        return sum;
    }
}

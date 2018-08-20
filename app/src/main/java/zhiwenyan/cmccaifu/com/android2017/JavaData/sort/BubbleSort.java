package zhiwenyan.cmccaifu.com.android2017.JavaData.sort;

/**
 * Description: 冒泡排序
 * Data：2018/7/15
 * Author:Steven
 */
public class BubbleSort {
    /**
     * 冒泡排序
     */
    private void BubbleSort() {
        int arr[] = {12, 1, 12, 34, 56, 12, 9, 23};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {        // 相邻元素两两对比
                    int temp = arr[j + 1];        // 元素交换
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void main(String[] args) {
        BubbleSort sort=new BubbleSort();
        sort.BubbleSort();
    }
}

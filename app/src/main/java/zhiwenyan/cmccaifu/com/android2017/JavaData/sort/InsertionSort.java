package zhiwenyan.cmccaifu.com.android2017.JavaData.sort;

/**
 * Description:插入排序
 * Data：2018/7/15
 * Author:Steven
 */
public class InsertionSort {
    int arr[] = {12, 1, 12, 34, 56, 12, 9, 23, 0};

    private void InsertionSort() {
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }


        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void main(String[] args) {
        InsertionSort sort=new InsertionSort();
        sort.InsertionSort();
    }


}

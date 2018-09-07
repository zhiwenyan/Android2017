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
//        int preIndex, current;
//        for (int i = 1; i < len; i++) {
//            preIndex = i - 1;
//            current = arr[i];
//            while (preIndex >= 0 && arr[preIndex] > current) {
//                arr[preIndex + 1] = arr[preIndex];
//                preIndex--;
//            }
//            arr[preIndex + 1] = current;
//        }
//        for (int i = 1; i < len; i++) {
//            for (int j = i; j > 0; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = temp;
//                } else {
//                    break;
//                }
//            }
//        }

        int j, i;
        for (i = 1; i < len; i++) {
            int temp = arr[i];
            for (j = i; j > 0; j--) {
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            //插入适合的位置
            arr[j] = temp;

        }
        for (i = 0; i < len; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        sort.InsertionSort();
    }


}

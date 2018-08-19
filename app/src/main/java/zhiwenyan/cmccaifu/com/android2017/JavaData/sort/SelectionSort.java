package zhiwenyan.cmccaifu.com.android2017.JavaData.sort;

/**
 * Description:选择排序
 * <p>
 * 的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * Data：2018/7/15
 * Author:Steven
 */
public class SelectionSort {

    private void SelectionSort() {
        int arr[] = {12, 1, 12, 34, 56, 12, 9, 23};
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }


    private void SelectionSort1() {
        int arr[] = {12, 1, 12, 34, 56, 12, 9, 23};
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.SelectionSort();
    }

}

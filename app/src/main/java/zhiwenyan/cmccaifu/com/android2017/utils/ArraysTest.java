package zhiwenyan.cmccaifu.com.android2017.utils;

import android.util.SparseArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Data：8/3/2018-2:10 PM
 *
 * @author yanzhiwen
 */
public class ArraysTest {
    public static void main(String[] args) {

        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10};

        System.out.println(Arrays.binarySearch(a, 5));
        System.out.println(Arrays.copyOf(a, 10)[5]);
        Arrays.sort(a);
        System.out.println(a[a.length - 1]);
        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6);
        System.out.println(Collections.binarySearch(list, 3));
        Collections.swap(list, 1, 2);
        System.out.println(list.get(1));

    }
}

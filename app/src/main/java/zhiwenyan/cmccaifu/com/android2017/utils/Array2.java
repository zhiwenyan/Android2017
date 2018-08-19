package zhiwenyan.cmccaifu.com.android2017.utils;

import java.util.Scanner;

/**
 * 二维数组
 * Created by yanzhiwen on 2017/11/3.
 */

public class Array2 {

//    public T get(R r) {
////        return (T) String.valueOf(r);
////    }

    public static void main(String[] args) {
//        Array2<String, Integer> array = new Array2<>();
//        array.get(1);

        int a[][] = {{1, 1}, {1, 2}};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println(a[i][j]);
            }
        }
        //从控制台获取行数
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
//根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
        int[][] arr = new int[row][];
//遍历二维数组
        for (int i = 0; i < row; i++) {
            //初始化每一行的这个一维数组
            arr[i] = new int[i + 1];
            //遍历这个一维数组，添加元素
            for (int j = 0; j <= i; j++) {
                //每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {//每一个元素是它上一行的元素和斜对角元素之和
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}

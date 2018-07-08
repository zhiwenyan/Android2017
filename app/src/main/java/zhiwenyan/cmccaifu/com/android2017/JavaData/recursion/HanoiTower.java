package zhiwenyan.cmccaifu.com.android2017.JavaData.recursion;

/**
 * Description: 汉诺塔问题
 * Data：2018/7/8
 * Author:Steven
 */
public class HanoiTower {


    /**
     * 移动盘子
     *
     * @param topN  移动的盘子数
     * @param from  起始塔座
     * @param inter 中间塔座
     * @param to    目标塔座
     */
    public static void doTower(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("盘子1从from" + from + "塔座到to" + to + "塔座");
        } else {
            doTower(topN - 1, from, to, inter);
            System.out.println("盘子" + topN + "从from" + from + "塔座到to" + to + "塔座");
            doTower(topN - 1, inter, from, to);
        }

    }

    public static void main(String[] args) {
        doTower(3, 'A', 'B', 'C');
    }
}

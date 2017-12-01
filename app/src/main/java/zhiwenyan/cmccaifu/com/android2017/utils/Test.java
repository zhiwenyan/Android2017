package zhiwenyan.cmccaifu.com.android2017.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yanzhiwen on 2017/11/8.
 */
class word extends Thread {
    public void run() {
        //   super.run();
        String[] s = {"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡"};
        try {
            //s.length?
            for (int i = 0; i < s.length; i++) {
                //s[i].length()?  length跟length()的区别？
                for (int j = 0; j < s[i].length(); j++) {
                    Thread.sleep(200 * (i + 1));
                    System.out.print(s[i].charAt(j));
                }
                System.out.println();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Test {
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 验证邮箱
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isEmail(final String input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final String input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    public static void main(String[] args) {
        System.out.println(isEmail("1304109092@qq.com"));
        word w = new word();
        w.start();
        //15.0,76.85;
        float a = 15.0f;
        float b = 76.85f;
        System.out.println(b - a);
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(2);
        lists.add(1);
        for (int i = 0; i < lists.size(); i++) {
            //每一个元素去比较下
            for (int j = i + 1; j < lists.size(); j++) {
                //重复的元素相等
                if (lists.get(i).equals(lists.get(j))) {
                    System.out.println("重复的元素" + lists.get(i) + "重复的位置" + i + "," + j);
                }
            }
        }

    }
}




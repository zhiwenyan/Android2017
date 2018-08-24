package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：7/2/2018-3:00 PM
 *
 * @author yanzhiwen
 */
public class StringUtils {
    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = "a" + "b";
        String str5 = str1 + str2;
        String str6 = new String("ab");

        System.out.println(str3.equals(str6)); //true
        System.out.println(str3 == str6);  //false
        System.out.println(str3 == str4); //true
        System.out.println(str4 == str5);
        System.out.println((str1+str2) == str5);





        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = "ab" + "c";
        System.out.println("a == b" + (a == b)); //true
        System.out.println("a == c" + (a == c)); //false
        System.out.println("a == d" + (a == d)); //true
        System.out.println("b == c" + (b == c)); //false
        System.out.println("b == d" + (b == d)); //true
        System.out.println("c == d" + (c == d)); //false




        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");

        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd");


        StringBuilder ss=new StringBuilder();
        ss.append(1);


        String ss1="a";
        String ss2="b";
        String ss3="ab";
        String ss4=ss1+ss2;
        System.out.println((ss1+ss2)==ss3);
        System.out.println(ss4==ss3);
    }
}

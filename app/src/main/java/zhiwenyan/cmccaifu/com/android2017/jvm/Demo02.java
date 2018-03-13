package zhiwenyan.cmccaifu.com.android2017.jvm;

/**
 * Description:
 * Data：3/8/2018-3:55 PM
 *
 * @author: yanzhiwen
 */
public class Demo02 {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(System.getProperty("java.class.path"));
        ArrayList<java.lang.String> arrayList = new ArrayList<>();
        arrayList.add("a");
        System.out.println(arrayList.getClass().getClassLoader());
        System.out.println("**********************************");
        String s = "AA";
        System.out.println(s.getClass().getClassLoader().getParent());
        System.out.println(System.class.getClassLoader());
        java.util.ArrayList arrayList1 = new java.util.ArrayList();
        System.out.println(arrayList1.getClass().getClassLoader());
        Demo02 demo02=new Demo02();
        System.out.println(demo02.getClass().getClassLoader());
    }
}

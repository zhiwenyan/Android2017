package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple5;

/**
 * Description:
 * Data：6/6/2018-3:55 PM
 *
 * @author yanzhiwen
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSomeThing() {

    }

    public static void main(String[] args) {
        EnumSingleton enumSingleton1=EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2=EnumSingleton.INSTANCE;
        System.out.println(enumSingleton1);
        System.out.println(enumSingleton2);
    }
}

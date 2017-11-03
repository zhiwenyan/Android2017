package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple1;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class TeacherEat implements Eat {


    private Eat mEat;

    public TeacherEat(PersonEat eat) {
        mEat = eat;
    }
    @Override
    public void eat() {
        System.out.println("点菜");
        System.out.println("点饭");
        eat();
        System.out.println("盘子不用自己送");
    }
}

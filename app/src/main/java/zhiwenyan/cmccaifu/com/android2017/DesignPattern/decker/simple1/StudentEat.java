package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple1;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class StudentEat implements Eat {

    private Eat mEat;

    public StudentEat(PersonEat eat) {
        mEat = eat;
    }

    @Override
    public void eat() {
        System.out.println("点菜");
        eat();
        System.out.println("吃完送到指定的地点");

    }
}

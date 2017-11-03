package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple1;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class Client {
    public static void main(String[] args) {
        //一般的写法，new对象调用方法
        PersonEat personEat=new PersonEat();
        TeacherEat teacherEat = new TeacherEat(personEat);
        teacherEat.eat();

        //装饰者设计模式怎么写？？？一般情况下把类作为构造参数传递
    }
}

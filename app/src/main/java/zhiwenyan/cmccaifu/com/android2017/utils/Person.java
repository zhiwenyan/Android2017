package zhiwenyan.cmccaifu.com.android2017.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Data：8/9/2018-10:17 AM
 *
 * @author yanzhiwen
 */
public class Person {
    private int id;
    private int age;
    private String name;

    public Person() {
    }

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();



        Person person = new Person(1, 24, "steven");
        Person person2 = new Person(1, 24, "steven");
        Person p2 = person;
        System.out.println(person.equals(person2));
        System.out.println(person == person2);
        System.out.println(p2 == person);
        System.out.println(p2);
        System.out.println(p2.getAge());
        String ss = "ssss";
        String ss1 = "ssss";
        System.out.println(ss.equals(ss1));
        System.out.println(ss == ss1);

    }
}

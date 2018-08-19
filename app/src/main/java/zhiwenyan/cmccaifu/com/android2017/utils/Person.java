package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：2018/8/19
 * Author:Steven
 */
public class Person extends Object{
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person();
        System.out.println(person1.equals(person2));

        System.out.println(person1.hashCode());
        try {
            System.out.println(person1.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

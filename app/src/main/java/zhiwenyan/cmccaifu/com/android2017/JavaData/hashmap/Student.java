package zhiwenyan.cmccaifu.com.android2017.JavaData.hashmap;

/**
 * Description:
 * Data：9/11/2018-3:48 PM
 *
 * @author yanzhiwen
 */
public class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return (( Student ) obj).name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

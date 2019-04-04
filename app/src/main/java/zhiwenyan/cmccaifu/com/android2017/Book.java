package zhiwenyan.cmccaifu.com.android2017;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhiwenyan on 5/13/17.
 */

public class Book implements Parcelable {
    public int id;
    public int age;
    public String name;

    public Book(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将当前对象写入序列化结构中
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.age);
        dest.writeString(this.name);
    }

    /**
     * 反序列化  从序列化的对象创建原始对象
     *
     * @param in
     */
    protected Book(Parcel in) {
        this.id = in.readInt();
        this.age = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}

package zhiwenyan.cmccaifu.com.android2017;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhiwenyan on 5/13/17.
 */

public class Book implements Parcelable {
    int id;
    String type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.id = in.readInt();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
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

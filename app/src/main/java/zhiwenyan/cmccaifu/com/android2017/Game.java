package zhiwenyan.cmccaifu.com.android2017;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhiwenyan on 5/24/17.
 */

public class Game implements Parcelable {
    public String gameName;
    public String gameDescribe;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gameName);
        dest.writeString(this.gameDescribe);
    }

    public Game(String gameName, String gameDescribe) {
        this.gameName = gameName;
        this.gameDescribe = gameDescribe;
    }

    public Game() {
    }

    protected Game(Parcel in) {
        this.gameName = in.readString();
        this.gameDescribe = in.readString();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}

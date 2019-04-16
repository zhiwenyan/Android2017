package zhiwenyan.cmccaifu.com.android2017.utils.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ProviderActivity extends AppCompatActivity {
    private static final String TAG = ProviderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);


        Uri bookUri = Uri.parse("content://zhiwenyan.cmccaifu.com.android2017.utils.db.book.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "Android开发艺术探索");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.id = bookCursor.getInt(0);
            book.name = bookCursor.getString(1);
            Log.d(TAG, "query book" + book.toString());
        }
        bookCursor.close();
        Uri userUri = Uri.parse("content://zhiwenyan.cmccaifu.com.android2017.utils.db.book.provider/user");

        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.id = userCursor.getInt(0);
            user.name = userCursor.getString(1);
            user.sex = userCursor.getString(2);
            Log.d(TAG, "query user" + user.toString());
        }
        userCursor.close();
    }
}

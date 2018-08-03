package zhiwenyan.cmccaifu.com.android2017.Gson.LargeData;

/**
 * Description:
 * Data：7/25/2018-11:39 AM
 *
 * @author yanzhiwen
 */
public class LargeData {

    private long[] numbers;

    public void create(final int length) {
        numbers = new long[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = i;
        }
    }

    public long[] getNumbers() {
        return numbers;
    }

}

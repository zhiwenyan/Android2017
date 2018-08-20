package zhiwenyan.cmccaifu.com.android2017.Socket;

import android.util.Log;

/**
 * Description:
 * Data：2018/7/29
 * Author:Steven
 */
public class EchoClient1 {
    private static final String TAG = "EchoClient1";

    private final LongLiveSocket mLongLiveSocket;

    public EchoClient1(String host, int port) {
        mLongLiveSocket = new LongLiveSocket(
                host, port,
                (data, offset, len) -> Log.i(TAG, "EchoClient: received: " + new String(data, offset, len)),
                () -> true);
    }

    public void send(String msg) {
        mLongLiveSocket.write(msg.getBytes(), new LongLiveSocket.WritingCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onFail(byte[] data, int offset, int len) {
                Log.w(TAG, "onFail: fail to write: " + new String(data, offset, len));
                mLongLiveSocket.write(data, offset, len, this);
            }
        });
    }

    public void close() {
        mLongLiveSocket.close();
    }
}

package zhiwenyan.cmccaifu.com.android2017.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.InjectView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class HttpURLConnectionActivity extends BaseActivity {
    @InjectView(R.id.tv)
    TextView mTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net;
    }


    @OnClick(R.id.downLoadBtn)
    public void onClick() {
        DownloadWebpageText down = new DownloadWebpageText();
        down.execute("https://www.baidu.com/");
    }

    public class DownloadWebpageText extends AsyncTask<String, Void, String> {
        private int contentLength;

        @Override
        protected String doInBackground(String... params) {
            return connectNet(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTv.setText(s);
        }

        private String connectNet(String url) {
            InputStream inputStream = null;
            try {
                URL url1 = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.setReadTimeout(8 * 1000);
                connection.setConnectTimeout(10 * 1000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                contentLength = connection.getContentLength(); //获取大小
                inputStream = connection.getInputStream(); //获取输入流
                return readIt(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        //文字信息
        private String readIt(InputStream stream) throws IOException {
            Reader reader;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[1024];
            reader.read(buffer);
            return new String(buffer);
        }

        //图片
        private void readImg(InputStream stream) {
            //解码
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            // TODO: 4/19/17

        }
    }
}

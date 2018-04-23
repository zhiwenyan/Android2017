package zhiwenyan.cmccaifu.com.android2017.XmlResourceParser;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.R;

public class XmlResourceParserActivity extends AppCompatActivity {
    private static final String TAG = "XmlResourceParserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_resource_parser);
        logXmlData();
    }

    public void logXmlData() {
        XmlResourceParser xmlParser = getResources().getXml(R.xml.xml);

        try {
            int event = xmlParser.getEventType();   //先获取当前解析器光标在哪
            while (event != XmlPullParser.END_DOCUMENT) {    //如果还没到文档的结束标志，那么就继续往下处理
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "xml解析开始");
                        break;
                    case XmlPullParser.START_TAG:
                        //一般都是获取标签的属性值，所以在这里数据你需要的数据
                        Log.d(TAG, "当前标签是：" + xmlParser.getName());
                        if (xmlParser.getName().equals("Node")) {
                            //两种方法获取属性值
                            Log.d(TAG, "第一个属性：" + xmlParser.getAttributeName(0)
                                    + ": " + xmlParser.getAttributeValue(0));
                            Log.d(TAG, "第二个属性：" + xmlParser.getAttributeName(1) + ": "
                                    + xmlParser.getAttributeValue(null, "att2"));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.d(TAG, "Text:" + xmlParser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                event = xmlParser.next();   //将当前解析器光标往下一步移
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package zhiwenyan.cmccaifu.com.android2017.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Iterator;
import java.util.Set;

/**
 * Description:  Android与js交互
 *
 * https://juejin.im/post/5c695fbbf265da2dbe02d37e
 * Data：2/19/2019-9:37 AM
 *
 * @author yanzhiwen
 */
public class XWebView extends WebView {
    public XWebView(Context context) {
        this(context, null);
    }

    public XWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // 此处的 launcher 可以自定义，最终是 JS 中要使用的对象
        addJavascriptInterface(new JsInterface(), "launcher");


        //方法二：通过 WebViewClient 的 shouldOverrideUrlLoading() 方法回调拦截 url
        setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                // 例如：url = "js://webview?arg1=111&arg2=222"
                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                if (uri.getScheme().equals("js")) {
                    // 拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        // 执行JS所需要调用的逻辑
                        Log.e("TAG", "JS 调用了 Android 的方法");
                        Set<String> collection = uri.getQueryParameterNames();
                        Iterator<String> it = collection.iterator();
                        String result = "";
                        while (it.hasNext()) {
                            result += uri.getQueryParameter(it.next()) + ",";
                        }
                        //    tv_result.setText(result);
                    }
                    return true;
                }


                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        // 方法三：通过 WebChromeClient 的 onJsAlert() 、 onJsConfirm() 、 onJsPrompt（）
        // 方法回调拦截 JS 对话框 alert() 、 confirm() 、 prompt（） 消息
        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                // 例如：url = "js://webview?arg1=111&arg2=222"
                Uri uri = Uri.parse(message);
                Log.e("TAG", "----onJsPrompt--->>" + url + "," + message);
                // 如果url的协议 = 预先约定的 js 协议
                if (uri.getScheme().equals("js")) {
                    // 拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("prompt")) {
                        // 执行JS所需要调用的逻辑
                        Log.e("TAG", "JS 调用了 Android 的方法");
                        Set<String> collection = uri.getQueryParameterNames();
                        Iterator<String> it = collection.iterator();
                        String result2 = "";
                        while (it.hasNext()) {
                            result2 += uri.getQueryParameter(it.next()) + ",";
                        }
                        // tv_result.setText(result2);
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });


        String str = "test";
        // Android 调用 JS 方法
        this.loadUrl("javascript:if(window.callJS){window.callJS('" + str + "');}");

        //方法二： 通过 WebView 的 evaluateJavascript()
        //该方法比第一种方法效率更高，使用更简洁；
        //该方法执行不会刷新页面，而第一种方法（ loadUrl ）则会；
        //Android 4.4 以后才能使用。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.evaluateJavascript("javascript:if(window.callJS){window.callJS('" + str + "');}", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.e("TAG", "--------->>" + value);
                }
            });
        }

    }

    class JsInterface {
        /**
         * 这个方法由 JS 调
         *
         * @param value
         */
        @JavascriptInterface
        public void callAndroid(String value) {

        }
    }


    @Override
    public void destroy() {
        setWebViewClient(null);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(false);
        removeJavascriptInterface("launcher");
        removeAllViewsInLayout();

        removeAllViews();
        //clearCache(true);

        super.destroy();
    }
}

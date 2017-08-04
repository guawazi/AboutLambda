package cn.duozhuan.aboutlambda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

public class DemoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NestedScrollView nestScroll;
    private WebView mWebView;
    private CollapsingToolbarLayout collalsing;
    private AppBarLayout appbar;
    private TextView mTvDuozhuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mWebView = (WebView) findViewById(R.id.web_view);
        nestScroll = (NestedScrollView) findViewById(R.id.nest_scroll);
        collalsing = (CollapsingToolbarLayout) findViewById(R.id.collalsing);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        mTvDuozhuan = (TextView) findViewById(R.id.tv_duozhuan);
        setSupportActionBar(toolbar);
        WebViewClient client = new WebViewClient();
        mWebView.setWebViewClient(client);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setSupportMultipleWindows(true);
        String usragent = webSettings.getUserAgentString();
        usragent = usragent + " daichuqu housekeeper 2.4.2.58";
        webSettings.setUserAgentString(usragent);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        mWebView.loadUrl("http://www.duoz.net/");
        appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            Logger.e("verticalOffset:" + verticalOffset);
            if (verticalOffset < -200) {
                mTvDuozhuan.setTextColor(Color.BLACK);
            } else {
                mTvDuozhuan.setTextColor(Color.WHITE);
            }
        });
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, DemoActivity.class);
        context.startActivity(intent);
    }
}

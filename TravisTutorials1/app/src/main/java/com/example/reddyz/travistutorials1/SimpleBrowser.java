package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Reddyz on 12-10-2016.
 */
public class SimpleBrowser extends AppCompatActivity implements View.OnClickListener{

    EditText urlText;
    WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        setupWebView();

        Button go, back, refresh, forward, clear;
        go = (Button) findViewById(R.id.bGo);
        back = (Button) findViewById(R.id.bBack);
        forward = (Button) findViewById(R.id.bForward);
        refresh = (Button) findViewById(R.id.bRefresh);
        clear = (Button) findViewById(R.id.bClear);
        urlText = (EditText) findViewById(R.id.etURLInputText);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    private void setupWebView() {
        web = (WebView) findViewById(R.id.wvBrowser);
        web.setBackgroundColor(Color.LTGRAY);

        //Set webclient - to Load URL inside a webview(not in separate browser).
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.setWebViewClient(new WebViewClient());
        try {
            web.loadUrl("http://www.google.com");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack :
                if(web.canGoBack())
                    web.goBack();
                break;
            case R.id.bForward :
                if(web.canGoForward())
                    web.goForward();
                break;
            case R.id.bRefresh :
                web.reload();
                break;
            case R.id.bClear :
                web.clearHistory();
                break;
            case R.id.bGo :
                String urlStr = urlText.getText().toString();
                web.loadUrl(urlStr);
                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.hideSoftInputFromWindow(urlText.getWindowToken(), 0);
                break;
        }
    }
}

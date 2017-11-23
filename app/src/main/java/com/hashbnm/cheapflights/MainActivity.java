package com.hashbnm.cheapflights;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.ybq.android.spinkit.style.DoubleBounce;

public class MainActivity extends AppCompatActivity{

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setEnabled(true);
        progressBar.setVisibility(View.VISIBLE);

        final RelativeLayout cover=(RelativeLayout)findViewById(R.id.cover);

        webView=(WebView)findViewById(R.id.webView);

        webView.loadUrl("https://www.google.com/flights/");

//        webView.setWebChromeClient(new MyCustomChromeClient(this));
//        webView.setWebViewClient(new MyCustowebViewClient(this));
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        String injection="javascript:setTimeout(function(){ var x=document.getElementsByClassName('gws-flights-tabbed_carousel__header'); x[0].style.display='none'; var y=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); y[0].style.marginTop='-102px'; var z=document.getElementsByClassName('gws-flights-results__footer'); z[0].style.display='none'; var p=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); p[0].style.marginTop='-102px'; }, 5000);";

        webView.loadUrl(injection);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                String injection="javascript:setTimeout(function(){ var x=document.getElementsByClassName('gws-flights-tabbed_carousel__header'); x[0].style.display='none'; var y=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); y[0].style.marginTop='-102px'; var z=document.getElementsByClassName('gws-flights-results__footer'); z[0].style.display='none'; var p=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); p[0].style.marginTop='-102px'; }, 1000);";
//                String injection="javascript:var x=document.getElementsByClassName('gws-flights-tabbed_carousel__header'); x[0].style.display='none'; var y=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); y[0].style.marginTop='-102px'; var z=document.getElementsByClassName('gws-flights-results__footer'); z[0].style.display='none'; var p=document.getElementsByClassName('gws-flights-tabbed_carousel__carousel-item gws-flights-tabbed_carousel__selected gws-flights__visible-content'); p[0].style.marginTop='-102px'; ";

                webView.loadUrl(injection);

                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        cover.setVisibility(View.GONE);

                    }
                };

                Handler handler=new Handler();
                handler.postDelayed(runnable, 10000);
            }

        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}

package com.hamidi.forexrobot

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full dark status/nav bars
        window.statusBarColor     = 0xFF07091A.toInt()
        window.navigationBarColor = 0xFF07091A.toInt()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        setupWebView()
        webView.loadUrl("file:///android_asset/index.html")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        with(webView.settings) {
            javaScriptEnabled    = true
            domStorageEnabled    = true
            databaseEnabled      = true
            allowFileAccess      = true
            allowContentAccess   = true

            // Required: let file:// pages call Binance API (cross-origin)
            @Suppress("DEPRECATION")
            allowUniversalAccessFromFileURLs = true
            @Suppress("DEPRECATION")
            allowFileAccessFromFileURLs      = true

            // Required: let wss:// WebSocket inside HTTP/file context
            mixedContentMode   = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode          = WebSettings.LOAD_DEFAULT
            useWideViewPort    = true
            loadWithOverviewMode = true
            builtInZoomControls  = false
            displayZoomControls  = false
            setSupportZoom(false)
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView, request: WebResourceRequest
            ) = false

            override fun onReceivedError(
                view: WebView, request: WebResourceRequest, error: WebResourceError
            ) { /* suppress */ }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(msg: ConsoleMessage) = true
        }

        // GPU acceleration for smooth animations
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }

    override fun onResume()  { super.onResume();  webView.onResume()  }
    override fun onPause()   { webView.onPause(); super.onPause()     }
    override fun onDestroy() {
        webView.apply { stopLoading(); clearHistory(); clearCache(true); destroy() }
        super.onDestroy()
    }
}

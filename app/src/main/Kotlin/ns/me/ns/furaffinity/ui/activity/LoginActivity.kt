package ns.me.ns.furaffinity.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import ns.me.ns.furaffinity.Constants
import ns.me.ns.furaffinity.R
import ns.me.ns.furaffinity.databinding.ActivityLoginBinding
import ns.me.ns.furaffinity.datasouce.web.AppWebApiService
import ns.me.ns.furaffinity.di.Injectable
import ns.me.ns.furaffinity.ui.viewmodel.LoginViewModel
import ns.me.ns.furaffinity.util.LoginManager
import javax.inject.Inject

class LoginActivity : AbstractBaseActivity<LoginViewModel>(), Injectable {

    companion object {
        fun intent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var service: AppWebApiService

    override val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                LoginManager.instance.setCookie(this@LoginActivity, url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.webView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('block-menu-top')[0].style.display='none'; " + "})()")
            }

        }

        binding.webView.loadUrl("${Constants.WEB_BASE}login")

    }


}
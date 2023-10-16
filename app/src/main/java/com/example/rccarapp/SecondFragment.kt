package com.example.rccarapp


import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rccarapp.databinding.FragmentSecondBinding
import java.io.IOException
    import java.net.Socket
import java.util.concurrent.Executors


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var webView: WebView

    
    var backCountdown = 0
    var forwardCountdown = 0

    private fun client(number: Int){
        Executors.newSingleThreadExecutor().execute {
            try {
                val client = Socket("192.168.18.139", 4080)
                client.outputStream.write(number.toInt())
                client.close()
            } catch (e: IOException) {
                e.printStackTrace();
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        webView = view.findViewById(R.id.webView)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = WebViewClient()

        // this will load the url of the website
        //webView.loadUrl("http://192.168.18.144/")
        //webView.loadUrl("https://developer.android.com/")
        webView.loadUrl("http://192.168.18.144/")

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        //webView.settings.setSupportZoom(true)

        webView.settings.loadWithOverviewMode = true;
        webView.settings.useWideViewPort = true;
        webView.isVerticalScrollBarEnabled = true;


        binding.leftButton.setOnClickListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(context, getString(R.string.left_button_toast_text), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            client(1)
        }

        binding.middleButton.setOnClickListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(context, getString(R.string.middle_button_toast_text), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            client(2)
        }

        binding.rightButton.setOnClickListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(context, getString(R.string.right_button_toast_text), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            client(3)
        }

        binding.backButton.setOnClickListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(context, getString(R.string.back_button_toast_text), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            if(backCountdown==0) {
                client(5)
                backCountdown = 1

                // time count down for 1 second,
                // with 0.5 seconds as countDown interval
                object : CountDownTimer(1000, 500) {

                    // Callback function, fired on regular interval
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    // Callback function, fired when the time is up
                    override fun onFinish() {
                        client(6)
                        backCountdown=0
                    }
                }.start()
            }
        }

        binding.forwardButton.setOnClickListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(context, getString(R.string.forward_button_toast_text), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            if(forwardCountdown==0) {
                client(7)
                forwardCountdown = 1

                // time count down for 1 second,
                // with 0.5 seconds as countDown interval
                object : CountDownTimer(1000, 500) {

                    // Callback function, fired on regular interval
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    // Callback function, fired when the time is up
                    override fun onFinish() {
                        client(8)
                        forwardCountdown=0
                    }
                }.start()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
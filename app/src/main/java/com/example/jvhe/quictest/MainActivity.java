package com.example.jvhe.quictest;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btn_finish_url, btn_finish_cmd, btn_help;
    LSQUIC lsquic;
    WebView webview;
    WebSettings webSettings;
    String result;
    EditText et_url, et_cmd;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "메인 액티비티 실행");
        setContentView(R.layout.activity_main);
        lsquic = new LSQUIC();
        btn_finish_url = findViewById(R.id.btn_finish_url);
        handler = new Handler();
        btn_finish_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lsquic.setUrl(et_url.getText().toString());
                result = lsquic.finish();
                if (result.equals("")) {
                    result = "해당 웹 사이트는 quic를 지원하지 않습니다.";
                } else {
                    webview.loadData(result, "text/html", "utf-8");
                }
            }
        });
        btn_finish_cmd = findViewById(R.id.btn_finish_cmd);
        btn_finish_cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = lsquic.finish_cmd(et_cmd.getText().toString());
                if (result.equals("")) {
                    result = "해당 웹 사이트는 quic를 지원하지 않습니다.";
                } else {
                    webview.loadData(result, "text/html", "utf-8");
                }
            }
        });
        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

// 제목셋팅
                alertDialogBuilder.setTitle("Usage Examples");

// AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("\n" +
                                "Fetch Google's home page:\n" +
                                "\n" +
                                "    ./http_client -s www.google.com -p /\n" +
                                "\n" +
                                "The default port number is 443, but it can be specified after colon\n" +
                                "using the -s flag.  The value of the `host' header as well as the SNI\n" +
                                "value defaults to the host part of the -s option.  -H option can be\n" +
                                "used to override it.  For example:\n" +
                                "\n" +
                                "    ./http_client -H www.youtube.com -s www.google.com:443 -p / -M HEAD\n" +
                                "\n" +
                                "The host part can be an IP address.  Both IPv4 and IPv6 are supported.\n" +
                                "See ./http_client -h for a (long) list of different flags.\n" +
                                "\n" +
                                "POST a file to calculate its CRC32 checksum:\n" +
                                "\n" +
                                "    ./http_client -H www.litespeedtech.com -s 443 \\\n" +
                                "                        -p /cgi-bin/crc32.cgi -P file-256M -M POST\n" +
                                "\n" +
                                "    HTTP/1.1 200 OK\n" +
                                "    content-type: text/plain\n" +
                                "    date: Fri, 09 Jun 2017 08:40:45 GMT\n" +
                                "    server: LiteSpeed\n" +
                                "    alt-svc: quic=\":443\"; v=\"35,37\"\n" +
                                "\n" +
                                "    CRC32: 2A0E7DBB\n" +
                                "\n" +
                                "This is a good way to check that the payload gets to the other side\n" +
                                "correctly.  The CGI script is:\n" +
                                "\n" +
                                "    #!/usr/bin/perl\n" +
                                "    use String::CRC32;\n" +
                                "    printf \"Content-type: text/plain\\r\\n\\r\\nCRC32: %X\\n\", crc32(*STDIN)\n" +
                                "\n" +
                                "On the command line, I do\n" +
                                "\n" +
                                "    alias crc32=\"perl -MString::CRC32 -e'printf qq(%X\\n), crc32(<>)'\"\n" +
                                "\n" +
                                "To submit several requests concurrently, one can use -n and -r options:\n" +
                                "\n" +
                                "    ./http_client -H www.litespeedtech.com -s 443 \\\n" +
                                "                -p /cgi-bin/crc32.cgi -P file-256M -M POST -n 3 -r 10\n" +
                                "\n" +
                                "This will open three parallel connections which will make ten POST\n" +
                                "requests together.\n" +
                                "\n" +
                                "To perform load testing, it is good to mix sending and receiving data:\n" +
                                "\n" +
                                "    for i in {1..100}; do\n" +
                                "        ./http_client $COMMON_OPTS -p /cgi-bin/crc32.cgi -P file-256M \\\n" +
                                "                                                    -M POST >out-post.$i &\n" +
                                "        ./http_client $COMMON_OPTS -p /docs/file-256M >out-get.$i        &\n" +
                                "        sleep 1\n" +
                                "    done\n" +
                                "\n" +
                                "If you don't want to create a hundred 256-megabyte out-get.* files, use -K\n" +
                                "flag to discard output.")
                        .setCancelable(true);
                alertDialogBuilder.show();
            }
        });
        et_url = findViewById(R.id.et_url);
        et_cmd = findViewById(R.id.et_cmd);

        webview = findViewById(R.id.webview);
//        webview.setWebChromeClient(new WebChromeClient());
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewGroup webParent = (ViewGroup) webview.getParent();
        if (webParent != null) {
            webParent.removeView(webview);
        }
        webview.destroy();
    }
}

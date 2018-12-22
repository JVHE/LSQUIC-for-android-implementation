package com.example.jvhe.quictest;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LSQUIC {
    private static final String TAG = "LSQUIC";

    static {
        System.loadLibrary("http_client");
    }

    // url은 기본적으로 IPv4와 IPv6 둘 다 지원한다.
    // 포트에 관한 예외처리 필요
    String url = "www.google.com";
    // 포트는 기본적으로 443이다.
    int port = 443;

    String[] args = new String[5];

    // 결과값을 받아와 저장하는 변수
    static String result;


    private native int http_call(String[] args);

    private native int hello_world(int a, int b);

    // 설정한 url과 포트 번호
    // finish()는 주어진 정보를 이용해 인터넷에 QUIC 요청을 한다.
    // 만약 URL 정보가 없다면 실패를 반환한다.
    public String finish() {
        Log.e(TAG, "finish 호출");
        if (url.equals("")) {
            System.out.println("url이 설정되어 있지 않습니다. 연결을 종료합니다.");
            return "";
        }

        // 일단 url, port 순서대로 옵션을 넣어보자.
        // 뒤에는 path 변수다.
        args[0] = "./http_client";
        args[1] = "-s";
        args[2] = url + ":" + port;
        args[3] = "-p";
        args[4] = "/";

        Log.e(TAG, "http call 실행전");

        clearResult();
        http_call(args);
        Log.e(TAG, "http call 실행후\nresult is " + result);
        return result;
    }

    // 설정한 url과 포트 번호
    // finish()는 주어진 정보를 이용해 인터넷에 QUIC 요청을 한다.
    // 만약 URL 정보가 없다면 실패를 반환한다.
    public String finish_cmd(String cmd) {
        Log.e(TAG, "finish_cmd 호출");

        String []cmds = cmd.split("\\s");

        Log.e(TAG, "http call 실행전");
        // 결과값 클리어
        clearResult();
        http_call(cmds);
        Log.e(TAG, "http call 실행후\nresult is " + result);
        return result;
    }


    // 받아온 결과값을 클리어 해주는 메소드.
    // 기본적으로 finish에서 http_call을 하기 직전에 한 번 호출되어 결과값을 받을 문자열 변수 result의 값을 지워준다.
    public void clearResult() {
        this.result = "";
    }

    // 결과값을 update 해주는 메소드. 네이티브 코드에서 stream값을 size크기만큼 받아와 result에 덧붙여 준다.
    public static void updateResult(byte[] bytes) {
        String byteValues = null;
        try {
            byteValues = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result += byteValues;
    }


    // JNI call 체크 메소드
    public void helloWorld() {
        Log.e(TAG, "helloWorld 실행전");
        int aa = 0;
        aa = hello_world(1, 2);
        System.out.println(aa);
        Log.e(TAG, "helloWorld 실행후");
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}

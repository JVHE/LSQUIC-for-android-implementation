# LSQUIC-for-android-implementation
This is the implementation of litespeedtech's lsquic-client on Android.

이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.

## Description
This is the implementation of litespeedtech's lsquic-client on Android.
For more information, see https://github.com/litespeedtech/lsquic-client
This is only tested arm architecture.


이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.
https://github.com/litespeedtech/lsquic-client 에 많은 내용이 잘 기술되어 있습니다.
아직은 arm아키텍쳐의 장치에서만 작동합니다.

## How to use it
Since this application has wrapped the http_client bin file of lsquic-client, it works by giving the command of http_client.
It is really simple to use. Initialize LSQUIC object, then use finishCmd() or finish()

usage example1:
```Java
LSQUIC lsquic = new LSQUIC();
// lsquic.finishCmd returns all response string including headers
String result = lsquic.finishCmd("./http_client -s www.google.com -p /");
// if you want to get only body, then you can use lsquic.getResult_body();
result = lsquic.getResult_body();
if (result.equals("")) {
  result = "This web site does not support QUIC.";
}
webview.loadData(result, "text/html", "utf-8");   // Initialize your WebView before
```
usage example2:
```Java
LSQUIC lsquic = new LSQUIC();
lsquic.setUrl("www.google.com");
lsquic.setPort(443);  // default is 443. You don't have to call this method if the port number is 443.
String result = lsquic.finish();
// if you want to get only body, then you can use lsquic.getResult_body();
result = lsquic.getResult_body();
if (result.equals("")) {
  result = "This web site does not support QUIC.";
}
webview.loadData(result, "text/html", "utf-8");   // Initialize your WebView before
```
I'm not recommand use lsquic.finish(). because it is still in limited use. It is recommended to be used after being supplemented later.

Examples of use are well documented at https://github.com/litespeedtech/lsquic-client/blob/master/EXAMPLES.txt



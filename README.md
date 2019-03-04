# LSQUIC-for-android-implementation
This is the implementation of litespeedtech's lsquic-client on Android.

## Description
This is the implementation of litespeedtech's lsquic-client on Android.<br/>
For more information, see https://github.com/litespeedtech/lsquic-client<br/>
It only works on ARM architecture yet.

#### What is QUIC?
> QUIC (pronounced 'quick') is an experimental transport layer network protocol initially designed, implemented, and deployed by Google  in 2012, and announced publicly in 2012 as experimentation broadened.<br/>
> In October 2018, the IETF's HTTP and QUIC Working Group made an official request to rename the protocol HTTP/3 in advance of making it a worldwide standard. (From Wikipedia)

[This](https://www.chromium.org/quic) is a good description of QUIC

## How to use it
Since this application has wrapped the http_client bin file of lsquic-client, it works by giving the command of http_client.<br/>
It is simple to use. Initialize LSQUIC object, then use finishCmd() or finish().

usage example 1:
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
usage example 2:
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
I don't recommend use lsquic.finish() because it is still in limited use. It is recommended to be used after being supplemented later.<br/>
Examples of use are well documented at https://github.com/litespeedtech/lsquic-client/blob/master/EXAMPLES.txt



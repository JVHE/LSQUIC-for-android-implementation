# LSQUIC-for-android-implementation
This is the implementation of litespeedtech's lsquic-client on Android. 이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.

##Description
This is the implementation of litespeedtech's lsquic-client on Android. 이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.
For more information, see https://github.com/litespeedtech/lsquic-client
This is only tested arm architecture.
Since my application has wrapped the http_client bin file of lsquic-client, it works by entering the command of http_client.

##Usage
usage example:
LSQUIC lsquic = new LSQUIC();
String result = lsquic.finish_cmd("./http_client -s www.google.com -p /");

Examples of use are well documented at https://github.com/litespeedtech/lsquic-client/blob/master/EXAMPLES.txt

# LSQUIC-for-android-implementation
This is the implementation of litespeedtech's lsquic-client on Android. 이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.

## Description
This is the implementation of litespeedtech's lsquic-client on Android.
For more information, see https://github.com/litespeedtech/lsquic-client
This is only tested arm architecture.


이 프로젝트는 litespeedtech사의 lsquic-client를 안드로이드에 이식시킨 것 입니다.
https://github.com/litespeedtech/lsquic-client 에 많은 내용이 잘 기술되어 있습니다.
아직은 arm아키텍쳐의 장치에서만 작동합니다.

## Usage
Since my application has wrapped the http_client bin file of lsquic-client, it works by giving the command of http_client.

usage example:
```Java
LSQUIC lsquic = new LSQUIC();
String result = lsquic.finish_cmd("./http_client -s www.google.com -p /");
```

Examples of use are well documented at https://github.com/litespeedtech/lsquic-client/blob/master/EXAMPLES.txt


http_client 실행 파일에 wrapping 함수를 만들어 구현해 뒀기 때문에, 명령어를 finish_cmd에 입력만 하면 됩니다.

사용 예시:
```Java
LSQUIC lsquic = new LSQUIC();
String result = lsquic.finish_cmd("./http_client -s www.google.com -p /");
```
사용 예시는 https://github.com/litespeedtech/lsquic-client/blob/master/EXAMPLES.txt 에 잘 나와있습니다.

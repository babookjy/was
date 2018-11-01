# was
Web Application Server

※ 모 기업의 기술 과제 -> servlet-mapping 이 되는 was를 구현하라고 했다.
※ 급하게 하느라 꼼꼼히 챙기지 못해서 지적을 좀 받긴 했고 결과는 어영부영 통과되었다.
※ JDK 는 8버전을 사용하였고 Lombok을 사용하였다. IDE에서 테스트 시 Lombok도 설치 바람.
※ Maven Build 시 maven-dependency-plugin를 사용하였다. Build후 생성되는 "lib" 폴더도 함께 사용해야 한다.

1.  host 별로 각각 다른 servlet mapping을 위한 설정
	host 구분 : C:\Windows\System32\drivers\etc\hosts 파일에서 
	127.0.0.1   www.was-path-1.com
	127.0.0.1  	www.was-path-2.com 로 구분하여 구현하였다.
   - www.was-path-1.com => was.path1 패키지의 데이터
   - www.was-path-2.com => was.path2 패키지의 데이터
   
2. properties.json에는 html을 정의하였고 servlet.json에는 Port 및 Host별 servlet-mapping 정보를 정의하였다.

3. 각 에러 코드 별로 RequestHandler.class 에서 properties.json의 정보를 이용하여 해당 html 파일를 출력한다.

4. 403 보안규칙 역시 RequestHandler.class에서 제어하고 있다.

5. 원래는 resource를 환경별로 구분하여 logback을 설정해야 하지만 간단한 테스트 소스이므로 default(Local) resource/logback.xml 하나에 정의하였다.

6. java.io.writer가 아닌 header와 body를 outputstream으로 write하도록 구현하였다.
   
7. 주요 로직은 test 패키지 안에 junit test 소스를 구현하였다.
# was
Web Application Server

�� �� ����� ��� ���� -> servlet-mapping �� �Ǵ� was�� �����϶�� �ߴ�.
�� ���ϰ� �ϴ��� �Ĳ��� ì���� ���ؼ� ������ �� �ޱ� �߰� ����� ��ο� ����Ǿ���.
�� JDK �� 8������ ����Ͽ��� Lombok�� ����Ͽ���. IDE���� �׽�Ʈ �� Lombok�� ��ġ �ٶ�.
�� Maven Build �� maven-dependency-plugin�� ����Ͽ���. Build�� �����Ǵ� "lib" ������ �Բ� ����ؾ� �Ѵ�.

1.  host ���� ���� �ٸ� servlet mapping�� ���� ����
	host ���� : C:\Windows\System32\drivers\etc\hosts ���Ͽ��� 
	127.0.0.1   www.was-path-1.com
	127.0.0.1  	www.was-path-2.com �� �����Ͽ� �����Ͽ���.
   - www.was-path-1.com => was.path1 ��Ű���� ������
   - www.was-path-2.com => was.path2 ��Ű���� ������
   
2. properties.json���� html�� �����Ͽ��� servlet.json���� Port �� Host�� servlet-mapping ������ �����Ͽ���.

3. �� ���� �ڵ� ���� RequestHandler.class ���� properties.json�� ������ �̿��Ͽ� �ش� html ���ϸ� ����Ѵ�.

4. 403 ���ȱ�Ģ ���� RequestHandler.class���� �����ϰ� �ִ�.

5. ������ resource�� ȯ�溰�� �����Ͽ� logback�� �����ؾ� ������ ������ �׽�Ʈ �ҽ��̹Ƿ� default(Local) resource/logback.xml �ϳ��� �����Ͽ���.

6. java.io.writer�� �ƴ� header�� body�� outputstream���� write�ϵ��� �����Ͽ���.
   
7. �ֿ� ������ test ��Ű�� �ȿ� junit test �ҽ��� �����Ͽ���.
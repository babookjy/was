package was;

import java.net.ServerSocket;
import java.net.Socket;

import was.config.Properties;
import was.server.RequestHandler;
import was.servlet.SimpleServletMapper;
import was.util.HttpUtils;

public class Application {
		
    public static void main(String[] args) throws Exception {

    	SimpleServletMapper.setMap(HttpUtils.parseData("/servlet.json"));
    	
    	try (ServerSocket listenSocket = new ServerSocket(Properties.getDefaultPort())) {
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
            	RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
    	}
    }
}

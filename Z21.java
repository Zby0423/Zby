import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Z21 {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器启动，等待连接...");
        Socket socket = server.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg;
        while ((msg = br.readLine()) != null) {
            System.out.println("客户端：" + msg);
        }
        br.close();
        socket.close();
        server.close();
    }
}
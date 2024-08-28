import java.net.*;
import java.io.*;

/**
 * @ClassName: Client
 * @Description 客户端
 * @author Linux_Mumu
 */
public class Client {
    static String reply;

    public static void senTCP(String str) throws Exception {
        // 172.16.49.135
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        OutputStream os = socket.getOutputStream();
        os.write(str.getBytes());
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        byte[] buffer2 = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len2;
        if ((len2 = is.read(buffer2)) != -1) {
            baos.write(buffer2, 0, len2);
        }
        System.out.println(baos.toString());
        reply = baos.toString();

        socket.close();
    }
}

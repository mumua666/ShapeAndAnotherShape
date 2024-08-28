import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @ClassName: Server
 * @Description 服务器
 * @author Linux_Mumu
 */
public class Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static InputStream is;
    static ByteArrayOutputStream baos;
    static byte[] buffer;
    static int len;
    static ArrayList<String> username = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();
    static ArrayList<String> history = new ArrayList<>();
    static String reply;
    static boolean existUser, checkPassword;
    static String[] strArray;
    static OutputStream os;
    static String loginName;

    public Server() {
        init();
    }

    public static void init() {
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                baos = new ByteArrayOutputStream();
                buffer = new byte[1024];
                if ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                System.out.println(baos.toString());
                os = socket.getOutputStream();
                strArray = baos.toString().split("-");
                reply = "";
                existUser = false;
                checkPassword = false;
                switch (strArray[0]) {
                    case "LogIn": {
                        if (username.contains(strArray[1])) {
                            existUser = true;
                            int index = username.indexOf(strArray[1]);
                            if (password.get(index).equals(strArray[2]))
                                checkPassword = true;
                        }
                        if (existUser && checkPassword) {
                            reply = "登录成功";
                            loginName = strArray[1];
                        } else if (existUser && !checkPassword)
                            reply = "密码错误";
                        else if (!existUser)
                            reply = "用户不存在";
                        os.write(reply.getBytes());
                    }
                        break;
                    case "GetLoginName": {
                        reply = loginName;
                        os.write(reply.getBytes());
                    }
                        break;
                    case "SignUp": {
                        if (username.contains(strArray[1])) {
                            existUser = true;
                        }
                        if (existUser)
                            reply = "用户已存在";
                        else {
                            username.add(new String(strArray[1]));
                            password.add(new String(strArray[2]));
                            reply = "注册成功";
                        }
                        os.write(reply.getBytes());
                    }
                        break;
                    case "History": {
                        if (history.size() == 0)
                            reply = "暂无历史对局";
                        else {
                            reply = "历史对局";
                            for (String history : history) {
                                reply += "+";
                                reply += history;
                            }
                        }
                        os.write(reply.getBytes());
                    }
                        break;
                    case "changeHardLevel": {
                        reply = "切换难度";
                        // NumCardsUtil.changeHardLevel();
                        os.write(reply.getBytes());
                    }
                        break;
                    case "reInitCardsIcon": {
                        reply = "切换牌面";
                        // NumCardsUtil.reInitCardsIcon();
                        os.write(reply.getBytes());
                    }
                        break;
                    case "changeLayout": {
                        reply = "切换牌局";
                        // NumCardsUtil.changeLayout();
                        os.write(reply.getBytes());
                    }
                        break;
                    case "shuffle": {
                        reply = "重新打乱";
                        // NumCardsUtil.shuffle();
                        os.write(reply.getBytes());
                    }
                        break;
                    case "***": {
                        history.add(baos.toString());
                        reply = "收到对局数据";
                        System.out.println("收到对局数据");
                        os.write(reply.getBytes());
                    }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

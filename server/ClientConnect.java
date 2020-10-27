package server;

import graphic.Authorization;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

class ClientConnect {
    private int port;
    private String host;
    private SocketChannel socketChannel = null;
    private Logger log = Logger.getLogger(this.getClass().getName());
    private boolean acs = false;
    private String loginUser;
    private String[] map;

    ClientConnect(String host, int port){
        this.host = host;
        this.port = port;
        connect();
    }

    private void connect(){
        Scanner inSc = new Scanner(System.in);
        InetSocketAddress adress = new InetSocketAddress(this.host, this.port);
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(adress);
        } catch (IOException e){
            e.printStackTrace();
        }
        //new Authorization(socketChannel);
        //new Authorization(socketChannel);
// new Thread(() -> new Authorization(socketChannel));

        /*System.out.println("Подключаемся к серверу... ");
        while (!acs) {
            try {
                socketChannel = SocketChannel.open();
                //socketChannel.configureBlocking(false);
                socketChannel.connect(adress);
                log.info("Подключился к порту 8989");

                acs = true;
                //System.out.println(acs);
            } catch (IOException e) {
                System.out.println("Не удалось подключиться к серверу");
                System.out.println("Хотите переподключиться? [y/n]");
                acs = false;
                if (!inSc.nextLine().equals("y")) {
                    acs=false;
                    inSc.close();
                    System.exit(0);
                }

            }
        } */
        /*try {
            ConcurrentSkipListMap<String, String> mapUser = new ConcurrentSkipListMap<>();
            boolean loginIn = false;
            /*while(!loginIn && socketChannel.isConnected()) {
                Scanner in = new Scanner(System.in);
                System.out.println("Войти в систему или зарегестрируйтесь: [S/R]");
                String acc = in.nextLine();
                if (acc.equalsIgnoreCase("R")) {

                    System.out.println("    Введите логин: ");
                    String login = in.nextLine();
                    System.out.println("    Введите почту: ");
                    String mail = in.nextLine();
                    System.out.println("На вашу почту отправлено письмо с паролем");

                    mapUser.put("login", login);
                    mapUser.put("mail", mail);
                    mapUser.put("command", "register");

                    socketChannel.write(ByteBuffer.wrap(server.Serialisation.serialize(mapUser)));
                    String strReg = new server.Message(socketChannel).recirveMessage();
                    System.out.println(strReg);
                } else if (acc.equalsIgnoreCase("S")) {
                    System.out.println("    Введите логин: ");
                    String login = in.nextLine();
                    this.loginUser = login;
                    System.out.println("    Введите пароль: ");
                    String pass = in.nextLine();
                    mapUser.put("login", login);
                    mapUser.put("password", pass);
                    mapUser.put("command", "sign_in");
                    socketChannel.write(ByteBuffer.wrap(server.Serialisation.serialize(mapUser)));
                    String str = new server.Message(socketChannel).recirveMessage();
                    System.out.println(str + '\n');
                    if (str.contains("Вы авторизированны")){
                        loginIn = true;
                    }
                }
            } */

           /* if(loginIn) {
                while (socketChannel.isConnected()) {
                    ConcurrentSkipListMap<String, String> map;

                    System.out.println("Введите комманду: ");
                    map = server.ReaderCommand.readingCommand();
                    map.put("login", loginUser);
                    socketChannel.write(ByteBuffer.wrap(server.Serialisation.serialize(map)));
                    String re = new server.Message(socketChannel).recirveMessage();

                    System.out.println(re);
                    if (map.get("command").equals("exit")) {
                        socketChannel.close();
                        System.exit(0);
                    }
                } */
          /*  }
        } catch (IOException e){
            System.out.println("Конец соединения");
            e.printStackTrace();
            System.exit(-1);
        } */


    }



}

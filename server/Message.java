package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Message {
    private SocketChannel socketChannel;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public Message(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }


    public void sendMessage(String string) throws
            IOException {
        log.info("Отправляется сообщение: " + string);
        //while (true){
        ObjectOutputStream oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        oos.writeObject(string);
        oos.flush();
        oos.close();
    }

    public String recirveMessage() {
        String string = "!";
        try {

            ObjectInputStream ois = new ObjectInputStream(socketChannel.socket().getInputStream());
            string = (String) ois.readObject();
            //log.info("Принят объект: " + string);
            //log.info("По сокету " + socketChannel.socket());
           // ois.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e1){
            e1.printStackTrace();
        }
        return string;
    }


}

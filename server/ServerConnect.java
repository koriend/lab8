package server;

import graphic.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentSkipListMap;

public class ServerConnect  extends Thread{

    ObjectInputStream ois;
    private GUI gui;
    private SocketAddress socketAddress;
    private boolean exit = false;
    private boolean isWork;
    private String login;

    public ServerConnect(SocketAddress socketAddress, GUI gui, String login){
        this.gui = gui;
        this.socketAddress = socketAddress;
        this.login = login;
    }

    @Override
    public void run() {

    }

    private void connect(){
        SocketChannel channel;
        isWork = false;
        ObjectOutputStream oos;
        try{
            channel = SocketChannel.open(socketAddress);
            oos = new ObjectOutputStream(channel.socket().getOutputStream());
            ois = new ObjectInputStream(channel.socket().getInputStream());
            ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
            map.put("command", "show");
            oos.writeObject(ByteBuffer.wrap(Serialisation.serialize(map)));
            //new Th
        } catch (IOException e){

        }


    }
}

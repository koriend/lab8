package graphic;

import server.ObjectMessanger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentSkipListMap;

public class Command {

    private InetSocketAddress socket;
    SocketChannel sc;

    private boolean exit;
    private GUI gui;
    volatile boolean isWorking = false;
    private Handler handler;
    private ObjectMessanger objectMessanger;

    Command(InetSocketAddress address, GUI gui, String login, SocketChannel channel){
        sc = channel;
        this.socket = address;
        this.gui = gui;
        this.handler = new Handler(new InetSocketAddress("localhost", 8989), gui, login, this, sc);
        handler.setWorkIs(true);
        handler.start();
    }


     void getCollection(){
         System.out.println("kjkjk");
        ConcurrentSkipListMap<String, String> maps = new ConcurrentSkipListMap<>();
        maps.put("command", "show");
        try {
            sc.write(ByteBuffer.wrap(server.Serialisation.serialize(maps)));
            //ConcurrentSkipListMap<String, Thing> mai = (ConcurrentSkipListMap<String, Thing> )new ObjectMessanger(sc.socket()).recieveObject();
            //objectMessanger.sendObject(maps);
            //System.out.println(mai);
        } catch (IOException  e){
            e.printStackTrace();
        }
    }

    void remove_collection(String key, String login){
        ConcurrentSkipListMap<String, String> maps = new ConcurrentSkipListMap<>();
        maps.put("command", "remove");
        maps.put("key", key);
        maps.put("login", login);
        try{
            sc.write(ByteBuffer.wrap(server.Serialisation.serialize(maps)));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    void add_collection(String[][] arr, String login, String command){
        ConcurrentSkipListMap<String, String> maps = new ConcurrentSkipListMap<>();
        maps.put("command", command);
        maps.put("key", arr[0][1]);
        maps.put("nameOfObject", arr[0][1]);
        maps.put("valueOfObject", arr[1][1]);
        maps.put("idOfObject", arr[2][1]);
        maps.put("nameOfPlanet", arr[3][1]);
        maps.put("valueOfPlanet", String.valueOf((int)(Math.random()*10000)));
        maps.put("idOfPlanet", "2");
        maps.put("typeOfPlanet", "IS_PLANET");
        maps.put("login", login);
        try{
            sc.write(ByteBuffer.wrap(server.Serialisation.serialize(maps)));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void connect(){
        SocketChannel sc;
        isWorking = false;
        try{
            if(!exit){
                sc = SocketChannel.open(socket);
                objectMessanger = new ObjectMessanger(sc.socket());
                isWorking = true;
            }
        } catch (IOException e){
            while (!exit) {
                try {
                    Thread.sleep(1000);
                    sc = SocketChannel.open(socket);
                    sc.configureBlocking(false);
                    objectMessanger = new ObjectMessanger(sc.socket());
                    isWorking = true;
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } catch (IOException ignored) {
                }
            }
        }
    }

}

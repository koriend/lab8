package graphic;

import server.ObjectMessanger;
//import textPath.Thing;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

public class Handler extends Thread {

    private SocketAddress address;
    private GUI gui;
    private String login;
    private Command sender;
    private boolean isWork = false;
    private boolean isExit = false;
    private Logger log = Logger.getLogger(this.getClass().getName());
    private SocketChannel socketChannel;


    public Handler(SocketAddress address, GUI gui, String login, Command command, SocketChannel channel){
        this.socketChannel = channel;
        this.address = address;
        this.gui = gui;
        this.login = login;
        this.sender = command;
    }

    @Override
    public void run() {
        //connect();
        System.out.println("hjh");
        new Thread(sender::getCollection).start();
        while (!isExit){
            if(isWork){
                try{
                    long time = System.currentTimeMillis();

                    if(System.currentTimeMillis() - time > 90000) isExit = true;
                    Object message = new ObjectMessanger(socketChannel.socket()).recieveObject();
                    new Thread(() -> {
                        if (message instanceof String[]){

                        } else if(message instanceof ConcurrentSkipListMap){
                            ConcurrentSkipListMap<String, String[][]> map = new ConcurrentSkipListMap<>();
                            map = (ConcurrentSkipListMap<String, String[][]>)message;
                            gui.update_table(map);
                            System.out.println(map);
                        } else if(message instanceof String){
                            String msg = (String) message;
                            gui.frame_err(msg);
                        }
                    }).start();
                } catch (IOException | ClassNotFoundException e){
                    e.printStackTrace();

                }
            }
        }
    }

    public void setWorkIs(boolean work) {
        isWork = true;
    }

    /* private void connect(){

        isWork = false;
        try{
            socketChannel = SocketChannel.open(address);
            socketChannel.connect(address);
            log.info("Подключился к порту 8989");
            isWork = true;
            new Thread(sender::connect).start();
        } catch (IOException e){}
    } */
}

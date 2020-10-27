package multithread;

import server.Message;
import server.Serialisation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

public class RunMultiTest implements Runnable{

    private SocketChannel socketChannel;
    volatile private ArrayList<Long> timeList = new ArrayList<>();
    int port;
    String host;
    private long startTime;
    private long endTime;
    private Logger log = Logger.getLogger(this.getClass().getName());
    public RunMultiTest(){
            InetSocketAddress adress = new InetSocketAddress("localhost", 8989);
            System.out.println("Подключаемся к серверу... ");
            try {
                socketChannel = SocketChannel.open();
                //socketChannel.configureBlocking(false);
                socketChannel.connect(adress);
                startTime = System.currentTimeMillis();

            } catch (IOException e){
                System.out.println("Не удалось подключиться к серверу");
                //e.printStackTrace();
                System.exit(-1);
            }
    }

    @Override
    public void run() {
            int i = 0;
            ArrayList<ConcurrentSkipListMap<String, String>> comList = new ArrayList<>();
        ConcurrentSkipListMap<String, String> mapIns = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
        mapIns.put("command", "insert");
        mapIns.put("key", "Io");
        mapIns.put("nameOfObject", "Io");
        mapIns.put("valueOfObject", "76");
        mapIns.put("idOfObject", "4");

        ConcurrentSkipListMap<String, String> mapShow = new ConcurrentSkipListMap<>();
        mapShow.put("command", "show");

        ConcurrentSkipListMap<String, String> mapSave = new ConcurrentSkipListMap<>();
        mapSave.put("command", "save");

        ConcurrentSkipListMap<String, String> mapLoad = new ConcurrentSkipListMap<>();
        mapLoad.put("command", "load");

        ConcurrentSkipListMap<String, String> mapInser = new ConcurrentSkipListMap<>();
        mapInser.put("command", "insert");
        mapInser.put("key", "Upo");
        mapInser.put("nameOfObject", "Upo");
        mapInser.put("valueOfObject", "354");
        mapInser.put("idOfObject", "4");

        ConcurrentSkipListMap<String, String> mapRem = new ConcurrentSkipListMap<>();
        mapRem.put("command", "remove");
        mapRem.put("key", "Io");
        comList.add(mapSave);
        comList.add(mapLoad);
        comList.add(mapIns);
        comList.add(mapInser);
        comList.add(mapShow);
        comList.add(mapRem);



            while (i < 5){
                i++;
                int j = (int)(Math.random()* 6);
                try {
                    log.info("Отправляется комманда: " + comList.get(j).get("command") + '\n' + "От: " + Thread.currentThread().getName() + '\n');
                    socketChannel.write(ByteBuffer.wrap(Serialisation.serialize(comList.get(j))));
                } catch (IOException e){
                    e.printStackTrace();
                }

                String re =  new Message(socketChannel).recirveMessage();

                System.out.println(re);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println(":(");
                }
            }
          endTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + endTime + "(end: "+ Thread.currentThread().getName() + ")");
    }
}

package graphic;

import graphic.GUI;

import javax.swing.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Locale;

public class Worker extends SwingWorker<Void, Void> {

    private Locale locale;
    private String login;
    private SocketChannel channel;

    private InetSocketAddress address = new InetSocketAddress("localhost", 8989);
    Worker(Locale locale, String login, SocketChannel channel){
        this.locale = locale;
        this.login = login;
        this.channel = channel;
    }

    @Override
    protected Void doInBackground() {
        try{
            GUI gui = new GUI(locale, login);
            System.out.println("ere");
            gui.sender = new Command(address, gui, login, channel);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

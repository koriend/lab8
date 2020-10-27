package graphic;

import org.jdesktop.swingx.JXPanel;
import server.Message;
import server.Serialisation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentSkipListMap;

public class Authorization {
    private ResourceBundle resource;
    private Font font_all = new Font("Comic Sans MS", Font.PLAIN, 14);
    private Font font_gr = new Font("Comic Sans MS", Font.PLAIN, 14);
    private Font font_def;
    private Font font_big = new Font("OpenSans", Font.BOLD, 30);
    private Font font_med = new Font("OpenSans", Font.PLAIN, 16);
    private Font font_text = new Font("OpenSans", Font.PLAIN, 24);
    private Font font_beauty = new Font("Nord", Font.TRUETYPE_FONT, 35);
    private Font font_med2 = new Font("OpenSans", Font.PLAIN, 26);

    private Font font_gr_big = new Font("Aroania", Font.BOLD, 30);
    private Font font__gr_med = new Font("Aroania", Font.PLAIN, 16);
    private Font font_gr_text = new Font("Aroania", Font.PLAIN, 24);
    private Font font_gr_beauty = new Font("Aroania", Font.TRUETYPE_FONT, 35);
    private Font font_gr_med2 = new Font("Aroania", Font.PLAIN, 26);

    private JLabel test_login;
    private SocketAddress server = new InetSocketAddress("localhost", 8989);
    private JMenu menu_language;
    private JLabel text_login;
    private JTextField field_login;
    private JLabel text_password;
    private JPasswordField field_password;
    private JFrame frame;
    private JLabel server_dis;
    private JLabel user_exist;
    private JLabel wrong_email;
    private JLabel waiting;
    private JLabel wrong_pass;
    private JLabel wrong_login;
    private JButton button_log;
    private JButton button_reg;
    private JButton button_cancel;
    private JButton button_send;
    private JLabel text_port;
    private JXPanel panel_start;
    private JPanel panel_reg;
    private  JPanel panel_log;
    private JLabel text_email;
    private JTextField field_email;
    private JLabel welcome;
    private JButton test_log;
    private JButton test_cancel;
    private JButton test_send;
    private JButton test_in;
     private JTextField test_field_login;
     private SocketChannel socketChannel;
     private String login;
    private JLabel label1;
     private JLabel label;
    private JLabel label2;


    private Locale locale_ru = new Locale("ru");
    private Locale locale_gr = new Locale("gr");
    private Locale locale_es = new Locale("es", "HN");
    private Locale locale_cz = new Locale("cz");
    private Locale locale_chosse = Locale.getDefault();


    private String[] map;



    public Authorization(){
        initialization();
        properties();
        paint_menu();

        this.socketChannel = socketChannel;



        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel panel_main = new JPanel(cardLayout);

        panel_main.add(paint_start(), "start");
        panel_main.add(paint_login_in(), "log_in");
        panel_main.add(paint_registration(), "reg");

        test_cancel.addActionListener(event ->{
                cardLayout.show(panel_main, "start");
                frame.setTitle(resource.getString("title_main"));
                });
        button_cancel.addActionListener(event ->{
                cardLayout.show(panel_main, "start");
            frame.setTitle(resource.getString("title_main"));
        });
        test_log.addActionListener(event  -> {
            cardLayout.show(panel_main, "log_in");
            frame.setTitle(resource.getString("title_login"));

        });
        button_reg.addActionListener(event -> {
            cardLayout.show(panel_main, "reg");
            frame.setTitle(resource.getString("title_reg"));

        });

        test_in.addActionListener(event -> {
            new Thread(() -> {
                login = test_field_login.getText();
                test_cancel.setEnabled(false);
                test_in.setEnabled(false);
                field_password.setEditable(false);
                test_field_login.setEditable(false);
                connect(1);
                test_cancel.setEnabled(true);
                test_in.setEnabled(true);
                field_password.setEditable(true);
                field_password.setEditable(true);
                test_field_login.setEditable(true);
            }).start();

        });
        test_send.addActionListener(event -> {
            new Thread(() -> {
                test_field_login.setEditable(false);
                test_send.setEnabled(false);
                test_cancel.setEnabled(false);
                field_email.setEditable(false);
                connect(0);
                cardLayout.show(panel_main, "start");
            }).start();
        });


        frame.add(panel_main);
        // JPanel panel_main = new JPanel(cardLayout);

        frame.setTitle(resource.getString("title_main"));
        frame.setSize(600, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private void connect(int i){
        ConcurrentSkipListMap<String, String> mapUser = new ConcurrentSkipListMap<>();
        try {
            //socketChannel.close();
            socketChannel = SocketChannel.open(server);

            if (i == 0) {
                mapUser.put("login", field_login.getText());
                mapUser.put("mail", field_email.getText());
                mapUser.put("command", "register");
            } else {
                mapUser.put("login", test_field_login.getText());
                mapUser.put("password", new String(field_password.getPassword()));
                mapUser.put("command", "sign_in");
            }
            try {
                socketChannel.write(ByteBuffer.wrap(Serialisation.serialize(mapUser)));
            } catch (IOException e) {
                infoBox("Сервер недоступен. Повторите попытку позже", "");
            }
            String strReg = new Message(socketChannel).recirveMessage();
            if (Integer.parseInt(strReg) == 1) {
                infoBox("Вы авторизированы", "");
                work();
            } else infoBox("Пароль или логин не верен", "");


            //socketChannel.close();
        }catch (IOException e){}
    }

    private void work(){
        frame.setVisible(false);
        frame.dispose();
        new Worker(Locale.getDefault(), login, socketChannel).execute();

    }


    private void initialization(){
        frame = new JFrame();
        resource = ResourceBundle.getBundle("resources.language", locale_ru);
        menu_language = new JMenu(resource.getString("resources.language"));
        font_def = menu_language.getFont();
        text_login = new JLabel(resource.getString("login"));
        field_login = new JTextField();
        text_password = new JLabel(resource.getString("pass"));
        field_password = new JPasswordField();
        server_dis = new JLabel(resource.getString("server_disconnect"));
        user_exist = new JLabel(resource.getString("user_exist"));
        wrong_email = new JLabel(resource.getString("wrong_email"));
        waiting = new JLabel(resource.getString("wait"));
        wrong_pass = new JLabel(resource.getString("wrong_pass"));
        wrong_login = new JLabel(resource.getString("wrong_login"));
        button_log = new JButton(resource.getString("log_in"));
        button_reg = new JButton(resource.getString("reg"));
        button_cancel = new JButton(resource.getString("cancel"));
        button_send = new JButton(resource.getString("send"));
        text_port= new JLabel(resource.getString("port"));
        text_email = new JLabel(resource.getString("email"));
        field_email = new JTextField();
        panel_reg = new JPanel();
        panel_log = new JPanel();
        panel_start = new JXPanel();
        test_log = new JButton(resource.getString("log_in"));
        welcome = new JLabel(resource.getString("welcome"));
        test_cancel = new JButton(resource.getString("cancel"));
        test_send = new JButton(resource.getString("send"));
        test_in = new JButton(resource.getString("log_in"));
        test_field_login = new JTextField();
        label1 = new JLabel(resource.getString("welcomen"));
        label = new JLabel(resource.getString("reg"));
        label2 = new JLabel(resource.getString("title_main"));
        test_login = new JLabel(resource.getString("login"));


    }

    private void properties(){
        text_login.setHorizontalAlignment(SwingConstants.CENTER);
        text_password.setHorizontalAlignment(SwingConstants.RIGHT);

        user_exist.setForeground(Color.RED);
        wrong_email.setForeground(Color.RED);
        wrong_pass.setForeground(Color.RED);
        wrong_login.setForeground(Color.RED);
        waiting.setForeground(Color.BLACK);

        button_log.setVisible(false);
        button_reg.setVisible(false);
        button_send.setVisible(false);
        button_cancel.setVisible(false);
        wrong_login.setVisible(false);
        wrong_pass.setVisible(false);
        wrong_email.setVisible(false);
        waiting.setVisible(false);
        server_dis.setVisible(false);
        user_exist.setVisible(false);
        text_port.setVisible(false);
        text_password.setVisible(false);
        text_login.setVisible(false);
        field_password.setVisible(false);
        field_login.setVisible(false);
        welcome.setVisible(false);
        test_log.setVisible(false);
        test_cancel.setVisible(false);
        test_in.setVisible(false);

        button_cancel.setBorderPainted(false);
        button_cancel.setFocusPainted(false);
       // button_cancel.setContentAreaFilled(false);


        test_cancel.setBorderPainted(false);
        test_cancel.setFocusPainted(false);
       // test_cancel.setContentAreaFilled(false);

        test_log.setBorderPainted(false);
        test_log.setFocusPainted(false);
        //test_log.setContentAreaFilled(false);

        button_reg.setBorderPainted(false);
        button_reg.setFocusPainted(false);
       // button_reg.setContentAreaFilled(false);

       test_in.setBorderPainted(false);
        test_in.setFocusPainted(false);
        //test_in.setContentAreaFilled(false);
        //test_in.setBackground(Color.PINK);

        test_send.setBorderPainted(false);
        test_send.setFocusPainted(false);
        //test_send.setContentAreaFilled(false);


        field_email.setPreferredSize(new Dimension(160, 27));
        field_login.setPreferredSize(new Dimension(160, 27));
        field_password.setPreferredSize(new Dimension(160,27));

        test_in.setFont(font_med);
        text_email.setFont(font_med2);
        field_login.setFont(font_text);
        field_email.setFont(font_text);
        button_cancel.setFont(font_med);
        test_send.setFont(font_med);
        label2.setFont(font_big);
        test_login.setFont(font_med2);
        test_field_login.setFont(font_text);
        text_password.setFont(font_text);
        field_password.setFont(font_text);
        test_cancel.setFont(font_med);
        welcome.setFont(font_beauty);
        label1.setFont(font_text);
        test_log.setFont(font_med);
        button_reg.setFont(font_med);
        label.setFont(font_big);
        text_login.setFont(font_med2);

    }




    private JPanel paint_start(){
        JPanel base = new JPanel();
        base.setLayout(new BorderLayout());
        JXPanel color_name = new JXPanel();
        color_name.setBackground(new Color(14, 117, 127,90));
        color_name.setPreferredSize(new Dimension(360, 80));



        welcome.setForeground(Color.DARK_GRAY);

        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setVisible(true);
        color_name.setLayout(new BorderLayout());

        color_name.add(welcome, BorderLayout.CENTER);

        base.add(color_name, BorderLayout.NORTH);



       /* panel_start.setLayout(new GridBagLayout());
        panel_start.setBackground(new Color(242, 242, 242));
        GridBagConstraints grid_lay = new GridBagConstraints();
        //grid_lay.insets = new Insets(5,2,5,2);
        grid_lay.gridx = 0;
        grid_lay.gridy = 0;
        grid_lay.fill = GridBagConstraints.BOTH;
        grid_lay.anchor = GridBagConstraints.NORTH;
        grid_lay.gridwidth = 3; */

       panel_start.setLayout(new GridLayout(3, 0));
       JPanel p1 = new JPanel();

        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p1.setBackground(new Color(20,20,20));
        p2.setBackground(new Color(20,20,20));
        p3.setBackground(new Color(20,20,20));
        //JPanel p4 = new JPanel();




        label1.setForeground(new Color(89, 67, 40));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.setLayout(new BorderLayout());
        //label1.setFont();
        p1.add(label1, BorderLayout.CENTER);

        test_log.setBackground(new Color(242, 141, 82));
        test_log.setForeground(new Color(115, 89, 73));
        //Border line = new LineBorder(Color.BLACK);
        //Border margin = new EmptyBorder(5, 15, 5, 15);
        //Border compound = new CompoundBorder(line, margin);

        //test_log.setBorder(compound);
        test_log.setPreferredSize(new Dimension(166, 40));
        test_log.setVisible(true);
        test_log.setVerticalTextPosition(JLabel.TOP);
        p2.setBorder(BorderFactory.createEmptyBorder(20, 5, 10, 10));


        p2.add(test_log);



        button_reg.setBackground(new Color(242, 141, 82));
        button_reg.setForeground(new Color(115, 89, 73));
        button_reg.setPreferredSize(new Dimension(170, 40));
        button_reg.setVisible(true);
        button_reg.setVerticalTextPosition(JLabel.TOP);
        p3.setBorder(BorderFactory.createEmptyBorder(3, 5, 20, 10));

        p3.add(button_reg);


        panel_start.add(p1);
        panel_start.add(p2);
        panel_start.add(p3);

        base.add(panel_start, BorderLayout.CENTER);

        return base;

    }
    private JPanel paint_registration(){
        JPanel base1 = new JPanel();
        base1.setLayout(new BorderLayout());

        JPanel e1 = new JPanel();
        JXPanel color_name = new JXPanel();
        e1.setLayout(new BorderLayout());
        color_name.setBackground(new Color(242, 141, 82));
        e1.setPreferredSize(new Dimension(360, 60));
        e1.add(color_name, BorderLayout.SOUTH);

        JPanel c2 = new JPanel();
        c2.setLayout(new BorderLayout());
        //c2.setBackground(Color.PINK);
        c2.setPreferredSize(new Dimension(50, 60));
        JPanel c1 = new JPanel();
        c1.setBackground(new Color(242, 141, 82));
        JPanel t = new JPanel();


        label.setForeground(new Color(89, 67, 40));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        t.setBorder(BorderFactory.createEmptyBorder(6, 5, 10, 10));
        t.add(label, BorderLayout.WEST);
        e1.add(t, BorderLayout.CENTER);


        c2.add(c1, BorderLayout.WEST);
        e1.add(c2, BorderLayout.EAST);

        panel_reg.setBackground(new Color(242, 242, 242));
        base1.add(e1, BorderLayout.NORTH);

        panel_reg.setLayout(new BorderLayout());
        JPanel o1 = new JPanel();
        JPanel p1 = new JPanel();

        o1.setPreferredSize(new Dimension(50, 310));
        JPanel s = new JPanel();
        s.setBackground(new Color(242, 141, 82));
        o1.setLayout(new BorderLayout());
        o1.add(s, BorderLayout.WEST);
        panel_reg.add(o1, BorderLayout.EAST);





        p1.setLayout(new GridBagLayout());
        GridBagConstraints grid_lay = new GridBagConstraints();
        grid_lay.insets = new Insets(5,2,5,2);
        grid_lay.gridx = 0;
        grid_lay.gridy = 0;
        grid_lay.anchor = GridBagConstraints.WEST;


        text_login.setVisible(true);
        text_login.setForeground(new Color(89, 67, 40));
        p1.add(text_login, grid_lay);

        grid_lay.gridx++;
        grid_lay.fill = GridBagConstraints.BOTH;
        field_login.setVisible(true);
        field_login.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(115, 102, 86)));
        field_login.setBackground(p1.getBackground());


        //grid_lay.insets = new Insets(5, 2, 10, 10);
        p1.add(field_login, grid_lay);

        grid_lay.gridy++;
        grid_lay.gridx = 0;
        //grid_lay.insets = new Insets(5,2,5,2);
        grid_lay.fill = GridBagConstraints.NONE;
        grid_lay.anchor = GridBagConstraints.WEST;
        text_email.setVisible(true);
        text_email.setForeground(new Color(89, 67, 40));
        p1.add(text_email, grid_lay);


        grid_lay.gridx++;
        grid_lay.fill = GridBagConstraints.BOTH;
        grid_lay.anchor = GridBagConstraints.EAST;
        field_email.setBorder(BorderFactory.createMatteBorder(0,0,1,0, new Color(115, 102, 86)));
        field_email.setBackground(p1.getBackground());

        field_email.setVisible(true);
        p1.add(field_email, grid_lay);


        grid_lay.gridy++;
        grid_lay.gridx = 0;
        grid_lay.gridy++;
        button_cancel.setVisible(true);
        grid_lay.anchor = GridBagConstraints.WEST;
        button_cancel.setBackground(new Color(140, 47, 27));
        button_cancel.setForeground(new Color(217, 200, 189));
        button_cancel.setPreferredSize(new Dimension(135, 35));

        p1.add(button_cancel, grid_lay);
        //panel_log.add(button_reg, grid_lay);

        grid_lay.gridx++;
       // grid_lay.gridx++;
        //button_reg.setVisible(true);
        test_send.setVisible(true);

        //button_send.setFont(font_text);
        //panel_log.add(button_reg, grid_lay);
        test_send.setBackground(new Color(140, 47, 27));
        test_send.setForeground(new Color(217, 200, 189));
        test_send.setPreferredSize(new Dimension(135, 35));

        p1.add(test_send, grid_lay);

        grid_lay.gridy++;
        grid_lay.gridy++;
        grid_lay.gridx = 0;
        grid_lay.gridwidth = 2;
        grid_lay.gridheight = 5;
        grid_lay.anchor = GridBagConstraints.CENTER;
        grid_lay.fill = GridBagConstraints.BOTH;

        panel_reg.add(p1, BorderLayout.CENTER);
        base1.add(panel_reg, BorderLayout.CENTER);

        return base1;
    }
    private JPanel paint_login_in(){






        JPanel base1 = new JPanel();
        base1.setLayout(new BorderLayout());

        JPanel e1 = new JPanel();
        JXPanel color_name = new JXPanel();
        e1.setLayout(new BorderLayout());
        color_name.setBackground(new Color(242, 141, 82));
        e1.setPreferredSize(new Dimension(360, 60));
        e1.add(color_name, BorderLayout.SOUTH);

        JPanel c2 = new JPanel();
        c2.setLayout(new BorderLayout());
        //c2.setBackground(Color.PINK);
        c2.setPreferredSize(new Dimension(50, 60));
        JPanel c1 = new JPanel();
        c1.setBackground(new Color(242, 141, 82));
        JPanel t = new JPanel();


        label2.setForeground(new Color(89, 67, 40));
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        t.setBorder(BorderFactory.createEmptyBorder(6, 5, 10, 10));
        t.add(label2, BorderLayout.WEST);
        e1.add(t, BorderLayout.CENTER);


        c2.add(c1, BorderLayout.WEST);
        e1.add(c2, BorderLayout.EAST);

        panel_log.setBackground(new Color(242, 242, 242));
        base1.add(e1, BorderLayout.NORTH);

        panel_log.setLayout(new BorderLayout());
        JPanel o1 = new JPanel();
        JPanel p1 = new JPanel();

        o1.setPreferredSize(new Dimension(50, 310));
        JPanel s = new JPanel();
        s.setBackground(new Color(242, 141, 82));
        o1.setLayout(new BorderLayout());
        o1.add(s, BorderLayout.WEST);
        panel_log.add(o1, BorderLayout.EAST);





        p1.setLayout(new GridBagLayout());
        GridBagConstraints grid_lay = new GridBagConstraints();
        grid_lay.insets = new Insets(5,2,5,2);
        grid_lay.gridx = 0;
        grid_lay.gridy = 0;
        grid_lay.anchor = GridBagConstraints.WEST;



        test_login.setVisible(true);
        test_login.setForeground(new Color(89, 67, 40));
        p1.add(test_login, grid_lay);

        grid_lay.gridx++;
        grid_lay.fill = GridBagConstraints.BOTH;
        test_field_login.setVisible(true);
        test_field_login.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(115, 102, 86)));
        test_field_login.setBackground(p1.getBackground());


        //grid_lay.insets = new Insets(5, 2, 10, 10);
        p1.add(test_field_login, grid_lay);

        grid_lay.gridy++;
        grid_lay.gridx = 0;
        //grid_lay.insets = new Insets(5,2,5,2);
        grid_lay.fill = GridBagConstraints.NONE;
        grid_lay.anchor = GridBagConstraints.WEST;
        text_password.setVisible(true);

        text_password.setForeground(new Color(89, 67, 40));
        p1.add(text_password, grid_lay);


        grid_lay.gridx++;
        grid_lay.fill = GridBagConstraints.BOTH;
        grid_lay.anchor = GridBagConstraints.EAST;
        field_password.setBorder(BorderFactory.createMatteBorder(0,0,1,0, new Color(115, 102, 86)));
        field_password.setBackground(p1.getBackground());

        field_password.setVisible(true);
        p1.add(field_password, grid_lay);


        grid_lay.gridy++;
        grid_lay.gridx = 0;
        grid_lay.gridy++;
        test_cancel.setVisible(true);
        grid_lay.anchor = GridBagConstraints.WEST;
       test_cancel.setBackground(new Color(140, 47, 27));
        test_cancel.setForeground(new Color(217, 200, 189));
        test_cancel.setPreferredSize(new Dimension(135, 35));

        p1.add(test_cancel, grid_lay);
        //panel_log.add(button_reg, grid_lay);

        grid_lay.gridx++;
        // grid_lay.gridx++;
        //button_reg.setVisible(true);
        test_in.setVisible(true);

        //button_send.setFont(font_text);
        //panel_log.add(button_reg, grid_lay);
        test_in.setBackground(new Color(140, 47, 27));
        test_in.setForeground(new Color(217, 200, 189));
        test_in.setPreferredSize(new Dimension(135, 35));

        p1.add(test_in, grid_lay);

        grid_lay.gridy++;
        grid_lay.gridy++;
        grid_lay.gridx = 0;
        grid_lay.gridwidth = 2;
        grid_lay.gridheight = 5;
        grid_lay.anchor = GridBagConstraints.CENTER;
        grid_lay.fill = GridBagConstraints.BOTH;

        panel_log.add(p1, BorderLayout.CENTER);
        base1.add(panel_log, BorderLayout.CENTER);

        return base1;

    }

    private void paint_menu(){
        JMenuItem item_ru = new JMenuItem("Русский");
        JMenuItem item_gr = new JMenuItem("Ελληνικά");
        JMenuItem item_es = new JMenuItem("Español (Honduras)");
        JMenuItem item_cz = new JMenuItem("Česky");

        item_cz.addActionListener(event -> changeLanguage(locale_cz));
        item_ru.addActionListener(event -> changeLanguage(locale_ru));
        item_gr.addActionListener(event -> changeLanguage(locale_gr));
        item_es.addActionListener(event -> changeLanguage(locale_es));

        menu_language.add(item_es);
        menu_language.add(item_cz);
        menu_language.add(item_gr);
        menu_language.add(item_ru);
        JMenuBar menu_bar = new JMenuBar();
        menu_bar.add(menu_language);
        frame.setJMenuBar(menu_bar);
    }



    private void infoBox(String infoMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, infoMessage, resource.getString("att") + titleBar, JOptionPane.NO_OPTION);
    }

    private void changeLanguage(Locale locale){
        locale_chosse = locale;
        resource = ResourceBundle.getBundle("resources.language", locale_chosse);
        if(locale_chosse == locale_gr){
            test_in.setFont(font__gr_med);
            text_email.setFont(font_gr_med2);
            field_login.setFont(font_gr_text);
            field_email.setFont(font_gr_text);
            button_cancel.setFont(font__gr_med);
            test_send.setFont(font__gr_med);
            label2.setFont(font_gr_big);
            test_login.setFont(font_gr_med2);
            test_field_login.setFont(font_gr_text);
            text_password.setFont(font_gr_text);
            field_password.setFont(font_gr_text);
            test_cancel.setFont(font__gr_med);
            welcome.setFont(font_gr_beauty);
            label1.setFont(font_gr_text);
            test_log.setFont(font__gr_med);
            button_reg.setFont(font__gr_med);
            label.setFont(font_gr_big);
            text_login.setFont(font_gr_med2);
        } else {
            test_in.setFont(font_med);
            text_email.setFont(font_med2);
            field_login.setFont(font_text);
            field_email.setFont(font_text);
            button_cancel.setFont(font_med);
            test_send.setFont(font_med);
            label2.setFont(font_big);
            test_login.setFont(font_med2);
            test_field_login.setFont(font_text);
            text_password.setFont(font_text);
            field_password.setFont(font_text);
            test_cancel.setFont(font_med);
            welcome.setFont(font_beauty);
            label1.setFont(font_text);
            test_log.setFont(font_med);
            button_reg.setFont(font_med);
            label.setFont(font_big);
            text_login.setFont(font_med2);
        }

        resource = ResourceBundle.getBundle("resources.language", locale_chosse);
        menu_language.setText(resource.getString("resources.language"));
        text_login.setText(resource.getString("login"));
        text_password.setText(resource.getString("pass"));
        button_log.setText(resource.getString("log_in"));
        button_reg.setText(resource.getString("reg"));
        button_cancel.setText(resource.getString("cancel"));
        button_send.setText(resource.getString("send"));
        text_email.setText(resource.getString("email"));
        label1.setText(resource.getString("welcomen"));
        test_log.setText(resource.getString("log_in"));
        welcome.setText(resource.getString("welcome"));
        test_cancel.setText(resource.getString("cancel"));
        test_send.setText(resource.getString("send"));
        test_in.setText(resource.getString("log_in"));
        label.setText(resource.getString("reg"));
        label2.setText(resource.getString("title_main"));
        test_login.setText(resource.getString("login"));

    }

}

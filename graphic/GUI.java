package graphic;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI extends JFrame{

    private Font font2 = new Font("OpenSans", Font.PLAIN, 26);
    private Font font1 = new Font("OpenSans", Font.PLAIN, 14);
    private Font font21 = new Font("OpenSans", Font.PLAIN, 20);

    Collection<String[][]> col;

    private Font font_gr2 = new Font("OpenSans", Font.PLAIN, 26);
    private Font font_gr1 = new Font("OpenSans", Font.PLAIN, 14);
    private Font font_gr21 = new Font("OpenSans", Font.PLAIN, 20);

    private ConcurrentSkipListMap<String, String[][]> collection;
    private CopyOnWriteArrayList<Thing> graph_collection;
    private ConcurrentSkipListMap<String, Thing> temp_col;
    private CopyOnWriteArrayList<JButton> button_collection;
    private boolean clear;
    private String login;
    private JXTable tableX;
    private JTable table;
    private JMenu menu;
    private DefaultTableModel tableModel;
    private JXPanel panel_main;
    private JXPanel panel_right;
    private Locale locale_ru = new Locale("ru");
    private Locale locale_gr = new Locale("gr");
    private Locale locale_es = new Locale("es", "HN");
    private Locale locale_cz = new Locale("cz");
    private Locale locale_chosse = Locale.getDefault();
    private DateTimeFormatter displayDateTimeFormatter;
    private JLabel info_login;
    private ResourceBundle resource;
    private static JLabel text_connection = new JLabel();
    private JLabel text_info;
    private JButton button_exit;
    private Locale locale = Locale.getDefault();
    JLabel label;
    private JXStatusBar statusBar ;
    private JButton button_graph;
     Command sender;
    private JPanel p1 ;
    private JLabel info;
    private JPanel p2;
    private JPanel p3 ;
    private JPanel p4;
    private JLabel i1;
    private JLabel i2;
    private JLabel i3;
    private JLabel i4;
    private JLabel i5;
    private JLabel i6;
    private JLabel i7;
    private JLabel i8;
    private JLabel i9;
    private JLabel i10;
    private JLabel i11;
    private JLabel i12;
    private JLabel i13;
    private JButton button_delete;
    private JButton button_mod;
    private JButton button_create;
    private JTextField w1;
    private JTextField w2;
    private JTextField w3;
    private JTextField w4;
    private JTextField w5;
    private JTextField w6;
    private JTextField w7;
    JLabel data_pole = new JLabel(LocalDateTime.now().toString());
    private JButton button_ok;
    private JButton button_cr_cancel;
    private JButton button_add_if;
    private JButton button_save;
    private JPanel panel_graph;
    private JButton button_ok2;
    private String[] columns;
    private JButton button_info;
    private JButton canc;


    public GUI(Locale locale, String login){

          this.login = login;
          displayDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.MEDIUM).withLocale(locale);
          initialization();
          properties();
          paint_menu();

        CardLayout cardLayout = new CardLayout();
        JPanel panel_main = new JPanel(cardLayout);

        panel_main.add(paint_main_panel(), "table");
        panel_main.add(paint_graphes(), "graph");


        button_graph.addActionListener(ex -> {

            graph_collection = create_graph_col();
            paint_graphes();
            cardLayout.show(panel_main,"graph");
        });


          JXPanel t2 = paint_right_panel();
          t2.setPreferredSize(new Dimension(300,600));
          add(panel_main, BorderLayout.CENTER);
          add(t2, BorderLayout.EAST);



          setResizable(false);
          setLocationRelativeTo(null);


        setTitle("Lab 8");
        setSize(1366  , 768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initialization(){


        graph_collection = new CopyOnWriteArrayList<>();
        resource = ResourceBundle.getBundle("resources.language", locale_ru);
        menu = new JMenu(resource.getString("resources.language"));
        info_login = new JLabel(resource.getString("user") + ":" + login);
        button_exit = new JButton(resource.getString("exit"));
         label = new JLabel(resource.getString("connect"));
        panel_main = new JXPanel();
        statusBar = new JXStatusBar();
        panel_right = new JXPanel();
        tableX = new JXTable();
        table = new JTable();
         p1 = new JPanel();
        info = new JLabel(resource.getString("info_text"));
        button_graph = new JButton("Графика");
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();

        temp_col = new ConcurrentSkipListMap<>();
        i1 = new JLabel(resource.getString("caps_name"));
        i2 = new JLabel(resource.getString("name"));
        i3 = new JLabel(resource.getString("value"));
        i4 = new JLabel(resource.getString("type_id"));
        i5 = new JLabel(resource.getString("planet"));
        i6 = new JLabel(resource.getString("date"));
        i7 = new JLabel(resource.getString("owner"));
        i8 = new JLabel("");
        i9 = new JLabel("");
        i10 = new JLabel("");
        i11 = new JLabel("");
        i12 = new JLabel("");
        i13 = new JLabel("");
        w1 = new JTextField(10);
        canc = new JButton("На панель");
        w2 = new JTextField(10);
        w3 = new JTextField(10);
        w4 = new JTextField(10);
        w5 = new JTextField(10);
        w6 = new JTextField(10);
        w7 = new JTextField(10);


    button_collection = new CopyOnWriteArrayList<>();
        panel_graph = new JPanel();
        panel_graph.setLayout(null);

        button_save = new JButton(resource.getString("save"));

        button_add_if = new JButton(resource.getString("if_min"));
        button_ok = new JButton(resource.getString("ok"));
        button_ok2 = new JButton(resource.getString("ok"));
        button_cr_cancel = new JButton(resource.getString("cancel"));

        button_delete = new JButton(resource.getString("delete"));
        button_mod = new JButton(resource.getString("modif"));

        button_create = new JButton(resource.getString("create"));




    }

    private void properties() {
        text_connection.setBackground(Color.BLACK);
        text_connection.setOpaque(true);
        text_connection.setText("");
        text_connection.setBackground(Color.BLACK);
        Font font = new Font("Sans-Serif", Font.PLAIN, 16);
        text_connection.setFont(font);

        JXStatusBar.Constraint c = new JXStatusBar.Constraint();
        c.setFixedWidth(100);

        statusBar.add(label, c);
        statusBar.setVisible(true);



        i13.setFont(font21);
        i7.setFont(font21);
        i12.setFont(font21);
        i6.setFont(font21);
        i11.setFont(font21);
        i5.setFont(font21);
        i10.setFont(font21);
        i4.setFont(font21);
        i9.setFont(font21);
        i3.setFont(font21);
        i8.setFont(font21);
        i2.setFont(font21);
        i1.setFont(font2);

        button_delete.setFont(font1);
        button_mod.setFont(font1);

        button_cr_cancel.setFont(font1);
        button_ok.setFont(font1);
        button_save.setFont(font1);

        //getContentPane().add(panel_main, BorderLayout.CENTER);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        button_delete.addActionListener(event -> {
            new Thread(() -> {
                if (this.login.equals(i13.getText())) {
                    sender.remove_collection(i8.getText(), this.login);
                }
                void_right_panel();
            }).start();
        });

        button_create.addActionListener(event -> {
            new Thread(() -> panel_create()).start();
        });



        button_ok.addActionListener(event -> {
            new Thread(() -> {
                String[][] arr = {
                        {"name", w1.getText()},
                        {"value", w2.getText()},
                        {"id", w3.getText()},
                        {"planet", w4.getText()}
                };
                sender.add_collection(arr, login, "insert");
                void_right_panel();
            }).start();

        });

        button_cr_cancel.addActionListener(event -> {
            new Thread(() -> {
                void_right_panel();
            }).start();
        });

        button_add_if.addActionListener(event  -> {
            new Thread(() -> {
                panel_create();
            }).start();
        });

        button_mod.addActionListener(event -> {
            new Thread(() -> {
                panel_mod();
            }).start();
        });

        canc.addActionListener(ev -> {
            panel_graph.setVisible(false);
            panel_main.setVisible(true);
        });
        button_exit.addActionListener(ev -> {
            new Thread(() -> exit()).start();
        });

        button_save.addActionListener(ev -> {
            new Thread(() -> {
                String[][] arr ={
                        {"name", w1.getText()},
                        {"value", w2.getText()},
                        {"id", w3.getText()},
                        {"planet", w4.getText()}
                };
                sender.remove_collection(i1.getText(), login);
                sender.add_collection(arr, login, "insert");
                setInfo(arr);
            }).start();
        });

       /* button_info.addActionListener(ev -> {
            new Thread(() -> {
                String s = "";
                s = s +resource.getString("info_col") + '\n';
                //out.flush();
                s = s + resource.getString("size") + collection.size() + '\n';
                frame_err(s);
            }).start();
        }); */
    }

    public String[][] getTh(Thing thing){
        String[][] arr ={
                {"name", thing.getName()},
                {"value", thing.getValue()},
                {"id", thing.getId()},
                {"planet", thing.getPlanet_name()},
                {"p_v", ""},
                {"p_n", ""},
                {"", ""},
                {"date", thing.getDate()},
                {"user", thing.getUser()}
        };
        return arr;
    }

    private void paint_menu(){
        JMenuItem item_ru = new JMenuItem("Русский");
        JMenuItem item_gr = new JMenuItem("Ελληνικά");
        JMenuItem item_es = new JMenuItem("Español (Honduras)");
        JMenuItem item_cz = new JMenuItem("Česky");
        menu.add(item_es);
        menu.add(item_cz);
        menu.add(item_gr);
        menu.add(item_ru);

        item_cz.addActionListener(event -> changeLanguage(locale_cz));
        item_ru.addActionListener(event -> changeLanguage(locale_ru));
        item_gr.addActionListener(event -> changeLanguage(locale_gr));
        item_es.addActionListener(event -> changeLanguage(locale_es));


        JMenuBar menu_bar = new JMenuBar();
        menu_bar.add(menu);
        menu_bar.add(button_create);
        menu_bar.add(button_add_if);
        //menu_bar.add(button_info);

        menu_bar.add(Box.createHorizontalGlue());
        button_create.setFocusPainted(false);
        button_create.setBorderPainted(false);
        button_create.setContentAreaFilled(false);

        button_graph.setFocusPainted(false);
        button_graph.setBorderPainted(false);
        button_graph.setContentAreaFilled(false);

        button_add_if.setFocusPainted(false);
        button_add_if.setBorderPainted(false);
        button_add_if.setContentAreaFilled(false);

canc.setFocusPainted(false);
canc.setContentAreaFilled(false);
canc.setBorderPainted(false);

        menu_bar.add(button_graph);
        menu_bar.add(canc);
        button_exit.setFocusPainted(false);
        button_exit.setBorderPainted(false);
        button_exit.setContentAreaFilled(false);
        menu_bar.add(button_exit);



        //menu_bar.add(statusBar);
        setJMenuBar(menu_bar);
    }

    private JXPanel paint_main_panel(){

        JScrollPane scrollPane = new JScrollPane(paint_table());

        panel_main.setLayout(new BorderLayout());
        panel_main.add(scrollPane, BorderLayout.CENTER);
        return panel_main;
    }
    private JTable paint_table() {
        String[] columns = {resource.getString("name"), resource.getString("value"), resource.getString("type_id"), resource.getString("planet"), resource.getString("date"), resource.getString("owner")};
        tableModel = new TabelModel();
        tableModel.setColumnIdentifiers(columns);
        table = new JTable(tableModel);
       // DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();
        //renderer.setHorizontalAlignment(SwingConstants.LEFT);

        TableCellRenderer renderer_cell = new ColorRenderer();
        table.setDefaultRenderer(TableView.TableRow.class, renderer_cell);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);


        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        //table.getColumnModel().getColumn(6).setPreferredWidth(120);
      //  tableModel

        table.getSelectionModel().addListSelectionListener(event -> {
           if (!clear){
                new Thread(()->{
                    int index = table.getSelectedRow();
                    String name = (String) table.getValueAt(index, 0);
                    setInfo(collection.get(name));
                }).start();
            }
        });

        return table;
    }

    synchronized void update_table(ConcurrentSkipListMap<String, String[][]> map){
        //Collection<String[][]> col = map.values();
        this.collection = map;
        repaint_table(map);
    }

    private void exit(){
        this.setVisible(false);
        try{
            this.dispose();
            new Authorization();
        } catch (NullPointerException e){
        }
    }

    private CopyOnWriteArrayList<Thing> create_graph_col(){
        System.out.println(collection);
        System.out.println("jfhfud");
        CopyOnWriteArrayList<Thing> list_col = new CopyOnWriteArrayList<>();
        if(collection != null) {
       col = collection.values(); }
        for(String[][] thing : col){
            list_col.add(new Thing(thing));

        }

        for(Thing th : list_col){
            temp_col.put(th.getName(), th);
        }
        System.out.println("hjhj");
        System.out.println(col);
        System.out.println("hjhj");

        return list_col;
    }

    private  JPanel paint_graphes(){
        panel_graph.setLayout(new FlowLayout());

        for(Thing th : graph_collection){

            switch(th.getId()){
                case "1":
                    MachineButton b = new MachineButton();
                    b.setBorderPainted(false);
                    b.setFocusPainted(false);
                    b.setText(th.getName());
                    button_collection.add(b);
                    panel_graph.add(b);
                    break;
                case "2":
                    PlanetButton q = new PlanetButton();
                    q.setBorderPainted(false);
                    q.setFocusPainted(false);
                    q.setText(th.getName());
                    panel_graph.add(q);
                    button_collection.add(q);
                    break;
                case "3":
                    ReliefButon e = new ReliefButon(th, Color.BLACK);
                    e.setBorderPainted(false);
                    e.setText(th.getName());
                    e.setFocusPainted(false);
                    panel_graph.add(e);
                    button_collection.add(e);
                    break;
                case "4":
                    StarButton o = new StarButton();
                    o.setFocusPainted(false);
                    o.setBorderPainted(false);
                    o.setText(th.getName());
                    panel_graph.add(o);
                    button_collection.add(o);
                    break;
            }
        }

       /* for(JButton but : button_collection){
            new Thread(() -> {
                setInfo(getTh(temp_col.get(but.getText())));
            }).start();

        } */
        return panel_graph;
    }

    private void repaint_table(ConcurrentSkipListMap<String, String[][]> map){
        clear = true;
        table.clearSelection();
        tableModel.setRowCount(0);
        Collection<String[][]> col = map.values();
        for(String[][] arr : col){
            add_table(arr);
        }
        clear = false;

    }

    private void setInfo(String[][] arr){

        w1.setVisible(false);
        w2.setVisible(false);
        w3.setVisible(false);
        w4.setVisible(false);

        i1.setVisible(true);
        i2.setVisible(true);
        i3.setVisible(true);
        i4.setVisible(true);
        i5.setVisible(true);
        i6.setVisible(true);
        i7.setVisible(true);
        i8.setVisible(true);
        i9.setVisible(true);
        i10.setVisible(true);
        i11.setVisible(true);
        i12.setVisible(true);
        i13.setVisible(true);



        button_mod.setVisible(true);
        button_delete.setVisible(true);

        i1.setText(arr[0][1]);
        i8.setText(arr[0][1]);
        i9.setText(arr[1][1]);
        i10.setText(arr[2][1]);
        i11.setText(arr[3][1]);
        i12.setText(arr[7][1]);
        i13.setText(arr[8][1]);
    }

    private void panel_mod(){
        i8.setVisible(false);
        i9.setVisible(false);
        i10.setVisible(false);
        i11.setVisible(false);
        i12.setVisible(false);
        i13.setVisible(false);


        button_delete.setVisible(false);
        button_mod.setVisible(false);

        button_cr_cancel.setVisible(true);

        button_save.setVisible(true);

        w1.setText(i8.getText());
        w2.setText(i9.getText());
        w3.setText(i10.getText());
        w4.setText(i11.getText());

        w1.setVisible(true);
        w2.setVisible(true);
        w3.setVisible(true);
        w4.setVisible(true);

    }

    private void panel_create(){
        i8.setVisible(false);
        i9.setVisible(false);
        i10.setVisible(false);
        i11.setVisible(false);
        i12.setVisible(false);
        i13.setVisible(false);

        button_mod.setVisible(false);
        button_delete.setVisible(false);

        button_cr_cancel.setVisible(true);
        button_ok.setVisible(true);

        w1.setVisible(true);
        w2.setVisible(true);
        w3.setVisible(true);
        w4.setVisible(true);


    }

    private JXPanel paint_right_panel(){

        panel_right.setLayout(new BorderLayout());

        info.setHorizontalAlignment(JLabel.CENTER);

        p2.setPreferredSize(new Dimension(300, 60));
        p2.setBackground(new Color(140, 47, 27));
        //p2.setLayout(new BorderLayout());
        p2.add(info);
        info.setVisible(true);

        p3.setPreferredSize(new Dimension(300, 60));
        panel_right.add(p3, BorderLayout.SOUTH);

        info.setFont(new Font("OpenSans", Font.PLAIN, 30));
        info.setForeground(new Color(242, 141, 82));
        p1.setPreferredSize(new Dimension(300, 60));
        p1.setLayout(new BorderLayout());
        p1.add(info, BorderLayout.CENTER);
        p1.setBackground(new Color(140, 47, 27));


        p4.setBackground(new Color(242, 242, 242));
        SpringLayout spring = new SpringLayout();
        p4.setLayout(spring);




        spring.putConstraint(SpringLayout.WEST, i1, 120, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.EAST, i1, 100, SpringLayout.EAST, p4);
        spring.putConstraint(SpringLayout.NORTH, i1, 30, SpringLayout.NORTH, p4);


        spring.putConstraint(SpringLayout.NORTH, i2, 35, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i2, 65, SpringLayout.WEST, p4);

        spring.putConstraint(SpringLayout.EAST, i8, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i8, 35, SpringLayout.SOUTH, i1);


        //spring.putConstraint(SpringLayout.EAST, w1, 60, SpringLayout.EAST, p4);
        spring.putConstraint(SpringLayout.WEST, w1, 160, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, w1, 40, SpringLayout.SOUTH, i1);
        //spring.putConstraint(SpringLayout.SOUTH, w1, 80, SpringLayout.SOUTH, i1);



        spring.putConstraint(SpringLayout.NORTH, i3, 75, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i3, 65, SpringLayout.WEST, p4);

        spring.putConstraint(SpringLayout.EAST, i9, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i9, 75, SpringLayout.SOUTH, i1);

        //spring.putConstraint(SpringLayout.EAST, w1, 60, SpringLayout.EAST, p4);
        spring.putConstraint(SpringLayout.WEST, w2, 160, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, w2, 80, SpringLayout.SOUTH, i1);


        spring.putConstraint(SpringLayout.NORTH, i4, 115, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i4, 65, SpringLayout.WEST, p4);

        spring.putConstraint(SpringLayout.EAST, i10, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i10, 115, SpringLayout.SOUTH, i1);


        //spring.putConstraint(SpringLayout.EAST, w1, 60, SpringLayout.EAST, p4);
        spring.putConstraint(SpringLayout.WEST, w3, 160, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, w3, 120, SpringLayout.SOUTH, i1);

        spring.putConstraint(SpringLayout.NORTH, i5, 155, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i5, 65, SpringLayout.WEST, p4);

        spring.putConstraint(SpringLayout.EAST, i11, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i11, 155, SpringLayout.SOUTH, i1);


        //spring.putConstraint(SpringLayout.EAST, w1, 60, SpringLayout.EAST, p4);
        spring.putConstraint(SpringLayout.WEST, w4, 160, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, w4, 160, SpringLayout.SOUTH, i1);

        spring.putConstraint(SpringLayout.NORTH, i6, 235, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i6, 65, SpringLayout.WEST, p4);

        spring.putConstraint(SpringLayout.EAST, i12, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i12, 235, SpringLayout.SOUTH, i1);

        JLabel data_pole = new JLabel(LocalDateTime.now().toString());



        spring.putConstraint(SpringLayout.NORTH, i7, 275, SpringLayout.SOUTH, i1);
        spring.putConstraint(SpringLayout.WEST, i7, 65, SpringLayout.WEST, p4);


        spring.putConstraint(SpringLayout.EAST, i13, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, i13, 275, SpringLayout.SOUTH, i1);


        JLabel l = new JLabel(login);
        spring.putConstraint(SpringLayout.EAST, l, 230, SpringLayout.WEST, p4);
        spring.putConstraint(SpringLayout.NORTH, l, 35, SpringLayout.SOUTH, i1);


        w1.setVisible(false);
        w2.setVisible(false);
        w3.setVisible(false);
        w4.setVisible(false);

        p4.add(i7);
        p4.add(i13);
        p4.add(i6);
        p4.add(i12);
        p4.add(i5);
        p4.add(i11);
        p4.add(i4);
        p4.add(i10);
        p4.add(i9);
        p4.add(i3);
        p4.add(i8);
        p4.add(i2);
        p4.add(i1);
        p4.add(w1);
        p4.add(w2);
        p4.add(w3);
        p4.add(w4);







        button_cr_cancel.setBorderPainted(false);
        button_cr_cancel.setFocusPainted(false);

        button_save.setBorderPainted(false);
        button_save.setFocusPainted(false);

        button_ok.setFocusPainted(false);
        button_ok.setBorderPainted(false);



        button_delete.setBorderPainted(false);
        button_delete.setFocusPainted(false);

        button_mod.setFocusPainted(false);
        button_mod.setBorderPainted(false);




        button_ok.setBackground((new Color(242, 141, 82)));
        button_cr_cancel.setForeground(new Color(115, 89, 73));
        button_ok.setForeground(new Color(115, 89, 73));
        button_cr_cancel.setBackground((new Color(242, 141, 82)));

        button_save.setBackground((new Color(242, 141, 82)));
        button_save.setForeground(new Color(115, 89, 73));

        button_save.setPreferredSize(new Dimension(115, 30));
        button_cr_cancel.setPreferredSize(new Dimension(115, 30));
        button_ok.setPreferredSize(new Dimension(115, 30));

        button_delete.setBackground((new Color(242, 141, 82)));
        button_mod.setBackground((new Color(242, 141, 82)));
        button_delete.setForeground(new Color(115, 89, 73));
        button_mod.setForeground((new Color(115, 89, 73)));

        button_delete.setPreferredSize(new Dimension(115, 30));
        button_mod.setPreferredSize(new Dimension(115, 30));
        SpringLayout spring1 = new SpringLayout();
        p3.setLayout(spring1);



        spring1.putConstraint(SpringLayout.WEST,  button_delete, 25, SpringLayout.WEST, p3);
        spring1.putConstraint(SpringLayout.NORTH, button_delete, 10, SpringLayout.NORTH, p3);


        spring1.putConstraint(SpringLayout.WEST,  button_cr_cancel, 25, SpringLayout.WEST, p3);
        spring1.putConstraint(SpringLayout.NORTH, button_cr_cancel, 10, SpringLayout.NORTH, p3);

        spring1.putConstraint(SpringLayout.WEST,  button_mod, 160, SpringLayout.WEST, p3);
        spring1.putConstraint(SpringLayout.NORTH, button_mod, 10, SpringLayout.NORTH, p3);


        spring1.putConstraint(SpringLayout.WEST,  button_save, 160, SpringLayout.WEST, p3);
        spring1.putConstraint(SpringLayout.NORTH, button_save, 10, SpringLayout.NORTH, p3);

        spring1.putConstraint(SpringLayout.WEST,  button_ok, 160, SpringLayout.WEST, p3);
        spring1.putConstraint(SpringLayout.NORTH, button_ok, 10, SpringLayout.NORTH, p3);


        button_save.setVisible(false);
        button_cr_cancel.setVisible(false);
        button_ok.setVisible(false);

        p3.add(button_delete);
        p3.add(button_mod);
        p3.add(button_cr_cancel);
        p3.add(button_ok);
        p3.add(button_save);


        panel_right.add(p1, BorderLayout.NORTH);
        panel_right.add(p4, BorderLayout.CENTER);
        return panel_right;
    }

    private void add_table(String[][] arr){
        tableModel.addRow(new Object[]{
            arr[0][1], arr[1][1], arr[2][1], arr[3][1], arr[7][1], arr[8][1]
        });
    }

    private void void_right_panel(){
        i1.setVisible(false);
        i2.setVisible(false);
        i3.setVisible(false);
        i4.setVisible(false);
        i5.setVisible(false);
        i6.setVisible(false);
        i7.setVisible(false);
        i8.setVisible(false);
        i9.setVisible(false);
        i10.setVisible(false);
        i11.setVisible(false);
        i12.setVisible(false);
        i13.setVisible(false);
        w1.setVisible(false);
        w2.setVisible(false);
        w3.setVisible(false);
        w4.setVisible(false);
        w5.setVisible(false);
        button_cr_cancel.setVisible(false);
        button_ok.setVisible(false);

    }

    void frame_err(String infoMessage){

        JOptionPane optionPane = new JOptionPane(new JLabel(infoMessage,JLabel.CENTER));
        JDialog dialog = optionPane.createDialog("");
        dialog.setModal(true);
        dialog.setVisible(true);

    }

    private void changeLanguage(Locale locale){
        locale_chosse = locale;
        resource = ResourceBundle.getBundle("resources.language", locale_chosse);
        if(locale_chosse == locale_gr){
            i13.setFont(font_gr21);
            i7.setFont(font_gr21);
            i12.setFont(font_gr21);
            i6.setFont(font_gr21);
            i11.setFont(font_gr21);
            i5.setFont(font_gr21);
            i10.setFont(font_gr21);
            i4.setFont(font_gr21);
            i9.setFont(font_gr21);
            i3.setFont(font_gr21);
            i8.setFont(font_gr21);
            i2.setFont(font_gr21);
            i1.setFont(font_gr2);

            button_delete.setFont(font_gr1);
            button_mod.setFont(font_gr1);

            button_cr_cancel.setFont(font_gr1);
            button_ok.setFont(font_gr1);
            button_save.setFont(font_gr1);
        } else {
            i13.setFont(font21);
            i7.setFont(font21);
            i12.setFont(font21);
            i6.setFont(font21);
            i11.setFont(font21);
            i5.setFont(font21);
            i10.setFont(font21);
            i4.setFont(font21);
            i9.setFont(font21);
            i3.setFont(font21);
            i8.setFont(font21);
            i2.setFont(font21);
            i1.setFont(font2);

            button_delete.setFont(font1);
            button_mod.setFont(font1);

            button_cr_cancel.setFont(font1);
            button_ok.setFont(font1);
            button_save.setFont(font1);

        }

        resource = ResourceBundle.getBundle("resources.language", locale_chosse);

        info.setText(resource.getString("info_text"));

        menu.setText(resource.getString("resources.language"));
        info_login.setText(resource.getString("user") + ":" + login);
        button_exit .setText(resource.getString("exit"));
        info.setText(resource.getString("info_text"));
        i1.setText(resource.getString("caps_name"));
        i2.setText(resource.getString("name"));
        i3.setText(resource.getString("value"));
        i4.setText(resource.getString("type_id"));
        i5.setText(resource.getString("planet"));
        i6.setText(resource.getString("date"));
        i7.setText(resource.getString("owner"));
        button_save.setText(resource.getString("save"));

        button_add_if.setText(resource.getString("if_min"));
        button_ok.setText(resource.getString("ok"));
        button_ok2.setText(resource.getString("ok"));
        button_cr_cancel.setText(resource.getString("cancel"));

        button_delete.setText(resource.getString("delete"));
        button_mod.setText(resource.getString("modif"));

        button_create.setText(resource.getString("create"));
        label.setText(resource.getString("connect"));

    }


}
package server;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

public class ReaderCommand {
    private static ConcurrentSkipListMap<String, String> mapArg = new ConcurrentSkipListMap<>();
    //String[] comm = null;
    public ReaderCommand() {
    }

    static ConcurrentSkipListMap<String, String> readingCommand() {
        mapArg = new ConcurrentSkipListMap<>();
        Scanner in = new Scanner(System.in);
        int k = 0;
        String commandLine = null;
        String att = "";
        String s = "0";




        /**
         * обработка ctrl+d
         */
            try {
                commandLine = in.nextLine();
            } catch (NoSuchElementException ex) {
                att = "Завершен поток";
            }
            if (!att.equals("Завершен поток")) {
                commandLine = commandLine.trim();

                String re1 = Pattern.quote(" {");
                String re2 = Pattern.quote("}");
                String[] command_args = new String[2];

                try {
                    if (commandLine.contains(" ")) {
                        command_args = commandLine.split(" ", 2);
                        if (!command_args[0].equals("info_about_id") && !command_args[0].equals("sign_in") && !command_args[0].equals("register") && !command_args[0].equals("info")&& !command_args[0].equals("change_pass") && !command_args[0].equals("show") && !command_args[0].equals("exit") && !command_args[0].equals("help") && !command_args[0].equals("add_if_min") && !command_args[0].equals("remove") && !command_args[0].equals("remove_greater_key") && !command_args[0].equals("insert") && !command_args[0].equals("import") && !command_args[0].equals("save") && !command_args[0].equals("load") && !command_args[0].equals("test")) {
                            throw new ArgumentFormatException("Input correct command");
                        }
                        command_args[1] = command_args[1].replaceAll(re1, ",");
                        command_args[1] = command_args[1].replaceAll(re2, ",");

                    } else {
                        command_args[0] = commandLine;
                        command_args[1] = "";
                        if (command_args[0].equals("info") || command_args[0].equals("register") || command_args[0].equals("sign_in") || command_args[0].equals("test") || command_args[0].equals("show") || command_args[0].equals("exit")|| command_args[0].equals("change_pass") || command_args[0].equals("help") || command_args[0].equals("save") || command_args[0].equals("load")) {
                            command_args[1] = Integer.toString(0);
                        } else {
                            throw new ArgumentFormatException("Неверный формат комманды");
                        }
                    }
                } catch (ArgumentFormatException e) {
                    System.out.println(e.getExc());
                }
                if (command_args[1].equals("0")) {
                    mapArg.put("command", command_args[0]);
                    if(command_args[0].equals("change_pass")){
                        System.out.println("Введите новый пароль: "+'\n');
                        String pass = in.nextLine();
                        mapArg.put("pass", pass);
                    }
                } else if (command_args[0].startsWith("remove")) {
                    s = command_args[1];
                    mapArg.put("command", command_args[0]);
                } else {
                    //command_args[0] = command_args[0].trim();
                    if (command_args[0].equals("insert")) {
                        s = command_args[1].substring(0, command_args[1].indexOf(","));
                        //System.out.println(s);
                    }
                    try {
                        mapArg = parseObj(command_args[1]);
                    } catch (NullPointerException e) {
                    }
                    mapArg.put("command", command_args[0]);
                    //System.out.println(mapArg.get("key"));
                }
                if (!s.equals("0")) {
                    mapArg.put("key", s);
                }

                if (att.equals("Заврешен поток")) {
                    System.out.println("\u001b[31m" + att + "\u001b[0m");
                    System.exit(0);
                }
            } else {
                System.out.println(att);
                System.exit(0);
            }
            return mapArg;
    }

    private static ConcurrentSkipListMap<String, String> parseObj(String arg){
        ConcurrentSkipListMap<String, String> temp = new ConcurrentSkipListMap<>();
        String[] baseArg;
        try {
            baseArg = baseObjectParsing(arg);
            temp.put("nameOfObject", baseArg[0]);
            temp.put("valueOfObject", baseArg[1]);
            temp.put("idOfObject", baseArg[2]);
            if(Integer.parseInt(temp.get("idOfObject")) != 4){
                if (arg.contains("\"type\":")){
                    temp.put("typeOfPlanet", arg.substring(arg.indexOf("\"type\":") + 7, arg.indexOf(",", arg.indexOf("\"type\":") + 7)));
                    //System.out.println(Integer.parseInt(temp.get("idOfObject")));
                } else throw new ArgumentFormatException("Неверный формат");
                if(Integer.parseInt(temp.get("idOfObject")) != 2){
                    //System.out.println("kjkjkj");
                    String s = arg.substring(arg.indexOf("\"idOfPlanet\":")+13, arg.indexOf(",", arg.indexOf("\"idOfPlanet\":")+13));
                    arg = arg.substring(arg.indexOf("\"planet\":") + 9, arg.indexOf(s)+s.length());
                    baseArg = baseObjectParsing(arg);
                    temp.put("nameOfPlanet", baseArg[0]);
                    temp.put("valueOfPlanet", baseArg[1]);
                    temp.put("idOfPlanet", s);
                }
            }
        }catch (ArgumentFormatException e){
            System.out.println(e.getExc());
        }
        return temp;
    }

    private static String[] baseObjectParsing( String arg){
        String[] temp = new String[3];
        try {
            if (arg.contains("\"name\":")) {
                temp[0] = arg.substring(arg.indexOf("\"name\":") + 7, arg.indexOf(",", arg.indexOf("\"name\":") + 7));
            } else throw new ArgumentFormatException("Неверный формат объекта");
            if (arg.contains("\"value\":")) {
                temp[1] = arg.substring(arg.indexOf("\"value\":") + 8, arg.indexOf(",", arg.indexOf("\"value\":") + 8));
            } else throw new ArgumentFormatException("Неверный формат");
            if (arg.contains("\"id\":")) {
                temp[2] = arg.substring(arg.indexOf("\"id\":") + 5, arg.indexOf(",", arg.indexOf("\"id\":") + 5));
            }
        } catch (ArgumentFormatException e){
            System.out.println(e.getExc());
        }
        return temp;
    }

    /*private static String[] checkingCommand(String s){
        s = s.trim();
        String re = Pattern.quote(" {");
        String[] command = null;
        if(s.contains(" ")){
            command = s.split(" ", 3);
            if(command[1].contains("{")){
                command = s.split(re, 2);
                command[1] = "{" + command[1];
                command[2] = null;
            }
        } else {
            command = s.split(" ", 2);
        }
        return command;
    } */
}

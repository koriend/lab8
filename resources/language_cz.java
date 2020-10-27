package resources;

import java.util.ListResourceBundle;

public class language_cz extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"title_main", "Povolení"},
            {"title_login", "Přihlaste se"},
            {"title_reg", "Registrace"},
            {"resources.language", "Jazyk"},
            {"login", "Uživatelské jméno"},
            {"pass", "Heslo"},
            {"log_in", "Přihlásit se"},
            {"reg", "Registrace"},
            {"wrong_email", "Неверный почтовый адрес"},
            {"wrong_login", "Пользователя с данным логином не существует"},
            {"wrong_pass", "Неверный пароль"},
            {"user_exist", "Пользователь с данным логином уже существует"},
            {"server_disconnect", "Сервер недоступен"},
            {"wait", "Ожидание..."},
            {"send", "Odeslání"},
            {"cancel", "Zrušit"},
            {"port", "Порт:"},
            {"email", "Pošta"},
            {"welcome", "Vítejte na stránkách"},
            {"att", "Varování"},
            {"exit", "Konec"},
            {"user", "Uživatel"},
            {"info_text", "INFORMACE"},
            {"caps_name", "NÁZEV"},
            {"name", "Název: "},
            {"value", "Размер: "},
            {"planet", "Velikost: "},
            {"type_id","Typ: "},
            {"type_machine", "Машина"},
            {"type_planet", "Планета"},
            {"type_relief", "Рельеф"},
            {"type_star", "Звезда"},
            {"date", "Čas: "},
            {"owner", "Majitel: "},
            {"delete", "ODSTRANIT"},
            {"modif", "ИЗМЕНИТЬ"},
            {"create", "ZMĚNIT"},
            {"ok", "ZALOŽIT"},
            {"if_min", "Přidat(min)"},
            {"save", "ZACHOVAT"},
            {"info_col", "Typ položky v kolekci: Stroj, Planeta, Reliéf, Hvězda"},
            {"size", "Velikost: "},
            {"connect", "Souviset"},
            {"welcomen", "PŘIHLÁSIT NEBO ZADAT"}
    };
}

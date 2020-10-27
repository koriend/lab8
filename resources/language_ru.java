package resources;

import java.util.ListResourceBundle;

public class language_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"title_main", "Авторизация"},
            {"title_login", "Войти в систему"},
            {"title_reg", "Регистрация"},
            {"resources.language", "Язык"},
            {"login", "Логин      "},
            {"pass", "Пароль      "},
            {"log_in", "ВХОД"},
            {"reg", "РЕГИСТРАЦИЯ"},
            {"wrong_email", "Неверный почтовый адрес"},
            {"wrong_login", "Пользователя с данным логином не существует"},
            {"wrong_pass", "Неверный пароль"},
            {"user_exist", "Пользователь с данным логином уже существует"},
            {"server_disconnect", "Сервер недоступен"},
            {"wait", "Ожидание..."},
            {"send", "ОТПРАВИТЬ"},
            {"cancel", "ОТМЕНИТЬ"},
            {"port", "Порт:"},
            {"email", "Почта    "},
            {"welcome", "Какой-то текст"},
            {"att", "Предупреждение"},
            {"exit", "Выход"},
            {"user", "Логин"},
            {"info_text", "ИНФОРМАЦИЯ"},
            {"caps_name", "ИМЯ"},
            {"name", "Имя: "},
            {"value", "Размер: "},
            {"planet", "Планета: "},
            {"type_id","Тип: "},
            {"type_machine", "Машина"},
            {"type_planet", "Планета"},
            {"type_relief", "Рельеф"},
            {"type_star", "Звезда"},
            {"date", "Время: "},
            {"owner", "Владелец: "},
            {"delete", "УДАЛИТЬ"},
            {"modif", "ИЗМЕНИТЬ"},
            {"create", "Создать"},
            {"ok", "СОЗДАТЬ"},
            {"if_min", "Добавить(min)"},
            {"save", "СОХРАНИТЬ"},
            {"info_col", "Тип элементов в коллекции: Machine, Planet, Relief, Star"},
            {"size", "Размер: "},
            {"connect", "Conected"},
            {"welcomen", "ВОЙДИТЕ ИЛИ ЗАРЕГИСТРИРУЙТЕСЬ"}
    };
}

package resources;

import java.util.ListResourceBundle;

public class language_es_HN  extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"title_main", "Autorización"},
            {"title_login", "Iniciar sesión"},
            {"title_reg", "Registro"},
            {"resources.language", "Язык"},
            {"login", "Usuario      "},
            {"pass", "Contraseña    "},
            {"log_in", "ENTRADA"},
            {"reg", "INSCRIPCION"},
            {"wrong_email", "Неверный почтовый адрес"},
            {"wrong_login", "Пользователя с данным логином не существует"},
            {"wrong_pass", "Неверный пароль"},
            {"user_exist", "Пользователь с данным логином уже существует"},
            {"server_disconnect", "Сервер недоступен"},
            {"wait", "Ожидание..."},
            {"send", "ENVIAR"},
            {"cancel", "CANCELAR"},
            {"port", "Порт:"},
            {"email", "Correo    "},
            {"welcome", "Algun texto"},
            {"att", "Texto"},
            {"exit", "Salir"},
            {"user", "Usuario"},
            {"info_text", "INFORMACION"},
            {"caps_name", "Nombre"},
            {"name", "Nombre: "},
            {"value", "Tamaño: "},
            {"planet", "Planeta: "},
            {"type_id","Tipo: "},
            {"type_machine", "Машина"},
            {"type_planet", "Планета"},
            {"type_relief", "Рельеф"},
            {"type_star", "Звезда"},
            {"date", "Tiempo: "},
            {"owner", "Dueño: "},
            {"delete", "QUITAR"},
            {"modif", "CAMBIAR"},
            {"create", "Crear"},
            {"ok", "CREAR"},
            {"if_min", "Añadir(min)"},
            {"save", "AHORRA"},
            {"info_col", "Tipo de elementos de la colección:Maquina, Planeta, Alivio, Estrella"},
            {"size", "Estrella: "},
            {"connect", "Conectado"},
            {"welcomen", "INICIAR SESIÓN O REGISTRARSE"}
    };
}

package resources;


import java.util.ListResourceBundle;

public class language_gr extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"title_main", "Εξουσιοδότηση"},
            {"title_login", "Σύνδεση"},
            {"title_reg", "Εγγραφή"},
            {"resources.language", "Γλώσσα"},
            {"login", "Χρήστης      "},
            {"pass", "Κωδικός πρόσβασης    "},
            {"log_in", "ΕΙΣΟΔΟΣ"},
            {"reg", "ΕΓΓΡΑΦΗ"},
            {"wrong_email", "Неверный почтовый адрес"},
            {"wrong_login", "Пользователя с данным логином не существует"},
            {"wrong_pass", "Неверный пароль"},
            {"user_exist", "Пользователь с данным логином уже существует"},
            {"server_disconnect", "Сервер недоступен"},
            {"wait", "Ожидание..."},
            {"send", "ΑΠΟΣΤΟΛΗ"},
            {"cancel", "ΑΚΥΡΩΣΗ"},
            {"port", "Порт:"},
            {"email", "Tαχυδρο    "},
            {"welcome", "Κάποιο κείμενο"},
            {"att", "Κείμενο"},
            {"exit", "Έξοδος"},
            {"user", "Χρήστης"},
            {"info_text", "ΠΛΗΡΟΦΟΡΙΕΣ"},
            {"caps_name", "Όνομα"},
            {"name", "Όνομα: "},
            {"value", "Μέγεθος: "},
            {"planet", "Πλανήτης: "},
            {"type_id","Πληκ: "},
            {"type_machine", "Машина"},
            {"type_planet", "Планета"},
            {"type_relief", "Рельеф"},
            {"type_star", "Звезда"},
            {"date", "Ώρα: "},
            {"owner", "Ιδιοκτ: "},
            {"delete", "ΑΦΑΙΡΕΣΤΕ"},
            {"modif", "CAMBIAR"},
            {"create", "Δημιουργία"},
            {"ok", "Δημιουργία"},
            {"if_min", "Προσθέστε(min)"},
            {"save", "SAVE"},
            {"info_col", "Είδος αντικειμένων στη συλλογή: Μηχανή, Πλανήτης, Ανακούφιση, Αστέρι"},
            {"size", "Αστέρι: "},
            {"connect", "Συνδεδεμένο"},
            {"welcomen", "ΕΙΣΟΔΟΣ Ή ΕΓΓΡΑΦΗ"}
    };
}

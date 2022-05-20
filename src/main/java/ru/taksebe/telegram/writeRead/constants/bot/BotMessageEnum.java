package ru.taksebe.telegram.writeRead.constants.bot;

/**
 * Текстовые сообщения, посылаемые ботом
 */

public enum BotMessageEnum {

    //ответы на команды с клавиатуры
    HELP_MESSAGE("\uD83D\uDC4B Привет, я бот ЗагрузкаИОтправкаМатериалов, и я помогу Вам загрузить материалы и отправить их " +
            "необходимым пользователям.\n\n" +
            "❗ *Что Вы можете сделать:*\n" +
            "✅ Добавить пользователей или менеджеров;\n" +
            "✅ Загрузить материалы и настройки рассылки файлов;\n" +
            "✅ Рассылать материалы пользователям,в группы,в каналы по расписанию или сразу;\n" +
            "По всем вопросам разработки этого бота обращайтесь к моему создателю @tarasovIY\n\n" +
            "Удачи!\n\n" +
            "Воспользуйтесь клавиатурой, чтобы начать работу\uD83D\uDC47"),
    HELP_MESSAGE_EMPTY(""),
    CHOOSE_DICTIONARY_MESSAGE("Выберите словарь\uD83D\uDC47 "),

    UPLOAD_DICTIONARY_MESSAGE("Добавьте файл, соответствующий шаблону. Вы можете сделать это в любой момент"),
    NON_COMMAND_MESSAGE("Пожалуйста, воспользуйтесь клавиатурой\uD83D\uDC47"),

    //результаты загрузки словаря
    //SUCCESS_UPLOAD_MESSAGE("\uD83D\uDC4D Словарь успешно загружен"),
    SUCCESS_UPLOAD_MESSAGE("\uD83D\uDC4D Файл успешно загружен"), // эмодзи код Большой палец вверх

    EXCEPTION_TELEGRAM_API_MESSAGE("Ошибка при попытку получить файл из API Telegram"),
    EXCEPTION_TOO_LARGE_DICTIONARY_MESSAGE("В словаре больше 1 000 слов. Едва ли такой большой набор словарных " +
            "слов действительно нужен"),
    EXCEPTION_BAD_FILE_MESSAGE("Файл не может быть обработан. Вы шлёте мне что-то не то, балуетесь, наверное"),

    //ошибки загрузки файлов
    EXCEPTION_FILE_NOT_FOUND_IN_SAMPLE("\uD83D\uDED1 Файл не загружен! Имя загружаемого файла не найдено в образцах."), // эмодзи код знак стоп
    EXCEPTION_NOT_RIGHTS_SAVE_FILE("\uD83D\uDED1 У вас нет права на запись этого файла! Обратитесь к администратору."), // эмодзи код знак стоп

    // ошибки доступа к главной клавиатуре
    EXCEPTION_NOT_RIGHTS_UPLOAD_MATERIALS_BUTTON("\uD83D\uDED1 У вас нет права на нажатие этой кнопки UPLOAD_MATERIALS_BUTTON! Обратитесь к администратору."), // эмодзи код знак стоп
    EXCEPTION_NOT_RIGHTS_UPLOAD_MATERIALS_WITH_FILES_BUTTON("\uD83D\uDED1 У вас нет права на выполнение функционала этой кнопки! Обратитесь к администратору."), // эмодзи код знак стоп
    EXCEPTION_NOT_RIGHTS_UPLOAD_FILES_BUTTON("\uD83D\uDED1 У вас нет права на нажатие этой кнопки UPLOAD_FILES_BUTTON! Обратитесь к администратору."), // эмодзи код знак стоп

    //ошибки доступа к инлайн клавиатуре
    EXCEPTION_NOT_RIGHTS_TEMPLATE_NEW_BUTTON("\uD83D\uDED1 У вас нет права на нажатие этой кнопки TEMPLATE_NEW! Обратитесь к администратору."), // эмодзи код знак стоп

    //ошибки при обработке callback-ов
    EXCEPTION_BAD_BUTTON_NAME_MESSAGE("Неверное значение кнопки. Крайне странно. Попробуйте позже"),
    EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE("Словарь не найден"),
    EXCEPTION_DICTIONARY_WTF_MESSAGE("Нежиданная ошибка при попытке получить словарь. Сам в шоке"),
    EXCEPTION_TASKS_WTF_MESSAGE("Нежиданная ошибка при попытке получить задания. Сам в шоке"),
    EXCEPTION_TEMPLATE_WTF_MESSAGE("Нежиданная ошибка при попытке получить шаблон. Сам в шоке"),

    //прочие ошибки
    EXCEPTION_ILLEGAL_MESSAGE("Нет, к такому меня не готовили! Я работаю или с текстом, или с файлом"),
    EXCEPTION_WHAT_THE_FUCK("Что-то пошло не так. Обратитесь к программисту");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
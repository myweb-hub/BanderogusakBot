package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.*;


public class Main extends TelegramLongPollingBot {

    private Map<Long, Integer> levels = new HashMap<>(); //many users, so storage of level for each user


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        return "Banderogusakovybot";
    }
    @Override
    public String getBotToken() {
        return "6358278218:AAH98HyunMH7mti723kBad3S-g821nMwBiE";
    }
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")){
            //send image level 1
            sendImage("level-1", chatId);

            //send message
            SendMessage message = createMessage("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
                    "\n" +
                    "Ти отримуєш гусака №71\n" +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔\uFE0Fвиконувати завдання\n" +
                    "✔\uFE0Fпереходити на наступні рівні\n" +
                    "✔\uFE0Fзаробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень");
            message.setChatId(chatId);

            List<String> buttons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет)"
            );

            buttons =getRandom3(buttons);

            attachButtons(message, Map.of(
                   buttons.get(0), "lvl1_task",
                   buttons.get(1), "lvl1_task",
                   buttons.get(2), "lvl1_task"

            ));
           sendApiMethodAsync(message);

        }
        if (update.hasCallbackQuery()){

            if(update.getCallbackQuery().getData().equals("lvl1_task") && getLevel(chatId) == 1){
                //increase level
                setLevel(chatId, 2);

                //send image 2
                sendImage("level-2", chatId);

                //send message 2
                SendMessage message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)"
                );

                buttons = getRandom3(buttons);

                //gets random 3 buttons from 5 options
                attachButtons(message, Map.of(
                        buttons.get(0), "lvl2_task",
                        buttons.get(1), "lvl2_task",
                        buttons.get(2), "lvl2_task"

                ));
                sendApiMethodAsync(message);
            }

            if(update.getCallbackQuery().getData().equals("lvl2_task")&& getLevel(chatId) == 2){
                setLevel(chatId, 3);

                sendImage("level-3", chatId);

                SendMessage message = createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );

                buttons = getRandom3(buttons);

                attachButtons(message, Map.of(
                        buttons.get(0), "lvl3_task",
                        buttons.get(1), "lvl3_task",
                        buttons.get(2), "lvl3_task"

                ));
                sendApiMethodAsync(message);
            }

            if(update.getCallbackQuery().getData().equals("lvl3_task") && getLevel(chatId) == 3){
                setLevel(chatId, 4);

                sendImage("level-4", chatId);

                SendMessage message = createMessage("*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Купити Джавелін (50 монет)","lvl4_task"
                ));
                sendApiMethodAsync(message);
            }

            if(update.getCallbackQuery().getData().equals("lvl4_task") && getLevel(chatId) == 4){
                setLevel(chatId, 5);

                SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");
                message.setChatId(chatId);

                sendImage("final", chatId);

                sendApiMethodAsync(message);
            }
        }
    }
    //method is a number of functions, combined
    public Long getChatId(Update update) {//on input we receive an update, on output we receive a chat ID.
        //Long: type of Data in Java.

        if (update.hasMessage()) { //get ID if a message is received
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) { //get ID if a button is pressed
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }

    //map saves the name of the button and it's meaning
    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));

        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }


    public void  sendImage(String name, Long chatId){
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    } // return levels checks if user has a level, then returns it. if not, default level 1.

    public void setLevel(Long chatId, int level){
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> variants){
        ArrayList<String> copy = new ArrayList<>(variants);
        Collections.shuffle(copy);
        return copy.subList(0, 3);
    }
}
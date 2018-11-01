package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Basillio on 04.12.2017.
 */
public class BotClient extends Client {
    public class BotSocketThread extends Client.SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (!message.contains(": ")){
                return;
            }
            String[] splitMessage = message.split(": ");
            String name = splitMessage[0];
            String text = splitMessage[1];
            switch (text) {
                case "дата" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("d.MM.YYYY")));
                    break;
                case "день" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("d")));
                    break;
                case "месяц" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("MMMM")));
                    break;
                case "год" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("YYYY")));
                    break;
                case "время" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("H:mm:ss")));
                    break;
                case "час" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("H")));
                    break;
                case "минуты" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("m")));
                    break;
                case "секунды" :
                    sendTextMessage(String.format("Информация для %s: %s", name, dateFormat("s")));
                    break;
            }
        }

        private String dateFormat(String dateFormat){
            Calendar date = Calendar.getInstance();
            String dateToString = new SimpleDateFormat(dateFormat).format(date.getTime());
            return dateToString;
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole(){
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }



    public static void main(String[] args){
        BotClient botClient = new BotClient();
        botClient.run();
    }
}

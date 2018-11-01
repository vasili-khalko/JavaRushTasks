package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Basillio on 27.11.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("введите адрес сервера:");
        String serverAddress = ConsoleHelper.readString();
        /*String regExp = "(?:(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.{3}(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5]))|(localhost)";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(serverAddress);
        if (matcher.matches()){
            return serverAddress;
        } else {
            return getServerAddress();
        }*/
        return serverAddress;
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            clientConnected = false;
            ConsoleHelper.writeMessage("сбой отправки сообщения");
        }

    }

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоеденился к чату");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                try {
                message = connection.receive();

                    switch (message.getType()) {
                        case NAME_REQUEST:
                            String name = getUserName();
                            Message messageName = new Message(MessageType.USER_NAME, name);
                            connection.send(messageName);
                            break;

                        case NAME_ACCEPTED:
                            notifyConnectionStatusChanged(true);
                            return;

                        default:
                            throw new IOException("Unexpected MessageType");
                    }
                } catch (NullPointerException e) {
                    throw new IOException("Unexpected MessageType");
                }
            }

        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            Message message;
            while (true) {
                try {
                message = connection.receive();

                    switch (message.getType()) {
                        case TEXT:
                            processIncomingMessage(message.getData());
                            break;

                        case USER_ADDED:
                            informAboutAddingNewUser(message.getData());
                            break;

                        case USER_REMOVED:
                            informAboutDeletingNewUser(message.getData());
                            break;

                        default:
                            throw new IOException("Unexpected MessageType");
                    }
                } catch (NullPointerException e) {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        public void run() {
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try {
                Socket socket = new Socket(serverAddress, serverPort);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();

            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }

    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
                synchronized (Client.this) {
                    this.wait();
                }
        } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
                return;
                }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        String message;
        while (clientConnected){
            if ((message = ConsoleHelper.readString()).equals("exit")) {
                break;
            }
            if (shouldSendTextFromConsole()){
                sendTextMessage(message);
            }
        }
    }

    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }
}

package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Basillio on 17.11.2017.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args){
        ConsoleHelper.writeMessage("Введите порт сервера");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())){
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true){
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{

            boolean accepted = false;
            String name = null;
            while (!accepted) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && connectionMap.get(name) == null) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        accepted = true;
                    }
                }
            }
            return name;

        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            connectionMap.remove(userName);
            for (String name: connectionMap.keySet()) {
                connection.send(new Message(MessageType.USER_ADDED, name));
            }
            connectionMap.put(userName, connection);
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true){
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT) {
                    String text = userName.concat(": ").concat(receivedMessage.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage("Ошибка");
                }
            }
        }

        public void run(){
            ConsoleHelper.writeMessage("установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)){
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }
            ConsoleHelper.writeMessage("соединение с: " +socket.getRemoteSocketAddress()+" закрыто");
        }

    }



    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> entry: connectionMap.entrySet()
             ) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не удалось отправить пользователю: " + entry.getKey());
            }
        }
    }


}

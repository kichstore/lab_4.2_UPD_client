import java.io.*;
import java.net.*;

public class UDPClient {
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            double x;
            while (true) {
                System.out.print("Введите x: ");
                try {
                    x = Double.parseDouble(consoleReader.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("-------------------\nНекорректный ввод\n-------------------");
                }
            }

            double y;
            while (true) {
                System.out.print("Введите y: ");
                try {
                    y = Double.parseDouble(consoleReader.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("-------------------\nНекорректный ввод\n-------------------");
                }
            }

            double z;
            while (true) {
                System.out.print("Введите z: ");
                try {
                    z = Double.parseDouble(consoleReader.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("-------------------\nНекорректный ввод\n-------------------");
                }
            }

            // Отправляем данные серверу
            String inputData = x + " " + y + " " + z;
            byte[] sendData = inputData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String result = new String(receivePacket.getData()).trim();
            System.out.println("Результат: " + result);

            // Закрываем соединение
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
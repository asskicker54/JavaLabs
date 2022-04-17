package client.model;

import data.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Net {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final Data data;
    private Socket socket;

    public Net(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new Data();
    }
    public void stop() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    public void send(double a, double b, String operator) {
        data.setA(a);
        data.setB(b);
        data.setOperator(operator);
        try {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double get() throws ClassNotFoundException, IOException {
        return (Double)inputStream.readObject();
    }

    public void clear() {
        try {
            outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

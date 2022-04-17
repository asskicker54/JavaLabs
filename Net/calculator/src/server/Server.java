package server;

import data.Data;
import server.calculatror.Calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!serverSocket.isClosed()) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new Thread(new CalculatorThread(socket)).start();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CalculatorThread implements Runnable {

    private Data data;
    private double result;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final Socket socket;
    private boolean isClosed = false;

    @Override
    public void run() {
        while (!isClosed) {
            read();
            write();
        }
        stop();
    }

    public CalculatorThread(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            data = (Data) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        String operator = data.getOperator();
        System.out.print(data.getA() + " " + data.getB() + " " + data.getOperator() + "\n");
        switch (operator) {
            case "/" -> result = Calculator.division(data.getA(), data.getB());
            case "*" -> result = Calculator.multiply(data.getA(), data.getB());
            case "+" -> result = Calculator.add(data.getA(), data.getB());
            case "-" -> result = Calculator.minus(data.getA(), data.getB());
            case "close" -> isClosed = true;
            default -> result = data.getA();
        }
    }

    public void write() {
        System.out.print(result);
        try {
            out.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

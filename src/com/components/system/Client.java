package com.components.system;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String ip;
    private final int port;
    private Socket socket;
    public BufferedReader in;
    public PrintWriter out;


    public boolean isRun = true;


    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;

    }
    public void connectToServer(Object obj){
        try {

            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            Thread thread = new Thread((Runnable) obj);
            thread.start();

        } catch (IOException e) {
            this.isRun = false;
        }
    }





    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

}

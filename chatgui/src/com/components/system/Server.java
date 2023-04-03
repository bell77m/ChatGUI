package com.components.system;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private  int port;
    private ArrayList<ConnectionHandler> connection;

    ServerSocket serverSocket;
    boolean sucess;
    private ExecutorService pool;

    private boolean online;

    public boolean isOnline() {
        return online;
    }

    public Server(int port){
        this.port = port;
        connection = new ArrayList<>();
        sucess= false;

    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(2223);
            pool = Executors.newCachedThreadPool();
            this.online = !serverSocket.isClosed();
            while (!sucess) {
                Socket client = serverSocket.accept();



                ConnectionHandler handler = new ConnectionHandler(client);
                connection.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        sucess = true;
        try{
            if(!serverSocket.isClosed()){
                serverSocket.close();
                for(ConnectionHandler channel:connection){
                    channel.close();
                }
            }
        }catch (IOException e){

        }
    }

    public void broadcast(String message){
        for(ConnectionHandler channel: connection){
            if(channel != null){
                channel.sendMessage(message);
            }
        }
    }
    class ConnectionHandler implements Runnable{
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String username;
        public ConnectionHandler(Socket client){
            this.client = client;
        }
        @Override
        public void run() {
            try{
                out = new PrintWriter(client.getOutputStream(), true );
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Please enter your username: ");
                username = in.readLine();
                System.out.println(username + " is connected!");
                broadcast(username + " join the chat!");
                String message;
                while ((message = in.readLine()) != null){

                    if(message.equals("!exit")){
                            broadcast(username+" left the chat!");
                            close();


                    }else{
                        broadcast(String.format("%s : %s", username, message));
                    }
                }
            }catch (IOException e){
                broadcast(String.format("runServer was terminated"));
                close();
            }
        }


        public void sendMessage(String message){
            out.println(message);
        }
        public void close(){
            try{
                in.close();
                out.close();
                if(!client.isClosed()){
                    client.close();
                }
            }catch (IOException e){
                close();
            }
        }
    }

}

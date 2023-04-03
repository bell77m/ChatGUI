import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import com.components.system.Client;



public class ChatClient extends JFrame{
    private JTextField textField1;
    private JButton sendButton;
    private JPanel panel;
    private JPanel titleArea;
    private JPanel textArea;
    private JLabel title;
    private JTextArea chatArea;
    private Client client;

    private  String  ip;
    private  int port;

    private MessageListener messageListener;





    private class MessageListener implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = client.in.readLine()) != null) {
                    chatArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(String message){
        if(!message.isEmpty()){
            if(message.equals("!exit")){
                setVisible(false);
            }
            client.out.println(message);
            textField1.setText("");
        }
    }




    public ChatClient(String ip, int port) {

        this.ip = ip;
        this.port = port;

        client = new Client(ip, port);
        MessageListener messageListener = new MessageListener();
        client.connectToServer(messageListener);
        titleArea.setPreferredSize(new Dimension(95, 50));
        title.setText("Chat GUI");
        title.setForeground(new Color(255, 255, 255));
        title.setPreferredSize(new Dimension(75, 20));

        chatArea.setEditable(false);

        textArea.setPreferredSize(new Dimension(95, 50));
        textField1.setPreferredSize(new Dimension(75, 35));
        sendButton.setPreferredSize(new Dimension(75, 35));


        setContentPane(panel);

        validate();
        repaint();
        setResizable(false);

        setSize(533, 571);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sendMessage(textField1.getText());
                System.out.println(textField1.getText());
            }
        });


    }





    public static void main(String[] args) {


        Connect connect = new Connect();
        connect.connectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if((!connect.getTextField1().getText().isBlank() && !connect.getTextField2().getText().isBlank()) && connect.getTextField2().getText().matches("[0-9]+")){
                    connect.enable = true;
                    connect.setIp(connect.getTextField1().getText());
                    connect.setPort(Integer.parseInt(connect.getTextField2().getText()));
                    ChatClient chatClient = new ChatClient(connect.getTextField1().getText(), Integer.parseInt(connect.getTextField2().getText()));

                    connect.dispose();

                }else{
                    JOptionPane.showMessageDialog(null, "Something went wrong", "Waring", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



    }


}

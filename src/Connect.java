import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect extends JFrame{
    private JTextField textField1;
    private JPanel panel1;
    public JButton connectButton;
    private JTextField textField2;
    private String ip;
    private int port;
    public boolean enable = false;
    ChatClient chatClient;



    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }



    Connect(){
        setTitle("Connect to server");
        panel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        setContentPane(panel1);
        validate();
        repaint();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}

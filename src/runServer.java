import com.components.system.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class runServer extends JFrame{
    private JButton run;
    private JTextField textField1;
    private JTextArea textArea1;
    private JLabel myIp;
    private JPanel panel;
    private JLabel status;
    public JButton reloadButton;
    private boolean boolStatus = false;
    private int port;

    public void setBoolStatus(boolean boolStatus) {
        this.boolStatus = boolStatus;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean getStatus() {
        return boolStatus;
    }

    runServer(){

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        myIp.setText("IP: localhost");
        setContentPane(panel);

        validate();
        repaint();
        setResizable(false);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        status.setText(String.valueOf(boolStatus));


        run.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText() != null){
                    setPort(Integer.parseInt(textField1.getText()));
                    textField1.setEnabled(false);

                }
            }
        });
    }

    public static void main(String[] args) {
        runServer runServer = new runServer();
        Server server = new Server(runServer.getPort());

        runServer.reloadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                runServer.setBoolStatus(server.isOnline());
                runServer.status.setText(String.valueOf(runServer.getStatus()));
            }
        });
        server.run();
    }
}



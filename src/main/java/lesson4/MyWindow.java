package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyWindow extends JFrame {
    private JTextArea jta;
    private JTextField jtx;
    public MyWindow() {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());
        jta = new JTextArea();
        jta.setBackground(Color.YELLOW);
        jta.setSize(100, 100);
        JScrollPane jsc = new JScrollPane(jta);
        add(jsc, BorderLayout.CENTER);
        JPanel pn = new JPanel();
        pn.setLayout(new FlowLayout());
        jtx = new JTextField(20);
        pn.add(jtx);
        JButton jbt = new JButton();
        jbt.setSize(20,20);
        jbt.setText("Отправить");
        jbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMess();
            }
        });
        pn.add(jbt);
        add(pn, BorderLayout.SOUTH);
        jtx.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n') {
                    sendMess();
                }
            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
    }

    public void sendMess(){
        jta.append(jtx.getText() + '\n');
        jtx.setText("");
        jtx.grabFocus();
    }
}

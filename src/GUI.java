import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GUI implements ActionListener{
    JFrame frame;
    JTextArea plainTextField;
    JTextField urlField;
    JTextField usernameField;
    JTextField faketextField;
    JTextField keyLabel;
    JButton generateKey;
    JButton save;
    JButton addBtn;
    JLabel userNameLbl;
    JLabel keyLbl;
    public GUI(){
        frame = new JFrame("Cipher");
        save = new JButton("SaveAndGenerateKey");
        addBtn = new JButton("Add");
        urlField = new JTextField();
        plainTextField = new JTextArea();
        userNameLbl = new JLabel("UserName");
        keyLbl = new JLabel("Key: ");
        usernameField = new JTextField();
        faketextField = new JTextField();
        keyLabel = new JTextField();
        generateKey = new JButton("Save And Encrypt");
        plainTextField.setLineWrap(true);
        plainTextField.setWrapStyleWord(true);
        plainTextField.setBounds(10,10,480,100);
        urlField.setBounds(10,120,480,20);
        usernameField.setBounds(120,160,100,20);
        keyLabel.setBounds(120,200,100,30);
        save.setBounds(10,250,200,30);
        addBtn.setBounds(220,250,80,30);
        userNameLbl.setBounds(10,160,100,20);
        keyLbl.setBounds(10,200,100,30);
        addBtn.addActionListener(this);
        save.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(plainTextField);
        frame.add(usernameField);
        frame.add(urlField);
        frame.add(keyLabel);
        frame.add(addBtn);
        frame.add(save);
        frame.add(keyLbl);
        frame.add(userNameLbl);
        frame.setSize(520,500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save){
            if(usernameField.getText().length()==0 ){
                if(urlField.getText().length()>0){
                    KeyHandler keyHandler = new KeyHandler();
                    String key = keyHandler.createKey();
                    String cipher = new Cipher().encryptFileOnly(urlField.getText(),key);
                    keyLabel.setText(key);
                    FileReader fileReader = new FileReader();
                    try {
                        fileReader.writeToFile(urlField.getText(),cipher);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    plainTextField.setText(cipher);
                    return;
                }
                else{
                    return;
                }
            }
            KeyHandler keyHandler = new KeyHandler();
            String plainText = plainTextField.getText();
            String key = keyHandler.createKey();
            String user = usernameField.getText();
            keyLabel.setText(key);
            try {
                String cipherText = new Cipher().saveDetail(user,plainText,key,urlField.getText());
                plainTextField.setText(cipherText);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }


        }
        if(e.getSource() == addBtn){
            if(urlField.getText().length()>0){
                FileReader fileReader = new FileReader();
                plainTextField.setText(fileReader.readFile(urlField.getText()));
            }
        }
    }
}
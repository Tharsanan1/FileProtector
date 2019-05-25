import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class DisplayText implements ActionListener{
    JFrame frame;
    JTextArea plainTextField;
    JTextField usernameField;
    JTextField urlField;
    JLabel keyLabel;
    JTextField keyField;
    JButton show;
    JLabel userNameLbl;
    JLabel keyLbl;
    JButton restoreBtn;
    JButton displayBtn;
    public DisplayText(){
        frame = new JFrame("Display Data");
        displayBtn = new JButton("Display");
        restoreBtn = new JButton("Restore");
        urlField = new JTextField();
        show = new JButton("ShoWText");
        plainTextField = new JTextArea();
        usernameField = new JTextField();
        keyField = new JTextField();
        keyLabel = new JLabel();
        userNameLbl = new JLabel("UserName");
        keyLbl = new JLabel("Key: ");
        plainTextField.setBounds(10,10,480,100);
        urlField.setBounds(10,120,480,20);
        usernameField.setBounds(120,160,100,20);
        keyLabel.setBounds(120,200,100,30);
        keyField.setBounds(120,200,100,30);
        show.setBounds(10,250,100,30);
        userNameLbl.setBounds(10,160,100,20);
        keyLbl.setBounds(10,200,100,30);
        restoreBtn.setBounds(10,300,100,20);
        displayBtn.setBounds(10,330,100,20);
        restoreBtn.addActionListener(this);
        displayBtn.addActionListener(this);
        show.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(plainTextField);
        frame.add(usernameField);
        frame.add(keyLabel);
        frame.add(restoreBtn);
        frame.add(keyField);
        frame.add(keyLbl);
        frame.add(userNameLbl);
        frame.add(urlField);
        frame.add(displayBtn);
        //frame.add(generateKey);
        frame.add(show);
        frame.setLocation(521,0);
        frame.setSize(520,500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==show){
            if(keyField.getText().length()==0 || usernameField.getText().length()==0){
                if(urlField.getText().length()>0){
                    FileReader fileReader = new FileReader();
                    String text = fileReader.readFile(urlField.getText());
                    Cipher cipher = new Cipher();
                    String normalText = cipher.getDataForFile(text,keyField.getText());
                    plainTextField.setText(normalText);
                }
                return;
            }
            String data = "Database error";
            try {
                data = new Cipher().getData(usernameField.getText(),keyField.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            plainTextField.setText(data);

        }
        if(e.getSource()==restoreBtn){
            if(plainTextField.getText().length()>0){
                FileReader fileReader  =new FileReader();
                try {
                    fileReader.writeToFile(urlField.getText(),plainTextField.getText());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if(e.getSource()==displayBtn){
            if(urlField.getText().length()>0){
                FileReader fileReader = new FileReader();
                String data = fileReader.readFile(urlField.getText());
                plainTextField.setText(data);
            }
            else if(usernameField.getText().length()>0){
                try {
                    SqlHandler sqlHandler = new SqlHandler();
                    String data = sqlHandler.getData(usernameField.getText());
                    plainTextField.setText(data);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

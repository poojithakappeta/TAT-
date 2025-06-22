import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.*;

public class login extends JFrame{
   
    public void initialize(){
        
        final  Font mainFont=new Font("Segoe print",Font.BOLD,18);
    JTextField tfreg;

        JLabel lbloginform=new JLabel("LOGIN",SwingConstants.CENTER);
        lbloginform.setFont(mainFont);
        JLabel lbreg=new JLabel("REGSITRATION NO");
        lbreg.setFont(mainFont);
        tfreg=new JTextField();
        tfreg.setFont(mainFont);
        
        JPanel formPanel= new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));
        formPanel.add(lbloginform);
        formPanel.add(lbreg);
        formPanel.add(tfreg);
        
        JButton blogin=new JButton("login");
        blogin.setFont(mainFont);
        blogin.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
    String reg=tfreg.getText();
    

    user User=getAuthenticateduser(reg);


    if(User!=null){
        MainFrame mainframe=new MainFrame();
        mainframe.initialize(User);
        
        dispose();
    }
    else{
        JOptionPane.showMessageDialog(login.this,"register number invalid","try again",JOptionPane.
        ERROR_MESSAGE);
    }
}
        });
        JButton btnlogin=new JButton("login");
        JButton btnCancel=new JButton("Cancel");
btnCancel.setFont(mainFont);

btnCancel.addActionListener(new ActionListener(){
    
    public void actionPerformed(ActionEvent e){
        dispose();

    }
});
btnlogin.addActionListener(new ActionListener(){
    
    public void actionPerformed(ActionEvent e){
        dispose();

    }
});

JPanel buttonsPanel=new JPanel();
buttonsPanel.setLayout( new GridLayout(1,2,10,0));
buttonsPanel.add(btnlogin);
buttonsPanel.add(btnCancel);


        add(formPanel,BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.SOUTH);

    setTitle("LOGIN FORM");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(400,500);
    setMinimumSize(new Dimension(350,450));
    setVisible(true);
     
}
    
private user getAuthenticateduser(String reg)
{
    user User = null;
    final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
    try{
        Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

        String sql = "SELECT * FROM users WHERE reg=? ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, reg);
    
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            User =new user();
            User.reg=resultSet.getString("register no");

        }
        preparedStatement.close();
        conn.close();

    }catch(Exception e){
        System.out.println("data base connection failed");
    }
return User;

}
}

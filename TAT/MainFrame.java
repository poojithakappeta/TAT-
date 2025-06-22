import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame{
    public void initialize(user User){
         
        JPanel infoPanel=new JPanel();
        infoPanel.setLayout(new GridLayout(0,2,5,5));

infoPanel.add(new JLabel("reg"));
infoPanel.add(new JLabel(User.reg));
add(infoPanel,BorderLayout.NORTH);


        setTitle("DashBoard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

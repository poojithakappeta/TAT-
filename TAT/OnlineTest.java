import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class OnlineTest extends JFrame implements ActionListener  
{  
    JLabel l;  
    JTextField tfreg;
    JRadioButton jb[]=new JRadioButton[5];  
    JButton b1,b2,b3,b4;  
    ButtonGroup bg; 
    JPanel formPanel; 
    JLabel lbloginform;
    JLabel lbreg;

    JPanel buttonsPanel;
    int count=0,attempted=0,current=-1,x=1,y=1,now=0,flag=0; 
int a;
    OnlineTest(String s)  
    {  
        super(s);
        l=new JLabel();  
        add(l);  
        bg=new ButtonGroup();  
        for(int i=0;i<5;i++)  
        {  
            jb[i]=new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);  
        }
        b1=new JButton("Start");   
        b1.addActionListener(this); 
        add(b1); 
        welcome();
        b2=new JButton("Previous"); 
        b3=new JButton("Result"); 
        b4=new JButton("login"); 
        b2.addActionListener(this); 
        b3.addActionListener(this);  
    
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                login loginform= new login();
                loginform.initialize();  
            
                }
            }
                    );
        
        add(b2);add(b3);add(b4);
        
        l.setBounds(30,40,450,20);  
        if(current !=-1)
        {   
            jb[0].setBounds(50,80,100,20);  
            jb[1].setBounds(50,110,100,20);  
            jb[2].setBounds(50,140,100,20);  
            jb[3].setBounds(50,170,100,20);  
        }
        b1.setBounds(100,240,100,30);  
        b2.setBounds(270,240,100,30);  
        b3.setBounds(400,240,100,30);
        b4.setBounds(700,50,100,30);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250,100);  
        setVisible(true);  
        setSize(900,900);  
    }
    public void actionPerformed(ActionEvent e)  
    {  
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
            Statement stmt = con.createStatement();
            if(e.getSource()==b1 && current ==9 ) 
            {
                adduserans(); 
                JOptionPane.showMessageDialog(this,"done\n");   
            }
            else if(e.getSource()==b1)  
            {  
                if(current == -1) 
                {
                    
                    b1.setText("Next"); 
                }
                else
                    adduserans(); 
                current++; 
                setnext(); 
            }  
            else if(e.getSource()==b2 && current ==0 ) 
            {
                adduserans(); 
                JOptionPane.showMessageDialog(this,"done\n");   
            }
            else if(e.getSource()==b2)  
            {   
                current--;  
                adduserans();
                setnext();   
            }  
            else if(e.getActionCommand().equals("Result"))  
            {  
                
                check(); 
                if(flag==0)
                 a = JOptionPane.showConfirmDialog(this,"Attempted questions: "+attempted+" / 10\nYour Score: "+count+" / 10\nPercentage: "+(count*10)+" %\n");  
                else if(flag==1)
                 a = JOptionPane.showConfirmDialog(this,"Attempted questions: "+attempted+" / 10\nYour Score: "+count+" / 10\nPercentage: "+(count*10)+" %\n");     
                else
                 a = JOptionPane.showConfirmDialog(this,"Attempted questions: "+attempted+" / 10\nYour Score: "+count+" / 10\nPercentage: "+(count*10)+" %\n");     
                
            
            
                    stmt.executeUpdate("delete from ua");
                    stmt.executeUpdate("delete from stuua");
                    stmt.executeUpdate("delete from stuqao");
                    stmt.executeUpdate("delete from qao");
                    System.exit(0); 
            
            } 
        }
        catch(Exception ex)
        {
            System.out.println("actionPerformed"+ex);
        } 
    }  
    
    
    void welcome() 
    {
        l.setText("Welcome to TAT. Click start") ;
    }
    void setnext() 
    {  
        jb[4].setSelected(true);  
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
            
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
            Statement stmt = con.createStatement();
            if(current==0)  
            {    
                String sql="select * from stuqao where qno=1"; 
                ResultSet rs = stmt.executeQuery(sql);
                rs.next(); 
                String s1 =rs.getString("question"); 
                String s2 =rs.getString("option1"); 
                String s3 =rs.getString("option2"); 
                String s4 =rs.getString("option3"); 
                String s5 =rs.getString("option4"); 
                l.setText("Q.1 "+s1); 
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);
            }  
            if(current==1)  
            {  
                String sql="select * from stuqao where qno=2";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.2 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==2)  
            {  
                String sql="select * from stuqao where qno=3";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.3 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==3)  
            {  
                String sql="select * from stuqao where qno=4";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.4 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==4)  
            {  
                String sql="select * from stuqao where qno=5";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.5 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==5)  
            {  
                String sql="select * from stuqao where qno=6";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.6 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==6)  
            {  
                String sql="select * from stuqao where qno=7";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.7 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==7)  
            {  
                String sql="select * from stuqao where qno=8";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.8 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==8)  
            {  
                String sql="select * from stuqao where qno=9";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.9 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            if(current==9)  
            {  
                String sql="select * from stuqao where qno=10";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("question");
                String s2 =rs.getString("option1");
                String s3 =rs.getString("option2");
                String s4 =rs.getString("option3");
                String s5 =rs.getString("option4");
                l.setText("Q.10 "+s1);  
                jb[0].setText(s2);jb[1].setText(s3);jb[2].setText(s4);jb[3].setText(s5);  
            }  
            l.setBounds(30,40,450,20);  
            for(int i=0,j=0;i<=90;i+=30,j++)  
                jb[j].setBounds(50,80+i,200,20);  
        }   
        catch(Exception e)
        {
            System.out.println("setnext\n"+e);
        }
    }  
    void adduserans() 
    {
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
        
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = con.createStatement();
            for(int i=0;i<=3;i++)
            {
                if(jb[i].isSelected()) 
                {
                    String sql1 = "insert into stuua(qno,userans) values("+(current+1)+",'"+jb[i].getText()+"') on duplicate key update userans='"+jb[i].getText()+"'";
                    
                    stmt.executeUpdate(sql1);
                    //con.close();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("adduserans\n"+e);
        }
    }
    void check() 
    {  
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
        
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = con.createStatement();
            for(int i=1;i<=10;i++)
            {
                String sql="select userans, correctans from stuua where qno="+i+"";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                String s1 =rs.getString("userans");
                String s2 =rs.getString("correctans");
                if(!(s1.equals(""))) 
                    attempted++;
                if(s1.equals(s2)) 
                 count++;
            }
        }
        catch(Exception e)
        {
            System.out.println("check\n"+e);
        }
    }
    
    
    static void qaoDBcon() 
    {
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
            
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into qao values(1,'which of the following is preserved in execution of transaction in isolation?','atomicity','isolation','consistency','durability')");
            stmt.executeUpdate("insert into qao values(2,'which normalization form is based on transitive dependency ?','1nf','2nf','3nf','bcnf')");
            stmt.executeUpdate("insert into qao values(3,'which of the following is not the  command of DDL ?','alter','create','delete','drop')");
            stmt.executeUpdate("insert into qao values(4,'what is rows of relation known as  ?','tuples','degree','entity','columns')");
            stmt.executeUpdate("insert into qao values(5,' which of the following is known as minimal superkey?','primary key','candidate key','foreign key','none')");
            stmt.executeUpdate("insert into qao values(6,'which normal form deals with multivalued dependency ?','1nf','2nf','3nf','4nf')");
            stmt.executeUpdate("insert into qao values(7,'after whicch operation is the modify operation done ?','look_up','insert','delete','all')");
            stmt.executeUpdate("insert into qao values(8,'which normal form contains information about a single entity ?','1nf ','2nf','3nf','4nf')");
            stmt.executeUpdate("insert into qao values(9,'number of tuples of a relation known as  ?','column ','cordinality','entity','none')");
            stmt.executeUpdate("insert into qao values(10,'total view of a database is known as ?','physical view ','internal view','conceptual view','external view')");
            stmt.executeUpdate("insert into qao values(11,'what is  a table joined with itself called?','join','self join','outer join','equi join')");
            stmt.executeUpdate("insert into qao values(12,'rectangles in ER diagram represents ?','tuples','tables','attributes','entity sets')");
            stmt.executeUpdate("insert into qao values(13,'select the relational algebra operations?','union ','select','rename','all of the above')");
            stmt.executeUpdate("insert into qao values(14,'Which is AS  clause used for ?','rename','selection','join','projection')");
            stmt.executeUpdate("insert into qao values(15,'how many levels are there in architecture of database ?','2','3','4','5')");
            stmt.executeUpdate("insert into qao values(16,'A relational database developer refers to a record as?','attributes ','tuples','relational','none ')");
            stmt.executeUpdate("insert into qao values(17,'relatinal language is a  ____ language ?','high level','procedural','non procedural','data definition')");
            stmt.executeUpdate("insert into qao values(18,'select the correct foreign key constraint  ?','referencial ','entity integrity','domain integrity','none')");
            stmt.executeUpdate("insert into qao values(19,'Which command is used to remove a stored function from db?','delete','drop','erase','remove')");
            stmt.executeUpdate("insert into qao values(20,'Which command is used to change data in table?','insert','update','merge','none')");
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("qaoDBcon\n"+e);
        }
    }
    static void uaDBcon() 
    {
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
        
            Connection con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into ua values(1,'','consistency')");
            stmt.executeUpdate("insert into ua values(2,'','3nf')");
            stmt.executeUpdate("insert into ua values(3,'','delete')");
            stmt.executeUpdate("insert into ua values(4,'','tuples')");
            stmt.executeUpdate("insert into ua values(5,'','foreign key')");
            stmt.executeUpdate("insert into ua values(6,'','4nf')");
            stmt.executeUpdate("insert into ua values(7,'','look_up')");
            stmt.executeUpdate("insert into ua values(8,'','4nf')");
            stmt.executeUpdate("insert into ua values(9,'','cordinality')");
            stmt.executeUpdate("insert into ua values(10,'','conceptual view')");
            stmt.executeUpdate("insert into ua values(11,'','self join')");
            stmt.executeUpdate("insert into ua values(12,'','entity sets')");
            stmt.executeUpdate("insert into ua values(13,'','all of the above')");
            stmt.executeUpdate("insert into ua values(14,'','rename')");
            stmt.executeUpdate("insert into ua values(15,'','3')");
            stmt.executeUpdate("insert into ua values(16,'','tuples')");
            stmt.executeUpdate("insert into ua values(17,'','non procedural')");
            stmt.executeUpdate("insert into ua values(18,'','referencial ')");
            stmt.executeUpdate("insert into ua values(19,'','drop')");
            stmt.executeUpdate("insert into ua values(20,'','update')");
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("uaDBcon\n"+e);
        }
    }
    static void pickrandom()
    {
        final String DB_URL = "jdbc:mysql://localhost:3306/tat";
    final String USERNAME = "root";
    final String PASSWORD = "root";
        try
        {
            Connection con=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        
        
            Statement stmt = con.createStatement();
            int a[]=new int[21];
            int c=0;
            int p;
            for(int i=0;i<=20;i++)
                a[i]=0;
            while(c!=10)
            {
                p=1+(int)(Math.random()*20); 
                if(a[p]==0)
                {
                    a[p]=1; 
                    c++; 
                }
            }
            c=0;
            for(int i=1;i<=20;i++)
            {
                if(a[i]==1) 
                {
                    c++;
                    String sql="select * from qao where qno="+i+"";
                    ResultSet randomrs = stmt.executeQuery(sql);
                    randomrs.next();
                    String s1 =randomrs.getString("question");
                    String s2 =randomrs.getString("option1");
                    String s3 =randomrs.getString("option2");
                    String s4 =randomrs.getString("option3");
                    String s5 =randomrs.getString("option4");
                    stmt.executeUpdate("insert into stuqao values("+c+",'"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')");
                    randomrs.close();
                    sql="select * from ua where qno="+i+"";
                    randomrs = stmt.executeQuery(sql);
                    randomrs.next();
                    s1 =randomrs.getString("correctans");
                    stmt.executeUpdate("insert into stuua values("+c+",'','"+s1+"')");
                    randomrs.close();

                }
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("pickrandom\n"+e);
        }
    }


public static void main(String[] args) {
     int i;
    
    
    qaoDBcon(); 
        uaDBcon(); 
        pickrandom(); 
    for(i=0;i<1000000000;i++);
    
    
        new OnlineTest("ONLINE EXAM");
    
}

}


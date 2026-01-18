package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

class MainPanel extends JPanel
{
    private JTextField txtStuName;
    private JRadioButton rbtnBCA,rbtnBTech,rbtnMCA,rbtnMTech;
    private ButtonGroup bgrpCourse = new ButtonGroup();
    private JComboBox cboxSubject;
    private JList lstStatus;
    private JScrollPane spnStatus;
    private JButton btnRegistration,btnDelete,btnClear,btnExit;
    private String[] bca   = {"C Language","Data Structure","DBMS","Java SE"};
    private String[] btech = {"Object Design using UML","Numerical","Artificial Intelligence","Java EE"};
    private String[] mca   = {"Data Structure","Object Design Technique","DBMS","Artificial Intelligence"};
    private String[] mtech = {"VLSI","Advance DBMS","Advance Networking","TCP/IP"};
    
    private Vector<String> statusList = new Vector<String>();
    private String course = "BCA";
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        add(temp);
        return temp;
    }
    private JRadioButton makeRadioButton(String cap,int x,int y,int w,int h)
    {
        JRadioButton temp = new JRadioButton(cap);
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBounds(x, y, w, h);
        temp.setOpaque(false);
        bgrpCourse.add(temp);
        temp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JRadioButton ob = (JRadioButton)e.getSource();
                course = ob.getText();
                cboxSubject.removeAllItems();
                if(ob == rbtnBCA)
                {
                    for(String s:bca) cboxSubject.addItem(s);
                }
                else if(ob == rbtnBTech)
                {
                    for(String s:btech) cboxSubject.addItem(s);
                }
                else if(ob == rbtnMCA)
                {
                    for(String s:mca) cboxSubject.addItem(s);
                }
                else if(ob == rbtnMTech)
                {
                    for(String s:mtech) cboxSubject.addItem(s);
                }
            }
        });
        add(temp);
        return temp;
    }
    private JComboBox makeComboBox(int x,int y,int w,int h,String[] items)
    {
        JComboBox temp = new JComboBox(items);
        temp.setFont(new Font("Verdana", 1, 16));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object ob = e.getSource();
                if(ob == btnRegistration)
                {
                    String name = txtStuName.getText();
                    String subj = (String)cboxSubject.getSelectedItem();
                    String list = String.format("%-20s|%-6s|%s", name,course,subj);
                    statusList.add(list);
                    lstStatus.setListData(statusList);
                    txtStuName.selectAll();
                    txtStuName.grabFocus();
                }
                else if(ob == btnDelete)
                {
                    statusList.remove(lstStatus.getSelectedIndex());
                    lstStatus.setListData(statusList);
                }
                else if(ob == btnClear)
                {
                    int confirm = JOptionPane.showConfirmDialog(null, "Want to clear all?");
                    if(confirm == 0)
                    {
                        statusList.clear();
                        lstStatus.setListData(statusList);
                    }
                }
                else if(ob == btnExit)
                {
                    System.exit(0);
                }
            }
        });
        super.add(temp);
        return temp;
    }
    public MainPanel()
    {
        makeLabel("Student Name",                10, 10,200,30);
        txtStuName = makeTextField(                 210, 10,300,30);
        makeLabel("Course",                      10, 50,100,30);
        rbtnBCA    = makeRadioButton("BCA",     130, 50,100,30);
        rbtnBCA.setSelected(true);
        rbtnBTech  = makeRadioButton("BTech",   230, 50,100,30);
        rbtnMCA    = makeRadioButton("MCA",     330, 50,100,30);
        rbtnMTech  = makeRadioButton("MTech",   430, 50,100,30);
        makeLabel("Subject",                     10, 90,100,30);
        cboxSubject= makeComboBox(                  210,90,300,30,bca);
        makeLabel("Status",                      10,130,100,30);
        lstStatus  = new JList(statusList);
        lstStatus.setFont(new Font("Courier New",1,18));
        spnStatus = new JScrollPane(lstStatus);
        spnStatus.setBounds(                         10,170,500,200);
        spnStatus.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(spnStatus);
        
        btnRegistration = makeButton("Registration", 28,380,100,30);
        btnDelete       = makeButton("Delete",      156,380,100,30);
        btnClear        = makeButton("Clear",       284,380,100,30);
        btnExit         = makeButton("Exit",        412,380,100,30);
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 470);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Book List");
        frame.setResizable(false);
    }
}

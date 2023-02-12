import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import javax.sound.sampled.*;
import java.io.*;
import java.io.File;

public class JTableRow {
    static int medCount = 0;
    static Map<String, String> medList = new HashMap<>();

    public static void main(String[] args){
        
        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Name","Time"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        // create JTextFields
        JTextField medName = new JTextField();
        JTextField timeH = new JTextField();
        JTextField timeM = new JTextField();
        JTextField AMPM = new JTextField();
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");     
        
        medName.setBounds(20, 220, 100, 25);
        timeH.setBounds(20, 250, 100, 25);
        timeM.setBounds(20, 280, 100, 25);
        AMPM.setBounds(20, 310, 100, 25);
        
        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        // add JTextFields to the jframe
        frame.add(medName);
        frame.add(timeH);
        frame.add(timeM);
        frame.add(AMPM);
    
        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        String[] srow = new String[4];
        
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = medName.getText();
                row[1] = timeH.getText();
                row[2] = timeM.getText();
                row[3] = AMPM.getText();
                srow[0] = medName.getText();
                srow[1] = timeH.getText();
                srow[2] = timeM.getText();
                srow[3] = AMPM.getText();
                if(e.getActionCommand() == "Add"){
                    String str1 = srow[0];
                    String str2 = srow[1];
                    String str3 = srow[2];
                    String str4 = srow[3];
                    String promptmsg = "";
                    int min=0, hour=0;
                    try{
                        hour = Integer.parseInt(str2);
                        min = Integer.parseInt(str3);
                    }
                    catch(NumberFormatException nfe){
                        timeH.setText("Enter numbers");
                        timeM.setText("Enter numbers");
                    }
                    if(str1 == null)
                    {
                        promptmsg = "Enter name of medicine";
                        medName.setText(promptmsg);
                    }
                    else if(str2=="" || str2.length() > 2 || hour <= 0 || hour > 12)
                    {
                        promptmsg = "Enter time of medicine";
                        timeH.setText(promptmsg);
                    }
                    else if(str3=="" || str3.length() > 2 || min < 0 || min > 59)
                    {
                        promptmsg = "Enter time of medicine";
                        timeM.setText(promptmsg);
                    }
                    else if(str4=="" || str4.length() > 2)
                    {
                        promptmsg = "Enter AM/PM";
                        AMPM.setText(promptmsg);
                    }
                    else
                    {
                        str4 = str4.toLowerCase();
                        if(str4 == "am" && hour == 12)
                        {
                            hour = 0;
                        }
                        else if(str4 == "am")
                        {
                            hour += 12;
                        }
                        //if()
                        String time = "";
                        time = str2 + str3;
                        enterMed(str1, time);
                    }
                }
                
                // add row to the model
                model.addRow(row);
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            medName.setText(model.getValueAt(i, 0).toString());
            timeH.setText(model.getValueAt(i, 1).toString());
            timeM.setText(model.getValueAt(i, 2).toString());
            AMPM.setText(model.getValueAt(i, 3).toString());
        }
        });
        
        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if(i >= 0) 
                {
                   model.setValueAt(medName.getText(), i, 0);
                   model.setValueAt(timeH.getText(), i, 1);
                   model.setValueAt(timeM.getText(), i, 2);
                   model.setValueAt(AMPM.getText(), i, 3);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
        @Override
        public void run() {
           check();
        }
}, 0, 5000);
    }

    static void enterMed(String medName, String time) 
    {
        ++medCount;
        medList.put(medName, time);
    }

    static void check()
    {
        GregorianCalendar gcalender = new GregorianCalendar();
        int hour = gcalender.get(Calendar.HOUR);
        int min = gcalender.get(Calendar.MINUTE);
        int sec = gcalender.get(Calendar.SECOND);
        String temptime1 = Integer.toString(hour) + Integer.toString(min) + "00";
        for(Map.Entry<String, String> e : medList.entrySet())
        {
            if(e.getValue().equals(temptime1))
            {
                try
                {
                    playSound();
                }
                catch(Exception e1)
                {
                    System.out.print("hi");
                }
                break;
            }
        }
    }

    static void playSound()
    {
        try
        {
            File file=new File("Sound2.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException e)
        {
            System.out.println("e handled");
        }
        catch(IOException ioe)
        {
            System.out.println("e handled");
        }
        catch(LineUnavailableException lue)
        {
            System.out.println("e handled");
        }
    }
}

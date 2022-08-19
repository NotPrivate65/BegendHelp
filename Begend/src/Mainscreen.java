import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class Mainscreen extends JFrame {

    private JMenuBar menubar;
    private JMenu menu;
    private StandartDates std = new StandartDates();
    private JLabel date_Label_Top;

    private JLabel dataPath;

    private JButton begin_Button;
    private JButton end_Button;

    private JLabel begin_Time_Label;
    private JLabel end_Time_Label;

    private JLabel fullHours;
    private String fullHoursTxt;
    private double fullHoursDouble;
    int button_isPressed_end = 0;
    int button_isPressed_begin = 0;

     FileWriting fileWriting = new FileWriting();

     Icon waring = UIManager.getIcon("OptionPane.warningIcon");

     long millisBegin = 0;


     FileReadingSystem FRS = new FileReadingSystem();


     public String filePathDay = "";
     public String filePathCW = "";

    //Description ->|Date       |TimeBegin  |TimeEnd    |Hours
    //Fileformat -> DDMMYYYY;   HHMMSS;     HHMMSS;     HH;

    CalendarWeekScreen cWS = new CalendarWeekScreen();

    public void mainScreenVis(boolean visibility, String file){

        filePathDay = file;


        String [] fileCW_buffer = file.split(Pattern.quote(File.separator));
        fileCW_buffer[fileCW_buffer.length-1] = "CW.bgd";

        for(int i = 0; i < fileCW_buffer.length -1; i++){
            filePathCW += fileCW_buffer[i]+"\\";
        }
        filePathCW += fileCW_buffer[fileCW_buffer.length-1];



        setTitle("Begend");
        setSize(800, 630);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Make sure you clicked the [End] Button to prevent an ERROR!", "Warning!", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, waring);

                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                }else if(confirmed == JOptionPane.NO_OPTION){
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        setResizable(false);



        //MenuBar
        menubar = new JMenuBar();

        menu = new JMenu("Menu");
        JMenuItem cw = new JMenuItem("Calendarweek");
        cw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cWS.calendarWeekScreenVis(true, filePathCW, filePathDay);
                dispose();
            }
        });
        JMenuItem year = new JMenuItem("Year");
        menu.add(cw);
        menu.add(year);
        menubar.add(menu);

        setJMenuBar(menubar);

        //DataPathlabel
        dataPath = new JLabel(file);
        dataPath.setBounds(10, 540,300,30);
        dataPath.setFont(new Font("",Font.PLAIN,10));
        dataPath.setForeground(new Color(255, 32, 32));
        add(dataPath);


        //Table
        String[][] table_txt = new String[FRS.getTableTxtLenght()][4]; //= new String[4000000][4];
        String [] column = {"Date", "Begin", "End", "Hours"};

        JTable jT = new JTable(table_txt, column){
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jT.setBounds(300,40,400,500);
        jT.setFont(new Font("",Font.PLAIN, 15));


        JScrollPane jSP = new JScrollPane(jT,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jSP.setBounds(300, 40,400,500);

        add(jSP);

        //Datelabel
        String date_Top = std.getDDMMYYYY();

        int dateLabel_width = 130;
        int dateLabel_X = (getSize().width - dateLabel_width)/2;

        date_Label_Top = new JLabel(date_Top);
        date_Label_Top.setBounds(dateLabel_X, 5,dateLabel_width,30);
        date_Label_Top.setFont(new Font("",Font.PLAIN,25));
        add(date_Label_Top);


        //Begin
        begin_Button = new JButton("Begin");
        begin_Button.setBackground(Color.LIGHT_GRAY);
        begin_Button.setBounds(10,40,150,50);
        begin_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(button_isPressed_begin == 0) {
                    begin_Time_Label.setText(std.getHHMMSS());

                    button_isPressed_begin = 1;
                    button_isPressed_end = 0;

                    end_Time_Label.setText("");

                    String begin_Date_File = std.getDDMMYYYY() + ";" + std.getHHMMSS();

                    String begin_CW_File = std.getMMYYYY() +";";

                    fileWriting.appendToFile(filePathDay, begin_Date_File);
                    fileWriting.appendToFile(filePathCW, begin_CW_File);


                    millisBegin = System.currentTimeMillis();

                }else{
                    System.out.println("You can only press once!");
                }
            }
        });
        add(begin_Button);

        begin_Time_Label = new JLabel();
        begin_Time_Label.setBounds(170,40,100,50);
        begin_Time_Label.setFont(new Font("",Font.PLAIN,15));
        add(begin_Time_Label);


        //End
        end_Button = new JButton("End");
        end_Button.setBackground(Color.LIGHT_GRAY);
        end_Button.setBounds(10,100,150,50);
        end_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(button_isPressed_end == 0 && button_isPressed_begin == 1){

                    button_isPressed_end = 1;
                    button_isPressed_begin = 0;

                    end_Time_Label.setText(std.getHHMMSS());

                    long millisEnd = System.currentTimeMillis();

                    double hours = ((double) millisEnd-millisBegin)/3600000;
                    BigDecimal bd = new BigDecimal(hours).setScale(4, RoundingMode.HALF_UP);
                    hours = bd.doubleValue();

                    String toFile_End = ";"+ std.getHHMMSS() +";"+ String.valueOf(hours) +";\n";
                    String toCW_End = std.getCW() +";"+ hours +";\n";

                    fileWriting.appendToFile(filePathDay, toFile_End);
                    fileWriting.appendToFile(filePathCW, toCW_End);

                    FRS.readFileTxt(file);


                    for(int i = 0; i < FRS.getTableTxtLenght(); i++){
                        for(int j = 0; j < FRS.getTableTxtPlaceLenght(i); j++){
                            String string = FRS.getTableTxt(i,j);
                            table_txt[i][j] = string;
                        }
                    }

                    jT.updateUI();

                    for(int i = 0; i < table_txt.length; i++){
                        if(table_txt[i][3] != null){

                            fullHoursDouble += Double.parseDouble(table_txt[i][3]);

                        }else{
                            i = table_txt.length;
                        }
                    }

                    BigDecimal bd2 = new BigDecimal(fullHoursDouble).setScale(4, RoundingMode.HALF_UP);
                    fullHoursDouble = bd2.doubleValue();
                    fullHoursTxt = String.valueOf(fullHoursDouble);
                    fullHoursDouble = 0;
                    fullHours.setText(fullHoursTxt + "h");

                    System.gc();

                }else{
                    System.out.println("You can only press once!");
                }
            }
        });
        add(end_Button);

        end_Time_Label = new JLabel();
        end_Time_Label.setBounds(170,100,100,50);
        end_Time_Label.setFont(new Font("",Font.PLAIN,15));
        add(end_Time_Label);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;

            int line_count = 0;
            int column_count = 0;

            while ((line = reader.readLine()) != null) {

                String[] cache = line.split(";");

                for (int i = 0; i < cache.length; i++) {
                    table_txt[line_count][i] = cache[i];
                }
                line_count++;
            }
            reader.close();

            for(int i = 0; i < table_txt.length; i++){
                if(table_txt[i][3] != null){

                    fullHoursDouble += Double.parseDouble(table_txt[i][3]);

                }else{
                    i = table_txt.length;
                }
            }
            BigDecimal bd2 = new BigDecimal(fullHoursDouble).setScale(4, RoundingMode.HALF_UP);
            fullHoursDouble = bd2.doubleValue();
            fullHoursTxt = String.valueOf(fullHoursDouble);
            fullHoursDouble = 0;


        } catch (
                FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException exep) {
            throw new RuntimeException(exep);
        }





        fullHours = new JLabel(fullHoursTxt + "h");
        fullHours.setBounds(10,200,200,30);
        fullHours.setFont(new Font("",Font.PLAIN,20));
        add(fullHours);

        setVisible(visibility);

    }

}

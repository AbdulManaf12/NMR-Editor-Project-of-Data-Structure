import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Frames extends JFrame
{
    public static File file=null;
    private static JTextArea textArea1;
    private JPanel panel = new JPanel();
    private JScrollPane sc;
    private MenuBar menuBar = new MenuBar(this);
    private int position = 0;
    Frames()
    {
        super("NMR Editor");
        _init_("");
    }
    private void _init_(String text)
    {
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("icons/logo.png"));
        setIconImage(img.getImage());
        setLocation(300,100);
        setFocusable(true);
        Utilities.Themes("Nimbus");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Close();
            }});
        setMinimumSize(new Dimension(500,500));
        panel.setBackground(new Color(35, 110, 232));
        panel.setLayout(null);
        panel.addComponentListener(new Window());
        textArea1 = new JTextArea();
        Utilities.f = this;
        position = textArea1.getCaretPosition();
        textArea1.setLineWrap(false);
        textArea1.setFont(new Font(Font.DIALOG,Font.PLAIN,24));
        textArea1.setTabSize(4);
        textArea1.setSelectionColor(Color.GREEN);
        textArea1.addKeyListener(new UndoRedo(this,new AutoSuggest()));
        sc = new JScrollPane(textArea1);
        sc.setBounds(10,25,465,425);
        textArea1.setText(text);
        panel.add(menuBar);
        panel.add(sc);
        add(panel);
        textArea1.setCaretPosition(position);
        textArea1.setFocusable(true);
        setVisible(true);
    }
    class Window implements ComponentListener
    {
        public void componentResized(ComponentEvent e) {
            sc.setBounds(10,25,getWidth()-30,getHeight()-85);
            menuBar.setBounds(10,0,getWidth()-30,20);
            validate();
        }
        public void componentMoved(ComponentEvent e) {}
        public void componentShown(ComponentEvent e) {}
        public void componentHidden(ComponentEvent e) {}
    }
    public static JTextArea getTextArea()
    {
        return textArea1;
    }
    public static void setTextArea1(String text)
    {
        textArea1.setText(text);
    }
    public static void SaveFile()
    {
        try(BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Utf-8")))
        {
            Recent.addPathInRecent(file.getAbsolutePath());
            os.write(textArea1.getText());
            os.close();os.flush();
        }catch (Exception e){}
    }
    public static void OpenText()
    {
        if (file.exists()) {
            try{
                if (!file.canRead())
                    throw new IOException();
                if (!file.canWrite())
                    throw new IOException();
                if (!file.canExecute())
                    throw new IOException();
                BufferedReader os = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Utf-8"));
                String temp;
                textArea1.setText("");
                while ((temp = os.readLine()) != null) {
                    textArea1.setText(textArea1.getText() + temp + "\n"); }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,"File is not Found: \n "+file.getAbsolutePath());
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null,"NMR Editor Not Support these extension File");
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"NMR Editor is not able to recongnize this issue\n Contact us on: 0300-3141736");
            }
        }
    }
    private static boolean CheckExtension(String path){
        if (path.contains(".docx") || path.contains(".doc") || path.contains(".exe") || path.contains(".pdf")){
            return false;
        }
        else if (path.contains(".xls")||path.contains(".jar")||path.contains(".rar")||path.contains(".zip")){
            return false;
        }
        return true;
    }
    public static void New()
    {
        MainPage.main(null);
    }
    public static void Close(){
        if (textArea1.getText().length()!=0 || file != null)
        {
            Save();
        }
        Recent.setDataIntoFile();
        System.exit(0);
    }
    public static void Save() {
        if (file != null) {
            SaveFile();
            Recent.addPathInRecent(file.getAbsolutePath());
        }
        else {
            String path = Utilities.FileChooser("Save");
            if (!path.equals("nullnull")) {
                file = new File(path);
                SaveFile();
            }
        }
    }
    public static void SaveAs()
    {
        String path = Utilities.FileChooser("Save As");
        if (!path.equals("nullnull")) {
            SaveFile(new File(path));
            Recent.addPathInRecent(path);
        }
    }
    public static void Open()
    {
        String path = Utilities.FileChooser("Open");
        if (!CheckExtension(path)){
            String extension = path.substring(path.indexOf("."),path.length());
            JOptionPane.showMessageDialog(null,"NMR Editor Not Support \""+extension+"\" extension File");
            Open();
        }
        else if (!path.equals("nullnull"))
        {
            Frames.file = new File(path);
            if (Frames.file != null) {
                Frames.OpenText();
            }
        }
    }
    public static void SaveFile(File f)
    {
        try(BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"Utf-8")))
        {   os.write(textArea1.getText());
            os.close();os.flush();
        }catch (Exception e){}
    }
}
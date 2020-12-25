import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Recent extends JMenu
{
    private static JMenuItem list[];
    private static int values=-1;
    private static Queue queue;
    private static JFrame f;
    private static File file;
    private static OSType detectedOS;
    Recent(JFrame f)
    {
        super("Recent");
        this.f  =f;
        _init_();
    }

    private void _init_() {
        FindPathAsOS();
        queue = getDataFromFile();
        list = getJMenuItem(queue);
    }
    private void FindPathAsOS(){
        getOperatingSystemType();
        try {
            if (detectedOS == OSType.Windows) {
                String path = System.getProperty("user.home") + "\\AppData\\Local\\NMR Editor";
                file = new File(path);
                if (!file.exists())
                    file.mkdir();
                file = new File(file.getAbsolutePath() + "\\RecentFileData");
                if (!file.exists())
                    file.createNewFile();
            }
            else if (detectedOS == OSType.Linux){
                String path = System.getProperty("user.home") + "/.local/share/NMR Editor";
                file = new File(path);
                if (!file.exists())
                    file.mkdir();
                file = new File(file.getAbsolutePath() + "/RecentFileData");
                if (!file.exists())
                    file.createNewFile();
            }
            else if (detectedOS == OSType.MacOS){
                String path = System.getProperty("user.home") + "/Library/Preferences/NMR Editor";
                file = new File(path);
                if (!file.exists())
                    file.mkdir();
                file = new File(file.getAbsolutePath() + "/RecentFileData");
                if (!file.exists())
                    file.createNewFile();
            }
        }
        catch (Exception e){}
    }
    private JMenuItem[] getJMenuItem(Queue list){
        if (list.isEmpty()){
            return null;
        }
        else {
            JMenuItem menuItem[] = new JMenuItem[list.getData().length];
            String s[] = list.getData();
            for (int i=0; i<menuItem.length; i++) {
                menuItem[i] = new JMenuItem(s[i]);
                menuItem[i].addActionListener(new ActionRecent());
                add(menuItem[i]);
                values++;
            }
            return menuItem;
        }
    }

    private Queue getDataFromFile(){
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            Queue l = new Queue();
            String temp;
            while ((temp = reader.readLine()) != null) {
                l.enqueue(temp);
            }
            reader.close();
            return l;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Queue();
    }
    public static void setDataIntoFile() {
        if (Frames.file != null)
            queue.enqueue(Frames.file.getAbsolutePath());
        try {
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Utf-8"));
            while (!queue.isEmpty())
            {
                os.write(queue.dequeue());
                os.newLine();
            }
            os.close();
        }
        catch (Exception e) { }
    }
    public static void addPathInRecent(String path){
        queue.enqueue(path);
    }
    class ActionRecent implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i<list.length; i++){
                if (list[i] == e.getSource()){
                    Frames.SaveFile();
                    Frames.file = new File(list[i].getText());
                    Frames.OpenText();
                }
            }
        }
    }
    public enum OSType {
        Windows, MacOS, Linux
    };
    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            }
        }
        return detectedOS;
    }
}
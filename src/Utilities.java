import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

public class Utilities
{
    public static JFrame f;
    private static Highlighter highlighter;
    private static int previous=-1;

    public static String FileChooser(String title)
    {
        JFrame fs = new JFrame();
        FileDialog f = new FileDialog(fs,title);
        if (title.equalsIgnoreCase("Save") || title.equalsIgnoreCase("Save as"))
            f.setMode(FileDialog.SAVE);
        f.setDirectory("C:\\");
        f.setMultipleMode(false);
        f.setVisible(true);
        String s =  f.getDirectory()+((f.getFile() != null)?f.getFile().toString():null);
        if (s.contains("exe"))
            s= "nullnull";
        return s;
    }
    public static void Themes(String theme)
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (theme.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            if (f != null)
                SwingUtilities.updateComponentTreeUI(f);
        }catch (Exception e){}
    }
    static class Find extends JFrame
    {
        Find()
        {
            super("Find");
            setLocation(520,170);
            setResizable(false);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    ClearHighlight();
                    dispose();
                }
            });
            setSize(300,80);
            setType(Type.UTILITY);
            setAlwaysOnTop(true);
            setLayout(new FlowLayout());
            _init_();
            setVisible(true);
        }
        private void _init_()
        {
            JTextField tf = new JTextField(15);
            JButton b = new JButton("Find");
            add(tf);add(b);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (tf.getText().length()!=0){
                        Find(tf.getText());
                    }
                }
            });
        }
        private static void Find(String data)
        {
            try {
                ClearHighlight();
                Document doc = Frames.getTextArea().getDocument();
                String text = doc.getText(0, doc.getLength());
                int pos = 0;
                while ((pos=text.toUpperCase().indexOf(data.toUpperCase(),pos)) >= 0)
                {
                    HighlightText(pos,pos+data.length());
                    pos += data.length();
                }
            }
            catch (Exception  ex){}
        }
    }
    static class Replace extends JFrame
    {
        private int position;
        Replace()
        {
            super("Replace");
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ClearHighlight();
                    dispose();
                }
            });
            setLocation(520,170);
            setResizable(false);
            setSize(250,170);
            setLayout(new FlowLayout());
            setType(Type.UTILITY);
            setAlwaysOnTop (true);
            _init_();
            setVisible(true);
        }
        private void _init_()
        {
            JLabel l1 = new JLabel("To:   ");
            JTextField tf = new JTextField(15);
            JLabel l2 = new JLabel("With: ");
            JTextField ef = new JTextField(15);
            JButton b1 = new JButton("Replace");
            JButton b2 = new JButton("Replace All");
            add(l1);add(tf);add(l2);add(ef);add(b1);add(b2);
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (tf.getText().length()!=0&&ef.getText().length()!=0){
                        position = Frames.getTextArea().getCaretPosition();
                        Replace(tf.getText(),ef.getText());
                    }
                }
            });
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (tf.getText().length()!=0&&ef.getText().length()!=0){
                        position = Frames.getTextArea().getCaretPosition();
                        ReplaceAll(tf.getText(), ef.getText());
                    }
                }
            });
        }
        private void Replace(String to,String with)
        {
            if (!to.equals("")&&!with.equals("")) {
                ClearHighlight();
                String s1 = Frames.getTextArea().getText();
                int i1 = s1.indexOf(to);
                Frames.setTextArea1(change(s1, with, i1, i1 + to.length()));
                Find.Find(with);
                Frames.getTextArea().setCaretPosition(position);
            }
        }
        private void ReplaceAll(String to,String with)
        {
            try {
                if (!to.equals("")&&!with.equals("")) {
                    ClearHighlight();
                    Document doc = Frames.getTextArea().getDocument();
                    String text = doc.getText(0, doc.getLength());
                    int pos = 0;
                    while ((pos = text.toUpperCase().indexOf(to.toUpperCase(), pos)) >= 0) {
                        text = change(text, with, pos, pos + to.length());
                        pos += to.length();
                    }
                    Frames.setTextArea1(text);
                    Find.Find(with);
                    Frames.getTextArea().setCaretPosition(position);
                }
            }
            catch (Exception  ex){}
        }
        private String change(String text,String newword,int start,int end)
        {
            String s1 = text.substring(0,(start>-1)?start:0);
            String s2 = s1 + newword + text.substring(end);
            return s2;
        }
    }
    public static Find getFind()
    {
        return new Find();
    }
    public static Replace getReplace()
    {
        return new Replace();
    }
    public static void HighlightText(int start,int end)
    {
        if (start < Frames.getTextArea().getText().length() && end < Frames.getTextArea().getText().length()) {
            try {
                highlighter = Frames.getTextArea().getHighlighter();
                Highlighter.HighlightPainter painter =
                        new DefaultHighlighter.DefaultHighlightPainter(
                                (Frames.getTextArea().getBackground() == Color.BLACK) ? Color.WHITE : Color.green);
                painter =
                        new DefaultHighlighter.DefaultHighlightPainter(
                                (Frames.getTextArea().getBackground() == Color.GREEN) ? Color.WHITE : Color.RED);
                highlighter.addHighlight(start, end, painter);
            } catch (Exception e) {
            }
        }
    }
    private static void ClearHighlight()
    {
        if (highlighter != null && !UIManager.getLookAndFeel().getName().equals("CDE/Motif"))
            highlighter.removeAllHighlights();
    }
}
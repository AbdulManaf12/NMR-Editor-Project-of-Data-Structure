import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MenuBar extends JMenuBar
{
    private JMenu file,View,Format,Help,recent;
    private JMenuItem newfile,openfile,savefile,saveasfile;
    private JMenuItem cut,copy,paste,selcetall,find,replace,clear;
    private JMenu size,font,fontstyle,background,textcolor,Themes;
    private JRadioButtonMenuItem wordwrap,linewrap;
    private JRadioButtonMenuItem DIALOG,DIALOG_INPUT,MONOSPACED,ROMAN_BASELINE,SANS_SERIF,SERIF;
    private JRadioButtonMenuItem PLAIN,BOLD,ITALIC,CENTER_BASELINE;
    private JRadioButtonMenuItem nums[];
    private JRadioButtonMenuItem bred,bblack,bgreen,bwhite,bblue,byellow,bpink;
    private JRadioButtonMenuItem tred,tblack,tgreen,twhite,tblue,tyellow,tpink;
    private JMenuItem feedback,About,Shortcuts,Contact;
    public static JRadioButtonMenuItem wordsuggetion;
    private JRadioButtonMenuItem Metal,Nimbus,CDE_Motif,Windows,Windows_Classic;
    public static JMenuItem undo,redo;
    private JFrame f;

    MenuBar(JFrame f)
    {
        this.f  =f;
        setBounds(10,0,490,25);
        File();
        View();
        Format();
        Help();
    }
    private void File()
    {
        file = new JMenu("File");
        newfile = new JMenuItem("New ",KeyEvent.VK_N);
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openfile = new JMenuItem("Open",KeyEvent.VK_O);
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        savefile = new JMenuItem("Save",KeyEvent.VK_S);
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveasfile = new JMenuItem("Save as",KeyEvent.VK_S);
        saveasfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        recent = new Recent(f);
        file.add(newfile);file.add(openfile);file.add(savefile);file.add(saveasfile);
        file.add(recent);add(file);
        newfile.addActionListener(new ActionFile());
        openfile.addActionListener(new ActionFile());
        savefile.addActionListener(new ActionFile());
        saveasfile.addActionListener(new ActionFile());
    }
    class ActionFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == newfile)
                Frames.New();
            else if(e.getSource() == openfile)
                Frames.Open();
            else if(e.getSource() == savefile)
                Frames.Save();
            else if(e.getSource() == saveasfile)
                Frames.SaveAs();
        }
    }
    private void View()
    {
        View = new JMenu("View");
        cut = new JMenuItem("Cut",KeyEvent.VK_X);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        copy = new JMenuItem("Copy",KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        paste = new JMenuItem("Paste",KeyEvent.VK_P);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        selcetall = new JMenuItem("Select All",KeyEvent.VK_A);
        selcetall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        wordwrap = new JRadioButtonMenuItem("Word Wrap");
        linewrap = new JRadioButtonMenuItem("Line Wrap");
        wordsuggetion = new JRadioButtonMenuItem("Word Suggestion");
        wordsuggetion.setSelected(true);
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        find = new JMenuItem("Find",KeyEvent.VK_F);
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        replace = new JMenuItem("Replace",KeyEvent.VK_R);
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        clear = new JMenuItem("Clear");

        View.add(copy);View.add(cut);View.add(paste);View.add(selcetall);
        View.add(find);View.add(replace);View.add(undo);View.add(redo);
        View.add(wordwrap);View.add(linewrap);View.add(wordsuggetion);View.add(clear);add(View);
        cut.addActionListener(new ActionView());
        copy.addActionListener(new ActionView());
        paste.addActionListener(new ActionView());
        selcetall.addActionListener(new ActionView());
        wordwrap.addActionListener(new ActionView());
        linewrap.addActionListener(new ActionView());
        find.addActionListener(new ActionView());
        replace.addActionListener(new ActionView());
        clear.addActionListener(new ActionView());
        undo.addActionListener(new ActionView());
        redo.addActionListener(new ActionView());
        wordsuggetion.addActionListener(new ActionView());
    }
    class ActionView implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==wordwrap)
                Frames.getTextArea().setWrapStyleWord(!Frames.getTextArea().getWrapStyleWord());
            else if(e.getSource() == linewrap)
                Frames.getTextArea().setLineWrap(!Frames.getTextArea().getLineWrap());
            else if (e.getSource()==clear)
                Frames.getTextArea().setText("");
            else if (e.getSource()==cut){
                if (Frames.getTextArea().getSelectionStart() != Frames.getTextArea().getSelectionEnd())
                    Frames.getTextArea().cut();}
            else if (e.getSource()==copy){
                if (Frames.getTextArea().getSelectionStart() != Frames.getTextArea().getSelectionEnd())
                    Frames.getTextArea().copy(); }
            else if (e.getSource()==paste)
                    Frames.getTextArea().paste();
            else if (e.getSource()==selcetall)
                Frames.getTextArea().selectAll();
            else if (e.getSource() == find)
                Utilities.getFind();
            else if (e.getSource()==replace)
                Utilities.getReplace();
            else if (e.getSource()==undo)
                UndoRedo.ChooseComand(UndoRedo.CTRL_Z);
            else if (e.getSource()==redo)
                UndoRedo.ChooseComand(UndoRedo.CTRL_Y);
            else if (e.getSource()==wordsuggetion){
                if (wordsuggetion.isSelected())
                    wordsuggetion.setSelected(true);
                else
                    wordsuggetion.setSelected(false);
            }
        }
    }
    private void Format()
    {
        Format = new JMenu("Format");
        font = new JMenu("Fonts");
        DIALOG = new JRadioButtonMenuItem("DIALOG");
        DIALOG_INPUT = new JRadioButtonMenuItem("DIALOG_INPUT");
        SANS_SERIF = new JRadioButtonMenuItem("SANS_SERIF");
        SERIF = new JRadioButtonMenuItem("SERIF");
        MONOSPACED = new JRadioButtonMenuItem("MONOSPACED");
        DIALOG.setSelected(true);
        font.add(DIALOG);font.add(DIALOG_INPUT);font.add(SANS_SERIF);font.add(SERIF);font.add(MONOSPACED);
        DIALOG.addActionListener(new ActionFontName());DIALOG_INPUT.addActionListener(new ActionFontName());
        SANS_SERIF.addActionListener(new ActionFontName());SERIF.addActionListener(new ActionFontName());MONOSPACED.addActionListener(new ActionFontName());
        fontstyle = new JMenu("Font Style");
        PLAIN = new JRadioButtonMenuItem("PLAIN");
        PLAIN.setSelected(true);
        BOLD = new JRadioButtonMenuItem("BOLD");
        ITALIC = new JRadioButtonMenuItem("ITALIC");
        ROMAN_BASELINE = new JRadioButtonMenuItem("ROMAN_BASELINE");
        CENTER_BASELINE = new JRadioButtonMenuItem("CENTER_BASELINE");
        fontstyle.add(PLAIN);fontstyle.add(BOLD);fontstyle.add(ITALIC);
        fontstyle.add(ROMAN_BASELINE);fontstyle.add(CENTER_BASELINE);
        PLAIN.addActionListener(new ActionFontStyle());BOLD.addActionListener(new ActionFontStyle());
        ITALIC.addActionListener(new ActionFontStyle());ROMAN_BASELINE.addActionListener(new ActionFontStyle());
        CENTER_BASELINE.addActionListener(new ActionFontStyle());
        size = new JMenu("Size");
        nums = new JRadioButtonMenuItem[31];
        int s=0;
        for (int i=8; i<70; i++)
        {
            if (i%2==0) {
                nums[s] = new JRadioButtonMenuItem(i + "");
                nums[s].addActionListener(new ActionFontSize());
                if (i==24)
                    nums[s].setSelected(true);
                size.add(nums[s++]);
            }
        }
        background = new JMenu("Background Color");
        bred = new JRadioButtonMenuItem("Red");
        bblack = new JRadioButtonMenuItem("Black");
        bgreen = new JRadioButtonMenuItem("Green");
        bwhite = new JRadioButtonMenuItem("White");
        bblue = new JRadioButtonMenuItem("Blue");
        byellow = new JRadioButtonMenuItem("Yellow");
        bpink = new JRadioButtonMenuItem("Pink");
        bwhite.setSelected(true);
        background.add(bred);background.add(bblack);background.add(bgreen);background.add(bwhite);
        background.add(bblue);background.add(byellow);background.add(bpink);
        bred.addActionListener(new ActionBackgroundColor());bblack.addActionListener(new ActionBackgroundColor());bgreen.addActionListener(new ActionBackgroundColor());
        bwhite.addActionListener(new ActionBackgroundColor());bblue.addActionListener(new ActionBackgroundColor());byellow.addActionListener(new ActionBackgroundColor());
        bpink.addActionListener(new ActionBackgroundColor());
        textcolor = new JMenu("Text Color");
        tred = new JRadioButtonMenuItem("Red");
        tblack = new JRadioButtonMenuItem("Black");
        tgreen = new JRadioButtonMenuItem("Green");
        twhite = new JRadioButtonMenuItem("White");
        tblue = new JRadioButtonMenuItem("Blue");
        tyellow = new JRadioButtonMenuItem("Yellow");
        tpink = new JRadioButtonMenuItem("Pink");
        tblack.setSelected(true);
        textcolor.add(tred);textcolor.add(tblack);textcolor.add(tgreen);textcolor.add(twhite);
        textcolor.add(tblue);textcolor.add(tyellow);textcolor.add(tpink);
        tgreen.addActionListener(new ActiontextColor());tpink.addActionListener(new ActiontextColor());
        tred.addActionListener(new ActiontextColor());tblack.addActionListener(new ActiontextColor());
        twhite.addActionListener(new ActiontextColor());tyellow.addActionListener(new ActiontextColor());
        tblue.addActionListener(new ActiontextColor());
        Format.add(font);Format.add(fontstyle);Format.add(size);
        Format.add(background);Format.add(textcolor);add(Format);
    }
    class ActionFontName implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==MONOSPACED){Formating.setFont(Font.MONOSPACED);}
            if (e.getSource()==SERIF){Formating.setFont(Font.SERIF);}
            if (e.getSource()==SANS_SERIF){Formating.setFont(Font.SANS_SERIF);}
            if (e.getSource()==DIALOG){Formating.setFont(Font.DIALOG);}
            if (e.getSource()==DIALOG_INPUT){Formating.setFont(Font.DIALOG_INPUT);}
            FontNameSelection(e.getSource());
        }
    }
    class ActionFontSize implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 31; i++) {
                if (e.getSource() == nums[i])
                    Formating.setSize(Integer.parseInt(nums[i].getText())); }
            SizeSelection(e.getSource());
        }
    }
    class ActionFontStyle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==PLAIN){Formating.setFontStyle(Font.PLAIN);}
            else if (e.getSource()==BOLD){Formating.setFontStyle(Font.BOLD);}
            else if (e.getSource()==ITALIC){Formating.setFontStyle(Font.ITALIC);}
            else if (e.getSource()==ROMAN_BASELINE){Formating.setFontStyle(Font.ROMAN_BASELINE);}
            else if (e.getSource()==CENTER_BASELINE){Formating.setFontStyle(Font.CENTER_BASELINE);}
            FontStyleSelection(e.getSource());
        }
    }
    class ActionBackgroundColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==bblack){Frames.getTextArea().setBackground(Color.BLACK);}
            else if (e.getSource()==bblue){Frames.getTextArea().setBackground(Color.BLUE);}
            else if (e.getSource()==byellow){Frames.getTextArea().setBackground(Color.YELLOW);}
            else if (e.getSource()==bgreen){Frames.getTextArea().setBackground(Color.GREEN);}
            else if (e.getSource()==bpink){Frames.getTextArea().setBackground(Color.PINK);}
            else if (e.getSource()==bwhite){Frames.getTextArea().setBackground(Color.WHITE);}
            else if (e.getSource()==bred){Frames.getTextArea().setBackground(Color.RED);}
            ColorBackgroundSelction(e.getSource());
        }
    }
    class ActiontextColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==tblack){Frames.getTextArea().setForeground(Color.BLACK);}
            else if (e.getSource()==tblue){Frames.getTextArea().setForeground(Color.BLUE);}
            else if (e.getSource()==tyellow){Frames.getTextArea().setForeground(Color.YELLOW);}
            else if (e.getSource()==tgreen){Frames.getTextArea().setForeground(Color.GREEN);}
            else if (e.getSource()==tpink){Frames.getTextArea().setForeground(Color.PINK);}
            else if (e.getSource()==twhite){Frames.getTextArea().setForeground(Color.WHITE);}
            else if (e.getSource()==tred){Frames.getTextArea().setForeground(Color.RED);}
            ColorTextSelction(e.getSource());
        }
    }
    private void Help()
    {
        Help = new JMenu("Help");
        Themes = new JMenu("Themes");
        Metal=new JRadioButtonMenuItem("Metal");Nimbus=new JRadioButtonMenuItem("Nimbus");
        CDE_Motif=new JRadioButtonMenuItem("CDE/Motif");Windows=new JRadioButtonMenuItem("Windows");
        Windows_Classic=new JRadioButtonMenuItem("Windows Classic");
        Contact = new JMenuItem("Contact Us");
        Themes.add(Metal);Themes.add(Nimbus);Themes.add(CDE_Motif);Themes.add(Windows);Themes.add(Windows_Classic);
        Nimbus.addActionListener(new ActionHelp());Metal.addActionListener(new ActionHelp());CDE_Motif.addActionListener(new ActionHelp());
        Windows.addActionListener(new ActionHelp());Windows_Classic.addActionListener(new ActionHelp());Contact.addActionListener(new ActionHelp());
        Nimbus.setSelected(true);
        Shortcuts = new JMenuItem("Shortcuts");
        Shortcuts.addActionListener(new ActionHelp());
        feedback = new JMenuItem("Feedback");
        feedback.addActionListener(new ActionHelp());
        About = new JMenuItem("About");
        About.addActionListener(new ActionHelp());
        Help.add(Themes);Help.add(Shortcuts);Help.add(feedback);Help.add(About);Help.add(Contact);add(Help);
    }
    class ActionHelp implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==Shortcuts)
                new Shortcuts();
            if (e.getSource()==feedback)
                try{Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " +
                        "https://forms.gle/yrwd5GaRzvxh2cYr6");}catch (Exception ex){}
            if (e.getSource() == About)
                new About();
            if (e.getSource() == Contact)
                new Contact();
            else {
                if (e.getSource() == Nimbus)
                    Utilities.Themes(Nimbus.getText());
                else if (e.getSource() == Metal)
                    Utilities.Themes(Metal.getText());
                else if (e.getSource() == Windows)
                    Utilities.Themes(Windows.getText());
                else if (e.getSource() == Windows_Classic)
                    Utilities.Themes(Windows_Classic.getText());
                else if (e.getSource() == CDE_Motif)
                    Utilities.Themes(CDE_Motif.getText());
                ThemesSelction(e.getSource());
            }
        }
    }
    private void FontNameSelection(Object e)
    {
        if (e!=MONOSPACED)
            MONOSPACED.setSelected(false);
        if (e!=DIALOG)
            DIALOG.setSelected(false);
        if (e!=DIALOG_INPUT)
            DIALOG_INPUT.setSelected(false);
        if (e!=SERIF)
            SERIF.setSelected(false);
        if (e!=SANS_SERIF)
            SANS_SERIF.setSelected(false);
    }
    private void FontStyleSelection(Object e)
    {
        if (e!=PLAIN)
            PLAIN.setSelected(false);
        if (e!=ITALIC)
            ITALIC.setSelected(false);
        if (e!=CENTER_BASELINE)
            CENTER_BASELINE.setSelected(false);
        if (e!=ROMAN_BASELINE)
            ROMAN_BASELINE.setSelected(false);
        if (e!=BOLD)
            BOLD.setSelected(false);
    }
    private void ColorBackgroundSelction(Object e)
    {
        if (e!=bblue)
            bblue.setSelected(false);
        if (e!=bblack)
            bblack.setSelected(false);
        if (e!=bwhite)
            bwhite.setSelected(false);
        if (e!=bgreen)
            bgreen.setSelected(false);
        if (e!=bpink)
            bpink.setSelected(false);
        if (e!=bred)
            bred.setSelected(false);
        if (e!=byellow)
            byellow.setSelected(false);
    }
    private void ColorTextSelction(Object e)
    {
        if (e!=tblue)
            tblue.setSelected(false);
        if (e!=twhite)
            twhite.setSelected(false);
        if (e!=tblack)
            tblack.setSelected(false);
        if (e!=tgreen)
            tgreen.setSelected(false);
        if (e!=tred)
            tred.setSelected(false);
        if (e!=tpink)
            tpink.setSelected(false);
        if (e!=tyellow)
            tyellow.setSelected(false);
    }
    private void SizeSelection(Object e)
    {
        for (int i=0; i<nums.length; i++)
        {
            if (nums[i] != e)
                nums[i].setSelected(false);
        }
    }
    private void ThemesSelction(Object e)
    {
        if (e!=Nimbus)
            Nimbus.setSelected(false);
        if (e!=Metal)
            Metal.setSelected(false);
        if (e!=Windows)
            Windows.setSelected(false);
        if (e!=Windows_Classic)
            Windows_Classic.setSelected(false);
        if (e!=CDE_Motif)
            CDE_Motif.setSelected(false);
    }
}
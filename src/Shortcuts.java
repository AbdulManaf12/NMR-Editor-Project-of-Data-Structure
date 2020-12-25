import javax.swing.*;
import java.awt.*;

public class Shortcuts extends JFrame
{
    Shortcuts(){
        super("Shortcut");
        setSize(400,400);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        setAlwaysOnTop(true);
        getContentPane().setBackground(new Color(35, 110, 232));
        setType(Type.UTILITY);
        setLocation(420,120);
        _init_();
        setVisible(true);
    }
    private void _init_() {
        JLabel title = new JLabel("NMR Shortcuts");
        title.setFont(new Font(Font.SERIF,Font.BOLD,30));
        title.setBounds(80,0,300,100);
        add(title);
        String shortcutname[] = {"Name","Key","Description"};
        String shortcut[][] =
                {
                        {"Copy","Ctrl+C","Copy text"},
                        {"Cut","Ctrl+X","Cut text"},
                        {"Paste","Ctrl+V","Add text"},
                        {"Undo","Ctrl+Z","Reverse last action"},
                        {"Redo","Ctrl+Y","Reverse of Undo"},
                        {"Save","Ctrl+S","Save File"},
                        {"Save as","Alt+S","Save new File"},
                        {"New","Ctrl+N","Open new window"},
                        {"Open","Ctrl+O","Open File"},
                        {"Select All","Ctrl+A","Select whole text"},
                        {"Find","Ctrl+F","Find text"},
                        {"Replace","Ctrl+R","Replace Text"},
                        {"Close","Ctrl+W","Close Window"}
                };
        JTable table = new JTable(shortcut,shortcutname);
        table.setEnabled(false);
        table.setDragEnabled(false);
        table.setBounds(30,90,330,220);
        add(table);
    }
}

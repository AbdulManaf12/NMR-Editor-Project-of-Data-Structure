import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UndoRedo implements KeyListener
{
    private JFrame f;
    private AutoSuggest autoSuggest;
    private static Stack stack = new Stack(100);
    private static Stack redo = new Stack(50);

    public static final byte INSERT = 1;
    public static final byte CTRL_Z = 2;
    public static final byte CTRL_Y = 3;

    UndoRedo(JFrame f,AutoSuggest autoSuggest){
        this.f = f;
        this.autoSuggest = autoSuggest;
    }
    public void keyTyped(KeyEvent e) {
        if (Frames.file != null)
            f.setTitle("NMR Editor - " + Frames.file.getAbsolutePath());
        if (Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
            ChooseComand(INSERT);
        if (MenuBar.wordsuggetion.isSelected())
            autoSuggest.OpenWordsSuggestion(e);
    }
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            f.setTitle("NMR Editor - "+Frames.file.getAbsolutePath());
        }
        if ((e.getKeyCode() == KeyEvent.VK_W) &&
                ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0))
            Frames.Close();
        if ((e.getKeyCode() == KeyEvent.VK_Z) &&
                ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0))
            ChooseComand(CTRL_Z);
        else if ((e.getKeyCode() == KeyEvent.VK_Y) &&
                ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0))
            ChooseComand(CTRL_Y);
    }

    public void keyReleased(KeyEvent e) {
        if (MenuBar.wordsuggetion.isSelected())
            autoSuggest.Movement(e);
    }
    public static void ChooseComand(byte command){
        switch (command){
            case INSERT:
                setINSERT();
                break;
            case CTRL_Z:
                setCTRL_Z(stack.pop());
                break;
            case CTRL_Y:
                setCTRL_Y();
                break;
        }
    }
    public static void setINSERT(){
        stack.push(Frames.getTextArea().getText(),INSERT);
        redo.setEmpty();
    }
    private static void setCTRL_Z(Node n){
        if (n != null) {
            switch (n.command) {
                case INSERT:
                    Frames.getTextArea().setText(n.data);
                    redo.push(n.data, CTRL_Z);
                    break;
                case CTRL_Y:
                    Frames.getTextArea().setText(n.data);
                    stack.push(n.data, CTRL_Z);
                    break;
                case CTRL_Z:
                    setCTRL_Z(n.next);
                    break;
            }
        }
    }
    private static void setCTRL_Y() {
        if (!redo.isEmpty()){
            Node n = redo.pop();
            Frames.getTextArea().setText(n.data);
            stack.push(n.data,CTRL_Y);
        }
    }
}
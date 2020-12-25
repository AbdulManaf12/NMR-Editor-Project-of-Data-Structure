import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AutoSuggest
{
    private SuggestionPanel suggestion;
    private JTextArea textarea;

    AutoSuggest(){
        initUI();
    }
    public void showSuggestionLater() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showSuggestion();
            }

        });
    }
    protected void showSuggestion() {
        hideSuggestion();
        final int position = textarea.getCaretPosition();
        Point location;
        try {
            location = textarea.modelToView(position).getLocation();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            return;
        }
        String text = textarea.getText();
        int start = Math.max(0, position - 1);
        while (start > 0) {
            if (!Character.isWhitespace(text.charAt(start))) {
                start--;
            }
            else {
                start++;
                break;
            }
        }
        if (start > position) {
            return;
        }
        final String subWord = text.substring(start, position);
        if (subWord.length() < 1) {
            return;
        }
        suggestion = new SuggestionPanel(textarea, position, subWord, location,this);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textarea.requestFocusInWindow();
            }
        });
    }
    public void hideSuggestion() {
        if (suggestion != null) {
            suggestion.hide();
        }
    }
    protected void initUI() {
        JPanel panel = new JPanel(new BorderLayout());
        this.textarea = Frames.getTextArea();
    }
    public void OpenWordsSuggestion(KeyEvent e){
        if (e.getKeyChar()==KeyEvent.VK_BACK_SPACE)
            hideSuggestion();
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if (suggestion != null) {
                if (suggestion.insertSelection()) {
                    e.consume();
                    final int position = textarea.getCaretPosition();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                textarea.getDocument().remove(position - 1, 1);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
    public void Movement(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_DOWN && suggestion != null) {
            suggestion.moveDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && suggestion != null) {
            suggestion.moveUp();
        }
        else if (Character.isLetterOrDigit(e.getKeyChar())) {
            showSuggestionLater();
        }
        else if (Character.isWhitespace(e.getKeyChar())) {
            hideSuggestion();
        }
    }
}

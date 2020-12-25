import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SuggestionPanel
{
    private final JList<String> list;
    private final JPopupMenu popupMenu;
    private final String subWord;
    private JTextArea textarea;
    private final int insertionPosition;
    private int numberOfWordsInList = 10;
    private SuggestionPanel suggestion;
    private AutoSuggest wordAssist;

    public SuggestionPanel(JTextArea textarea, int position, String subWord, Point location,AutoSuggest wordAssist) {
        this.textarea = textarea;
        this.wordAssist = wordAssist;
        this.insertionPosition = position;
        this.subWord = subWord;
        suggestion=this;
        popupMenu = new JPopupMenu();
        popupMenu.removeAll();
        popupMenu.setOpaque(false);
        popupMenu.setBorder(null);
        list = createSuggestionList(position, subWord);
        if (list == null)
            return;
        popupMenu.add(list , BorderLayout.CENTER);
        int x = ((location.x+120) > textarea.getWidth())? textarea.getWidth()-130 : location.x;
        popupMenu.show(textarea, x, textarea.getBaseline(0, 0) + location.y);
    }

    public void hide() {
        popupMenu.setVisible(false);
        if (suggestion == this) {
            suggestion = null;
        }
    }

    private JList<String> createSuggestionList(final int position, final String subWord) {
        LinkedList data = searchForWord(subWord + "*", numberOfWordsInList);
        if (data.isEmpty()) {
            return null;
        }
        JList<String> assistList = new JList<>(data.getData());
        assistList.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        assistList.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        assistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assistList.setBackground(new Color(35, 110, 232));
        assistList.setForeground(Color.BLACK);
        assistList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    insertSelection();
                    wordAssist.hideSuggestion();
                    textarea.requestFocus();
                    UndoRedo.setINSERT();
                }
            }
        });
        return assistList;
    }
    public boolean insertSelection() {
        if (list.getSelectedValue() != null) {
            try {
                final String selectedSuggestion = list.getSelectedValue().substring(subWord.length()) + " ";
                textarea.getDocument().insertString(insertionPosition, selectedSuggestion, null);
                return true;
            }
            catch (Exception e1) { }
        }
        return false;
    }

    public void moveUp() {
        int index = Math.min(list.getSelectedIndex() - 1, 0);
        selectIndex(index);
    }
    public void moveDown() {
        int index = Math.min(list.getSelectedIndex() + 1, list.getModel().getSize() - 1);
        selectIndex(index);
    }
    private void selectIndex(int index) {
        final int position = textarea.getCaretPosition();
        list.setSelectedIndex(index);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textarea.setCaretPosition(position);
            }
        });
    }

    public LinkedList searchForWord(String searchCriteria, int numberOfWordsToReturn)
    {
        LinkedList foundList = new LinkedList(numberOfWordsToReturn);
        char a = (char) ((searchCriteria.charAt(0) > 96)?searchCriteria.charAt(0)-32:searchCriteria.charAt(0));
        String FilePath = "Words/"+ a +".txt";
        String regEx = searchCriteria.replace("?", ".").replace("-", ".").replace("*", ".*?").toLowerCase();
        try{
            InputStream fis = getClass().getClassLoader().getResourceAsStream(FilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.matches(regEx)) {
                    line = line.trim().toLowerCase();
                    foundList.addAtLast(line);
                    if (foundList.isFull())
                        break;
                }
            }
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return foundList;
    }
}
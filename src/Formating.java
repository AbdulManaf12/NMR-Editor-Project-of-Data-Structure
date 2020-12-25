import javax.swing.*;
import java.awt.*;

public class Formating
{
    private static JTextArea area;

    static {
        area = Frames.getTextArea();
    }
    public static void setFont(String name)
    {
        area.setFont(new Font(name,area.getFont().getStyle(),area.getFont().getSize()));
    }
    public static void setFontStyle(int style)
    {
        area.setFont(new Font(area.getFont().getName(),style,area.getFont().getSize()));
    }
    public static void setSize(int size)
    {
        area.setFont(new Font(area.getFont().getName(),area.getFont().getStyle(),size));
    }
    public static Color getBackgroundColor(){
        return area.getBackground();
    }
    public static Color getTextColor(){
        return area.getForeground();
    }
}

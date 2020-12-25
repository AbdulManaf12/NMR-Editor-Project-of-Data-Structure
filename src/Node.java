public class Node
{
    public String data;
    public byte command;
    public Node next;

    Node(String data,byte command){
        this.command = command;
        this.data = data;
    }
}

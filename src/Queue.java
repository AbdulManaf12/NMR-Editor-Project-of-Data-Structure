public class Queue
{
    class Node {
        private String data;
        private Node next;
        Node(String data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
    private int lenght = 10,counter=0;

    public void enqueue(String data){
        if (Find(data))
            return;
        if (isFull()){
            Node n = head;
            while (n.next.next != null)
                n = n.next;
            n.next = null;
            tail = n;
            counter--;
        }
        if (isEmpty()){
            head = new Node(data);
            tail = head;
        }
        else
        {
            tail.next = new Node(data);
            tail = tail.next;
        }
        counter++;
    }
    public String dequeue(){
        String data = head.data;
        head = head.next;
        counter--;
        return data;
    }
    public boolean isEmpty(){
        return head==null;
    }
    public boolean isFull(){
        return counter==lenght;
    }
    public int length(){
        return counter;
    }
    public String[] getData(){
        if (isEmpty())
            return null;
        else {
            String arrayList[] = new String[counter];
            Node n = head;
            int i = 0;
            while (n != null){
                arrayList[i++] = n.data;
                n = n.next;
            }
            return arrayList;
        }
    }
    public boolean Find(String data){
        Node start = head;
        while (start != null){
            if (start.data.equals(data))
                return true;
            start = start.next;
        }
        return false;
    }
}


public class LinkedList
{
    private class Node{
        Node next;
        String data;
        Node(String data){
            this.data = data;
        }
    }
    private Node head;
    private int counter = 0;
    private int length;

    LinkedList(){
        length = 10;
    }
    LinkedList(int length){
        this.length = length;
    }
    public void add(String data){
        if (isFull()){
            Removelast();
        }
        if (isEmpty()){
            head = new Node(data);
        }
        else {
            Node n = new Node(data);
            n.next = head;
            head = n;
        }
        counter++;
    }
    public void addAtLast(String s){
        if (isFull()){
            return;
        }
        if (isEmpty()){
            head = new Node(s);
            counter++;
        }
        else {
            Node n = head;
            while (n.next != null){
                n = n.next;
            }
            n.next = new Node(s);
            counter++;
        }
    }
    public String get(){
        if (isEmpty()){
            return "null";
        }
        else {
            String temp = head.data;
            head = head.next;
            counter--;
            return temp;
        }
    }
    public String getFromLast(){
        if (isEmpty()){
            return "null";
        }
        else {
            String temp;
            if (head.next == null){
                temp = head.data;
            }
            else if(head.next.next == null){
                temp = head.next.data;
            }
            else {
                Node n = head;
                while (n.next.next != null) {
                    n = n.next;
                }
                temp = n.next.data;
                n.next = null;
            }
            counter--;
            return temp;
        }
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
    public void setEmpty(){
        head = null;
    }
    public LinkedList getCopy(){
        LinkedList l = new LinkedList();
        l.head = head;
        return l;
    }
    private void Removelast(){
        Node n = head;
        while (n.next.next != null)
            n = n.next;
        n.next = null;
        counter--;
    }
    public boolean Find(String data,Node start){
        while (start != null){
            if (start.data.equals(data))
                return true;
            start = start.next;
        }
        return false;
    }
    public int lenght(){
        return counter;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public boolean isFull(){
        return counter == length;
    }
    public void RemoveDuplicates(){
        if (!isEmpty()){
            Node n = head;
            if (Find(n.data,n.next)) {
                head = head.next;
                counter--;
            }
            else{
                while (n.next != null){
                    if (Find(n.next.data,n.next.next)){
                        n.next = n.next.next;
                        counter--;
                    }
                    n = n.next;
                } }}
    }
}
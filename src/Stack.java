public class Stack
{
    private Node top;
    private int length,counter=0;

    Stack(int Size){
        this.length = Size;
    }
    public void push(String data,byte command){
        if (isFull()){
            Node n = top;
            while (n.next.next != null)
                n = n.next;
            n.next = null;
            counter--;
        }
        if (isEmpty()){
            top = new Node(data,command);
        }
        else {
            Node n = new Node(data, command);
            n.next = top;
            top = n;
        }
        counter++;
    }
    public Node pop(){
        if (!isEmpty()){
            Node n = top;
            top = top.next;
            counter--;
            return n;
        }
        return null;
    }
    public Node peek(){
        return top;
    }
    public void setEmpty(){
        top = null;
        counter = 0;
    }
    public boolean isEmpty(){
        return top == null;
    }
    public boolean isFull(){
        return counter == length;
    }
}
public interface StackInterface {
    public void push(Object o);    //works for size=1...works for size n
    public Object pop() throws EmptyStackException; //works
    public Object top() throws EmptyStackException; //works
    public boolean isEmpty(); //works
    public String toString();  //works
    }     

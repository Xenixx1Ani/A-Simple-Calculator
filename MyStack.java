public class MyStack{
        public int size;
        private Object S[];
        private int t = -1;//t indicates position of top-1(index position)
        public MyStack(){this(1);}
        public MyStack(int s){
           size = s; 
           S = new Object[size];//size set to value passed
        }
        public boolean isEmpty(){
            return(t<0);//true only if stack has no elements ie t=-1 (disregarding overflow)
        }//constant
        public void resize(int s){
            Object S1[]= new Object [size/2];
            for(int i=0;i<(size/2);i++){
                S1[i]=S[i];
            }
            S = new Object[size];
            for(int i=0;i<(size/2);i++){
                S[i]=S1[i];
            }    //resizes S in its scope
        }//O(n)
        public int size(){
            return size;
        }//constant
        public void push(Object o){
            if ((t+1) == size){
                size=size*2;
                resize(size);
            }
            S[++t] = o;//control flows here anyways after if
        }//cconstant(n if needs to be increased)
        public Object top() throws EmptyStackException {
            if (isEmpty()){
             throw new EmptyStackException();
             }
            return S[t];
        }//constant
        public Object pop() throws EmptyStackException {
            if (isEmpty()){
             throw new EmptyStackException();
             }
            Object elm=S[t];
            S[t--] = null; // Dereference S[top]
            return elm;
        }//constant
        public String toString(){
            String st = new String();
            st=null;
            if (t<=(-1)) {
                return "[]";
            }   //deals with empty case
            else {
            st="[";
            for(int i=t;i>0;i--){
                st=st+S[i] +", ";       
                 }
            st=st+S[0]+"]";  
        }
        return st;
        }
}//O(n)
class EmptyStackException extends RuntimeException{
    public EmptyStackException(){
        super("The Stack is Empty");
    }
}
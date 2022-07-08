public class Calculator{
    public int evaluatePostFix(String s) throws InvalidPostfixException {
        int result;
        MyStack nums= new MyStack();
        boolean mul=false;  //this is highlight and is important for multiole digits
        for(int i=0;i<s.length();i++){
            int n1,n2;
            char o = s.charAt(i);
            try{
            if(o>='0'&& o<='9'){
                if(mul){
                    int t=(s.charAt(i) - '0');            
                    int last=(Integer)nums.pop();
                    nums.push((last*10+t));          //this takes care of numbers with more than 1 digit
                }
                else{
                int t=(s.charAt(i) - '0');  //if a number is encountered it is stored in the stack
                nums.push(t);
                mul=true;}
            }
            
            else if((o=='+'||o=='-'||o=='*')&&(nums.size()>=2)){ //all legal characters here
                mul=false;          
               if(o=='+'){
                n1=(Integer)nums.pop();
                n2=(Integer)nums.pop();
                result=n1+n2;
                nums.push(result);
              }
               else if(o=='-'){
                n1=(Integer)nums.pop();
                n2=(Integer)nums.pop();
                result=n2-n1;
                nums.push(result);
              }
               else if(o=='*'){
                n1=(Integer)nums.pop();
                n2=(Integer)nums.pop();
                result=n1*n2;
                nums.push(result);
               }
            }


            
            else if(o==' ') {
                mul=false;
                continue;}
            else throw new InvalidPostfixException();//accounts for any other symbols and too little numbers
            }
            catch(EmptyStackException e){
                throw new InvalidPostfixException();  //catches empty stack and returns it as invalid postfix
            }
        
        }
        int res=(int)nums.pop();
        return res;
    }//O(n)

    public boolean precedance(char a,char b){
        int pre_a=0;
           if(a=='-'){
            pre_a=1;
           }
           else if(a=='+'){
            pre_a=1;
        }
           else if(a=='*'){
            pre_a=3;
        }
           int pre_b=0;
           if(b=='-'){
            pre_b=1;
            }
           else if(b=='+'){
            pre_b=1;
            }
           else if(b=='*'){
            pre_b=3;
            }
        return (pre_a>=pre_b);
    }

    public String convertExpression (String s) throws InvalidExprException{   
        boolean mul=false;
        String res;
        MyStack operator= new MyStack();
        String r="";//ensures r is empty
        try{
        for(int i=0;i<s.length();i++){
            char o = s.charAt(i);
           // System.out.println(o);
            if(o>='0'&& o<='9'){
                if(!mul)
                r=r+" "+o;
                else r=r+o;
                mul=true;

            }
            else if(o=='+'||o=='*'||o=='-'&&i>0){
                mul=false;
                if(operator.isEmpty()){
                    operator.push(o);
                    continue;
                }
                if(!precedance((char)operator.top(),o)){//pes_top<o
                    operator.push(o);
                continue;
            }

                if(precedance((char)operator.top(),o)){ //pres top>
                while(precedance((char)operator.top(),o)){
                    r=r+" "+operator.pop();
                    if(operator.isEmpty())break;
                }
                operator.push(o);
                continue;
            }
        }
            else if(o=='('){
                mul=false;
                operator.push(o);
            }
            else if(o==')'){
                mul=false;
                while((char)operator.top()!='('){
                    r=r+" "+operator.pop();
                }
                operator.pop();   //removes ()
            }
            else if(o==' '){
                mul=false;
                continue;
            }
        
            else throw new InvalidExprException();
    }
     if(!operator.isEmpty()){
        while(!operator.isEmpty()){
            if((char)operator.top()=='('){
                throw new InvalidExprException();
            }
            r=r+" "+operator.pop();
            
        }
    }
    if(!operator.isEmpty()){
        while(!operator.isEmpty()){
            if((char)operator.top()=='('){
                throw new InvalidExprException();
            }
            r=r+" "+operator.pop();
        }
    }
  res=r.substring(1);
evaluatePostFix(res);//optional...can be removesin case it is known that the expression is valid...done becasue it is easier to find errors in postfix expression
        }
catch(InvalidPostfixException E){
    throw new InvalidExprException();
}
    return res;
    }

}
class InvalidPostfixException extends RuntimeException{
        public InvalidPostfixException(){
            super("The Given postfix expression is Invalid");
        }
    }
class InvalidExprException extends RuntimeException{
        public InvalidExprException(){
            super("The Given expression is Invalid");
        }
}
package core.tricky;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void test(){
        try{
            System.out.println(doStuff(new String[]{"x"}));
        }catch (Exception e){
            System.out.println("exec");
        }
        doStuff(new String[]{"x"});
    }

    public static int doStuff(String[] args){
        return Integer.parseInt(args[0]);
    }
}

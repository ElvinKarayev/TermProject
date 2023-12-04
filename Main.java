import java.util.HashMap;
import java.util.LinkedList;
import Library.UserData.*;


public class Main {
    public static void main(String[] args) {
    
        LinkedList<HashMap<String,String>> Users=new LinkedList<>();
        //System.out.println(first.getIndex());
        Users=UserDataManagement.LoadFile();
        System.out.println(Users);
        
    }

}

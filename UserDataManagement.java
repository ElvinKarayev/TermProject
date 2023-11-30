import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class UserDataManagement{
    

    public static LinkedList<HashMap<String,String>> LoadFile(){
        LinkedList<HashMap<String,String>> allUsersData=new LinkedList<>();
        try (FileInputStream input = new FileInputStream("./Resources/UserDataBase.csv")) {
            StringBuilder reader=new StringBuilder();

            int a=input.read();

            while(a!=-1){
                reader.append((char)a);
                a=input.read();
            }

            String fullData=reader.toString();
            String[] splittedData=fullData.split("\n");
            String[] userData;
            for (int i=0;i<splittedData.length;i++){
                userData=splittedData[i].split(",");
                HashMap<String,String> UserInfo=new HashMap<>();
                UserInfo.put(userData[1], userData[2]);

                allUsersData.addLast(UserInfo);
            }
        } catch (IOException e) {
            System.out.println("Error:Couldn't read file");
            e.printStackTrace();
        } 
        return allUsersData;
        
    }  
    public static void addUser(){
        try (FileOutputStream a = new FileOutputStream("./Resources/UserDataBase.csv",true)) {
        } catch (IOException e) {
            System.out.println("Error:Couldn't add User");
            e.printStackTrace();
        }
    }






}
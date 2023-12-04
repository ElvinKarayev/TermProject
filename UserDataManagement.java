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
                UserInfo.put(userData[0], userData[1]);

                allUsersData.add(UserInfo);
            }
        } catch (IOException e) {
            System.out.println("Error:Couldn't read file");
            
            
        }
        return allUsersData; 
        
    }  
    //will return the hashmap of the user if it is found otherwise it will throw exception
    public static HashMap<String, String> getUser(String username) {
        LinkedList<HashMap<String, String>> allUsersData = LoadFile();

        for (HashMap<String, String> user : allUsersData) {
            if (user.containsKey(username)) {
                return user; 
            }
        }

        
        System.out.println("User not found");
        throw new userNotFound();
    }
    public static void addUser(User person){
        
            try (FileOutputStream writer = new FileOutputStream("./Resources/UserDataBase.csv",true)) {
                if(UserDataManagement.CheckDatabase(person)) throw new usrExstException();
                else{
                writer.write((person.toString()+"\n").getBytes());}
            } catch (IOException e) {
                System.out.println("Error:Couldn't add User");
                
            } catch(usrExstException e){
                System.out.println("Error:User already exist");
            }
    }
 
        
    public static boolean CheckDatabase(User e){
        int flag=0;//to check whether true or false
        LinkedList<HashMap<String,String>> Users;
        Users=UserDataManagement.LoadFile();//loads all Users from database
        for (HashMap<String,String> user : Users) {//checks whether username exist in the database or not
            if(user.containsKey(e.getUsername())){
                flag++;
                break;
            }
        }
        return flag>0?true:false;
    }
    public static void updateUserPassword(String new_pass, String username){
        LinkedList<HashMap<String, String>> allUsersData = LoadFile();
        for (HashMap<String, String> user : allUsersData) {
            if (user.containsKey(username)) {
                user.put(username, new_pass);
                break;
            }
        }
        try (FileOutputStream writer = new FileOutputStream("./Resources/UserDataBase.csv")) {
            for (HashMap<String, String> user : allUsersData) {
                String userInfo = user.keySet().iterator().next() + "," + user.values().iterator().next() + "\n";
                writer.write(userInfo.getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error: Couldn't update User");
        }
    }






}
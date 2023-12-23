package Library.UserData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class UserDataManagement {

    public static LinkedList<HashMap<String, String>> LoadFile() {
        
        LinkedList<HashMap<String, String>> allUsersData = new LinkedList<>();
        try (BufferedReader input=new BufferedReader(new FileReader("./Resources/UserDataBase.csv"))) {
            StringBuilder reader=new StringBuilder();
            String a=input.readLine();
            while(a != null){
                reader.append(a+'\n');
                a=input.readLine();
            }
            String fullData = reader.toString();
            String[] splittedData = fullData.split("\n");
            String[] userData;
            for (int i = 0; i < splittedData.length; i++) {
                userData = splittedData[i].split(",");
                HashMap<String, String> UserInfo = new HashMap<>();
                UserInfo.put(userData[0].trim(), userData[1].trim());


                allUsersData.add(UserInfo);
            }
        } catch (IOException e) {

            System.out.println("Error:couldn't read file");
        }
        return allUsersData;

    }

    // will return the hashmap of the user if it is found otherwise it will throw
    // error
    public static User getUser(String username) {

        LinkedList<HashMap<String, String>> allUsersData = LoadFile();

        for (HashMap<String, String> user : allUsersData) {
            if (user.containsKey(username)) {

                String password = user.get(username); // Get the password from the HashMap
                return new User(username, password);
            }
        }

        throw new UserNotFound();
    }

    public static int addUser(User person) {
        try (BufferedWriter writer=new BufferedWriter(new FileWriter("./Resources/UserDataBase.csv"))) {
            if(UserDataManagement.CheckDatabase(person)){
                throw new usrExstException();
            }
            else{
                writer.write((person + "\n"));
                return 1;
            }
        } catch (IOException e) {
            return -1;
        } catch (usrExstException e){
            return -2;
        }
    }

    public static boolean CheckDatabase(User e) {
        int flag = 0;// to check whether true or false
        LinkedList<HashMap<String, String>> Users;
        Users = UserDataManagement.LoadFile();// loads all Users from database
        for (HashMap<String, String> user : Users) {// checks whether username exist in the database or not
            if (user.containsKey(e.getUsername())) {

                flag++;
                break;
            }
        }
        return flag > 0 ? true : false;
    }

    public static void updateUserPassword(String new_pass, String username) {

        LinkedList<HashMap<String, String>> allUsersData = LoadFile();
        for (HashMap<String, String> user : allUsersData) {
            if (user.containsKey(username)) {
                user.put(username, new_pass);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Resources/UserDataBase.csv"))) {
            for (HashMap<String, String> user : allUsersData) {
                String userInfo = user.keySet().iterator().next() + "," + user.values().iterator().next() + "\n";
                writer.write(userInfo);
            }
        } catch (IOException e) {
            System.out.println("Error: Couldn't update User's password");
        }
    }

}
package Library.UserData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Handles the management of user data, including operations such as loading and saving user information.
 * This class interacts with the data layer to perform CRUD (Create, Read, Update, Delete) operations for user entities.
 * @author Elvin Garayev
 */
public class UserDataManagement {
    /**
     * Loads the user data from a persistent storage.
     * @return a List containing user data in the form of HashMaps.
     */
    public static LinkedList<HashMap<String, String>> LoadFile() {

        LinkedList<HashMap<String, String>> allUsersData = new LinkedList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("./Resources/UserDataBase.csv"))) {
            StringBuilder reader = new StringBuilder();
            String a = input.readLine();
            while (a != null) {
                reader.append(a + '\n');
                a = input.readLine();
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

    /**
     * Retrieves a User object based on the provided username.
     * 
     * @param username The username of the user to be retrieved.
     * @return A User object containing the user's details.
     * @throws userNotFound If the user with the specified username is not found.
     */
    public static User getUser(String username) throws userNotFound {

        LinkedList<HashMap<String, String>> allUsersData = LoadFile();

        for (HashMap<String, String> user : allUsersData) {
            if (user.containsKey(username)) {

                String password = user.get(username); // Get the password from the HashMap
                return new User(username, password);
            }
        }

        throw new userNotFound();
    }
    /**
     * Adds a new user to the user database.
     *
     * @param person The User object representing the user to be added.
     * @return An integer indicating the result of the operation:
     *         -1 if an IOException occurred while writing to the database file.
     *         -2 if the user already exists in the database.
     *         -3 if the username provided is invalid (does not start with an alphabetic character).
     *         -4 if the username length is less than 3 characters.
     *         -5 if the password provided is invalid (length is less than 8 characters).
     *          1 if the user was successfully added to the database.
     */
    public static int addUser(User person) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Resources/UserDataBase.csv", true))) {
            if (UserDataManagement.CheckDatabase(person)) {
                throw new usrExstException();
            } else if (person.getUsername() == null || person.getUsername().length() < 3) {
                throw new UsernameLengthException();
            } else if (!Character.isAlphabetic(person.getUsername().charAt(0))) {
                throw new InvalidUsernameException();
            } else if (person.getPassword().length() < 8) {
                throw new InvalidPasswordException();
            } else {
                writer.write((person + "\n"));
                return 1;
            }
        } catch (IOException e) {
            return -1;
        } catch (usrExstException e) {
            return -2;
        } catch (InvalidUsernameException iue) {
            return -3;
        } catch (UsernameLengthException ule) {
            return -4;
        } catch (InvalidPasswordException ipe) {
            return -5;
        }
    }
    /**
     * Checks if a user with the given username exists in the user database.
     *
     * @param e The User object representing the user to be checked.
     * @return {@code true} if a user with the provided username exists in the database,
     *         {@code false} otherwise.
     */
    public static boolean CheckDatabase(User e) {
        LinkedList<HashMap<String, String>> users = UserDataManagement.LoadFile(); // loads all users from the database
        // Checks if any user in the database has the same username as the provided user
        return users.stream().anyMatch(user->user.containsKey(e.getUsername())); 
    }
    /**
     * Updates a user's password in the user database.
     *
     * @param new_pass The new password to set for the user.
     * @param username The username of the user whose password needs to be updated.
     */
    public static void updateUserPassword(String new_pass, String username) {

        LinkedList<HashMap<String, String>> allUsersData = LoadFile();
        // Find the user with the provided username and update their password
        allUsersData.stream()
                    .filter(user -> user.containsKey(username))
                    .findFirst()
                    .ifPresent(user -> user.put(username, new_pass));
        // Write the updated user data back to the database file
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
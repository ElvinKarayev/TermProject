# Movie Library System

## Overview
The Movie Library System is a comprehensive application designed to manage movie collections and user data. It provides functionalities for managing movies, users, and watchlists, along with a user-friendly graphical interface for interaction.

## Features
- **User Management:** Handle user data, including registration, login, and user information management.
- **Movie Database:** Manage a collection of movies, including adding, removing, and searching for movies.
- **Watchlist Management:** Allows users to create and manage their personal watchlists of movies.
- **Graphical User Interface:** User-friendly interfaces for interacting with the system's functionalities.

## Modules
1. **Movie Management**
   - `Movie.java`: Represents a movie with attributes like title, director, etc.
   - `MovieDatabase.java`: Manages the collection of movies.
   - `MovieAlreadyAdded.java`, `movieNotExist.java`: Exceptions related to movie management.

2. **User Data Management**
   - `User.java`: Represents a user in the system.
   - `UserDataManagement.java`: Handles operations related to user data.
   - Exception classes like `InvalidPasswordException`, `InvalidUsernameException`, etc., for user-related error handling.

3. **Watchlist**
   - `Watchlist.java`: Manages a user's watchlist.
   - `WatchlistNotExist.java`: Exception handling for watchlist operations.

4. **Graphical User Interface (GUI)**
   - Classes like `AddMovieGUI.java`, `AdminPanelGUI.java`, `LoginGUI.java`, etc., providing the GUI for various system functionalities.

## Contributions
- **Ramiz**: Developed the `MovieDatabase` and `Movie` classes, and worked partially on `Watchlist`.
- **Elvin**: Implemented `UserDataManagement` and `User` classes, and all exception handling related to user data and worked partially on `Watchlist`.
- **Manaf**: Responsible for all `GUI` components, creating user-friendly interfaces for the system.

## Getting Started
- start the `Main.java`
- if you have a registered account already just type the username and password and login. if you don't follow the next step below
- click the `sign up` button and then register an account by typing Username and Password
- then if you wish you can either view movies or add to watchlist
- in the watchlist you can `delete` or `view` or even sort your movies based on `Running Time`, `Title`, `Release Year`
- if you want to log out and log in with another account there is a button log out you can click to that and you will be redirected to the log in page

## Link to the video
- https://adauniversity-my.sharepoint.com/:v:/g/personal/rmammadov16266_ada_edu_az/EUlmKd5i6IFDr9pz4N7Iwy0BPUFWrRJf6ZUVREpytA_r5w?e=8SBydy

public class Main {
    public static void main(String[] args) {
        MovieDatabase db = new MovieDatabase();

        // Adding movies
        db.addMovie(new Movie("Inception", "Christopher Nolan", 2010, 148));
        db.addMovie(new Movie("Interstellar", "Christopher Nolan", 2014, 169));

        // Attempting to add a duplicate movie
        db.addMovie(new Movie("Inception", "Christopher Nolan", 2010, 148));

        // Removing a movie
        db.removeMovie("Interstellar");


        // Getting details of a movie
        Movie interstellar = db.getMovieDetails("Inception");
        if (interstellar != null) {
            interstellar.displayInfo();
        } else {
            System.out.println("Movie not found");
        }
    }
}

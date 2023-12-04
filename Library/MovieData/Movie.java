package Library.MovieData;
public class Movie{


    private String title="";
    private String director="";
    private int releaseYear;
    private int runningTime;


    public Movie(String title, String director, int releaseYear, int runningTime){
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.runningTime = runningTime;
    }

    //Getters

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public String getTitle() {
        return title;
    }

    //Setters

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Director: " + director);
        System.out.println("Release Year: " + releaseYear);
        System.out.println("Running Time: " + runningTime + " minutes");
    }

    public String getMovieSummary() {
        return title + " (" + releaseYear + ") - Directed by " + director;
    }


}
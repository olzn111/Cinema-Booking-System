package data;

public class Movie {
    private String title;
    private String genre;
    private String startTime;
    private String theaterType; // Standard, Premium, Couple, IMAX

    public Movie(String title, String genre, String startTime, String theaterType) {
        this.title = title;
        this.genre = genre;
        this.startTime = startTime;
        this.theaterType = theaterType;
    }

    // Getter (화면 공유용)
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getStartTime() { return startTime; }
    public String getTheaterType() { return theaterType; }
}
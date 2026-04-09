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

    // Getter들 (이게 있어야 나중에 화면에 보여줌)
    public String getTitle() { return title; }
    public String getStartTime() { return startTime; }
}
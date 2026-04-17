import service.MovieService;
import service.ReservationService;
import service.SampleDataLoader;
import service.UserService;
import ui.ConsoleUi;
import ui.MovieConsoleUi;
import ui.SeatUi;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        ReservationService reservationService = new ReservationService();

        SampleDataLoader sampleDataLoader = new SampleDataLoader(movieService);
        sampleDataLoader.loadData();

        SeatUi seatUi = new SeatUi();
        MovieConsoleUi movieConsoleUi = new MovieConsoleUi(movieService, reservationService, seatUi);
        ConsoleUi consoleUi = new ConsoleUi(userService, movieConsoleUi);

        consoleUi.start();
    }
}
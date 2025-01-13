package application_adminscreens;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {
    private final StringProperty movie;
    private final StringProperty hall;
    private final StringProperty date;
    private final StringProperty session;

    public Schedule(String movie, String hall, String date, String session) {
        this.movie = new SimpleStringProperty(movie);
        this.hall = new SimpleStringProperty(hall);
        this.date = new SimpleStringProperty(date);
        this.session = new SimpleStringProperty(session);
    }

    public String getMovie() {
        return movie.get();
    }

    public void setMovie(String movie) {
        this.movie.set(movie);
    }

    public StringProperty movieProperty() {
        return movie;
    }

    public String getHall() {
        return hall.get();
    }

    public void setHall(String hall) {
        this.hall.set(hall);
    }

    public StringProperty hallProperty() {
        return hall;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getSession() {
        return session.get();
    }

    public void setSession(String session) {
        this.session.set(session);
    }

    public StringProperty sessionProperty() {
        return session;
    }
}

package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    Link homePage;
    LocalDate dateStart;
    LocalDate dateEnd;
    String title;
    String text;

    public Organization(Link homePage, LocalDate dateStart, LocalDate dateEnd, String title, String text) {
        Objects.requireNonNull(dateStart, "Date start was not choose");
        Objects.requireNonNull(dateEnd, "Date end was not choose");
        Objects.requireNonNull(title, "Title was not choose");
        this.homePage = homePage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization that)) return false;

        if (!Objects.equals(homePage, that.homePage)) return false;
        if (!dateStart.equals(that.dateStart)) return false;
        if (!dateEnd.equals(that.dateEnd)) return false;
        if (!title.equals(that.title)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + dateStart.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organiztion{" +
                "homePage=" + homePage +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

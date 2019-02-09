package models;


import java.util.List;

public class Dvd extends LibraryItem{
    private List<String> languages;
    private List<String> subtitles;
    private String producer;
    private List<String> actors;



    public Dvd(String isbn, String title, String sector, DateTime publicationDate,
               String producer, List<String> actors ,List<String> languages, List<String> subtitles) {
        super(isbn, title, sector, publicationDate);
        this.languages = languages;
        this.subtitles = subtitles;
        this.producer = producer;
        this.actors = actors;

    }
    public Dvd(){}

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "languages=" + languages +
                ", subtitles=" + subtitles +
                ", producer=" + producer +
                ", actors=" + actors +
                '}';
    }
}

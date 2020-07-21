package com.byron.pojo;

public class Movie {
    private String name;
    private String score;
    private String evaluatorNum;
    private String director;
    private String screenWriter;
    private String actor;
    private String type;
    private String filmMakingPlace;
    private String language;
    private String releaseDate;
    private String timeSpan;
    private String alias;

    public Movie() {
    }

    public Movie(String name, String score, String evaluatorNum, String director, String screenWriter, String actor, String type, String filmMakingPlace, String language, String releaseDate, String timeSpan, String alias) {
        this.name = name;
        this.score = score;
        this.evaluatorNum = evaluatorNum;
        this.director = director;
        this.screenWriter = screenWriter;
        this.actor = actor;
        this.type = type;
        this.filmMakingPlace = filmMakingPlace;
        this.language = language;
        this.releaseDate = releaseDate;
        this.timeSpan = timeSpan;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEvaluatorNum() {
        return evaluatorNum;
    }

    public void setEvaluatorNum(String evaluatorNum) {
        this.evaluatorNum = evaluatorNum;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenWriter() {
        return screenWriter;
    }

    public void setScreenWriter(String screenWriter) {
        this.screenWriter = screenWriter;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilmMakingPlace() {
        return filmMakingPlace;
    }

    public void setFilmMakingPlace(String filmMakingPlace) {
        this.filmMakingPlace = filmMakingPlace;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", evaluatorNum='" + evaluatorNum + '\'' +
                ", director='" + director + '\'' +
                ", screenWriter='" + screenWriter + '\'' +
                ", actor='" + actor + '\'' +
                ", type='" + type + '\'' +
                ", filmMakingPlace='" + filmMakingPlace + '\'' +
                ", language='" + language + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", timeSpan='" + timeSpan + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }

    public String toLine(){
        return name+"\t"+score+"\t"+evaluatorNum+"\t"+director+"\t"+
                screenWriter+"\t"+"actor"+"\t"+type+"\t"+filmMakingPlace+
                "\t"+language+"\t"+releaseDate+"\t"+timeSpan+"\t"+alias+"\r\n";
    }
}

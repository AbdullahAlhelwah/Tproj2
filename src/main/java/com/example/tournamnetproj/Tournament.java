package com.example.tournamnetproj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

abstract public class Tournament implements Serializable {
    // attributes
    private String name;
    private boolean isIndividual;
    private String sport;
    private ArrayList<Team> teams;
    private Date startDate;
    private Date endDate;
    private boolean hasFinished;
    private Team winner;
    protected ArrayList<Match> matches;

    // constructor
    public Tournament(String name, boolean isIndividual, String sport, Date startDate, Date endDate) {
        this.name = name;
        this.isIndividual = isIndividual;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = new ArrayList<>();
        this.hasFinished = false;
    }

    // another constructor without endDate; automatically set by the system
    public Tournament(String name, boolean isIndividual, String sport, Date startDate) {
        this.name = name;
        this.isIndividual = isIndividual;
        this.sport = sport;
        this.startDate = startDate;
        this.teams = new ArrayList<>();
        this.hasFinished = false;
    }

    // Getters
    public boolean getIsIndividual() {
        return isIndividual;
    }

    public String getName() {
        return name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getSport() {
        return sport;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int getNumberOfTeams() {
        return teams.size();
    }

    public boolean getHasFinished() {
        return hasFinished;
    }

    public Team getWinner() {
        return winner;
    }

    // Setters
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    // abstract methods
    public abstract void generateMatches(int restDays) throws Exception;

    public abstract void viewStanding();

    // non-abstract methods
    public void addTeam(Team t) {
        teams.add(t);
    }

    public Match getMatch(Team a, Team b) throws Exception {
        if (matches.size() == 0) throw new Exception("matches not generated ");
        for (Match m : matches) {
            if ((m.getTeam1().equals(a) && m.getTeam2().equals(b)) || (m.getTeam1().equals(b) && m.getTeam2().equals(a)))
                return m;
        }

        return null;
    }

    public String getDetails() {
        String details;
        if (this.getClass().getName() == "com.example.tournamnetproj.RoundRobin") {
            details = "Type: " + "RoundRobin" + "\n" +
                    "Name: " + name + "\n" +
                    "Sport: " + sport + "\n" +
                    "Start Date: " + startDate + "\n" +
                    "End Date: " + endDate + "\n" +
                    "Number of Teams: " + teams.size() + "\n" +
                    "Winner: " + winner + "\n";
        } else {
            details = "Type: " + "elimination" + "\n" +
                    "Name: " + name + "\n" +
                    "Sport: " + sport + "\n" +
                    "Start Date: " + startDate + "\n" +
                    "End Date: " + endDate + "\n" +
                    "Number of Teams: " + teams.size() + "\n" +
                    "Winner: " + winner + "\n";
        }

        return details;
    }
}
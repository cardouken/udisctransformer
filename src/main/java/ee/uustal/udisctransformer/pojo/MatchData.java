package ee.uustal.udisctransformer.pojo;

import ee.uustal.udisctransformer.enums.CompetitionAccessLevel;
import ee.uustal.udisctransformer.enums.CompetitionType;

import java.util.Calendar;

public class MatchData {

    private String name;
    private int id;
    private int type;
    private Calendar date;
    private boolean isTraining;
    private CompetitionAccessLevel accessLevel;
    private CompetitionType competitionType;

    public String getName() {
        return name;
    }

    public MatchData setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public MatchData setId(int id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public MatchData setType(int type) {
        this.type = type;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public MatchData setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public MatchData setTraining(boolean training) {
        isTraining = training;
        return this;
    }

    public CompetitionAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public MatchData setAccessLevel(CompetitionAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public MatchData setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
        return this;
    }
}

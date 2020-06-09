package ee.uustal.udisctransformer.api.request;

public class CreateMatchRequest {

    private int competitionType;
    private int courseId;

    public int getCompetitionType() {
        return competitionType;
    }

    public CreateMatchRequest setCompetitionType(int competitionType) {
        this.competitionType = competitionType;
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public CreateMatchRequest setCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }
}

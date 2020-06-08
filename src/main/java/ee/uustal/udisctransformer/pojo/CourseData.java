package ee.uustal.udisctransformer.pojo;

public class CourseData {

    private String courseName;
    private int courseId;

    public String getCourseName() {
        return courseName;
    }

    public CourseData setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public CourseData setCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }
}

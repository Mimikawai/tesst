package hungpt.com.vn;
public class Subject {


    //declaring variables

    private final int subjectId;
    private final String subjectCode;
    private final String subject;
    private final int teacherIds[];



    //initializing variables
    public Subject(int subjectId, String subjectCode, String subject, int teacherIds[]) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subject = subject;
        this.teacherIds = teacherIds;
    }

    public int getsubjectId() {
        return this.subjectId;
    }

    public String getsubjectCode() {
        return this.subjectCode;
    }

    public String getsubjectName() {
        return this.subject;
    }

    public int getRandomteacherId() {

        //randomizing
        int teacherId = teacherIds[(int) (teacherIds.length * Math.random())];
        return teacherId;
    }
}

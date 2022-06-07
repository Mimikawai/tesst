package hungpt.com.vn;
public class Class
{

    //declaring variables

    private final int classId;
    private final int studentCaseId;
    private final int subjectId;
    
    public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getTimecaseId() {
		return timecaseId;
	}

	public void setTimecaseId(int timecaseId) {
		this.timecaseId = timecaseId;
	}

	public int getStudentCaseId() {
		return studentCaseId;
	}

	public int getSubjectId() {
		return subjectId;
	}



	private int teacherId;
    private int timecaseId;
    private int roomId;

    //contractor

    public Class(int classId, int studentCaseId, int subjectId) {
        this.classId = classId;
        this.subjectId = subjectId;
        this.studentCaseId = studentCaseId;
    }

    //getters and setters

    

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getClassId() {
        return this.classId;
    }

    public int getRoomId() {
        return this.roomId;
    }

}

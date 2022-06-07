package hungpt.com.vn;
public class StudentCase {



    //declaring variables

    private final int caseId;
    private final int caseSize;
    private final int subjectIds[];
    
    
    
	public StudentCase(int caseId, int caseSize, int[] subjectIds) {
		super();
		this.caseId = caseId;
		this.caseSize = caseSize;
		this.subjectIds = subjectIds;
	}
	
	public int getCaseIds() {
		return caseId;
	}
	public int getCaseSize() {
		return caseSize;
	}
	public int[] getSubjectIds() {
		return subjectIds;
	}


    //initializing variables

    


}

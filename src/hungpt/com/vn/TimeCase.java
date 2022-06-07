package hungpt.com.vn;
public class TimeCase {



    //declaring variables
    private final int timecaseId;
    private final String timecase;



    //initialising variables
    public TimeCase(int timecaseId, String timecase){
        this.timecaseId = timecaseId;
        this.timecase = timecase;
    }



    public int getTimecaseId(){
        return this.timecaseId;
    }

    public String getTimecase(){
        return this.timecase;
    }
}

package hungpt.com.vn;
public class Teacher {


    //declaring variables
    private final int teacherId;
    private final String teacherName;
    private int preferedroom = 0;
    private int preferedtime = 0;



    //initializing variables

    public Teacher(int teacherId, String teacherName){
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Teacher(int teacherId, String teacherName, int preferedroom){
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.preferedroom = preferedroom;
    }

    public Teacher(int teacherId, String teacherName, int preferedroom, int preferedtime){
        this.preferedroom = preferedroom;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.preferedtime = preferedtime;
    }
    public int getteacherId(){
        return this.teacherId;
    }

    public String getTeacherName(){
        return this.teacherName;
    }

    public int getPreferedroom(){
        return  this.preferedroom;
    }

    public int getPreferedtime(){return this.preferedtime;}

}

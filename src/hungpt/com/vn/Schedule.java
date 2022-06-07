package hungpt.com.vn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schedule {
    private final HashMap<Integer, Classroom> rooms;
    private final HashMap<Integer, Teacher> teachers;
    private final HashMap<Integer, Subject> subjects;
    private final HashMap<Integer, StudentCase> cases;
    private final HashMap<Integer, TimeCase> timecases;
    private final HashMap<Integer, List<Class>> roomMap;
    private final HashMap<Integer, List<Class>> teaMap;
    private final HashMap<Integer, List<Class>> subjectMap;
    private final HashMap<Integer, List<Class>> caseMap;
    private Class classes[];

    public HashMap<Integer, List<Class>> getRoomMap() {
        return roomMap;
    }

    public HashMap<Integer, List<Class>> getTeaMap() {
        return teaMap;
    }

    public HashMap<Integer, List<Class>> getSubjectMap() {
        return subjectMap;
    }

    public HashMap<Integer, List<Class>> getCaseMap() {
        return caseMap;
    }

    private int numClasses = 0;


    public Schedule() {
        this.rooms = new HashMap<Integer, Classroom>();
        this.teachers = new HashMap<Integer, Teacher>();
        this.subjects = new HashMap<Integer, Subject>();
        this.cases = new HashMap<Integer, StudentCase>();
        this.timecases = new HashMap<Integer, TimeCase>();

        this.roomMap = new HashMap<>();
        this.teaMap = new HashMap<>();
        this.subjectMap = new HashMap<>();
        this.caseMap = new HashMap<>();
    }

    public Schedule(Schedule cloneable) {
        this.rooms = cloneable.getRooms();
        this.teachers = cloneable.getTeachers();
        this.subjects = cloneable.getSubjects();
        this.cases = cloneable.getCases();
        this.timecases = cloneable.getTimecases();

        this.roomMap = new HashMap<>();
        this.teaMap = new HashMap<>();
        this.subjectMap = new HashMap<>();
        this.caseMap = new HashMap<>();
    }

    private HashMap<Integer, StudentCase> getCases() {
        return this.cases;
    }

    private HashMap<Integer, TimeCase> getTimecases() {
        return this.timecases;
    }

    private HashMap<Integer, Subject> getSubjects() {
        return this.subjects;
    }

    private HashMap<Integer, Teacher> getTeachers() {
        return this.teachers;
    }



    public void addRoom(int roomId, String roomName, int capacity) {
        this.rooms.put(roomId, new Classroom(roomId, roomName, capacity));
    }



    public void addTeacher(int teacherId, String teacherName) {
        this.teachers.put(teacherId, new Teacher(teacherId, teacherName));
    }



    public void addTeacher(int teacherId, String teacherName, int preferedroom){
        this.teachers.put(teacherId, new Teacher(teacherId, teacherName, preferedroom));
    }



    public void addTeacher(int teacherId, String teacherName, int preferedroom, int preferedtime){
        this.teachers.put(teacherId, new Teacher(teacherId, teacherName, preferedroom, preferedtime));
    }


    public void addSubject(int SubjectId, String SubjectCode, String Subject, int teacherIds[]) {
        this.subjects.put(SubjectId, new Subject(SubjectId, SubjectCode, Subject, teacherIds));
    }



    public void addCase(int caseId, int caseSize, int SubjectIds[]) {
        this.cases.put(caseId, new StudentCase(caseId, caseSize, SubjectIds));
        this.numClasses = 0;
    }



    public void addTimecase(int timecaseId, String timecase) {
        this.timecases.put(timecaseId, new TimeCase(timecaseId, timecase));
    }



    public void createClasses(Individual individual) {

        Class classes[] = new Class[this.getNumClasses()];

        int chromosome[] = individual.getChromosome();
        int chromosomePos = 0;
        int classIndex = 0;

        for (StudentCase casee : this.getCasesAsArray()) {
            int subjectIds[] = casee.getSubjectIds();
            for (int subjectId : subjectIds) {

                Class newClass = new Class(classIndex, casee.getCaseIds(), subjectId);


                newClass.setTimecaseId(chromosome[chromosomePos]);
                chromosomePos++;


                newClass.setRoomId(chromosome[chromosomePos]);
                chromosomePos++;


                newClass.setTeacherId(chromosome[chromosomePos]);
                chromosomePos++;


                this.roomMap.putIfAbsent(newClass.getRoomId(), new ArrayList<>());
                this.caseMap.putIfAbsent(newClass.getStudentCaseId(), new ArrayList<>());
                this.subjectMap.putIfAbsent(newClass.getSubjectId(), new ArrayList<>());
                this.teaMap.putIfAbsent(newClass.getTeacherId(), new ArrayList<>());

                this.roomMap.get(newClass.getRoomId()).add(newClass);
                this.caseMap.get(newClass.getStudentCaseId()).add(newClass);
                this.subjectMap.get(newClass.getSubjectId()).add(newClass);
                this.teaMap.get(newClass.getTeacherId()).add(newClass);

                classes[classIndex] = newClass;

                classIndex++;

            }
        }

        this.classes = classes;
    }



    public Classroom getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Classroom) this.rooms.get(roomId);
    }

    public HashMap<Integer, Classroom> getRooms() {
        return this.rooms;
    }



    public Classroom getRandomRoom() {
        Object[] roomsArray = this.rooms.values().toArray();
        Classroom room = (Classroom) roomsArray[(int) (roomsArray.length * Math.random())];
        return room;
    }



    public Teacher getTeacher(int teacherId) {
        return (Teacher) this.teachers.get(teacherId);
    }



    public Subject getSubject(int subjectId) {
        return (Subject) this.subjects.get(subjectId);
    }



    public int[] getCaseSubjects(int caseId) {
        StudentCase cas = (StudentCase) this.cases.get(caseId);
        return cas.getSubjectIds();
    }


    public StudentCase getCase(int caseId) {
        return (StudentCase) this.cases.get(caseId);
    }



    public StudentCase[] getCasesAsArray() {
        return (StudentCase[]) this.cases.values().toArray(new StudentCase[this.cases.size()]);
    }



    public TimeCase getTimecase(int timecaseId) {
        return (TimeCase) this.timecases.get(timecaseId);
    }



    public TimeCase getRandomTimecase() {
        Object[] timecaseArray = this.timecases.values().toArray();
        TimeCase timecase = (TimeCase) timecaseArray[(int)(timecaseArray.length * Math.random())];
        return timecase;

    }



    public Class[] getClasses() {
        return this.classes;
    }



    public int getNumClasses() {
        if (this.numClasses > 0) {
            return this.numClasses;
        }
        int numClasses = 0;
        StudentCase cases[] = (StudentCase[]) this.cases.values().toArray(new StudentCase[this.cases.size()]);
        for (StudentCase cas : cases) {
            numClasses += cas.getSubjectIds().length;
        }
        this.numClasses = numClasses;

        return this.numClasses;
    }



    public int calcClashes(int size) {

        int clashes = 100;
        for (Class classA : this.classes) {
            //  room capacity
            int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
            int groupSize = this.getCase(classA.getStudentCaseId()).getCaseSize();
            if (roomCapacity < groupSize) {
                clashes = clashes-33*size;
            }

            //room occupied
            for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId()&& classA.getTimecaseId() == classB.getTimecaseId()&& classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }

            //professor available
            for (Class classB : this.classes) {
                if (classA.getTeacherId() == classB.getTeacherId() && classA.getTimecaseId() == classB.getTimecaseId() && classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }
            //professor preferred room check
            for (Class classB : this.classes) {
                int tmp_Tea= classB.getTeacherId();
                int tmp_Room=classB.getRoomId();
                if (this.getTeacher(tmp_Tea).getPreferedroom()== tmp_Room){
                    clashes++;
                }
            }
            // professor preferred time
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getTeacherId();
                int tmp_Time=classB.getTimecaseId();
                if (this.getTeacher(tmp_Prof).getPreferedtime()== tmp_Time){
                    clashes=clashes+2;
                }
            }
        }
        return clashes;
    }
}
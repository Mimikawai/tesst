package hungpt.com.vn;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ScheduleAlgo {

	public static Schedule initializeSchedule() {

		Schedule schedule = new Schedule();

		String roomCfg = ApplicationCfg.classRoom;
		int dem = ScheduleAlgo.DemKyTu(roomCfg, ',');
		String[] splitRoom = roomCfg.split(",");
		for (int i = 0; i <= dem; i++) {
			String[] room = splitRoom[i].split(":");
			String name = room[0];
			int siso = Integer.parseInt(room[1]);
			schedule.addRoom(i + 1, name, siso);
		}
		//
		//
		// schedule.addRoom(1, "A", 15); // ID lop, ten lop, si so
		// schedule.addRoom(2, "B", 30);
		// schedule.addRoom(4, "C", 20);
		// schedule.addRoom(5, "D", 25);
		// schedule.addRoom(3, "E", 20);

		String timeLost = ApplicationCfg.addTimeslot;
		int demTimeLost = ScheduleAlgo.DemKyTu(timeLost, ',');
		String[] splitTimeLost = timeLost.split(",");
		for (int i = 1; i <= demTimeLost; i++) {
			schedule.addTimecase(i + 1, splitTimeLost[i]);
		}

		// schedule.addTimeslot(1, "Monday 9:00 - 11:00");
		// schedule.addTimeslot(2, "Monday 11:00 - 13:00");
		// schedule.addTimeslot(3, "Monday 13:00 - 15:00");
		// schedule.addTimeslot(4, "Tuesday 9:00 - 11:00");
		// schedule.addTimeslot(5, "Tuesday 11:00 - 13:00");
		// schedule.addTimeslot(6, "Tuesday 13:00 - 15:00");
		// schedule.addTimeslot(7, "Wednesday 9:00 - 11:00");
		// schedule.addTimeslot(8, "Wednesday 11:00 - 13:00");
		// schedule.addTimeslot(9, "Wednesday 13:00 - 15:00");
		// schedule.addTimeslot(10, "Thursday 9:00 - 11:00");
		// schedule.addTimeslot(11, "Thursday 11:00 - 13:00");
		// schedule.addTimeslot(12, "Thursday 13:00 - 15:00");
		// schedule.addTimeslot(13, "Friday 9:00 - 11:00");
		// schedule.addTimeslot(14, "Friday 11:00 - 13:00");
		// schedule.addTimeslot(15, "Friday 13:00 - 15:00");
		
		
		schedule.addTeacher(1, "Pham Van A", 2, 8); 
		schedule.addTeacher(2, "Pham Van B", 2,6);
		schedule.addTeacher(3, "Pham Van C", 3,4);
		schedule.addTeacher(4, "Pham Van D", 1,2);
		schedule.addTeacher(5, "Pham Van E", 5, 6);
		schedule.addTeacher(6, "Pham Van F", 1, 11);
		// ID giao su ,
		// ten giao su
		// tiet uu tien

		String courseCfg = ApplicationCfg.addCourse;
		int demCourse = ScheduleAlgo.DemKyTu(courseCfg, ';');
		String[] splitCourse = courseCfg.split(";");
		for (int i = 0; i <= demCourse; i++) {
			String[] room = splitCourse[i].split(":");
			String maMonhoc = room[0];
			String tenMonhoc = room[1];
			String idGiaosu = room[2];
//			
//			System.out.println(maMonhoc);
//			System.out.println(tenMonhoc);
//			System.out.println(idGiaosu);

//			int demidGiaosu = ScheduleAlgo.DemKyTu(idGiaosu, ',');
			try {
				int demidGiaosu = ScheduleAlgo.DemKyTu(idGiaosu, ',');
				String[] splitidGiaosu = idGiaosu.split(",");
				if (demidGiaosu < 1) {
					int a = Integer.parseInt(splitidGiaosu[0]);
					schedule.addSubject(i + 1, maMonhoc, tenMonhoc, new int[] { a });
				} else if (demidGiaosu == 1) {
					int a = Integer.parseInt(splitidGiaosu[0]);
					int b = Integer.parseInt(splitidGiaosu[1]);
					schedule.addSubject(i + 1, maMonhoc, tenMonhoc, new int[] { a, b });
				} else if (demidGiaosu > 1 & demidGiaosu <= 2) {
					int a = Integer.parseInt(splitidGiaosu[0]);
					int b = Integer.parseInt(splitidGiaosu[1]);
					int c = Integer.parseInt(splitidGiaosu[2]);
					schedule.addSubject(i + 1, maMonhoc, tenMonhoc, new int[] { a, b, c });
				} else {
					int a = Integer.parseInt(splitidGiaosu[0]);
					int b = Integer.parseInt(splitidGiaosu[1]);
					int c = Integer.parseInt(splitidGiaosu[2]);
					int d = Integer.parseInt(splitidGiaosu[3]);
					schedule.addSubject(i + 1, maMonhoc, tenMonhoc, new int[] { a, b, c, d });
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		schedule.addCourse(1, "AA", "Algorithm", new int[] { 1, 2, 4 }); // ID mon hoc, ma mon hoc, ten mon hoc, ID giao su																		


		schedule.addCase(1, 10, new int[] { 1, 3, 4 });
		schedule.addCase(2, 30, new int[] { 2, 3, 5, 6 }); // ID nhom, so luong trong nhom, cac mon hoc trong nhom
		schedule.addCase(3, 18, new int[] { 3, 4, 5 });
		schedule.addCase(4, 25, new int[] { 1, 4, 7 });
		schedule.addCase(5, 20, new int[] { 2, 3, 5 });
		schedule.addCase(6, 22, new int[] { 1, 4, 5 });
		schedule.addCase(7, 16, new int[] { 1, 3 });
		schedule.addCase(8, 18, new int[] { 2, 6, 7 });
		schedule.addCase(9, 24, new int[] { 1, 6 });
		schedule.addCase(10, 25, new int[] { 3, 4, 7 });

		return schedule;
	}

	public static void PrintClassAll(Schedule schedule) {
		try {
			Class classes[] = schedule.getClasses();
			int classIndex = 1;
			SimpleDateFormat dateFor = new SimpleDateFormat("ddMMyyyyHHmmss");
			String stringDate = dateFor.format(new Date());
			FileWriter myWriter = new FileWriter("./result/result_" + stringDate + ".txt");

			for (Class bestClass : classes) {
				System.out.println("CLASS " + classIndex + ":");
				System.out.println("Subject: " + schedule.getSubject(bestClass.getSubjectId()).getsubjectName());
				System.out.println("STUDENT Case: " + schedule.getCase(bestClass.getStudentCaseId()).getCaseIds());
				System.out.println("ROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
				System.out
						.println("Teacher: " + schedule.getTeacher(bestClass.getTeacherId()).getTeacherName());
				System.out.println("TIMEcase: " + schedule.getTimecase(bestClass.getTimecaseId()).getTimecase());
				System.out.println("*****************************************************************");

				myWriter.write("CLASS " + classIndex + ":" + "\r\n");
				myWriter.write("Subject: " + schedule.getSubject(bestClass.getSubjectId()).getsubjectName() + "\r\n");
				myWriter.write("STUDENT Case: " + schedule.getCase(bestClass.getStudentCaseId()).getCaseIds() + "\r\n");
				myWriter.write("ROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber() + "\r\n");
				myWriter.write(
						"Teacher: " + schedule.getTeacher(bestClass.getTeacherId()).getTeacherName() + "\r\n");
				myWriter.write("TIMEcase: " + schedule.getTimecase(bestClass.getTimecaseId()).getTimecase() + "\r\n");
				myWriter.write("*****************************************************************" + "\r\n");
				classIndex++;
			}
			System.out.println("Successfully wrote to the file.");
			myWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Class> getClasses(Schedule schedule, String type, int id) {
		switch (type) {
		case "ROOM":
			return schedule.getRoomMap().get(id);
		case "PROF":
			return schedule.getTeaMap().get(id);
		case "MODULE":
			return schedule.getSubjectMap().get(id);
		case "GROUP":
			return schedule.getCaseMap().get(id);
		default:
			return null;
		}
	}

	public static void PrintClasses(Schedule schedule, String type, int id) {
		List<Class> classes = getClasses(schedule, type, id);
		for (Class bestClass : classes) {
			printClass(schedule, bestClass);
		}
	}

	public static void printClass(Schedule schedule, Class bestClass) {
		System.out.println("Subject: " + schedule.getSubject(bestClass.getSubjectId()).getCourseName());
		System.out.println("Student Case: " + schedule.getCase(bestClass.getStudentCaseId()).getCaseIds());
		System.out.println("Classroom: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
		System.out.println("Teacher: " + schedule.getTeacher(bestClass.getTeacherId()).getTeacherName());
		System.out.println("Time Case: " + schedule.getTimecase(bestClass.getTimecaseId()).getTimecase());
		System.out.println("*********************************************************************");
	}

	public static int DemKyTu(String chuoiCandem, char kytuDem) {
		int count = 0;
		for (int i = 0; i < chuoiCandem.length(); i++) {
			if (chuoiCandem.charAt(i) == kytuDem) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {

		Schedule schedule = initializeSchedule();

		GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);

		Population population = ga.initializingPopulation(schedule);

		ga.calcPopulation(population, schedule);

		int generation = 1;

		System.out.println("Hệ thống bắt đầu tính toán vui lòng chờ trong ít phút!");
		while (ga.isTerminating(generation, 100) == false && ga.isTerminating(population) == false) {

			System.out
					.println("Generation No." + generation + " Best fitness: " + population.getFittest(0).getFitness());

			population = ga.crossoverPopulation(population);

			population = ga.mutatingPopulation(population, schedule);

			ga.calcPopulation(population, schedule);

			generation++;
		}

		// Print fitness
		schedule.createClasses(population.getFittest(0));
		System.out.println();
		System.out.println("Solution found in " + generation + " generations");
		System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
		System.out.println("Clashes: " + schedule.calcClashes(100));

		PrintClassAll(schedule);

	}
}
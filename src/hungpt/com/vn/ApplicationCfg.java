package hungpt.com.vn;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ApplicationCfg extends BaseConfig {

	private final static String CONFIG_PATH 	= "./config/application.cfg";
	public static String	classRoom;
	public static String	addTimeslot;
	public static String	addCourse;
	@Override
	protected void getAllParas() {
		classRoom 				= properties.getProperty("classRoomDoc", classRoom);
		addTimeslot				= properties.getProperty("addTimeslot", addTimeslot);
		
		addCourse				= properties.getProperty("addCourse", addCourse);
	}
	
	public ApplicationCfg(String configPath) {
		super(configPath);
	}

	private static ApplicationCfg _shareInstance = null;

	public static ApplicationCfg shareInstance() {
		if (_shareInstance == null) {
//			System.out.println(CONFIG_PATH);
			new ApplicationCfg(CONFIG_PATH);
		}
		return _shareInstance;
	}

	static {
		ApplicationCfg.shareInstance();
	}
	

//	public static void main(String[] args) 
//    { 	
//		System.out.println(ApplicationCfg.addCourse);
//		String courseCfg = ApplicationCfg.addCourse;
//		int demCourse = ScheduleAlgo.DemKyTu(courseCfg, ';');
//		String[] splitCourse = courseCfg.split(";");
//		for (int i = 0; i <= demCourse; i++) {
//			String[] room = splitCourse[i].split(":");
//			String maMonhoc = room[0];
//			String tenMonhoc = room[1];
//			String idGiaosu = room[2];
//			
//			System.out.println(maMonhoc);
//			System.out.println(tenMonhoc);
//			System.out.println(idGiaosu);
//		}
		

//		System.out.println(a);
////		String room = ApplicationCfg.classRoom;
////    	int asoRoom = ScheduleAlgo.DemKyTu(room, ',');
////    	String[] name = room.split(",");
//////		System.out.println(name[0]);
//////		System.out.println(name[1]);
////    	for (int i = 0; i <= asoRoom; i++) {
////    		String[] name = room.split(",");
////    		System.out.println(name[i]);
////    		System.out.println(name[1]);
////    		room = name[1];
//////    		schedule.addRoom(i, "E", 20);
//		}
//    }
}

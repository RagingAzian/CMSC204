import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class CourseDBManager implements CourseDBManagerInterface {
	
	private CourseDBStructure CDS;
	
	CourseDBManager() {
		CDS = new CourseDBStructure(20);
	}

	@Override
	public void add(String courseID, int CRN, int noOfCreds, String roomNo, String name) {
		CDS.add(new CourseDBElement(courseID, CRN, noOfCreds,roomNo, name));
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return CDS.get(crn);
		} catch (IOException e) {
			return null;
		}	
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
			
		Scanner scan = new Scanner(input);
		while(scan.hasNextLine()) {
			String[] temp = scan.nextLine().split("\\s");
			String instructor = "";
			for(int i = 4; i < temp.length; i ++)
				instructor += temp[i] + " ";
			add(temp[0], Integer.valueOf(temp[1]), Integer.valueOf(temp[2]), temp[3], instructor.trim());			
		}
		scan.close();
	}

	@Override
	public ArrayList<String> showAll() {
		return CDS.showAll();
	}
}
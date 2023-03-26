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
	/**
	 * Adds a course (CourseDBElement) with the given information to
	 * CourseDBStructure.
	 * 
	 * @param id         course id
	 * @param crn        course crn
	 * @param credits    number of credits
	 * @param roomNum    course room number
	 * @param instructor name of the instructor
	 */
	public void add(String courseID, int CRN, int noOfCreds, String roomNo, String name) {
		CDS.add(new CourseDBElement(courseID, CRN, noOfCreds, roomNo, name));
	}

	@Override
	/**
	 * finds CourseDBElement based on the crn key
	 * 
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	public CourseDBElement get(int crn) {
		try {
			return CDS.get(crn);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	/**
	 * Reads the information of courses from a test file and adds them to the
	 * CourseDBStructure data structure
	 * 
	 * @param input input file
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException {

		Scanner scan = new Scanner(input);
		while (scan.hasNextLine()) {
			String[] temp = scan.nextLine().split("\\s");
			String instructor = "";
			for (int i = 4; i < temp.length; i++)
				instructor += temp[i] + " ";
			add(temp[0], Integer.valueOf(temp[1]), Integer.valueOf(temp[2]), temp[3], instructor.trim());
		}
		scan.close();
	}

	@Override
	/**
	 * @return an array list of string representation of each course in the data
	 *         structure separated by a new line. Refer to the following example:
	 *         Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular
	 *         Room:SC100 Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody
	 *         Room:SC200
	 */
	public ArrayList<String> showAll() {
		return CDS.showAll();
	}
}
public class CourseDBElement implements Comparable<CourseDBElement> {
	String courseID;
	int CRN;
	int noOfCreds;
	String roomNo;
	String name;

	int hashcode;

	/**
	 * CourseDBElement Constructor
	 * 
	 * @param courseID
	 * @param CRN
	 * @param noOfCreds
	 * @param roomNo
	 * @param name
	 */
	public CourseDBElement(String courseID, int CRN, int noOfCreds, String roomNo, String name) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.noOfCreds = noOfCreds;
		this.roomNo = roomNo;
		this.name = name;
	}

	/**
	 * CourseDBElement constructor
	 */
	public CourseDBElement() {
		courseID = "";
		CRN = 0;
		noOfCreds = 0;
		roomNo = "";
		name = "";
	}

	@Override
	/**
	 * Compares CourseDBElement objects
	 */
	public int compareTo(CourseDBElement element) {
		if (this.getCRN() == element.getCRN())
			return 0;
		return 1;
	}

	/**
	 * Return String representation of CourseDBElement object
	 */
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + noOfCreds + " Instructor:" + name + " Room:"
				+ roomNo;
	}

	/**
	 * Sets the CRN
	 * 
	 * @param CRN
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
	 * Gets the CRN
	 * 
	 * @return
	 */
	public int getCRN() {
		return CRN;
	}

	/**
	 * Gets the Course ID
	 * 
	 * @return
	 */
	public String getID() {
		return courseID;
	}

	/**
	 * Gets the room number
	 * 
	 * @return
	 */
	public String getRoomNum() {
		return roomNo;
	}
}

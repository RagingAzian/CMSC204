import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {
	private int tableSize;
	private LinkedList<CourseDBElement>[] hashTable;

	/**
	 * Constructor for CourseDBStructure which calculates the hashTable size
	 * 
	 * @param num
	 */
	public CourseDBStructure(int num) {
		int temp = (int) (num / 1.5);
		tableSize = findNextPrime(temp);
		hashTable = new LinkedList[tableSize];
		for (int x = 0; x < tableSize; x++) {
			hashTable[x] = new LinkedList<CourseDBElement>();
		}
	}

	/**
	 * Constructor for CourseDBStructure with String and default size
	 * 
	 * @param s
	 * @param size
	 */
	public CourseDBStructure(String s, int size) {
		this.tableSize = size;
		hashTable = new LinkedList[tableSize];
		for (int x = 0; x < tableSize; x++) {
			hashTable[x] = new LinkedList<CourseDBElement>();
		}
	}

	/**
	 * Adds a CourseDBElement object to the CourseDBStructure using the hashcode of
	 * the CourseDatabaseElemen object's crn value. If the CourseDatabaseElement
	 * already exists, exit quietly
	 * 
	 * @param element the CourseDBElement to be added to CourseDBStructure
	 */
	public void add(CourseDBElement element) {
		int index = Integer.toString(element.getCRN()).hashCode() % tableSize;
		if (hashTable[index].isEmpty()) {
			hashTable[index].add(element);
		} else {
			for (CourseDBElement e : hashTable[index]) {
				if (e.getCRN() == element.getCRN()) {
					hashTable[index].set(0, element);
				}
			}
		}
	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	public CourseDBElement get(int crn) throws IOException {
		int hashCode = Integer.toString(crn).hashCode();
		int index = hashCode % tableSize;
		if (hashTable[index] == null) {
			throw new IOException();
		}
		for (CourseDBElement e : hashTable[index]) {
			if (e.getCRN() == crn) {
				return e;
			}
		}
		throw new IOException();
	}

	/**
	 * @return an array list of string representation of each course in the data
	 *         structure separated by a new line. Refer to the following example:
	 *         Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular
	 *         Room:SC100 Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody
	 *         Room:SC200
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> courseList = new ArrayList<>();
		for (LinkedList<CourseDBElement> list : hashTable) {
			if (list != null) {
				for (CourseDBElement e : list) {
					courseList.add(e.toString());
				}
			}
		}
		return courseList;
	}

	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the
	 * array)
	 */
	public int getTableSize() {
		return tableSize;
	}

	/**
	 * Method to check if a number is prime or not
	 * 
	 * @param num
	 * @return
	 */
	public boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for (int x = 2; x < num; x++) {
			if (num % x == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to find the next prime for hashtable size
	 * 
	 * @param num
	 * @return
	 */
	public int findNextPrime(int num) {
		while (!isPrime(num) || num % 4 != 3) {
			num++;
		}
		return num;
	}
}
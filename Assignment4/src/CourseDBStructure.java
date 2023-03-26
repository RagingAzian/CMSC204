import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{
    private int size;
    private LinkedList<CourseDBElement>[] hashTable;

    public CourseDBStructure(int n){
        int temp = (int)(n / 1.5);
        size = findNextPrime(temp);
        hashTable = new LinkedList[size];
        for(int x= 0;x<size;x++){
            hashTable[x]=new LinkedList<CourseDBElement>();
        }
    }

    public CourseDBStructure(String s, int size){
        this.size = size;
        hashTable = new LinkedList[size];
        for(int x= 0;x<size;x++){
            hashTable[x]=new LinkedList<CourseDBElement>();
        }
    }

    public void add(CourseDBElement element) {
        int index = Integer.toString(element.getCRN()).hashCode() % size;
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

    public CourseDBElement get(int crn) throws IOException {
        int hashCode = Integer.toString(crn).hashCode();
        int index = hashCode % size;
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

    public int getTableSize(){
        return size;
    }
    
    private boolean isPrime(int num) {
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
    private int findNextPrime(int n) {
        while (!isPrime(n)||n%4!=3) {
            n++;
        }
        return n;
    }
}
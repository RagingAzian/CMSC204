import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{
    private int tableSize;
    private LinkedList<CourseDBElement>[] hashTable;

    public CourseDBStructure(int n){
        int size = (int)(n / 1.5);
        tableSize = findNextPrime(size);
        hashTable = new LinkedList[tableSize];
    }

    public CourseDBStructure(String s, int size){
        this.tableSize = size;
        hashTable = new LinkedList[tableSize];
    }

    public void add(CourseDBElement element){
        int hashcode = element.getCRN()%tableSize;
        if(hashTable[hashcode]==null){
            hashTable[hashcode].add(element);
            return;
        }
        else{
            hashTable[hashcode].set(0,element);
        }
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
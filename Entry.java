
/**
 *
 * @author Kelsey
 */
public class Entry {
    int count, int attributeNum;
    String value;
    
    public Entry(String val, int attNum) {
        count = 1;
        value = val;
        attributeNum = attNum;
    }
    
    public void countIncrement() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getValue() {
        return value;
    }
    
    public int getAttNum() {
        return attributeNum;
    }
}

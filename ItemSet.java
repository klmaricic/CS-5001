

/**
 *
 * @author Kelsey
 */
public class ItemSet {
    int count;
    List itemList<Entry> = new ArrayList()<>;
    
    public ItemList() {
        count = 0;
    }
    
    public void countIncrement() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
    
    public ArrayList getItemList() {
        return itemList;
    }
}

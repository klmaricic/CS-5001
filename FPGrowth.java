

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author Kelsey
 */
public class FPGrowth {
    private int numAttributes, numInstances, minCount;
    private String file, line = "";
    private BufferedReader br = null;
    private ArffLoader.ArffReader arff;
    private Instances data;
    private List<Entry> frequentSet = new ArrayList<>();
    private List<ArrayList> dataSet = new ArrayList<>();
    
    public FPGrowth(String filePath, int minCoverage){
        file = filePath;
        minCount= minCoverage;
        
        try{
            br = new BufferedReader(new FileReader(file));  
        } catch(FileNotFoundException e) {}
        try {
            arff = new ArffLoader.ArffReader(br);
        } catch (IOException ex) {
            Logger.getLogger(Apriori.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data = arff.getData();
        data.setClassIndex(data.numAttributes() - 1);
        
        numAttributes = data.numAttributes();
        numInstances = data.numInstances();
        
        System.out.println((data.toString()));
    }
    
    public void createFrequentSet() {
        //Loops through the dataset and creates an array containing every value and counts how often that value appears
        for(int i = 0; i < numInstances; i++) {
            for(int j = 0; j < numAttributes; j++) {
                //If that instance value is not already in the array, then add it
                if(!frequentSet.contains(new Entry(data.instance(i).stringValue(j), j))) {
                    frequentSet.add(new Entry(data.instance(i).stringValue(j), j));
                }
                //else increment the count of that instance value in the array
                else {
                    int index = frequentSet.indexOf(new Entry(data.instance(i).stringValue(j), j));
                    frequentSet.get(index).countIncrement();
                }
            }
        }
        
        //removes all entries that don't meet the minimum coverage
        for(int i = frequentSet.size()-1; i >= 0; i--) {
            if(frequentSet.get(i).getCount() < minCount) {
                frequentSet.remove(i);
            }
        }
        
        //Organizes the entries from highest coverage to lowest
        MergeSort mergeSorter = new MergeSort();
        mergeSorter.sort(frequentSet);
    }
    
    public void sortInstances() {
        for(int i = 0; i < numInstances; i++) {
            //Creates an ArrayList for each instance and stores it in the dataSet ArrayList
            dataSet.add(new ArrayList());
            
            //Adds the values that meet the coverage minimum to the corresponding instance ArrayList in order of descending coverage
            for(int j = 0; j < frequentSet.size(); j++) {
                for(int k = 0; k < numAttributes; k++) {
                    //if the value in the arff dataSet equals the value in the frequentSet and has same attribute number
                    if(data.instance(i).stringValue(k).equals(frequentSet.get(j).getValue()) && k == frequentSet.get(j).getAttNum()) {
                        dataSet.get(dataSet.size()-1).add(new Entry(data.instance(i).stringValue(k), k));
                    }
                }
            }
        }
    }
	
	public void createFPTree() {
		
	}
}

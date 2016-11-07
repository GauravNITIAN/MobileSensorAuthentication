package com.nitp.humansensor;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import net.sf.javaml.classification.tree.RandomForest;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
import android.os.Environment;



public class AlgorithmML {

	static int random = 100;
    static Random rg=new Random(random);
    
    
      
      
      
     public static double BuildRandomForest(String Train,String test,int featues,int treeCount) throws IOException
    {
        RandomForest rn = new RandomForest(treeCount, true, featues, rg);
         //This Will load the Data Set from the Train Dataset 
        // featues is no of feature vector excluding class level 
        // Comma seperated features it represents
        
        // Read the file here 
        
        File mtest = new File(Environment
 	            .getExternalStorageDirectory(), test);
        
        File mtrain = new File(Environment
 	            .getExternalStorageDirectory(), Train);
        
        
        
             
        
        Dataset data = FileHandler.loadDataset(mtrain, featues, ",");
        
        // Here it will built the Classifer as Random Forest
        rn.buildClassifier(data);
        
        // Testing data to find out the Accracy or labels
        Dataset dataForClassification = FileHandler.loadDataset(mtest, featues, ",");
        
         /*
         A PerformanceMeasure is a wrapper around the values for the true positives, 
        true negatives, false positives and false negatives. This class also provides
        a number of convenience method to calculate number of aggregate measures like 
        accuracy, f-score, recall, precision, sensitivity, specificity,
        */
       
       //Write Onliy if We have permmission to write it into the file
       //means if we set the witeInfile to true the the output labels will be put into the file
     
       double res = Test(dataForClassification,rn);
       
        
     return res;   
    }
    // This is used to Write the Predicted Class Labels In the File 
    public static double Test(Dataset Testfile,RandomForest rn) throws IOException
    {
    	
      double accuracy  = 0;  // We compute the accracy based on this we give the result
      int count = 0;  // count the test sample
       
        for(int i=0;i<Testfile.size();i++)
        {
       
      
        // Instance is the input vector that may be traindata or testdata depending what you 
         // have supplied for testing dataset
            // Here it will get the instance and classify the output label that will be stored in a
       Instance instance =  Testfile.instance(i);
       String a =(String) rn.classify( instance);
       
       
       
      accuracy +=Double.parseDouble(a);
      count++;
       
       
       
     
    }
        
       accuracy = accuracy/count;
       
       // put the score as accracy
       
       	
    	   
       return accuracy;
        
    }
    
    
      
    
	
public static void main(String args[]) throws IOException
{
	// Taking the command line argumnets

	
	
	if(args.length > 3)
	{
	String train = args[0];
	String test= args[1];
	int featues = Integer.parseInt(args[2]);
	int treeCount = Integer.parseInt(args[3]);
	
	AlgorithmML.BuildRandomForest(train, test, featues, treeCount);
}
	
}
	


}



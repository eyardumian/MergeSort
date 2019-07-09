import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
/**
 *Application to sort an array of random numbers. 
 *
 *Program 1 
 *@author Erika Yardumian - CPSC - 3273
 *@version 3/29/19
 */

public class CollectDataTwo {
      
   static void Merge(int A[], int p, int q, int r) {
      //System.out.println(Arrays.toString(A));
      //System.out.println(p);
      //System.out.println(q);
      //System.out.println(r);
      int n1 = q - p + 1;
      int n2 = r - q;
      int L[] = new int[n1 +1];
      int R[] = new int[n2 +1];
      for (int i = 0; i < n1; i++) {
         L[i] = A[p + i];
      }
      for (int j = 0; j < n2; j++) {
         R[j] = A[q +1 +j];
      }
      L[n1] = 100000; 
      R[n2] = 100000; 
      int i = 0;
      int j = 0;
      int k = p;
      for (k = p; k < r + 1; k++) {
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i = i + 1;
         }
         else {
            A[k] = R[j];
            j = j+1;
         }
         //System.out.println(Arrays.toString(A));
      
      }
              
   }
   
   static void MergeSort(int A[],int p, int r) {
      if (p < r) {
         int q = (p + r)/2;
         MergeSort(A, p, q);
         MergeSort(A, q + 1, r);
         Merge(A, p, q, r);
      }
   }

   
   public static void main(String[] args) {
   //Generates random numbers.
      Random random = new Random();
   //Array to store 1000 random values.
      int[] G = new int[100000];
      int[] A = new int[100000];
      int maxvalue = 100000;
      int counter = 0;
      int n;
      ArrayList<Long> time = new ArrayList<Long>(counter);
      ArrayList<Long> size = new ArrayList<Long>(counter);
      ArrayList<Long> ExecutionTime = new ArrayList<Long>(counter);
      //Generates the random values array.         
      for (int i = 0; i < G.length; i++) {
         G[i] = random.nextInt(maxvalue);
      }        
      for (n = 10; n < G.length + 1; n += 1000) { 
      //Copies values from array G to array A.
         for (int k = 0; k < n; k++) {
            A[k] = G[k];
         }
         System.out.println(Arrays.toString(A));
      
         //Start timing 
         long startTime = System.nanoTime();
      //Sorts array A. 
         MergeSort(A, 0, n - 1);
      
      //End timing.
         long endTime = System.nanoTime();
         counter++;
         long totalTime = endTime - startTime;
         long log = (long)(Math.log(n)/Math.log(2));
         long ExTime = (totalTime/((long)n * log));
         //System.out.println(totalTime);
         //System.out.println(n);
         //System.out.println(ExTime);
         System.out.println(Arrays.toString(A));
      
         time.add(totalTime);
         size.add((long)n);
         ExecutionTime.add(ExTime);
      }
   
      File F = new File("Times.txt");
      try {
         PrintWriter printWriter = new PrintWriter(F);
         for (int i = 0; i < counter; i++) {
            printWriter.println(size.get(i) + ", " + ExecutionTime.get(i));
         }
         printWriter.close();
      }
      catch (FileNotFoundException ex) {
         System.out.println("File not found.");
      }
   }
}

/*************************************************************************************************************\
*   FileName: GenAlg.java              Author: Michael Kelly                                                  *
*                                                                                                             *
*   Puprose:	A simple genetic algorithm that uses the Population (and thus the Individual) class to        *
*       simulate generations of the population after certain factors like cross-over, mutation, and fitness   *
*       selection.                                                                                            *
*   Version: 1.1.0                                                                                            *
*       The interface for this algorithm has been changed to not use the Population() class, but to generate  *
*       the population from inputs from the user directly in this client.  The crossover, mutation, and       *
*       fitness selection have all be revamped to include this change and improve upon their previous         *
*       weaknesses.                                                                                           *
\*************************************************************************************************************/


import java.util.Scanner;
import java.io.*;

public class GenAlg
{
	public static void main (String[] args)
	{
           double CRSCHNCE, MUTCHNCE;
           int Generations, curGen = 0;

           Scanner scan = new Scanner (System.in);

           Population Pop = new Population();

           System.out.print ("Enter the Cross-over Coefficient: ");
           CRSCHNCE = scan.nextDouble ();

           System.out.print ("Enter the Mutation Coefficient: ");
           MUTCHNCE = scan.nextDouble ();
           System.out.println ();

           System.out.print("Enter the number of generations you wish to run: ");
           Generations = scan.nextInt ();
           System.out.println ();

           System.out.println ("The average fitness of the original population is: " + Pop.getAvgFitness ());
           /*try
           {
	           Pop.printPopStats(Generations, "Run_of_" + Generations + "_generations.txt");
           }

           catch (IOException e)
           {
	           System.out.println ("IOException: " + e.getMessage());
           }*/

           while (curGen < Generations)
           {
	           Pop.Parents();
               Pop = Pop.CrossPop (CRSCHNCE);
               Pop = Pop.MutatePop (MUTCHNCE);
               Pop = Pop.replaceWeak();
               try
               {
               		Pop.printPopStats(Generations, "Run_of_" + Generations + "_generations.txt");
           	   }

           	   catch (IOException e)
           	   {
	           	   System.out.println ("I/O exception occured: " + e.getMessage());
           	   }
               curGen++;
               System.out.println ("The average fitness of this population is: " + Pop.getAvgFitness ());
           }
	}
}

/*************************************************************************************************************\
*   FileName: GenAlg.java              Author: Michael Kelly               Date: 2/17/07                      *
*                                                                                                             *
*   Puprose:	The Population class to be used in a Genetic algorithm.  The basic class accessors and        *
*       mutators are included, along with the code for cross-over, mutations, and fitness selection.          *
*                                                                                                             *
*   Version: 1.1.0                                                                                            *
*       The Mutation, cross-over, and fitness election algorithms have been added as methods to the population*
*       class for ease of use.  These algorithms themselves have been reworked for better efficiency and      *
*       modeling capability.                                                                                  *
\*************************************************************************************************************/


import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.*;

public class Population
{
	Random gen = new Random();

    Scanner scan = new Scanner(System.in);

	float avgFitness;
	int Fitness;
	int sampleFitness = 0, curPop = 0;
	final int POPSIZE = 1000;
	final int LENGTH = 25;
	Individual[] individuals = new Individual[POPSIZE];
	DecimalFormat fmt = new DecimalFormat ("0000");
	int[] perfects = new int[POPSIZE];
	int generations = 0;
	FileWriter writer;
	boolean[] parents = new boolean[POPSIZE];

	//	creates a new population object by calling the Individual constructor
	public Population ()
	{
		for (int i = 0; i < POPSIZE; i++)
		{
			individuals[i] = new Individual ();
		}
	}

        public Population(Population curPop)
        {
           for (int i = 0; i < POPSIZE; i++)
           {
              individuals[i] = curPop.getIndividual(i);
           }
        }

	//method to retrieve the avg. fitness of the population
	public float getAvgFitness()
	{
		int fitness = 0;
		for (int index = 0; index < POPSIZE; index++)
		{
			fitness += individuals[index].getFitness();
		}
		avgFitness = (float) fitness / POPSIZE;
		return avgFitness;
	}

	//method to return the Individual object from the population
	public Individual getIndividual(int index)
	{
		return individuals[index];
	}

	//method to make changes to an individual
	public void edIndividual(int Ind, int Chrome, int newAl)
	{
		individuals[Ind].setAllele(newAl, Chrome);
	}

	public int getPopSize ()
	{
		return POPSIZE;
	}

	public int indLength (int index)
	{
		return individuals[index].getIndSize();
	}

        public void setIndividual(int index, Individual newind1)
	{
		individuals[index] = newind1;
	}

        /****************************************************************************************************\
         *  Method that performs the mutation stage of evolution by searching through each individual and    *
         *  applying the chance of mutation, MUTCHNCE (prompted from the user), and then changing the allele *
         *  to a new random value.                                                                           *
         \***************************************************************************************************/
        public Population MutatePop(double MUTCHNCE)
        {
           double mutability;

           for (int index3 = 0; index3 < POPSIZE; index3++)
		{
			if ((gen.nextDouble() * gen.nextDouble() * gen.nextDouble()) > (MUTCHNCE * 2) && !parents[index3])
			{
				mutability = gen.nextDouble();
				if (mutability < .3)
				{
					this.edIndividual (index3, gen.nextInt (LENGTH), -1);
					Individual curInd = this.getIndividual (index3);
				}
				else
				{
                                        if (mutability < .7)
					{
						this.edIndividual (index3, gen.nextInt (LENGTH), 1);
						Individual curInd = this.getIndividual (index3);
					}
					else
					{
						this.edIndividual (index3, gen.nextInt (LENGTH), 0);
						Individual curInd = this.getIndividual (index3);
					}
				}
			}
		}

           return this;

        }

        /******************************************************************************************\
         *  Method for performing the cross-over algorithm on a population.  Each individual is    *
         *  given the opputunity to cross-over a number of times based on its fitness.  The number *
         *  of times an individuall will cross-over is equal to its fitness divided by the average *
         *  fitness of the population, or through "roulette wheel sampling."                       *
         \*****************************************************************************************/
        public Population CrossPop(double CRSCHNCE)
        {
           int totalFitness = 0, rouletteNeedle, crossnode = 0, accumFitness = 0, curInd = 0;
           double crossSucept;

           /*for (int i = 0; i < POPSIZE; i++)
           {
              totalFitness += individuals[i].getFitness();
           }*/

              for (int iter = 0; iter < POPSIZE; iter++)
              {
                 for (int index0 = 0; index0 < POPSIZE; index0++)
                 {
	                crossSucept = gen.nextInt (10) + 1;

                    if (parents[iter])
                    {
                       if (parents[index0])
                       {
	                      if (crossSucept <= (CRSCHNCE * 10))
	                      {
		                      crossnode = (gen.nextInt (LENGTH - 2)) + 1;

                       		   for (int index1 = 0; index1 < crossnode; index1++)
                       		   {
                       		      this.edIndividual (iter, index1, individuals[index0].getAllele(index1));
                        		  this.edIndividual (index0, LENGTH - (index1 + 1), individuals[iter].getAllele (LENGTH - (index1 + 1)));
                  		       }
                      		}
                       }
                    }
                 }
              }

          return this;
        }

        public boolean isPerfect(int ind)
		{
			if (individuals[ind].getFitness() == LENGTH)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
        /**********************************************************************************************************\
        *	Recursively checks the current population for perfect individuals (i.e. all ones or all negative ones).*
        \**********************************************************************************************************/
        public int[] Perfects()
        {
	        for (int i = 0; i < POPSIZE; i++)
	        {
		        if (individuals[i].getFitness() == LENGTH && perfects[i] != i)
		        {
			        //System.out.println ("Individual " + i + " is perfect.");
			        perfects[i] = i;
		        }

		        else
		        {
			        perfects[i] = -1;
		        }
	        }

	        return perfects;
        }

        public void printPopStats(int Generations, String fileName) throws IOException
        {
	        writer = new FileWriter (fileName, true);

            try{

		        for (int i = 0; i < POPSIZE; i++)
	        	{
		        	writer.append (i + "\tof\t" + POPSIZE + "\t<");

		        	for (int iter = 0; iter < LENGTH; iter++)
		        	{
			        	if (individuals[i].getAllele(iter) == -1)
			        	{
				        	writer.append ("-");
			        	}

			        	else
			        	{
				        	writer.append ("" + individuals[i].getAllele(iter));
			        	}
		        	}



		        	if (this.isPerfect(i) && i == this.Perfects()[i])
		        	{
		        		writer.append (">" + "\tfitness: " + individuals[i].getFitness() + "\tsentiment: " + individuals[i].getSentiment()  + "\tPerfect!" );
	        		}

	        		else
	        		{
		        		writer.append (">" + "\tfitness: " + individuals[i].getFitness() + "\tsentiment: " + individuals[i].getSentiment());
        			}

        			if (individuals[i].getSentiment() == 20 || individuals[i].getSentiment() == -20)
        			{
	        			writer.append ("Fulfilled!");
        			}
        			else
        			{
	        			writer.append ("\r\n");
        			}

		        	if (i == (POPSIZE - 1))
		        	{
			        	writer.append ("\r\n");
			        	writer.append ("Generation: " + (generations + 1));
			        	writer.append ("\r\n");

			        	generations++;
		        	}
	        	}

		        writer.close();
        }

    	catch (Exception e)
    	{
	    	System.err.println ("Error writing to file!: " + e.toString());
    	}
	}
	public void Parents()
	{
		int singles = 0;

		while (singles < POPSIZE)
           {
              if (individuals[singles].getFitness()  > this.getAvgFitness())
              {
                 parents[singles] = true;
              }
              else
              {
                 parents[singles] = false;
              }

              singles++;
	        }
	}

	public Population replaceWeak()
	{
		int curInd = 0, modelInd = 1;

		while (curInd < POPSIZE)
		{
			if (parents[curInd])
			{
				curInd++;
			}
			while (!parents[modelInd])
			{
				modelInd++;

				if (modelInd >= POPSIZE)
				{
					modelInd = 1;
				}
			}

			if (!parents[curInd] && parents[modelInd])
			{
				individuals[curInd] = individuals[modelInd];
				curInd++;
				modelInd++;

				if (modelInd >= POPSIZE)
				{
					modelInd = 1;
				}

				this.Parents();
			}
		}

		return this;
	}
}

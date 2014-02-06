//******************************************************************
//	FileName: Individual.java	Author: Michael Kelly
//
//	Creates the "individual" class to be used to build a population
//******************************************************************


import java.util.Random;

public class Individual
{
	Random Gen = new Random();
	//	Constants and array declaration for the Individual class
	final private int POSITIVE = 1;
	final private int ZERO = 0;
	final private int NEGATIVE = -1;
	final int LENGTH = 25;
	int[] Alleles = new int[LENGTH];

	//****************************************************************
	//	Sets up the individual by assigning five random alleles
	//****************************************************************
	public Individual ()
	{

		for (int i = 0; i < LENGTH; i++)
		{
			if (Gen.nextInt(9) <= 2)
			{
				Alleles[i] = NEGATIVE;
			}
			else
			{
				if (Gen.nextInt(9) < 5)
				{
					Alleles[i] = ZERO;
				}
				else
				{
					Alleles[i] = POSITIVE;
				}
			}
		}
	}
	//	Method to calculate the fitness of an individual
	public int getFitness()
	{
		int fitness = 0;
		for (int iter = 0; iter <(LENGTH); iter++)
		{
			fitness = fitness + Math.abs (Alleles[iter]);
		}
		return fitness;
	}

	//	Method to change an allele (as in mutation or crossover)
	public void setAllele(int newAl, int index)
	{
		Alleles[index] = newAl;
	}

	//	method to return the allele of a specific chromosome
	public int getAllele(int index)
	{
		return Alleles[index];
	}
	public void printInd()
	{
		System.out.print ("< ");

		for (int i = 0; i < LENGTH; i++)
		{
			System.out.print (Alleles[i] + " ");
		}

		System.out.println (">\t" + this.getFitness());
	}
	public int getIndSize ()
	{
		return LENGTH;
	}
	public int getSentiment()
	{
		int sentiment = 0;

		for (int iter = 0; iter < LENGTH; iter++)
		{
			sentiment = sentiment + Alleles[iter];
		}
		return sentiment;
	}
}




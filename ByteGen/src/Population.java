/*
 * Population.java
 *
 * Created on May 24, 2007, 12:58 AM
 *
 * A population filled with individuals to be collectivelly observed.
 *
 */

/**
 *
 * @author Michael Kelly
 */
public class Population
{
    private Individual[] population;
    private int size, length;
    private Float totFitness;
    /** Creates a new instance of Population */
    public Population (int mySize, int myLength)
    {
	size = mySize;
	length = myLength;
	
	population = new Individual[size];
	
	for (int i = 0; i < size; i++)
	{
	    population[i] = new Individual(length);
	}
    }
    
    public String toString ()
    {
	String result = "";
	
	for (int i = 0; i < size; i++)
	{
	    result = result + "\n" + population[i].toString ();
	}
	
	return result;
    }
    
    public Float getAvgFit ()
    {
	totFitness = new Float (0);
	
	for (int i = 0; i < size; i++)
	{
	    totFitness = totFitness + population[i].getFit ();
	}
	return (totFitness / size);
    }
    
    public void mutate ()
    {
	for (int i = 0; i < size; i++)
	{
	    if (Math.random () < .5)
		population[i].mutate ((int) (Math.random () * length));
	}
    }
    
    public void crossover ()
    {
	for (int i = 0; i < size; i++)
	{
	    if (Math.random () < .5 && i != 0)
	    {
		population[i].crossover(population[i - 1], (int) (Math.random () * length));
		population[i - 1].crossover (population[i], (int) (Math.random () * length));
	    }
	}
    }
}

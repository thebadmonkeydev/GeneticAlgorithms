/*
 * Individual.java
 *
 * Created on May 24, 2007, 12:51 AM
 *
 * Represents an individual with a definable number
 * of defining characteristics (byte information {1, 0}).
 *
 */
import java.util.Random;
/**
 *
 * @author Michael Kelly
 */
public class Individual
{
    private int[] individual;
    private int length, fitness = 0;
    private Random rand;
    
    /** Creates a new instance of Individual */
    public Individual (int len)
    {
	rand = new Random ();
	
	length = len;
	individual = new int[length];
	
	for (int i = 0; i < length; i++)
	{
	    individual[i] = rand.nextInt (2);
	    fitness = fitness + individual[i];
	}
    }
    
    public String toString ()
    {
	String result = "";
	
	for (int i = 0; i < length; i++)
	{
	    result = result + " " + individual[i];
	}
	
	return result;
    }
    
    public int getFit ()
    {
	return fitness;
    }
    
    public void mutate (int index)
    {
	if (individual[index] == 0)
	    individual[index] = 1;
	else
	    individual[index] = 0;
    }
    
    public void crossover (Individual parent, int index)
    {
	for (int i = 0; i < length; i++)
	{
	    if (i >= index)
		individual[i] = parent.individual[i];
	}
    }
}

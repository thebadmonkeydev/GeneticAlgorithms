/*
 * Main.java
 *
 * Created on May 24, 2007, 12:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	final int POP_SIZE = 10, IND_LENGTH = 10;
	
	Population pop = new Population (POP_SIZE, IND_LENGTH);
	
	System.out.println (pop.toString ());
	System.out.println ("Average Fitness: " + pop.getAvgFit (). toString ());
	
	pop.mutate ();
	
	System.out.println ("Population after Mutation:\n" + pop.toString ());
	System.out.println ("Average Fitness after Mutation: " + pop.getAvgFit (). toString ());
	
	pop.crossover ();
	
	System.out.println ("Population after Crossover:\n" + pop.toString ());
	System.out.println ("Average Fitness after Crossover: " + pop.getAvgFit ().toString ());
    }

	protected void finalize () throws Throwable
	{
	}
    
}

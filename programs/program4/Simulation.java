/**
 * Isai Lopez
 * ilopezro
 * Program 4
 * Feb 19, 2018
 * (Doc Description)
 */

import java.io.*; 
import java.util.Scanner;

public class Simulation
{
	public static Job getJob(Scanner in)
	{
		String[] s = in.nextLine().split(" "); 
		int a = Integer.parseInt(s[0]); 
		int d = Integer.parseInt(s[1]); 
		return new Job(a, d);
	}

	public static void main(String [] args) throws IOException
	{
		Scanner in; 
		PrintWriter rpt; 
		PrintWriter trc;
		Job[] jobArray; 
		Queue[] processors; 
		Queue storage = new Queue(); 
		Queue backup = new Queue(); 
		double averageWait = 0.00;
		int totalWait = 0;
		int maxWait = 0;
		int wait = 0;
		int currTime;
		int minNum = 0;
		int numJobs; 

		//checks command line arguments
		if(args.length < 1)
		{
			System.out.println("Usage: Simulation <input_file>");
			System.exit(1);
		}

		//opens reader file and writer files
		in = new Scanner(new File(args[0]));  
		rpt = new PrintWriter(new FileWriter(args[0]+".rpt")); 
		trc = new PrintWriter(new FileWriter(args[0] + ".trc"));

		// numJobs 
		String numJobsString = in.nextLine();
		numJobs = Integer.parseInt(numJobsString);

		// Initializes array of Jobs and enqueues them onto storage + backup queue 
		jobArray = new Job[numJobs];
		for (int i = 0; i < numJobs; i++) {
			jobArray[i] = getJob(in);
			storage.enqueue(jobArray[i]);
			backup.enqueue(jobArray[i]); 
		} 

		// Writing of the heading of Report file + trace file
		rpt.println("Report file: " + args[0] + ".rpt");
		rpt.println(numJobs + " Jobs:");
		rpt.println(storage.toString());
		rpt.println("\n***********************************************************");

		trc.println("Trace file: " + args[0] + ".trc");
		trc.println(numJobs + " Jobs:");
		trc.println(storage.toString());

		//starts for loop
		for (int i = 1; i <= numJobs - 1; i++)
		{
			currTime= 0;
			minNum = 0;
			//initializes processors queue 
			processors = new Queue[i];
			for (int j = 0; j < i; j++) 
				processors[j] = new Queue();

			// Prints the processors wen all the processors are empty 
			trc.println("\n*****************************");
			if (i == 1)
				trc.println(i + " processor:");
			else
				trc.println(i + " processors:");
			trc.println("*****************************");
			trc.println("0: " + storage.toString()); 
			
			for (int j = 1; j <= i; j++) 
				trc.println(j + ": ");
			
			trc.println();
			int a = ((Job) storage.peek()).getArrival();
			int b = 1000; 

			// while unproccesed jobs remaining 
			while (storage.length() != numJobs || ((Job) storage.peek()).getFinish() == -1) 
			{

				// arrival or finish times determined to currTime
				if (storage.length() != 0) {
					if (((Job) storage.peek()).getFinish() == -1) 
					{
						a = ((Job) storage.peek()).getArrival();
					}
				}
				b = 1000;
				for (int j = 0; j < i;j++) 
				{
					if (processors[j].length() != 0) 
					{
						if (((Job) processors[j].peek()).getFinish() < b) 
						{
							b = ((Job) processors[j].peek()).getFinish();
						}
						if (storage.length() != 0) 
						{
							if (((Job) storage.peek()).getFinish() != -1)
							{
								a = b;
							}
						}
						if (storage.length() == 0)
							a = b;
					}
				}

				//sets currTime set to arrival or departure time 
				if (a < b) 
				{
					currTime = a;
				} 
				else if (b <= a) 
				{
					currTime = b;
				}

				// runs through to check if there are any jobs that are yet to be finished 
				if (storage.length() != numJobs)
				{
					for (int j = 0; j < i; j++) 
					{
						if (processors[j].length() != 0) 
						{
							if (((Job) processors[j].peek()).getFinish() == currTime)
							{
								storage.enqueue((Job) processors[j].dequeue());
								if (processors[j].length() != 0) {
									if (((Job) processors[j].peek()).getFinish() == -1)
										((Job) processors[j].peek()).computeFinishTime(currTime);
								}
							}
						}
					}
				}
				//arriving jobs are processed
				for (int k = 0; k < storage.length(); k++) 
				{
					for (int j = 0; j < i; j++) 
					{
						if (storage.length() > 0 && ((Job) storage.peek()).getArrival() == currTime & ((Job) storage.peek()).getFinish() == -1) 
						{

							// Finding processor of minimum length
							minNum = processors[0].length();
							for (int m = 0; m < i;m++) 
							{
								if (processors[m].length() < minNum)
									minNum = processors[m].length();
							}

							// Which processor for job to go to
							if (processors[j].length() == 0)
							{
								((Job) storage.peek()).computeFinishTime(currTime);
								processors[j].enqueue((Job) storage.dequeue());
							} else if (processors[j].length() == minNum) 
							{
								processors[j].enqueue((Job) storage.dequeue());
							}
						}
					}
				}
				//prints out trace
				trc.println("time=" + currTime);
				trc.println("0: " + storage.toString());

				for (int j = 0; j < i; j++) {
					trc.println((j + 1) + ": " + processors[j].toString());
				}
				trc.println();
			}

			//wait time are calculated here 
			for (int j = 0; j < numJobs; j++) 
			{
				wait = ((Job)storage.peek()).getFinish() - (((Job)storage.peek()).getArrival() + ((Job)storage.peek()).getDuration());
				storage.dequeue();
				if (wait > maxWait)
					maxWait = wait;
				totalWait = totalWait + wait;
			}
			averageWait = (double)totalWait/numJobs;

			//prints out to Report
			if (i == 1)
				rpt.printf("%d processor: total wait=%d, maxWait=%d, averageWait=%.2f\n", i, totalWait, maxWait, averageWait); 
			else
				rpt.printf("%d processors: total wait=%d, maxWait=%d, averageWait=%.2f\n", i, totalWait, maxWait, averageWait);

			//resets finish time for jobs and stores it in the storage once again 
			for (int j = 0; j < numJobs; j++) 
			{
				jobArray[j].resetFinishTime();
				storage.enqueue(jobArray[j]);
			}
			//resets wait times 
			maxWait = 0;
			wait = 0;
			totalWait = 0;
		}

		//closes reader file and writer files
		in.close();
		rpt.close();
		trc.close();
	}
}
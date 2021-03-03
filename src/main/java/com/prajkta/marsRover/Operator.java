package com.prajkta.marsRover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* This is the main entry point for the mars rover application.
 * The class contains methods to parse and validate the user inputs.
 * 
 * @author Prajkta Kunte
 * 
 */
public class Operator {
	static Logger logger = System.getLogger(Operator.class.getName());

	
	//User can provide input file containig commands or provide inputs on the console.
	public static void main(String args[]) throws IOException {
		
		ArrayList<String> userInput = new ArrayList<String>();
		BufferedReader buffer = null;
		try {
			if(args.length >0 && !args[0].isBlank())
				buffer = new BufferedReader(new FileReader(args[0]));
			else {				
	            buffer = new BufferedReader(new InputStreamReader(System.in));			
				System.out.println("Enter rover data and commands. Enter ## to exit to complete data entry:");
			}

			String line = null;
			
			while ((line = buffer.readLine()) != null) {
				line = line.trim();
				if (line.equals("##"))
					break;
				if (!line.isBlank())
					userInput.add(line);
			}

			Operator op = new Operator();
			op.processCommand(userInput);

			buffer.close();
			System.exit(0);
		} catch (Exception ex) {
			logger.log(Logger.Level.ERROR, ex.getMessage());
			System.exit(1);
		} finally {
			if (buffer != null)
				buffer.close();
		}
	}

	/*
	 * This method parses the command string passed and executes the commands. 
	 * The final position of the rovers after the navigation is printed. 
	 * Command string format: 
	 * navigation grid coordinates 
	 * rover coordinates 
	 * navigation instructions
	 * Eg: 5 5 
	 *     1 3 N 
	 *     LMR 
	 *     4 5 
	 *     LRL
	 * 
	 */
	public void processCommand(ArrayList<String> cmdList) throws IOException {
		try {

			if (cmdList.size() == 0)
				throw new Exception("Command string is empty.");

			// Init navigation grid boundaries and rover data
			int[] gridDimensions = getGridDimensions(cmdList.get(0));
			List<String> roverCmdStr = cmdList.subList(1, cmdList.size());
			LinkedList<String> roverData = parseRoverCmd(roverCmdStr);

			// Execute the rover navigation commands
			CommandModule cmd = new CommandModule();
			cmd.processCommand(gridDimensions, roverData);

		} catch (Exception ex) {
			throw new IOException(ex.getMessage());
		}

	}
	
	
	/* Parse grid dimensions */
	private int[] getGridDimensions(String dimensions) throws IOException {
		int ret[] = new int[2];
		if (dimensions != null) {
			String[] in = dimensions.split(" ");
			if (in.length < 2)
				throw new IOException("Insufficient navigation grid coordinates.");
			else {
				ret[0] = Integer.parseInt(in[0]);
				ret[1] = Integer.parseInt(in[1]);
				if (ret[0] < 0 || ret[1] < 0)
					throw new IOException("Grid coordinates cannot be negative.");
			}
		}
		return ret;
	}

	/* Parse rover data and commands */
	private LinkedList<String> parseRoverCmd(List<String> cmdList) {
		LinkedList<String> retList = new LinkedList<String>();

		int idx = 0;
		while (idx < cmdList.size()) {
			retList.add(cmdList.get(idx++) + ":" + cmdList.get(idx++));
		}
		for (String cmd : retList) {
			logger.log(Logger.Level.TRACE, cmd);
		}
		return retList;
	}
}

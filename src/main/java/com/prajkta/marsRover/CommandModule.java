package com.prajkta.marsRover;

import java.io.IOException;
import java.lang.System.Logger;
import java.util.LinkedList;

/* Class that controls the creation of rovers and execution of commands
 * @author Prajkta Kunte
 * 
 */
public class CommandModule {

	private static Logger logger = System.getLogger(CommandModule.class.getName());

	private static Navigator navigator = new Navigator();
	private static RoverSquad rSquad = new RoverSquad();

	public static Navigator getNavigator() {
		return navigator;
	}

	public static RoverSquad getrSquad() {
		return rSquad;
	}

	public void emptyRoverSquad() {
		rSquad.getSquad().clear();
	}
	
	//Method that executes Rover navigation
	public void processCommand(int[] gridDimensions, LinkedList<String> roverCmds) throws IOException {

		try {

			// Create rovers and map them to corresponding instructions.
			LinkedList<RoverInstructions> roverNavigationMap = createRoverNavigationMap(gridDimensions, roverCmds);

			// navigate per command
			navigate(roverNavigationMap);

		} catch (Exception ex) {
			throw new IOException(ex.getMessage());
		}
	}

	public LinkedList<String> getCurrentRoverPositions() {
		LinkedList<String> positions = new LinkedList<String>();
		for (Rover r : rSquad.getSquad()) {
			positions.add(r.getLocation().toString());
		}
		return positions;
	}

	//This method creates rover instances and creates a collection of rover and their corresponding commands. 
	private static LinkedList<RoverInstructions> createRoverNavigationMap(int[] gridDimensions,
			LinkedList<String> roverCmds) throws Exception {

		LinkedList<RoverInstructions> roverNavigationCmds = new LinkedList<RoverInstructions>();

		if (roverCmds != null) {

			// Update grid coordinates
			navigator.setMaxX(gridDimensions[0]);
			navigator.setMaxY(gridDimensions[1]);

			int roverId = 0;
			for (String rover : roverCmds) {
				roverId++;
				String[] rData = rover.split(":")[0].split(" ");
				String cmdStr = rover.split(":")[1];
				int x = Integer.parseInt(rData[0]);
				int y = Integer.parseInt(rData[1]);
				char direction = rData[2].charAt(0);
				Location loc = new Location(x, y, direction);
				Rover r = new Rover(roverId, loc);

				// What happens if position is already taken?
				if (navigator.isSafeMove(loc)) {
					navigator.getOccupiedPositions().add(loc.getPosition());
					rSquad.getSquad().add(r);
					RoverInstructions instr = new RoverInstructions(r, cmdStr);
					roverNavigationCmds.add(instr);
				} else {
					throw new Exception("Rover position " + rover + " is not valid.");
				}
			}
			logger.log(Logger.Level.TRACE, "Created " + roverId + " rovers.");
		}
		return roverNavigationCmds;
	}

	//This method performs the actual rover navigation and prints the final rover position.
	private static void navigate(LinkedList<RoverInstructions> roverNavigationCmds) throws Exception {
		try {
			System.out.println("New rover positions after navigation");
			for (RoverInstructions instruct : roverNavigationCmds) {
				navigator.navigate(instruct.rover, instruct.instructions);
				System.out.println(instruct.rover.getLocation().toString());
			}

		} catch (Exception ex) {
			throw new Exception("Error during navigation\n" + ex.getMessage());
		}
	}

}

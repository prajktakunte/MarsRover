package com.prajkta.marsRover;

/* Class to represent rover and its corresponsing instruction.
 * @author Prajkta Kunte
 * 
 */
public class RoverInstructions {
	Rover rover;
	String instructions;
	
	public RoverInstructions(Rover rover, String cmdStr){
		this.rover = rover;
		this.instructions = cmdStr;
	}
}

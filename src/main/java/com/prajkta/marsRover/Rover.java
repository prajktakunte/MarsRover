package com.prajkta.marsRover;

/* Class to represent rover.
 * @author Prajkta Kunte
 * 
 */
public class Rover {

	private int id;
    private Location location;

	public Rover(int id, Location location) {	
		this.id = id;
		this.location = location;
	}	
	
	public int getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}	
}

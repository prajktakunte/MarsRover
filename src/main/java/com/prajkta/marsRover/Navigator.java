package com.prajkta.marsRover;

import java.lang.System.Logger;
import java.util.HashSet;

/* Class that encapsulates the grid dimensions and navigation logic.
 * @author Prajkta Kunte
 * 
 */
public class Navigator {

	private static Logger logger = System.getLogger(Navigator.class.getName());

	private int minX = 0;
	private int minY = 0;
	private int maxX = 0;
	private int maxY = 0;
	private HashSet<Position> occupiedPositions;

	public Navigator() {
		this.minX = 0;
		this.minY = 0;
		this.maxX = 0;
		this.maxY = 0;
		occupiedPositions = new HashSet<Position>();
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public HashSet<Position> getOccupiedPositions() {
		return occupiedPositions;
	}

	public boolean isSafeMove(Location newLocation) {
		if (!occupiedPositions.contains(newLocation.getPosition()) && newLocation.getPosition().getX() >= this.minX
				&& newLocation.getPosition().getX() <= this.maxX && newLocation.getPosition().getY() >= this.minY
				&& newLocation.getPosition().getY() <= this.maxY)
			return true;
		else
			return false;
	}

	public Location turnLeft(Location oldLocation) {
		Location newLocation = new Location(oldLocation.getPosition(), oldLocation.getOrientationAsChar());
		switch (oldLocation.getOrientationAsChar()) {
		case 'N':
			newLocation.setOrientation('W');
			break;
		case 'W':
			newLocation.setOrientation('S');
			break;
		case 'S':
			newLocation.setOrientation('E');
			break;
		case 'E':
			newLocation.setOrientation('N');
			break;
		}
		return newLocation;
	}

	public Location turnRight(Location oldLocation) {
		Location newLocation = new Location(oldLocation.getPosition(), oldLocation.getOrientationAsChar());
		switch (oldLocation.getOrientationAsChar()) {
		case 'N':
			newLocation.setOrientation('E');
			break;
		case 'E':
			newLocation.setOrientation('S');
			break;
		case 'S':
			newLocation.setOrientation('W');
			break;
		case 'W':
			newLocation.setOrientation('N');
			break;
		}
		return newLocation;
	}

	public Location getNewLocation(Location oldLocation) {

		Location newLocation = new Location(oldLocation.getPosition(), oldLocation.getOrientationAsChar());
		Position p = null;
		switch (oldLocation.getOrientationAsChar()) {
		case 'N':
			p = new Position(oldLocation.getPosition().getX(), oldLocation.getPosition().getY() + 1);
			newLocation.setPosition(p);
			break;
		case 'W':
			p = new Position(oldLocation.getPosition().getX() - 1, oldLocation.getPosition().getY());
			newLocation.setPosition(p);
			break;
		case 'S':
			p = new Position(oldLocation.getPosition().getX(), oldLocation.getPosition().getY() - 1);
			newLocation.setPosition(p);
			break;
		case 'E':
			p = new Position(oldLocation.getPosition().getX() + 1, oldLocation.getPosition().getY());
			newLocation.setPosition(p);
			break;
		default:
			System.err.println("Invalid direction");
		}

		return newLocation;
	}

	public void navigate(Rover rover, String cmd) throws Exception {
		Location currLocation = rover.getLocation();
		Position currPosition = rover.getLocation().getPosition();
		Location newLocation = null;
		try {
			// Parse the command string and execute each navigation command
			for (int i = 0; i < cmd.length(); i++) {
				switch (cmd.charAt(i)) {
				case 'L':
					newLocation = turnLeft(rover.getLocation());
					break;
				case 'R':
					newLocation = turnRight(rover.getLocation());
					break;
				case 'M':
					newLocation = getNewLocation(rover.getLocation());
					if (!isSafeMove(newLocation))
						throw new Exception("Could not move from location " + rover.getLocation().toString() + " to "
								+ newLocation.toString());
					break;
				default:
					logger.log(Logger.Level.ERROR, "Invalid navigation command");
					break;
				}

				if (newLocation != null) {
					rover.setLocation(newLocation);
					this.occupiedPositions.remove(currPosition);
					this.occupiedPositions.add(newLocation.getPosition());
					currLocation = newLocation;
					currPosition = newLocation.getPosition();
					newLocation = null;
				}
			}

		} catch (Exception ex) {
			throw new Exception("Error while navigating rover{id:" + rover.getId() + " location: "
					+ rover.getLocation().toString() + "}\n" + ex.getMessage());
		}
	}
}

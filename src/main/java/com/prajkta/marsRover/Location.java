package com.prajkta.marsRover;


/* Class that encapsulates the rover coordinates and direction.
 * @author Prajkta Kunte
 * 
 */
public class Location {
	private Position pos;
	private Direction orientation;

	public Location(int x, int y, char direction) {
		this.pos = new Position(x, y);
		setOrientation(direction);
	}

	public Location(Position pos, char direction) {
		this.pos = pos;
		setOrientation(direction);
	}

	public Position getPosition() {
		return this.pos;
	}

	public void setPosition(Position pos) {
		this.pos = pos;
	}
	
	public Direction getOrientation() {
		return this.orientation;
	}	

	public char getOrientationAsChar() {
		return orientation.getDirectionAsChar();
	}

	public void setOrientation(char orientation) {
		switch(orientation) {
		case 'N':
			this.orientation = Direction.N;
			break;
		case 'S':
			this.orientation = Direction.S;
			break;
		case 'E':
			this.orientation = Direction.E;
			break;
		case 'W':
			this.orientation = Direction.W;
			break;			
		}
	}

	@Override
	public String toString() {
		return pos.toString() + " " + orientation.name();
	}
	
	@Override
	public int hashCode() {
	    return this.pos.hashCode() + this.orientation.hashCode();
	}	
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) 
	    	return false;
	    if (!(obj instanceof Location))
	        return false;
	    if (obj == this)
	        return true;
	    Location locationObj = (Location)obj;
	    return this.pos.equals(locationObj.pos)
	    		&& this.orientation.getDirectionAsChar() == locationObj.orientation.getDirectionAsChar();
	}		

}

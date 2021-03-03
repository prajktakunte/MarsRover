package com.prajkta.marsRover;

/* Class that encapsulates the (x,y) grid coordinates.
 * 
 * @author Prajkta Kunte
 * 
 */
public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return x + " " + y ;
	}
	
	@Override
	public int hashCode() {
        int hashcode = 0;		
		hashcode = x*1000+y;
	    return hashcode;	    
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (!(obj instanceof Position))
	        return false;
	    if (obj == this)
	        return true;
	    return this.x == ((Position) obj).x
	    		&& this.y == ((Position) obj).y;
	}	
}

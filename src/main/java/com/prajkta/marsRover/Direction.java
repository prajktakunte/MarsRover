package com.prajkta.marsRover;

/* Enum type for direction
 * @author Prajkta Kunte
 * 
 */
public enum Direction {
	N('N'), S('S'),E('E'), W('W');
    private final char asChar;	
    
    Direction(char directionAsChar) {
        this.asChar = directionAsChar;
    }
    public char getDirectionAsChar() {
        return asChar;
    }    
}

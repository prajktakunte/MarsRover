package com.prajkta.marsRover;

import java.util.ArrayList;
import java.util.List;

/* Collection of rovers deployed.
 * 
 * @author Prajkta Kunte
 * 
 */
public class RoverSquad {

	private List<Rover> squad;
	
	public RoverSquad() {
		this.squad = new ArrayList<Rover>();
	}

	public List<Rover> getSquad() {
		return this.squad;
	}
	
	public Rover getRoverById(int id) {
		for(Rover r: this.squad) {
			if (r.getId()==id)
				return r;
		}
		return null;
	}

}

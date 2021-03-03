package com.prajkta.marsRover;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for Mars Rover Navigation application.
 */
public class OperatorTest {

	@Test
	public void testValidInput() throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		Operator op = new Operator();
		CommandModule cmd = new CommandModule();
		cmd.emptyRoverSquad();		
		input.add("5 5");
		input.add("1 2 N");
		input.add("LMLMLMLMM");
		input.add("3 3 E");
		input.add("MMRMMRMRRM");
		System.out.println("\nInput");
		System.out.println("------------");		
		for(String i: input) {
			System.out.println(i);
		}
		System.out.println();
		op.processCommand(input);
		
		List<String> actual = cmd.getCurrentRoverPositions();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("1 3 N");
		expected.add("5 1 E");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testValidRectangleGridInput() throws IOException {
		ArrayList<String> input2 = new ArrayList<String>();
		Operator op2 = new Operator();
		CommandModule cmd2 = new CommandModule();
		cmd2.emptyRoverSquad();		
		input2.add("5 7");
		input2.add("1 5 N");
		input2.add("LRM");
		input2.add("3 7 S");
		input2.add("LMR");
		System.out.println("\nInput");
		System.out.println("------------");			
		for(String i: input2) {
			System.out.println(i);
		}
		System.out.println();
		op2.processCommand(input2);

		List<String> actual2 = cmd2.getCurrentRoverPositions();
		ArrayList<String> expected2 = new ArrayList<String>();
		expected2.add("1 6 N");
		expected2.add("4 7 S");
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void testZeroDimensionGridInput1() throws IOException {
		ArrayList<String> input3 = new ArrayList<String>();
		Operator op3 = new Operator();
		CommandModule cmd3 = new CommandModule();
		cmd3.emptyRoverSquad();		
		input3.add("0 0");
		input3.add("0 0 N");
		input3.add("LLLLL");
		System.out.println("\nInput");
		System.out.println("------------");			
		for(String i: input3) {
			System.out.println(i);
		}
		System.out.println();
		op3.processCommand(input3);

		List<String> actual3 = cmd3.getCurrentRoverPositions();
		ArrayList<String> expected3 = new ArrayList<String>();
		expected3.add("0 0 W");
		assertEquals(expected3, actual3);
	}	

	@Test(expected = Exception.class)
	public void testInvalidGridDimension() throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		input.add("0 0");
		input.add("3 2 N");
		input.add("LL");
		input.add("3 4 S");
		input.add("LL");
		Operator op = new Operator();
		op.processCommand(input);
	}

	@Test(expected = Exception.class)
	public void testInvalidRoverPosition() throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		input.add("10 10");
		input.add("-3 2 N");
		input.add("LL");
		input.add("3 -4 S");
		input.add("LL");
		Operator op = new Operator();
		op.processCommand(input);
	}

	@Test(expected = Exception.class)
	public void testInvalidRoverInstructions() throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		input.add("10 10");
		input.add("3 2 N");
		input.add("XYSD");
		input.add("3 -4 S");
		input.add("LXRF");
		Operator op = new Operator();
		op.processCommand(input);
	}

	@Test(expected = Exception.class)
	public void testDuplicateRoverInstructions() throws IOException {
		ArrayList<String> input = new ArrayList<String>();
		input.add("10 10");
		input.add("3 2 N");
		input.add("LL");
		input.add("3 2 N");
		input.add("LL");
		Operator op = new Operator();
		op.processCommand(input);
	}	
}

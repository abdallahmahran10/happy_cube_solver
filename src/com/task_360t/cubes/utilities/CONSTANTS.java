package com.task_360t.cubes.utilities;

public class CONSTANTS {
	/*
	 * Logging flag, if true the logger will be enabled
	 */
	public static final boolean LOGGER_ENABLE = true;
	/*
	 * Logging in file flag, if true logger will log in file
	 */
	public static final boolean LOGGER_ENABLE_FILE_LOGGING = true;
	/*
	 * Logger instant name
	 */
	public static final String CUBE_LOGGER_NAME = "CubesLogger";

	/*
	 * Piece top edge
	 */
	public static final int TOP_EDGE = 0;
	/*
	 * Piece right edge
	 */
	public static final int RIGHT_EDGE = 1;
	/*
	 * Piece bottom edge
	 */
	public static final int BOTTOM_EDGE = 2;
	/*
	 * Piece left edge
	 */
	public static final int LEFT_EDGE = 3;
	/*
	 * Filled cell symbol in piece
	 */
	public static final Object FILLED_CELL = "[]";
	/*
	 * Empty cell symbol in piece
	 */
	public static final Object EMPTY_CELL = "  ";
	/*
	 * Max number of faces in cube
	 */
	public static final int MAX_FACES = 6;
	/*
	 * Max number of the cells in on row/column in a piece
	 */
	public static final int MAX_CELLS = 5;
	/*
	 * input files names, for test purposes
	 */
	public static final String[] TEST_INPUT_FILES = { "input1.txt", "input2.txt", "input3.txt" };
	/*
	 * Separator between solutions
	 */
	public static final String SOLUTION_SEPARATOR = "###########################################################";
	/*
	 * number of arguments that should be send to the program in order to work
	 */
	public static final int PROGRAM_ARGUMENTS_COUNT = 2;
	/*
	 * program help message
	 */
	public static final String PROGRAM_HELP = "You need to send the strategy type and the input file path in order for the program to work.\n"+
												"# Solving strategy values:  \n"+
												"	 0: find one soltion\n"+
												"	 1: find all possible soltion\n" +
												"Command example: solver.jar 1 input.txt";
	/*
	 * Find one possible solution mode
	 */
	public static final String FIND_ONE_SOLUTION_STRATEGY = "0";
	/*
	 * Find all possible unique solutions mode 
	 */
	public static final String FIND_ALL_SOLUTION_STRATEGY = "1";
}

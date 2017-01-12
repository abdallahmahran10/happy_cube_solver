# Happy Cube Solver
This a solution to the Happy Cube puzzle

## User Guide

This project can be run with the following command:

	solver.java 1 intput.txt

You need to send the strategy type and the input file path as program argumenst in order for the program to work.
#### Solving strategy values:
	0: find one soltion
	1: find all possible soltion
				
#### Input file
The inut file should contains sex pieces with no lines sepration between them.		
					
Input file example:

		    []    
		  [][][]  
		[][][][][]
		  [][][]  
		    []    
		    []  []
		[][][][][]
		  [][][]  
		[][][][][]
		  []  [][]
		  []  []  
		  [][][] 
		[][][][][]
		  [][][] 
		    []   
		[]  []      
		[][][][][]     
		  [][][]      
		[][][][][]     
		  []  []      
		[]  []  []     
		[][][][][]     
		  [][][]      
		[][][][][]     
		[]  []  []     
		  []  []      
		[][][][]      
		  [][][][]     
		[][][][]      
		[][]  [] 

#### Output:

output will be generated in file called cube.txt in the same working directory.

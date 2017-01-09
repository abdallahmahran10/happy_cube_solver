package com.task_360t.cubes.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.task_360t.cubes.models.Cube;

public class FileHandler {
	static CubeLogger logger = CubeLogger.getInstant();
	
	public static void dumpCube(Cube cube)
	{
        try {
        	PrintWriter writer = new PrintWriter("cube.txt", "UTF-8");
        	writer.print(cube.toString());		
        	writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			logger.ERROR(e);
		}
	}

	/**
	 * @param cubes
	 */
	public static void dumpCubes(List<Cube> cubes) {

        try {
        	PrintWriter writer = new PrintWriter("cube.txt", "UTF-8");
        	for(Cube cube : cubes)
        	{
        		writer.print(cube.toString());
        		writer.print("###########################################################"+System.lineSeparator());
        	}
        	writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			logger.ERROR(e);
		}
	}
	public static String readFile(String fileName) {
		String fileContent = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    fileContent = sb.toString();
		    br.close();
		} catch (IOException e) {
			logger.ERROR(e);
		}
		return fileContent;
	}

}

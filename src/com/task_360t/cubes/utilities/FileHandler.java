package com.task_360t.cubes.utilities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.task_360t.cubes.models.Cube;

public class FileHandler {
	public static void dumpCube(Cube cube)
	{
        try {
        	PrintWriter writer = new PrintWriter("cube.txt", "UTF-8");
        	writer.print(cube.toString());		
        	writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

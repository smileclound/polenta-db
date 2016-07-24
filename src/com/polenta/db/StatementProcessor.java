package com.polenta.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class StatementProcessor implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	
	public StatementProcessor(InputStream statementInputStream, OutputStream statementOutputStream) {
	    in = new BufferedReader(new InputStreamReader(statementInputStream));
		out = new PrintWriter(statementOutputStream, true);
	}

	public void run() {
		String inputLine, outputLine;
		try {
			//inputLine = in.readLine();
			while ((inputLine = in.readLine()) != null) {
				System.out.println("Processing statement:\n" + inputLine);
			}
			System.out.println("Statement received.");
			out.println("Statement received.");
			out.flush();
		} catch (IOException e) {
			out.println("Failed to process statement.");
			out.flush();
			e.printStackTrace();
		}
	}
	
	
}

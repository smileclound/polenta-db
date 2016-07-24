package com.polenta.db.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.polenta.db.exception.InvalidStatementException;

public class SocketProcessor implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	
	public SocketProcessor(InputStream statementInputStream, OutputStream statementOutputStream) {
	    in = new BufferedReader(new InputStreamReader(statementInputStream));
		out = new PrintWriter(statementOutputStream, true);
	}

	public void run() {
		String statement;
		try {
			statement = in.readLine();
			//while ((inputLine = in.readLine()) != null) {
			System.out.println("Statement received:\n" + statement);
			StatementProcessor processor = new StatementProcessor(statement);
			@SuppressWarnings("unused")
			String result = processor.execute();
			out.println("Statement processed.");
			out.flush();
		} catch (IOException e) {
			out.println("Failed to read statement from socket.");
			out.flush();
			e.printStackTrace();
		} catch (InvalidStatementException e) {
			out.println("Failed to process statement.");
			out.flush();
			e.printStackTrace();
		} catch (Exception e) {
			out.println("Failed to process statement.");
			out.flush();
			e.printStackTrace();
		}
	}
	
	
}

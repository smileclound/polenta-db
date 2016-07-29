package com.polenta.db.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.polenta.db.exception.PolentaException;

public class SocketProcessor implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	public SocketProcessor(InputStream statementInputStream, OutputStream statementOutputStream) {
		reader = new BufferedReader(new InputStreamReader(statementInputStream));
		writer = new BufferedWriter(new OutputStreamWriter(statementOutputStream));
	}

	public void run() {
		String statement;
		try {
			statement = reader.readLine();
			System.out.println("Statement received:\n" + statement);
			StatementProcessor processor = new StatementProcessor(statement.trim());
			String result;
			try {
				result = processor.execute();
			} catch (PolentaException e) {
				result = e.getMessage();
				e.printStackTrace();
			} catch (Exception e) {
				result = "Failed to process statement.";
				e.printStackTrace();
			}
			System.out.println("Statement executed. Result:\n" + result);
			writer.write(result);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

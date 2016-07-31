package com.polenta.db.sorting;

import java.util.ArrayList;
import java.util.List;

import com.polenta.db.data.Row;

public class SorterMocker {

	public static List<Row> mockSetOfNames() {
		List<Row> unsortedRows = new ArrayList<Row>();
		
		Row row1 = new Row();
		row1.set("name", "Z");
		unsortedRows.add(row1);
		
		Row row2 = new Row();
		row2.set("name", "A");
		unsortedRows.add(row2);

		Row row3 = new Row();
		row3.set("name", "C");
		unsortedRows.add(row3);

		Row row4 = new Row();
		row4.set("name", "T");
		unsortedRows.add(row4);

		Row row5 = new Row();
		row5.set("name", "M");
		unsortedRows.add(row5);

		return unsortedRows;
	}

	public static List<Row> mockSetOfIDs() {
		List<Row> unsortedRows = new ArrayList<Row>();
		
		Row row1 = new Row();
		row1.set("id", new Integer(1));
		unsortedRows.add(row1);
		
		Row row2 = new Row();
		row2.set("id", new Integer(10));
		unsortedRows.add(row2);

		Row row3 = new Row();
		row3.set("id", new Integer(3));
		unsortedRows.add(row3);

		Row row4 = new Row();
		row4.set("id", new Integer(5));
		unsortedRows.add(row4);

		Row row5 = new Row();
		row5.set("id", new Integer(7));
		unsortedRows.add(row5);

		Row row6 = new Row();
		row6.set("id", new Integer(8));
		unsortedRows.add(row6);

		Row row7 = new Row();
		row7.set("id", new Integer(6));
		unsortedRows.add(row7);

		Row row8 = new Row();
		row8.set("id", new Integer(4));
		unsortedRows.add(row8);

		Row row9 = new Row();
		row9.set("id", new Integer(9));
		unsortedRows.add(row9);

		Row row10 = new Row();
		row10.set("id", new Integer(2));
		unsortedRows.add(row10);
		
		return unsortedRows;
	}

}

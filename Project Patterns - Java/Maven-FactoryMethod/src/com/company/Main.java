package com.company;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

class Database extends AbstractTableModel {
    private List<TableHeader> headers;
    private List<List<TableData>> data;

    public Database() {
        headers = new ArrayList<TableHeader>();
        data = new ArrayList<List<TableData>>();
    }
    public void addRow() {
        List<TableData> row = new ArrayList<TableData>();
        for(TableHeader col:headers)
        {
            row.add(col.create()); // wywołanie metody fabrykującej col

        }
        data.add(row);
        fireTableStructureChanged();
    }
    public void addCol(TableHeader type) {
        headers.add(type);
        for(List<TableData> row:data)
        {
            row.add(type.create());// wywołanie metody fabrykującej data

        }
        fireTableStructureChanged();
    }

    public int getRowCount() { return data.size(); }
    public int getColumnCount() { return headers.size(); }
    public String getColumnName(int column) {
        return headers.get(column).toString();
    }
    public Object getValueAt(int row, int column) {
        return data.get(row).get(column);
    }
}

interface TableData {
    final static Random rnd = new Random();
}

class TableDataInt implements TableData
{
    private int data;
    public TableDataInt() { data = rnd.nextInt(100); }
    public String toString() { return Integer.toString(data); }
}
class TableDataDouble implements TableData
{
    private double data;
    public TableDataDouble() { data = rnd.nextDouble();}
    public String toString() { return Double.toString(data); }
}
class TableDataChar implements TableData
{
    private char data;
    public TableDataChar() { data =(char)(rnd.nextInt(26)+'a');}
    public String toString() { return data+""; }
}
class TableDataBollean implements TableData
{
    private boolean data;
    public TableDataBollean() { data = rnd.nextBoolean();}
    public String toString() { return Boolean.toString(data); }
}
/* ... */

abstract class TableHeader
{
    public TableHeader() { }
//    public String toString() { return type; }
    public abstract TableData create();

}
class TableHeaderInt extends TableHeader
{


    public String toString() {
        return "INT";
    }
    public TableData create()
    {
        return new TableDataInt();
    }
}
class TableHeaderDouble extends TableHeader
{


    public String toString() {
        return "DOUBLE";
    }
    public TableData create()
    {
        return new TableDataDouble();
    }
}
class TableHeaderBoolean extends TableHeader
{


    public String toString() {
        return "BOOLEAN";
    }
    public TableData create()
    {
        return new TableDataBollean();
    }
}
class TableHeaderChar extends TableHeader
{



    public String toString() {
        return "CHAR";
    }
    public TableData create()
    {
        return new TableDataChar();
    }
}

public class Main {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Baza danych");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Database database = new Database();

        JTable table = new JTable(database);
        JMenuBar bar = new JMenuBar();

        JButton row = new JButton("Dodaj Wiersz");
        JButton col = new JButton("Dodaj Kolumnę");

        bar.add(row);
        bar.add(col);

        frame.add(new JScrollPane(table));
        frame.setJMenuBar(bar);

        frame.pack();
        frame.setVisible(true);

        row.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev)
            {
                database.addRow();
            }
        });
        col.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev)
            {
                Object option = JOptionPane.showInputDialog(
                        frame,
                        "Podaj typ kolumny",
                        "Dodaj Kolumnę",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new TableHeader[] {
                                new TableHeaderInt(),
                                new TableHeaderDouble(),
                                new TableHeaderChar(),
                                new TableHeaderBoolean(),
                        }, null);
                if(option == null)
                    return;
                database.addCol((TableHeader)option);
            }
        });
    }
}


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
            row.add(col.clone()); // wywołanie metody fabrykującej col

        }
        data.add(row);
        fireTableStructureChanged();
    }
    public void addCol(TableHeader type) {
        headers.add(type);
        for(List<TableData> row:data)
        {
            row.add(type.clone());// wywołanie metody fabrykującej data

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

interface TableData extends Cloneable {
    final static Random rnd = new Random();
    abstract public TableData clone();
}

class TableDataInt implements TableData
{
    private int data;
    public TableDataInt() { data = rnd.nextInt(100); }
    public String toString() { return Integer.toString(data); }
    public TableDataInt clone()
    {
        TableDataInt kopia = null;
        try { kopia = (TableDataInt) super.clone(); }
        catch(CloneNotSupportedException e) { }
        return kopia;
    }
}

class TableDataDouble implements TableData
{
    private double data;
    public TableDataDouble() { data = rnd.nextDouble();}
    public String toString() { return Double.toString(data);}

    public TableDataDouble clone()
    {
        TableDataDouble kopia = null;
        try { kopia = (TableDataDouble) super.clone(); }
        catch(CloneNotSupportedException e) { }
        return kopia;
    }

}
class TableDataChar implements TableData
{
    private char data;
    public TableDataChar() { data =(char)(rnd.nextInt(26)+'a');}
    public String toString() { return data+""; }
    public TableDataChar clone()
    {
        TableDataChar kopia = null;
        try { kopia = (TableDataChar) super.clone(); }
        catch(CloneNotSupportedException e) { }
        return kopia;
    }
}
class TableDataBollean implements TableData
{
    private boolean data;
    public TableDataBollean() { data = rnd.nextBoolean();}
    public String toString() { return Boolean.toString(data); }
    public TableDataBollean clone()
    {
        TableDataBollean kopia = null;
        try { kopia = (TableDataBollean) super.clone(); }
        catch(CloneNotSupportedException e) { }
        return kopia;
    }
}
/* ... */
class TableHeader
{
    private String type;
    private TableData prototyp;
    public TableHeader(String type,TableData data) {
        this.type=type;
        this.prototyp=data.clone();
    }

        public String toString() { return type; }
        protected TableData clone() {
        return this.prototyp.clone();
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
                                new TableHeader("INT", new TableDataInt()),
                                new TableHeader("DOUBLE", new TableDataDouble()),
                                new TableHeader("CHAR", new TableDataChar()),
                                new TableHeader("BOOLEAN", new TableDataBollean()),
                        }, null);
                if(option == null)
                    return;
                database.addCol((TableHeader)option);
            }
        });
    }
}


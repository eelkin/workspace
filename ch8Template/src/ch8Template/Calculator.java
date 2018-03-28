package ch8Template;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculator extends JApplet {

	private JTextField screen = new JTextField();
	Container container;
	public void init() {
		container=this.getContentPane();
		container.setLayout(new BorderLayout());
		createGUI();
	}

	public void createGUI() {
		JPanel holdGrid = new JPanel();
		JPanel leftGrid = new JPanel();
		JPanel midLeftGrid = new JPanel();
		JPanel midRightGrid = new JPanel();
		JPanel rightGrid = new JPanel();
		
		JButton zero = new JButton("0");
		JButton one = new JButton("1");
		JButton two = new JButton("2");
		JButton three = new JButton("3");
		JButton four = new JButton("4");
		JButton five = new JButton("5");
		JButton six = new JButton("6");
		JButton seven = new JButton("7");
		JButton eight = new JButton("8");
		JButton nine = new JButton("9");
		JButton dec = new JButton(".");
		JButton equals = new JButton("=");
		JButton div = new JButton("/");
		JButton mult = new JButton("*");
		JButton sub = new JButton("-");
		JButton add = new JButton("+");
		
		zero.addActionListener(e -> {
			screen.setText(screen.getText()+"0");
		});
		one.addActionListener(e -> {
			screen.setText(screen.getText()+"1");
		});
		two.addActionListener(e -> {
			screen.setText(screen.getText()+"2");
		});
		three.addActionListener(e -> {
			screen.setText(screen.getText()+"3");
		});
		four.addActionListener(e -> {
			screen.setText(screen.getText()+"4");
		});
		five.addActionListener(e -> {
			screen.setText(screen.getText()+"5");
		});
		six.addActionListener(e -> {
			screen.setText(screen.getText()+"6");
		});
		seven.addActionListener(e -> {
			screen.setText(screen.getText()+"7");
		});
		eight.addActionListener(e -> {
			screen.setText(screen.getText()+"8");
		});
		nine.addActionListener(e -> {
			screen.setText(screen.getText()+"9");
		});
		dec.addActionListener(e -> {
			screen.setText(screen.getText()+".");
		});
		div.addActionListener(e -> {
			screen.setText(screen.getText()+"/");
		});
		mult.addActionListener(e -> {
			screen.setText(screen.getText()+"*");
		});
		sub.addActionListener(e -> {
			screen.setText(screen.getText()+"-");
		});
		add.addActionListener(e -> {
			screen.setText(screen.getText()+"+");
		});
		
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		equals.addActionListener(e -> {
			String equation = screen.getText();
			try {
				screen.setText(engine.eval(equation).toString());
			} catch (ScriptException e1) {
				e1.printStackTrace();
			}
		});
		
		
		leftGrid.setLayout(new GridLayout(4, 1));
		leftGrid.add(seven);
		leftGrid.add(four);
		leftGrid.add(one);
		leftGrid.add(zero);
		
		midLeftGrid.setLayout(new GridLayout(4, 1));
		midLeftGrid.add(eight);
		midLeftGrid.add(five);
		midLeftGrid.add(two);
		midLeftGrid.add(dec);
		
		midRightGrid.setLayout(new GridLayout(4, 1));
		midRightGrid.add(nine);
		midRightGrid.add(six);
		midRightGrid.add(three);
		midRightGrid.add(equals);
		
		rightGrid.setLayout(new GridLayout(4, 1));
		rightGrid.add(div);
		rightGrid.add(mult);
		rightGrid.add(sub);
		rightGrid.add(add);
		
		holdGrid.setLayout(new GridLayout(1, 4));
		holdGrid.add(leftGrid);
		holdGrid.add(midLeftGrid);
		holdGrid.add(midRightGrid);
		holdGrid.add(rightGrid);
		
		container.add(holdGrid, BorderLayout.CENTER);
		container.add(screen, BorderLayout.NORTH);
	}
}

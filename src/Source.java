import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Source {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Source window = new Source();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Source() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Field field = new Field(0, 0, 250, 0, 2, 2, 50, 50);
		field.setBackground(Color.WHITE);
		field.setBounds(0, 0, 484, 261);
		frame.getContentPane().add(field);
		
		field.loadFieldData("E:\\\\Университет\\\\ООП\\\\Лабы\\\\OOP-Lab6-2DArrays\\\\input.txt");
		field.handleField2(5);
	}
}

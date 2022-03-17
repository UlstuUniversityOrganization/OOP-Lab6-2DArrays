import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;

public class Field extends JPanel {
	
	int[][] field;
	int[][] field2;
	
	int fieldPosX;
	int fieldPosY;
	
	int field2PosX;
	int field2PosY;
	
	int fieldWidth;
	int fieldHeight;
	int field2Width;
	int field2Height;
	int cellSizeX;
	int cellSizeY;
	int maxValue;
	
	public class int2{
		int x;
		int y;
	}
	
	public Field(
			int fieldPosX, int fieldPosY, 
			int field2PosX, int field2PosY, 
			int fieldWidth, int fieldHeight,
			int cellSizeX, int cellSizeY){
		
		//field = new int[fieldWidth][fieldHeight];
		//field2 = new int[fieldWidth][fieldHeight];
		
		this.fieldPosX = fieldPosX;
		this.fieldPosY = fieldPosY;
		this.field2PosX = field2PosX;
		this.field2PosY = field2PosY;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.field2Width = fieldWidth;
		this.field2Height = fieldHeight;
		this.cellSizeX = cellSizeX;
		this.cellSizeY = cellSizeY;
	}
	
	public int deleteRow(int[][] array, int yRow, int sizeX, int sizeY) {
		
		int yID = 0;
		for(int x = 0; x < sizeX; x++) {
			yID = 0;
			for(int y = 0; y < sizeY; y++) {
				array[x][yID] = array[x][y];
				
				if(y != yRow)
					yID++;
			}
		}
		return sizeY - 1;
	}
	
	public void handleField2(int minSumValue) {

		for(int y = 0; y < field2Height; y++) {
			int sum = 0;
			for(int x = 0; x < field2Width; x++) {
				sum += field2[x][y];
			}
			if(sum < minSumValue) {
				field2Height = deleteRow(field2, y, field2Width, field2Height);
				y--;
			}
		}
	}
	
	public void loadFieldData(String path) {
		try {
			Scanner sc = new Scanner(new File(path));
			
			fieldPosX = sc.nextInt();
			fieldPosY = sc.nextInt();
			
			fieldWidth = sc.nextInt();
			fieldHeight = sc.nextInt();
			
			field2Width = fieldWidth;
			field2Height = fieldHeight;
			
			cellSizeX = sc.nextInt();
			cellSizeY = sc.nextInt();
			
			maxValue = sc.nextInt();
			
			field = new int[fieldWidth][fieldHeight];
			field2 = new int[field2Width][field2Height];
			
			for(int y = 0; y < fieldHeight; y++)
				for(int x = 0; x < fieldWidth; x++) {
					field[x][y] = sc.nextInt();
					field2[x][y] = field[x][y];
				}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public float interpolate(float a, float b, float t) {
		return a + (b - a) * t;
	}
	
	public Color gradient(Color color1, Color color2, float t) {

		int r = (int)interpolate((float)color1.getRed(), (float)color2.getRed(), t);
		int g = (int)interpolate((float)color1.getGreen(), (float)color2.getGreen(), t);
		int b = (int)interpolate((float)color1.getBlue(), (float)color2.getBlue(), t);
		return new Color(r, g, b);
	}
	
	public void drawCell(Graphics g, int posX, int posY, int sizeX, int sizeY, Color color) {
		g.setColor(color);
		g.fillRect(posX, posY, sizeX, sizeY);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int y = 0; y < fieldHeight; y++)
			for(int x = 0; x < fieldWidth; x++)
			{
				int posX = fieldPosX + x * cellSizeX;
				int posY = fieldPosY + y * cellSizeY;
				//Color colorA = new Color(0, 0, 0);
				//Color colorB = new Color(255, 255, 255);
				
				Color colorA = new Color(62, 201, 146);
				Color colorB = new Color(4, 126, 214);
				float t = ((float)(field[x][y]) / (float)maxValue);
				drawCell(g, posX, posY, cellSizeX, cellSizeY, gradient(colorA, colorB, t));
			}
		
		for(int y = 0; y < field2Height; y++)
			for(int x = 0; x < field2Width; x++)
			{
				int posX = field2PosX + x * cellSizeX;
				int posY = field2PosY + y * cellSizeY;
				//Color colorA = new Color(0, 0, 0);
				//Color colorB = new Color(255, 255, 255);
				
				Color colorA = new Color(62, 201, 146);
				Color colorB = new Color(4, 126, 214);
				float t = ((float)(field2[x][y]) / (float)maxValue);
				drawCell(g, posX, posY, cellSizeX, cellSizeY, gradient(colorA, colorB, t));
			}

	}
}

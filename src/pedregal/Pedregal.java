package pedregal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pedregal {
	
	private int filas;
	private int columnas;
	private int matriz[][];
	
	public Pedregal(int fil, int col){
		this.filas = fil;
		this.columnas = col;

		this.matriz = new int[fil][col];

		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				matriz[i][j] = 0;
	}
	
	public Pedregal(int orden) {
		this.filas = this.columnas = orden;
		this.matriz = new int[this.filas][this.columnas];
		for (int i = 0; i < this.filas; i++)
			for (int j = 0; j < this.columnas; j++)
				this.matriz[i][j] = 0;
	}
	
	public Pedregal(int fil, int col, int[][] mat) {
		this.filas = fil;
		this.columnas = col;

		this.matriz = new int[fil][col];

		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				matriz[i][j] = mat[i][j];
	}
	
	public void calcula(int frente, int profundidad){
		int[][] aux = auxResultados();
		
		
	}
	
	public int[][] auxResultados(){
		int[][] aux = new int[this.filas][this.columnas];
		aux[0][0] = this.matriz[0][0];
		
		// cargo la primer fila
		for(int i = 1; i < this.filas; i++)
			aux[i][0] = this.matriz[i-1][0] + this.matriz[i][0];
		// cargo la primer columna
		for(int j = 1; j < this.columnas; j++)
			aux[0][j] = this.matriz[0][j-1] + this.matriz[0][j];
		
		// cargo los resultados del medio
		for(int i = 1; i < this.filas; i++)
			for(int j = 1; j < this.columnas; j++)
				aux[i][j] = aux[i-1][j] + aux[i][j-1] - aux[i-1][j-1] + this.matriz[i][j];
		
		return aux;
	}
	
	public void setValor(int fil, int col, int valor){
		this.matriz[fil][col] = valor;
	}
	
	public int getValor(int fil, int col){
		return this.matriz[fil][col];
	}
	
	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int[][] getMatriz() {
		return matriz;
	}
	
	public void mostarMatriz() {
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				System.out.println("Fila " + (i + 1) + "\tColumna " + (j + 1) + "\t" + this.matriz[i][j]);
			}
		}
	}
	
	public void leerArchivoMatriz(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));

			this.filas = sc.nextInt();
			this.columnas = sc.nextInt();

			this.matriz = new int[(int) this.filas][(int) this.columnas];

			for (int i = 0; i < this.filas; i++) {
				for (int j = 0; j < this.columnas; j++) {
					this.matriz[sc.nextInt()][sc.nextInt()] = sc.nextInt();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		sc.close();
	}
}

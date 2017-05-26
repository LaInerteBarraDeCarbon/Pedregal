package pedregal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pedregal {

	private int xTerreno, yTerreno, frente, lado, xPosicion, yPosicion;
	private int terreno[][];
	private boolean factible = false;
	private char orientacion;

	public Pedregal(String path) {
		try {
			leerArchivo(path);
		} catch (Exception e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	public void calcula() {
		int i = 0;
		while (!this.factible && i < this.xTerreno) {
			int j = 0;
			while (!this.factible && j < this.yTerreno) {
				if (terreno[i][j] == 0) {
					xPosicion = i;
					yPosicion = j;
					int suma = 0;

					for (int x = 0; x < this.frente; x++)
						for (int y = 0; y < this.lado; y++)
							if (i <= this.xTerreno - this.frente && j <= this.yTerreno - this.lado)
								suma += terreno[x][y];

					if (suma == 0) {
						this.factible = true;
						this.orientacion = 'S';
					} else {
						suma = 0;
						for (int x = 0; x < this.frente; x++)
							for (int y = 0; y < this.lado; y++)
								if (i <= this.xTerreno - this.lado && j <= this.yTerreno - this.frente)
									suma += terreno[y][x];

						if (suma == 0) {
							this.factible = true;
							this.orientacion = 'O';
						}
					}
				}
				j++;
			}
			i++;
		}
	}

	public void setValor(int fil, int col, int valor) {
		this.terreno[fil][col] = valor;
	}

	public int getValor(int fil, int col) {
		return this.terreno[fil][col];
	}

	public int getFilas() {
		return xTerreno;
	}

	public int getColumnas() {
		return yTerreno;
	}

	public int[][] getMatriz() {
		return terreno;
	}

	public void grabarArchivo(String path) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(path));
			if (this.factible) {
				salida.println("SI");
				salida.println(this.xPosicion + " " + this.yPosicion);
				salida.println(this.orientacion);
			} else
				salida.println("NO");
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerArchivo(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			this.xTerreno = sc.nextInt();
			this.yTerreno = sc.nextInt();
			this.frente = sc.nextInt();
			this.lado = sc.nextInt();
			this.terreno = new int[xTerreno][yTerreno];
			int anulados = sc.nextInt();

			for (int i = 0; i < anulados; i++)
				this.terreno[sc.nextInt() - 1][sc.nextInt() - 1] = 1;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		sc.close();
	}

}

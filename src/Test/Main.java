package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		char[] datas = new char[12];
		datas[0]=51;
		datas[1]=48;
		datas[2]=52;
		datas[3]=54;
		datas[4]=55;
		datas[5]=45;
		datas[6]=49;
		datas[7]=51;
		datas[8]=50;
		datas[9]=54;
		datas[10]=51;
		datas[11]=48;
		for(int i = 0 ; i < 12 ; i++)
			System.out.print(datas[i]);
		System.out.println();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Serveur ou client? (s/c) ");
		String rep;
		try {
			rep = input.readLine();

			if (rep.equals("s")) {
				TestServeur testS = new TestServeur();
				testS.start();
			} else {
				TestClient testC = new TestClient();
				testC.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package Test;

import java.sql.Time;
import java.sql.Timestamp;

import Client.Client;

public class TestClient {

	public void start() {
		Client client = new Client("challenge01.root-me.org", 51015);
		// Le serveur envoie son bonjour
		//String test = client.readBytesAsString(7);
		// On lui répond
	//	System.out.println("Recu:" + test);
		//if (test.equals("Bonjour")) {
			//System.out.println("Envoie:Les licornes multicolores");
		//	client.sendBytes("Les licornes multicolores".getBytes());
		String recu = client.readBytesAsString(50);
		System.out.println("Recu:"+recu);
		int maxC = 0;
		long max = 0;
		int taille = 12;
		byte[] datas = new byte[taille];
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
		for(byte c = 40;c < 60; c++ ){
			
			datas[11]=c;
			for(int i = 0 ; i < taille ; i++){
				client.addData(datas[i]);
			}
			System.out.println("Test:"+c);
			long tempsDebut = System.currentTimeMillis();
			client.sendData();
			recu = client.readBytesAsString(50);
			if(recu.equals("Bye")){
				System.out.println(recu);
				client.close();
				client = new Client("challenge01.root-me.org", 51015);
			}else{
				long tempsFin = System.currentTimeMillis();
				System.out.println(tempsFin-tempsDebut+"msec :"+recu);
				if(tempsFin-tempsDebut > max){
					max = tempsFin-tempsDebut;
					maxC = c;
					//System.out.println("Max = "+max+" pour "+maxC);
				}
				if(max > 6000){
					System.out.println("Max = "+max+" pour "+maxC);
					System.exit(0);
				}
			}
				
		}
		client.close();
		System.out.println("Max = "+max+" pour "+maxC);
		//}
	}
}

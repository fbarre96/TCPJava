package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import Client.Client;

public class Serveur extends Thread{
	private ServerSocket welcomeSocket;
	private Client c;
	public Serveur(int port) {
		 try {
			welcomeSocket = new ServerSocket(port);
			Socket s = welcomeSocket.accept();
			c = new Client(s);
			
			System.out.println("Envoi:Bonjour");
			c.sendBytes("Bonjour".getBytes());
			String recu = c.readBytesAsString(100);
			System.out.println("Recu:"+recu);
			System.out.println("Envoi:INIT");
			c.sendBytes("INIT".getBytes());
			byte[] dataRecu = new byte[40];
			c.readBytes(dataRecu);
			System.out.format("%c%d%d%d\n",(char)dataRecu[0],(byte)dataRecu[1],(byte)dataRecu[2],ByteBuffer.wrap(dataRecu, 3, 4).getInt());
			System.out.println("Envoi:Fin de partie. Deconnexion.");
			c.sendBytes("Fin de partie. Deconnexion.".getBytes());
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	

}

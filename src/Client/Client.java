package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
public class Client{
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;
	private ArrayList<Byte> data;
	
	public Client(String ip, int port) {
		data = new ArrayList<Byte>();
		try {
			clientSocket = new Socket(ip, port);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Client(Socket s) {
		data = new ArrayList<Byte>();
		try {
			clientSocket = s;
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public String readLine() {
		try {
			return inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	//Non bloquant
	public int readBytes(byte[] b){
		try {
			return inFromServer.read(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public void send(String string) {
		try {
			outToServer.writeBytes(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendBytes(byte[] b) {
		try {
			outToServer.write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String readBytesAsString(int taille) {
		byte[] b = new byte[taille];
		int reelTaille = readBytes(b);
		return new String(b).substring(0, reelTaille);
	}
	public boolean isClosed() {
		return clientSocket.isClosed();
	}
	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sendData(){
		Byte[] a = new Byte[1];
		Byte[] datas = data.toArray(a);
		byte[] RealDatas = new byte[datas.length];
		for(int i = 0 ; i < datas.length ; ++i){
			RealDatas[i] = datas[i].byteValue();
		}
		sendBytes(RealDatas);
		data.clear();
	}
	public void addData(char c){
		data.add((byte)c);
	}
	public void addData(byte b){
		data.add(b);
	}
	public void addData(int i){
		byte[] j = ByteBuffer.allocate(4).putInt(i).array();
		for(int z = 0 ; z < 4 ; ++z)
			data.add(new Byte(j[z]));
	}
	public void addData(short i){
		byte[] j = ByteBuffer.allocate(Short.BYTES).putShort(i).array();
		for(int z = 0 ; z < Short.BYTES ; ++z)
			data.add(new Byte(j[z]));
	}
}

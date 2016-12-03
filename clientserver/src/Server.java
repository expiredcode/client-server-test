import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	static List players = new ArrayList();
	 
	static long lastSeen=System.currentTimeMillis();
	
	
	public static void main(String[] args) throws Exception
	{
		int i = 0;
		boolean last = false;
		P p = new P();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(outputStream);		
		
		DatagramSocket serverSocket = new DatagramSocket(9876);
		serverSocket.setSoTimeout(30);
		while(true)
		{
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);			
			
			try{
				 serverSocket.receive(receivePacket);
				// byte[] data = receivePacket.getData();
				 String[] data = (new String(receivePacket.getData())).replaceAll("\\s+", "").split("\\$");
			/*	 ByteArrayInputStream in = new ByteArrayInputStream(data);
				 ObjectInputStream is = new ObjectInputStream(in);*/
				// try{
				//	 p = (P)is.readObject();
					 if(data[0].equals("prova"))//p.s=="")
					 {
						 //new player set s to PlayerX and add ip to players list
						 
						// p.s="Player1OK";
						 System.out.println("New Player connected "+data[1]);
						 players.add("Player1");
					 }
					 else{
						// System.out.println(""+i+data); i++;	
						 System.out.println("-"+data[0]+"-\n"+data[0].equals("prova"));
					 }
					 //check which player sent the packet and update the timecounter of the relative player timeFromLastReceivedPacked
					 //update with p info
					 lastSeen=System.currentTimeMillis();
					 last=false;
			//	 }catch(Exception e){}
			}catch(Exception e){ if(!e.toString().contains("timed"))System.out.println("Exception\n"+e.toString());}
			
			if(System.currentTimeMillis()-lastSeen>5000&&!last)
			{
				System.out.println("Player Timed out");
				players.clear();
				last=true;
			}
			
			//*
			//String sentence = new String(receivePacket.getData());
			//	System.out.println("RECEIVED: "+sentence);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
			//	String capitalizedSentence = sentence.toUpperCase();
			//i++;
			//String s = "Ok "+i; s.toUpperCase();
			//	sendData = capitalizedSentence.getBytes();
			
			//sendData = s.getBytes();
			//os.writeObject(p);*/
			if(!players.isEmpty())
			try{
			//sendData = outputStream.toByteArray();
			sendData = "Hello Player1$3.75".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			}catch(Exception e){}//*/
		}		
	}

}

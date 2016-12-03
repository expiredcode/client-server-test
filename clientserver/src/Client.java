import java.io.*;
import java.net.*;

class Client
{
	public static P p = new P();
	
	
	public static void main(String[] args) throws Exception
	{
		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout(30);
		
		byte[] receiveData = new byte[1024];
		//byte[] sendData = new byte[1024];
		
		long timeDelay = System.currentTimeMillis();
		long delay,lastSeen= System.currentTimeMillis();
		
		boolean last = false;
		
		p.s="";
		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		
		//byte[] sendData = outputStream.toByteArray();
		InetAddress IPAddress = InetAddress.getByName("localhost"); 
		
		while (true)
		{
			delay=System.currentTimeMillis()-timeDelay;
			if(delay>50)
			{
				try{			
					os.writeObject(p);
			//		sendData = outputStream.toByteArray();
					byte[] sendData = "prova$player1".getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress, 9876);
					clientSocket.send(sendPacket);
				}catch(Exception e){ System.out.println("Exception\n"+e.toString());}
				timeDelay = System.currentTimeMillis();
			//	System.out.println("yay");
			}else{}//System.out.println("Not yet");}
		//*	
		try{	
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				clientSocket.receive(receivePacket);
				/*byte[] data = receivePacket.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				p = (P)is.readObject();*/
				String[] data = new String(receivePacket.getData()).replaceAll("\\s+", "").split("\\$");
				/*if(p.s.contains("OK"))
				{
					p.s.substring(0, p.s.length()-2);
					System.out.println("Connected as "+p.s);
				}*/
				System.out.println(""+data[0]+" "+(Float.parseFloat(data[1])*10));
				lastSeen= System.currentTimeMillis();				
				last=false;
			}catch(Exception e){}//*/
		
			if(System.currentTimeMillis()-lastSeen>5000&&!last)
			{
				System.out.println("Server timed out");
				last = true;
			}
			
		}
	}
}
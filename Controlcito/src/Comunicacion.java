import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable{
	
	private MulticastSocket multi;
	private final String Grupo = "224.3.3.3";
	private final int Puerto = 5000;
	private InetAddress ia;
	
	public Comunicacion() {
		// TODO Auto-generated constructor stub
		try {
			multi = new MulticastSocket(Puerto);
			ia = InetAddress.getByName(Grupo);
			multi.joinGroup(ia);
			
		} catch (SocketException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (multi != null) {
				byte[] buffer = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				try {
					multi.receive(packet);
					setChanged();
					notifyObservers(packet);
					clearChanged();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}

}

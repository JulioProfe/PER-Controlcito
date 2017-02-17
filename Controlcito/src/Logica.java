import java.net.DatagramPacket;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer {
	private PApplet app;
	private int x, y, r, g, b;
	private Comunicacion com;
	private String data;

	public Logica(PApplet app) {
		// TODO Auto-generated constructor stub
		data = "";
		this.app = app;
		com = new Comunicacion();
		Thread hilo = new Thread(com);
		hilo.start();

		x = app.width / 2;
		y = app.height / 2;
		r = (int) app.random(255);
		g = (int) app.random(255);
		b = (int) app.random(255);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		DatagramPacket p = (DatagramPacket) arg1;
		data = new String(p.getData(), 0, p.getLength());
	}

	public void pintar() {
		// TODO Auto-generated method stub
		app.fill(r, (int) g, (int) b);
		app.noStroke();
		app.ellipse(x, y, 50, 50);
		
		if (data.equals("up")) {
			y --;
		}
		if (data.equals("down")) {
			y ++;
		}
		if (data.equals("right")) {
			x ++;
		}
		if (data.equals("left")) {
			x --;
		}
	}

}

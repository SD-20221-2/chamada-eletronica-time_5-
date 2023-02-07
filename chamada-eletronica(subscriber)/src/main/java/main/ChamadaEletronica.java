package main;

import java.nio.charset.Charset;
import org.zeromq.*;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class ChamadaEletronica {
	public static void main(String[] argv) {
		final Context context = ZMQ.context(1);
		final Socket subscriber = context.socket(SocketType.SUB);
		subscriber.connect("tcp://localhost:5555");

		String filter = "TAG INFO";
		subscriber.subscribe(filter.getBytes(Charset.forName("UTF-8")));
		while (true) {
			String msg = subscriber.recvStr();
			System.out.println(msg);
		}
	}
}

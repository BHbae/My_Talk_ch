package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server {

	// 유저 관리
	private static final int PORT = 5000;
	private static Vector<ConnectUser> connectedUsers = new Vector<>();

	// 프레임 창
	private ServerFream serverFream;
	private JTextArea mainBoard;

	// 소켓
	private ServerSocket serverSocket;
	private Socket socket;

	// 인, 아웃
	private PrintWriter out;
	private BufferedReader in;

	// 메세지 관리
	private String message;

	// 포트 번호
	private int protNum = 5002;

	public Server() {
		serverFream = new ServerFream(this);
		mainBoard = serverFream.getMainBoard();
	}

	// 포트번호 입력 장치
	public void serverStart() {

		try {
			serverSocket = new ServerSocket(protNum);
			serverFream.getConnectBtn().setEnabled(false);
			// 대기상태로 옮긴다
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "넘버 오류", "알림", JOptionPane.ERROR_MESSAGE, null);
			serverFream.getConnectBtn().setEnabled(true);
		}

	} // end of serverStart

	private void clientConnect() {
		new Thread(() -> {

			while (true) {
				try {
					socket = serverSocket.accept();
					// 창 띄워 줘야뎀
					mainBoard("사용자 연결 대기중");

					ConnectUser user = new ConnectUser(socket);

				} catch (IOException e) {
					mainBoard("사용자 연결 Error");

				}
			}

		}).start();
	}

	private void mainBoard(String str) {
		try {
			mainBoard.append(str);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입출력 에러!", "알림", JOptionPane.ERROR_MESSAGE, null);
		}

	}// end of mainBoard

	private class ConnectUser {

		// 소켓
		private Socket socket;

		// 입 출력
		private BufferedReader reader;
		private BufferedWriter writer;

		// 정보
		private String name;

		public ConnectUser(Socket socket) {
			this.socket = socket;
			ioConnect(); // 입출력 시작
		}

		// 입출력
		private void ioConnect() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				initInfo();
			} catch (Exception e) {
				mainBoard("입출력 안되고있어용");
				JOptionPane.showMessageDialog(null, "입출력 에러!", "알림", JOptionPane.ERROR_MESSAGE, null);
			}

		} // end of ioConnect

		private void initInfo() {

			try {
				name = reader.readLine();
				mainBoard("접속 : " + name + "\n");

			} catch (Exception e) {
				mainBoard("접속 Error");
			}

		}
		
		

	}// end of class

	// get,set

	public static void main(String[] args) {
		new Server();
	}

}// end of class

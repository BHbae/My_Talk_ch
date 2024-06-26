package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

// UserClient ex Sock  {
//	id =  1 
//	P
//	B
//}

public class Server {

	// 유저 관리
	private static final int PORT = 5010;
	private static Vector<PrintWriter> connectedUsers = new Vector<>();
	private static ArrayList<String> name = new ArrayList<>();

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
	private int protNum = 5010;

	// 아이피 셋

	public Server() {
		serverFream = new ServerFream(this);
		mainBoard = serverFream.getMainBoard();
	}

	// 포트번호 입력 장치
	public void serverStart() {

		try {
			serverSocket = new ServerSocket(protNum);
			serverFream.getConnectBtn().setEnabled(false);
			serverFream.getInputPort().setEditable(false);
			clientConnect();
			mainBoard("서버 오픈\n");
			System.out.println("serverStart");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "넘버 오류", "알림", JOptionPane.ERROR_MESSAGE, null);
			serverFream.getConnectBtn().setEnabled(true);
		}

	} // end of serverStart

	// 소켓 연결
	private void clientConnect() {
		new Thread(() -> {

			while (true) {
				try {
					mainBoard(">>>>>>>>>>>> 사용자 연결 대기중 <<<<<<<<<<\n");
					socket = serverSocket.accept();
					// 창 띄워 줘야뎀
					new ConnectUser(socket).start();
					mainBoard("연결했당\n");

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

	// 유저 관리
	private class ConnectUser extends Thread {

		// 소켓 관리
		private Socket socket;

		// 닉네임
		private String id;

		// 입 출력
		private BufferedReader reader;
		private PrintWriter out;

		public ConnectUser(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				id = reader.readLine();
				connectedUsers.add(out);
				mainBoard.append(id + " 입장\n");;
				broadcastMessage(id + " 입장");
				String message;
				while ((message = reader.readLine()) != null) {
					mainBoard.append(message + "\n");
					broadcastMessage(message);
					if (message.startsWith("ID")) {
						String id[] = message.split(" : "); 
						name.add(id[1]);
					}

				}
			} catch (Exception e) {
				broadcastMessage(id + " 나감\n");
				mainBoard.append(id + " 나감\n");
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					mainBoard("종료");
				}
			}
		}

	} // end of class

	// 모든 클라이언트에게 메세지 보내기 - 브로드 캐스트
	private void broadcastMessage(String message) {
		for (PrintWriter writer : connectedUsers) {
			writer.println(message);
		}
	}

	// get,set

	public static ArrayList<String> getName() {
		return name;
	}

	public static void setName(ArrayList<String> name) {
		Server.name = name;
	}

	// 메인
	public static void main(String[] args) {
		new Server();
	}

}// end of class

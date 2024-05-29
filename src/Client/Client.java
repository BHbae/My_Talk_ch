package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client {

	// 프레임
	private ClientFream clientFream;
	private JTextArea mainBoard;

	// 소켓
	private Socket socket;

	// 입출력 장치
	private BufferedReader reader;
	private BufferedWriter writer;
	private PrintWriter out;
	private BufferedReader keyboard; // 키보드 읽기

	// 연결 주소
	private String ip;
	private int port;

	// 이름
	private String name;

	// 메세지
	private String message;
	// 메세지 버튼
	private JButton messageBtn;
	
	// 아이피 필드
	private String ipFiled;

	public Client() {
		clientFream = new ClientFream(this);
		mainBoard = clientFream.getMainBoard();
		messageBtn = clientFream.getMessageBtn();
		ipFiled = clientFream.getInputIp().getText();
	}

	public void connectServer() {
		try {
			socket = new Socket(ipFiled, 5010);
			mainBoard.append("아이피 포트 연결\n");
			name = clientFream.getInputId().getText();
			clientFream.getConnectBtn().setEnabled(false);
			messageBtn.setEnabled(true);
			chatTing();
			connectIO();
		} catch (Exception e) {
			mainBoard("connectServer 오휴\n");
		}
	}

	private void connectIO() {
		try {
			// 입출력 장치
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (Exception e) {
			mainBoard.append("connectIO 오류");
		}
	} // end of connectIO

	// 서버에서 클라언트로
	private void chatTing() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			mainBoard.append(">>>> 서버에 접속 완료 <<<< \n");
			
			new Thread(() -> {
				try {
					String serverMsg;
					while((serverMsg = in.readLine()) != null) {
						mainBoard.append(serverMsg + "\n"); 
					}
				
				} catch (Exception e) {
					mainBoard.append("읽기오류");
				}
			}).start();

		} catch (Exception e) {
			mainBoard.append("chatTing");
		}

	}

	private void writer(String str) {
		try {
			writer.write(str + "\n");
			writer.flush();
			mainBoard.append(str);
		} catch (IOException e) {
			mainBoard("writer 오류 \n");
		}
	}

	private void mainBoard(String str) {
		try {
			mainBoard.append(str);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입출력 에러!", "알림", JOptionPane.ERROR_MESSAGE, null);
		}
		
	}// end of mainBoard
	
	public void sendMessage() {
		String message = clientFream.getMessageBox().getText();
		if (!message.isEmpty()) {
			out.println(name + " : " + message);
			// appendMessage(message);
			clientFream.getMessageBox().setText("");
		}
	}

	    public void setPrintWriter(PrintWriter out) {
	        this.out = out;
	    }

	    public void appendMessage(String message) {
	        mainBoard.append(message + "\n");
	    }
	    
	    
	    
	    
	    
// 겟셋
	

	public ClientFream getClientFream() {
		return clientFream;
	}

	public void setClientFream(ClientFream clientFream) {
		this.clientFream = clientFream;
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(BufferedReader keyboard) {
		this.keyboard = keyboard;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JButton getMessageBtn() {
		return messageBtn;
	}

	public void setMessageBtn(JButton messageBtn) {
		this.messageBtn = messageBtn;
	}
	// 메인
		public static void main(String[] args) {
			new Client();
		}
}

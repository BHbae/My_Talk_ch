package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFream extends JFrame {

	// 연결
	private Client mContext;

	private ScrollPane scrollPane;

	// 백그하운드 패널
	private BackgroundPanel backgroundPanel;

	// 포트 번호
	// private int protNum = 5000;

	// 중앙
	private JPanel mainPanel;
	private JTextArea mainBoard;

	// 아이피
	private JPanel ipPanel;
	private JLabel ipLabel;
	private JTextField inputIp;

	// 포트
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;

	// 닉넴
	private JPanel idPanel;
	private JLabel idLabel;
	private JTextField inputId;

	// 연결 버튼
	private JButton connectBtn;

	// 메세지
	private JPanel messagePanel;
	private JLabel messageLabel;
	private JTextField messageBox; // 보내는거
	private JButton messageBtn;

	public ClientFream(Client mContext) {
		this.mContext = mContext;
		InitFream();
		setting();
		initListener();
	}

	// 프레임
	private void InitFream() {
		// 백그라운드 이미지 컴포넌트
		backgroundPanel = new BackgroundPanel();

		// 중앙
		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		scrollPane = new ScrollPane();

		// 아이피 컴포넌트
		ipPanel = new JPanel();
		ipLabel = new JLabel("아이피");
		inputIp = new JTextField(13);

		// 포트 컴포넌트
		portPanel = new JPanel();
		portLabel = new JLabel("포트 번호");
		inputPort = new JTextField(12);

		// 닉네임 컴포넌트
		idPanel = new JPanel();
		idLabel = new JLabel("ID");
		inputId = new JTextField(10);

		// 연결 버튼
		connectBtn = new JButton("Connect");

		// 메세지
		messagePanel = new JPanel();
		messageLabel = new JLabel("메세지 입력란");
		messageBox = new JTextField(20);
		messageBtn = new JButton("보내기");

		inputPort.setText("5010");
		inputIp.setText("");

	}

	// 프레임 셋팅
	private void setting() {
		setTitle("춘식이의 속닥속닥");
		setSize(750, 850);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 화면 중앙에 셋팅
		setLocationRelativeTo(null);

		setLayout(null);

		// 백그라운드
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

//여기부터
		// 포트
		portPanel.setBounds(50, 50, 650, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.add(portLabel);
		portPanel.add(inputPort);

		// 아이피
		portPanel.add(ipLabel);
		portPanel.add(inputIp);

		// 이름
		portPanel.add(idLabel);
		portPanel.add(inputId);
		portPanel.add(connectBtn);
		backgroundPanel.add(portPanel);
// 여기까지 하나로

		// 메세지 컴포넌트
		messagePanel.setBounds(50, 760, 600, 50);
		messagePanel.setBackground(new Color(0, 0, 0, 0));
		messagePanel.add(messageBox);
		messagePanel.add(messageBtn);
		backgroundPanel.add(messagePanel);

		// 메인 컴포넌트
		mainPanel.setBounds(50, 100, 630, 650);
		mainPanel.setBackground(Color.WHITE);

		mainBoard.setEditable(false);
		mainPanel.add(scrollPane);
		scrollPane.setBounds(55, 100, 600, 650);
		scrollPane.add(mainBoard);
		backgroundPanel.add(mainPanel);

		setVisible(true);
	}

	private void initListener() {
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.connectServer();
			}
		});
		messageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.sendMessage();
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					mContext.sendMessage();
					break;
				}
			}
		});

	}

	// 백그라운드 내부 클래스
	private class BackgroundPanel extends JPanel {
		private JPanel backgroundPanel;
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/kakao2.png").getImage();
			backgroundPanel = new JPanel();
			add(backgroundPanel);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}

	}

	public Client getmContext() {
		return mContext;
	}

	public void setmContext(Client mContext) {
		this.mContext = mContext;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public BackgroundPanel getBackgroundPanel() {
		return backgroundPanel;
	}

	public void setBackgroundPanel(BackgroundPanel backgroundPanel) {
		this.backgroundPanel = backgroundPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}

	public JPanel getIpPanel() {
		return ipPanel;
	}

	public void setIpPanel(JPanel ipPanel) {
		this.ipPanel = ipPanel;
	}

	public JLabel getIpLabel() {
		return ipLabel;
	}

	public void setIpLabel(JLabel ipLabel) {
		this.ipLabel = ipLabel;
	}

	public JTextField getInputIp() {
		return inputIp;
	}

	public void setInputIp(JTextField inputIp) {
		this.inputIp = inputIp;
	}

	public JPanel getPortPanel() {
		return portPanel;
	}

	public void setPortPanel(JPanel portPanel) {
		this.portPanel = portPanel;
	}

	public JLabel getPortLabel() {
		return portLabel;
	}

	public void setPortLabel(JLabel portLabel) {
		this.portLabel = portLabel;
	}

	public JTextField getInputPort() {
		return inputPort;
	}

	public void setInputPort(JTextField inputPort) {
		this.inputPort = inputPort;
	}

	public JPanel getIdPanel() {
		return idPanel;
	}

	public void setIdPanel(JPanel idPanel) {
		this.idPanel = idPanel;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JTextField getInputId() {
		return inputId;
	}

	public void setInputId(JTextField inputId) {
		this.inputId = inputId;
	}

	public JButton getConnectBtn() {
		return connectBtn;
	}

	public void setConnectBtn(JButton connectBtn) {
		this.connectBtn = connectBtn;
	}

	public JPanel getMessagePanel() {
		return messagePanel;
	}

	public void setMessagePanel(JPanel messagePanel) {
		this.messagePanel = messagePanel;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(JLabel messageLabel) {
		this.messageLabel = messageLabel;
	}

	public JTextField getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(JTextField messageBox) {
		this.messageBox = messageBox;
	}

	public JButton getMessageBtn() {
		return messageBtn;
	}

	public void setMessageBtn(JButton messageBtn) {
		this.messageBtn = messageBtn;
	}

	// get,set

}

package Server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFream extends JFrame {

	private Server mCOntext;

	private ScrollPane scrollPane;

	// 백그하운드 패널
	private BackgroundPanel backgroundPanel;

	// 중앙 보드
	private JPanel mainPanel;
	private JTextArea mainBoard;

	// 포트패널
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;
	private JButton connectBtn;

	// 포트 번호
	private int protNum = 5002;

	public ServerFream(Server mContext) {
		this.mCOntext = mContext;
	}

	// 프레임 부착
	private void initFream() {

		// 백그라운드 패널
		backgroundPanel = new BackgroundPanel();

		// 중앙패널
		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		// 포트 패널
		portPanel = new JPanel();
		portLabel = new JLabel("포트번호");
		inputPort = new JTextField(5);
		connectBtn = new JButton("실행");

		// 포트 넘
		inputPort.setText("protNum");
	}

	// 프레임 셋팅
	private void setting() {
		setTitle("춘식이의 조잘조잘");
		setSize(750, 850);

		// 화면 중앙에 셋팅
		setLocationRelativeTo(null);

		// x 누르면 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);

		// 백그라운드 패널
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// 포트 패널
		portPanel.setBounds(50, 50, 200, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		portPanel.add(connectBtn);
		backgroundPanel.add(portPanel);

		// 메인 컴포넌트
		mainPanel.setBounds(50, 100, 320, 350);
		mainPanel.setBackground(Color.BLUE);

		mainBoard.setEditable(false);
		mainBoard.add(scrollPane);
		mainBoard.setBounds(55, 100, 300, 315);
		backgroundPanel.add(mainPanel);

		setVisible(true);
	}

	private void ininEvent() {
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mCOntext.serverStart();
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

	public Server getmCOntext() {
		return mCOntext;
	}

	public void setmCOntext(Server mCOntext) {
		this.mCOntext = mCOntext;
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

	public JButton getConnectBtn() {
		return connectBtn;
	}

	public void setConnectBtn(JButton connectBtn) {
		this.connectBtn = connectBtn;
	}

	public int getProtNum() {
		return protNum;
	}

	public void setProtNum(int protNum) {
		this.protNum = protNum;
	}

}
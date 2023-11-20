import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class graphic {
	public static void main(String[] args) {
		Client C = new Client("Login");
	}
}

class Client implements WindowListener {
	JFrame CL;
	JPanel p1 = new JPanel();
	Font f1 = new Font("휴먼매직", Font.BOLD, 20);
	ImageIcon image = new ImageIcon("login.png");
	ImageIcon image2 = new ImageIcon("register.png");
	ImageIcon image3 = new ImageIcon("rank.png");
	JTextField JID = new JTextField();
	JPasswordField JPW = new JPasswordField();
	JLabel L1 = new JLabel("Random Puzzle Online");
	JLabel ID = new JLabel("ID");
	JLabel PW = new JLabel("Password");
	JButton L, R, R2;

	Client(String name) {
		CL = new JFrame(name);
		CL.setSize(500, 400);
		CL.addWindowListener(this);
		CL.setLayout(null);
		CL.add(L1);
		CL.add(ID);
		CL.add(JID);
		CL.add(PW);
		CL.add(JPW);

		L = new JButton(image);
		R = new JButton(image2);
		R2 = new JButton(image3);

		L.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				L.setBorderPainted(true);
				L.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				L.setBorderPainted(false);
			}

			public void mouseClicked(MouseEvent e) {
				flow();
			}
		});
		R.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				R.setBorderPainted(true);
				R.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				R.setBorderPainted(false);
			}

			public void mouseClicked(MouseEvent e) {
				// 회원가입 파트
			}
		});
		R2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				R2.setBorderPainted(true);
				R2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				R2.setBorderPainted(false);
			}

			public void mouseClicked(MouseEvent e) {
				// 랭킹 파트
			}
		});
		CL.add(L);
		CL.add(R);
		CL.add(R2);

		L1.setBounds(125, 0, 250, 100);
		L1.setFont(f1);
		ID.setBounds(30, 215, 40, 30);
		JID.setBounds(80, 215, 150, 30);
		PW.setBounds(10, 250, 80, 30);
		JPW.setBounds(80, 250, 150, 30);
		L.setBounds(250, 205, image.getIconWidth() + 15, image.getIconHeight() + 15);
		L.setBorderPainted(false);
		L.setContentAreaFilled(false);
		L.setFocusPainted(false);
		R.setBounds(350, 205, image2.getIconWidth() + 15, image2.getIconHeight() + 15);
		R.setBorderPainted(false);
		R.setContentAreaFilled(false);
		R.setFocusPainted(false);
		R2.setBounds(200, 100, image3.getIconWidth() + 15, image3.getIconHeight() + 15);
		R2.setBorderPainted(false);
		R2.setContentAreaFilled(false);
		R2.setFocusPainted(false);

		CL.setVisible(true);
	}

	void flow() {
		String name = JOptionPane.showInputDialog("원하시는 필드의 크기를 입력해주세요."); // 입력창을 하나띄워서 N*N크기로 사진을 자르기위한 변수를 입력받는다.
		int size = Integer.parseInt(name); // 입력받은 값 size로 설정
		CL.setVisible(false);
		Puzzle puzzle = new Puzzle("퍼즐게임", size);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}

class Puzzle implements ActionListener, WindowListener {
	Time t;
	JFrame f;
	ImageIcon m1;
	JLabel l1 = new JLabel(m1);
	JButton[][] b1;
	Image[] image;
	Panel p = new Panel();
	String stemp;
	int i, j, size = 0, temp;
	int c1, c2;
	int[] check;
	int[] pArray;
	int rnum, pnum=0;
	int height = 0, width = 0;
	
	Set hs = new LinkedHashSet();
	Iterator it;

	Puzzle(String name, int size) {
		randPhoto(); // 랜덤사진 고르기 함수 호출
		f = new JFrame(name); // 메인에서 받아온 name으로 JFrame선언
		f.setSize(width + 15, height + 100); // 프레임크기를 width는 사진의 크기+버튼간 갭의 합으로 설정 , height는 timer공간만큼 더하여 설정
		this.size = size;
		flow();
	}

	void flow() {
		f.addWindowListener(this);
		fset();
		InArray();
		hsSet();
		makeMap();

	}

	void randPhoto() {// 랜덤사진
		int rand;

		rand = (int) (Math.random() * 3); // 0,1,2값 내에서 나온 숫자로 사진을 선택 , 아래switch참조
		switch (rand) {
		case 0:
			stemp = "a" + ".jpg";
			break;
		case 1:
			stemp = "b" + ".jpg";
			break;
		case 2:
			stemp = "c" + ".jpg";
			break;
		}
		m1 = new ImageIcon(stemp); // ImageIcon에 사진파일을 불러온다.
		height = m1.getIconHeight(); // 사진의 height width 값을 변수에 저장
		width = m1.getIconWidth();
	}

	void fset() { // JFrame 셋팅함수
		p.setSize(width, height); // panel의 크기를 설정한다 (JFrame은 height+100,width)
		p.setLayout(new GridLayout(size, size)); // GridLayOut형태로 N*N형태로 panel을 구분짓는다.
		f.add(p, "West"); // 서쪽방향에 panel을 위치

		f.setVisible(true);// Frame을 표시한다.
		t = new Time(stemp);
		f.add(t.tl, "North"); // 북쪽에 타이머의 라벨을 위치
		t.start(); // 타이머 스타트
	}
	void InArray() {
		pArray = new int[(size*size)];
		for(int i=0;i<size*size;i++) {
			pArray[i] = i;
			System.out.print(pArray[i]+" ");
			
		}
		for(int i=0;i<1000;i++) {
			int num = 0;
			rnum = (int) (Math.random() * ((size * size) - 2));
			pnum = (int) (Math.random() * 3);    //0(상) 1(우) 2(하) 3(좌)
			switch(pnum) {
			case 0:
				if(rnum / size == 0 ) {
					continue;
				}
				else {
					num = pArray[rnum];
					pArray[rnum] = pArray[rnum-size];
					pArray[rnum-size] = num;
				}
				
			case 1:
				if(rnum % size == size-1 || rnum == size * size - (size-1)) {
					continue;
				}
				else {
					num = pArray[rnum];
					pArray[rnum] = pArray[rnum+1];
					pArray[rnum+1] = num;
				}
			case 2:
				if(rnum / size == size-1 || rnum == size * size -(size+1) ) {
					continue;
				}
				else {
					num = pArray[rnum];
					pArray[rnum] = pArray[rnum+size];
					pArray[rnum+size] = num;
				}
			case 3:
				if(rnum / size == 0) {
					continue;
				}
				else {
					num = pArray[rnum];
					pArray[rnum] = pArray[rnum-1];
					pArray[rnum-1] = num;
				}
				
			}
			
		}
		System.out.println();
		for(int i =0 ;i<size*size;i++) {
			System.out.print(pArray[i]+" ");
		}
		
		
	}
	
	void hsSet() { // LinkedHashSet사용 시 중복없이 배열형성가능 값이 들어갈 때 크기가 커지는 형태이므로 size=5일시 25가 되기전까지 계속해서
					// 랜덤값을 대입
		while (hs.size() < (size * size) - 1) {
			temp = (int) (Math.random() * (size * size - 1));

			hs.add(temp);
		}
		it = hs.iterator(); // hs의 iterator를 저장
	}

	void makeMap() {
		int x = 0, y = 0, temp = 0;
		check = new int[size * size];
		image = new Image[size * size];
		b1 = new JButton[size][size];

		i = width / size; // size로 나눈 width값을 i
		j = height / size; // size로 나눈 height값을 j
		while (temp < size * size) { // N*N크기 만큼 표현
			Image im = m1.getImage(); // image객체 im에 m1의 이미지를 대입
			im = f.createImage(new FilteredImageSource(im.getSource(), new CropImageFilter(x, y, i, j))); // x,y위치로부터 i
																											// j크기만큼 사진을
																											// 자른다
			image[temp] = im;// 자른 사진크기를 image[0~n*n-1]범위까지 저장
			x += j;// x에 j를 더하여 사진을 자를때마다 자르는 위치가 미니사진의 한 개 크기만큼 밀리도록 한다.
			temp++;
			if (temp % size == 0) { // 만약n번만큼 자른 경우에는 다음라인으로 내려와서 잘라야하므로 y를i만큼 더하여 다음라인으로 이동하고 x는 다시 처음위치로 이동시킨다.
				y += i;
				x = 0;
			}
		}

		int a = 0;
		String stemp;
		int d = i, b = j;
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {

				if (it.hasNext()) { // 현재it은 hs의 iterator를 가리키는 중 , hs에 다음 값이 있을때 작동
					a = (int) it.next(); // it.next()에 저장된 값을 a에 대입
				} else {
					break;
				}

				b1[i][j] = new JButton(new ImageIcon(image[a])); // hs에는 0~n*n-1 범위까지의 랜덤 값이 들어있었고 image배열의 랜덤위치 값을
																	// JButton에 삽입하여 b1배열에 순차적으로 대입
				b1[i][j].addActionListener(this); // 이벤트추가

				stemp = String.valueOf(a);
				b1[i][j].setActionCommand(stemp);// 해당숫자값을 이벤트 매개변수로 넘김
				b1[i][j].setPreferredSize(new Dimension(d, b));// 이미지가 저장된 button크기를 width/n , height/n크기로 설정한 후
				p.add(b1[i][j]);// gridlayout형태의 panel에 저장
			}
		}

		b1[size - 1][size - 1] = new JButton(); // hs에는 0~n*n-1 범위까지의 랜덤 값이 들어있었고
												// image배열의 랜덤위치 값을
		// JButton에 삽입하여 b1배열에 순차적으로 대입

		b1[size - 1][size - 1].addActionListener(this); // 이벤트추가
		stemp = String.valueOf(size * size - 1);
		b1[size - 1][size - 1].setActionCommand(stemp);// 해당숫자값을 이벤트 매개변수로 넘김
		b1[size - 1][size - 1].setPreferredSize(new Dimension(d, b));// 이미지가 저장된 button크기를 width/n , height/n크기로 설정한 후
		p.add(b1[size - 1][size - 1]);// gridlayout형태의 panel에 저장

		c1 = size - 1;
		c2 = size - 1;
		LineBorder b2 = new LineBorder(Color.red, 5);
		b1[c1][c2].setBorder(b2);
	}

	void Counting() {
		int k = 0, temp, check = 0;
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++, k++) {
				temp = Integer.parseInt(b1[i][j].getActionCommand()); // getActionCommand를 사용하여 0~n*n-1의 버튼이 순차적으로
																		// 위치되어있는 지 확인함
				if (temp == k) {// 같다면 check값을 ++
					check++;
				}
			}
		}
		if (check == (size * size)) { // 모두 순차적으로 위치되어있다면 end()함수 호출
			end();
		}
	}

	void end() {
		JOptionPane.showMessageDialog(f, "퍼즐을 완성하셨습니다.\nClear Time : " + t.time);
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int a = 0, b = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (ae.getActionCommand() == b1[i][j].getActionCommand()) { // 두번째로 클릭한 버튼의 값을 찾아서 a,b에 해당 i,j번지를 대입
					a = i;
					b = j;
				}
			}
		}

		JButton tt = new JButton(); // 임시버튼을 만든다.
		LineBorder b2 = new LineBorder(Color.red, 5);
		b1[c1][c2].setBorder(tt.getBorder()); // 임시버튼의 테두리를 선택된 버튼에 적용

		if ((a + 1 == c1 && b == c2) || (a - 1 == c1 && b == c2) || (a == c1 && b + 1 == c2)
				|| (a == c1 && b - 1 == c2)) { // 상하좌우에 해당하는 위치만 각 if, else if문으로 검출

			tt.setActionCommand(b1[a][b].getActionCommand()); // 임시버튼에 두번째 클릭한 버튼의 ActionCommand를 대입
			tt.setIcon(b1[a][b].getIcon()); // tt의 Icon을 두번째 클릭 버튼의 Icon으로 설정

			// 첫클릭과 둘째클릭을 바꾸는 과정 | 임시버튼에 두번째 클릭 대입 -> 두번째 클릭에 첫번째 클릭 대입 -> 첫번째 클릭에 두번째 클릭 대입
			b1[a][b].setActionCommand(b1[c1][c2].getActionCommand());
			b1[a][b].setIcon(b1[c1][c2].getIcon());
			b1[a][b].setBorder(b2);
			b1[c1][c2].setActionCommand(tt.getActionCommand());
			b1[c1][c2].setIcon(tt.getIcon());
			c1 = a;
			c2 = b;
		}

		Counting();

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		f.setVisible(false);
		new Client("login");

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}

class Time extends Thread { // Thread클래스를 상속
	int time = 0;
	int flag = 0;
	String temp;
	JFrame Jtemp;
	JLabel tl = new JLabel("Time :" + time);// 라벨을 만들어서 타이머시간을 기재할 수 있도록함

	Time(String temp) {
		this.temp = temp;
		Jtemp = new JFrame();
		ImageIcon image = new ImageIcon(temp);
		JLabel label = new JLabel("퍼즐 원본");
		Jtemp.setSize(image.getIconWidth() + 15, image.getIconHeight() + 100);
		JButton jj = new JButton(image);
		label.setFont(new Font("Serif", Font.BOLD, 45));

		Jtemp.add(label, "North");
		Jtemp.add(jj);

		Jtemp.setVisible(true);

	}

	public void run() {
		tl.setFont(new Font("Serif", Font.BOLD, 30));
		tl.setForeground(Color.MAGENTA);
		while (true) {
			if (time >= 3 && flag == 0) {
				Jtemp.setVisible(false);
				flag = 1;
				time = 0;
				tl.setText("Time : " + time);
			}
			try {
				sleep(1000); // 1초마다 time값을 증가
				time++;
				if (flag == 0) {
					continue;
				}
				tl.setText("Time : " + time);// text값을 갱신시켜 그래픽상 숫자값이 변경되어 노출되게함
			} catch (InterruptedException e) {
			}
		}
	}

}
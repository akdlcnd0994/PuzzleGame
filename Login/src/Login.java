import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int pass = 0;
		System.out.println("LogIn을 위해 ID와 PW를 입력하세요.");
		System.out.println("ID 입력 : ");
		String id =scan.nextLine();
		System.out.println("PW 입력 : ");
		String pw = scan.nextLine();
		File file = new File("C:\\Users\\h\\Desktop\\MemberDB.txt");//입출력 파일 위치
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			try {
				while((line=bufReader.readLine()) != null) {
					int passId = line.indexOf(id);
					int passPw = line.indexOf(pw);
					if(passId != -1 && passPw != -1) {
						System.out.println("로그인 성공");
						pass = 1;	}			
					}
				if(pass==0) { System.out.println("로그인실패"); }
				bufReader.close();
			} catch (IOException e) { e.printStackTrace();	}	
		} catch (FileNotFoundException e) {	e.printStackTrace();}
	}

}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MemberJoin {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		File file = new File("C:\\Users\\h\\Desktop\\MemberDB.txt");//입출력 파일 위치
		try {
			FileWriter filewriter = new FileWriter(file, true);
			if(file.isFile() && file.canWrite()) {
				System.out.println("회원가입을 위해 ID와 PW를 입력하세요.");
				System.out.println("ID 입력: ");
				filewriter.append(scan.nextLine());
				filewriter.append("\t");
				System.out.println("PW 입력: ");
				filewriter.append(scan.nextLine());
				filewriter.append("\r");
				System.out.println("회원가입이 완료되었습니다.");
			}
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		
		System.out.println("* 대체법 암호화 프로그램 *\n");

		while (true) {
			System.out.println("  1: 단일치환 암호화");
			System.out.println("  2: 쌍자치환 암호화");
			System.out.println("  기타: 프로그램 종료");
			System.out.print("  선택> ");
			try {
				menu = sc.nextInt(); 
			} catch (InputMismatchException e) {
				menu = 0;
			}
			switch (menu) {
			case 1:  //단일치환
				MonoalphabeticSubstitution ms = new MonoalphabeticSubstitution();
				ms.create_board();
				ms.encrypt();
				ms.decrypt();	
				break;
			case 2:  //쌍자치환
				PolygramSubstitution ps = new PolygramSubstitution();	
				ps.create_board();
				ps.encrypt();
				ps.decrypt();	
				break;
			default:  //종료
				System.out.println("\n프로그램을 종료합니다.");
				System.exit(0);
			}
			System.out.println("\n\n");
		}
	}
}

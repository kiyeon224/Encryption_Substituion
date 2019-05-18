import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		
		System.out.println("* ��ü�� ��ȣȭ ���α׷� *\n");

		while (true) {
			System.out.println("  1: ����ġȯ ��ȣȭ");
			System.out.println("  2: ����ġȯ ��ȣȭ");
			System.out.println("  ��Ÿ: ���α׷� ����");
			System.out.print("  ����> ");
			try {
				menu = sc.nextInt(); 
			} catch (InputMismatchException e) {
				menu = 0;
			}
			switch (menu) {
			case 1:  //����ġȯ
				MonoalphabeticSubstitution ms = new MonoalphabeticSubstitution();
				ms.create_board();
				ms.encrypt();
				ms.decrypt();	
				break;
			case 2:  //����ġȯ
				PolygramSubstitution ps = new PolygramSubstitution();	
//				ps.create_board();
//				ps.encrypt();
//				ps.decrypt();	
				break;
			default:  //����
				System.out.println("\n���α׷��� �����մϴ�.");
				System.exit(0);
			}
			System.out.println("\n\n");
		}
	}
}
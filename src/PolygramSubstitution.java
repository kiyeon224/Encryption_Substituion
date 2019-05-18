import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolygramSubstitution {

	public String key;  //�Էµ� ��ȸŰ�� ����� ����
	public String plain;  //�Էµ� ���� ����� ����
	List<Character> encryption = new ArrayList<Character>(); //��ȣȭ�� ���ڿ��� ����� ����
	
	public static char board[][] = new char[5][5];  //��ȣ�ǿ� �ش��ϴ� 2���� �迭
	 
	/* �⺻������ */	
	public PolygramSubstitution() {
		input_data();  //������ �Է� �޼ҵ� ����
	}

	/* ��ȣŰ, �� �Է� �޼ҵ�*/
	public void input_data() {
		Scanner sc = new Scanner(System.in); 	//�Է��� ���� Scanner ����
		System.out.print("\n         ��ȣŰ �Է� : ");
		key = sc.nextLine().toUpperCase();  //��ȣŰ �Է� �� �빮�ڷ� ��ȯ
		System.out.print(" ��ȣȭ�� ���� �Է� : ");
		plain =  sc.nextLine().toUpperCase();  //�� �Է� �� �빮�ڷ� ��ȯ
		System.out.println();
	}
	
	/* ��ȣ�� ���� �޼ҵ� */
	public void create_board() {
		key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //key ������ ���ĺ� A���� Z���� �߰�
		String key_for_board = "";  //�ߺ��� ������ ������ ��ȣŰ�� ��� ���� 
		
		//���� ����
		key = key.replaceAll(" ", "");
		
		//�ߺ�ó�� 
		for (int i = 0; i < key.length(); i++) {  //����ڰ� �Է��� ���ڿ�(key)�� ���̸�ŭ �ݺ�
			if (!key_for_board.contains(String.valueOf(key.charAt(i)))) {  //str.contains(string): str�� string�� ���ԵǾ��ִ��� üũ
				key_for_board += String.valueOf(key.charAt(i));  //���ԵǾ����� �ʴٸ� key_for_board�� �ش� ���� �߰�
			}
		}
		
		//��ȣ�� ���� 
		for (int i=0, cnt=0; i<5; i++) { 
			for (int j=0; j<5; j++) {
				board[i][j] = key_for_board.charAt(cnt++);
			}
		}
		
		//������ ��ȣ�� ���
		System.out.print("������ ��ȣ�� : ");
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
			System.out.print("            ");
		}
		System.out.println();
	}

	/* ��ȣȭ �޼ҵ� */
	public void encrypt() {
		List<Character> plain_for_encrypt = new ArrayList<Character>();  //�Է¹��� ���� �ѱ��ھ� ������ �����迭
		
		//�Է��� �� ���� ����
		plain = plain.replaceAll(" ", "");

		//z�� Q�� �ٲ㼭 ó��
		plain = plain.replaceAll("Z", "Q");
		
		//���ӵǴ� ���ڰ� �ߺ��� X �߰�
		for (int i=0; i<plain.length(); i+=2) {
			try {
				if (String.valueOf(plain.charAt(i)).equals((String.valueOf(plain.charAt(i+1))))) {  //���ӵǴ� ���ڰ� ������
					plain_for_encrypt.add(plain.charAt(i));
					plain_for_encrypt.add('X');
					i--;
				} else {
					plain_for_encrypt.add(plain.charAt(i));
					plain_for_encrypt.add(plain.charAt(i+1));
				}
			} catch(IndexOutOfBoundsException e) { //plain�� ���� ������ Ȧ���� �� ���� �߻�
				plain_for_encrypt.add(plain.charAt(i));
				plain_for_encrypt.add('X');
			}
		}
		
		//X�� �߰��� �� 2���ھ� ���
		System.out.print("��ȣȭ �� : ");
		for (int i=0; i<plain_for_encrypt.size(); i+=2) {
			System.out.print(plain_for_encrypt.get(i) + "" + plain_for_encrypt.get(i+1) + " ");
		}
		System.out.println();
		
		//��ȣȭ 
		System.out.print("��ȣȭ �� : ");
		int x1 = 0, y1 = 0;  //ù��° ������ ��� ��
		int x2 = 0, y2 = 0;  //�ι�° ������ ��� ��
		for (int i=0; i<plain_for_encrypt.size(); i+=2) {
			
			//ù��°, �ι�° ���� ������ ��� �� �˻�
			for (int j=0; j<5; j++) {
				for (int k=0; k<5; k++) {
					if (board[j][k] == plain_for_encrypt.get(i)) {  //ù��° ���� �˻�
						x1 = j;
						y1 = k;
					}
					if (board[j][k] == plain_for_encrypt.get(i+1)) {  //�ι�° ���� �˻�
						x2 = j;
						y2 = k;
					}
				}
			}

			if (y1 == y2) {  //���� �࿡ ���� ��
				encryption.add(board[isLast(x1)][y1]);
				encryption.add(board[isLast(x2)][y2]);
			} else if (x1 == x2) {  //���� ���� ���� ��	
				encryption.add(board[x1][isLast(y1)]);
				encryption.add(board[x2][isLast(y2)]);
			} else {  //�ٸ� ��, �ٸ� ���� ���� ��
				encryption.add(board[x2][y1]);
				encryption.add(board[x1][y2]);
			}			
			
			//��ȣȭ�� ���ڿ� ���
			System.out.print((encryption.get(i)) + (encryption.get(i+1) + " "));
		}			
		System.out.println();
	}
	
	/* ��ȣȭ �޼ҵ� */
	public void decrypt() {
		List<Character> descryption = new ArrayList<Character>();

		//��ȣȭ 
		System.out.print("��ȣȭ �� : ");
		int x1 = 0, y1 = 0;  //ù��° ������ ��� ��
		int x2 = 0, y2 = 0;  //�ι�° ������ ��� ��
		for (int i=0; i<encryption.size(); i+=2) {
			
			//ù��°, �ι�° ���� ������ ��� �� �˻�
			for (int j=0; j<5; j++) {
				for (int k=0; k<5; k++) {
					if (board[j][k] == encryption.get(i)) {  //ù��° ���� �˻�
						x1 = j;
						y1 = k;
					}
					if (board[j][k] == encryption.get(i+1)) {  //�ι�° ���� �˻�
						x2 = j;
						y2 = k;
					}
				}
			}

			if (y1 == y2) {  //���� �࿡ ���� ��
				descryption.add(board[isFirst(x1)][y1]);
				descryption.add(board[isFirst(x2)][y2]);
			} else if (x1 == x2) {  //���� ���� ���� ��	
				descryption.add(board[x1][isFirst(y1)]);
				descryption.add(board[x2][isFirst(y2)]);
			} else {  //�ٸ� ��, �ٸ� ���� ���� ��
				descryption.add(board[x2][y1]);
				descryption.add(board[x1][y2]);
			}			
			
			//��ȣȭ�� ���ڿ� ���
			System.out.print((descryption.get(i)) + (descryption.get(i+1) + " "));
		}			
		System.out.println();
	}

	/* ��ȣȭ - ���̳� ���� ������ ���� �� */
	public int isLast(int xy) {
		if (xy >= 4) return 0;
		else return xy+1;
	}

	/* ��ȣȭ - ���̳� ���� ù��° ���� �� */
	public int isFirst(int xy) {
		if (xy <= 0) return 4;
		else return xy-1;
	}
}
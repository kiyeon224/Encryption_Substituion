import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonoalphabeticSubstitution {
	public String key;  //�Էµ� ��ȸŰ�� ����� ����
	public String plain;  //�Էµ� ���� ����� ����
	List<Character> encryption = new ArrayList<Character>(); //��ȣȭ�� ���ڿ��� ����� ����

	char[][] board = new char[2][26];  //��ȣ�ǿ� �ش��ϴ� 2���� �迭
	
	/* �⺻������ */
	public MonoalphabeticSubstitution() {
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
		String key_for_board = "";  //�ߺ��� ������ ������ ��ȣŰ�� ��� ���� 
		char last;
		
		//��ȣ�� 0��° �࿡ ���ĺ� A���� Z���� ���ʴ�� �Է� 
		for (int i=0; i<26; i++) {
			board[0][i] = (char)('A' + i);
		}

		//��ȣŰ ���� ����
		key = key.replaceAll(" ", "");
		
		//��ȣŰ�� �ߺ��� ������ ���ڿ��� key_for_board�� ����
		for(int i=0; i < key.length(); i++) {  //����ڰ� �Է��� ���ڿ�(key)�� ���̸�ŭ �ݺ�
			if(!key_for_board.contains(String.valueOf(key.charAt(i)))) {  //str.contains(string): str�� string�� ���ԵǾ��ִ��� üũ
				key_for_board += String.valueOf(key.charAt(i));  //���ԵǾ����� �ʴٸ� key_for_board�� �ش� ���� �߰�
			}
		}
		
		//key_for_board�� ����� ������ ���ڸ� ����
		last = key_for_board.charAt(key_for_board.length()-1);
		
		//key_for_board�� ������ ���ĺ� ����
		for (char i=(char)(last+1); i<='Z'; i++) {
			if(!key_for_board.contains(String.valueOf(i))) {  //str.contains(string): str�� string�� ���ԵǾ��ִ��� üũ
				key_for_board += i;  //���ԵǾ����� �ʴٸ� key_for_board�� �ش� ���� �߰�
			}
		}
		for (char i='A'; i<last; i++) {
			if(!key_for_board.contains(String.valueOf(i))) {  //str.contains(string): str�� string�� ���ԵǾ��ִ��� üũ
				key_for_board += i;  //���ԵǾ����� �ʴٸ� key_for_board�� �ش� ���� �߰�
			}
		}
		
		//key_for_board�� ���� ��ȣ��(board[1])�� ����
		for (int i=0; i<26; i++) {
			board[1][i] = key_for_board.charAt(i);
		}
		
		//���
		System.out.print("������ ��ȣ�� : ");
		for (int j=0; j<26; j++) {
			System.out.print((char)(board[0][j] + 'a' - 'A') + " ");  //�ҹ��ڷ� ���
		}
		System.out.println();
		System.out.print("            ");
		for (int j=0; j<26; j++) {
			System.out.print(board[1][j]+ " ");
		}
		System.out.println();
	}

	/* ��ȣȭ �޼ҵ� */
	public void encrypt() {
		//�Է��� �� ���� ����
		plain = plain.replaceAll(" ", "");
		
		//��ȣȭ 
		for (int i=0; i<plain.length(); i++) {
			for (int j=0; j<26; j++) {
				if (plain.charAt(i) == board[0][j]) {  //��ȣ�� 0��° �࿡�� �ش� ���ĺ��� �˻�
					encryption.add(board[1][j]);  //��ġ�ϴ� ���ĺ��� �ε����� 1��° �࿡�� ��ȣȭ�� �� �� 
					break;
				}
			}
		}
		
		//���
		System.out.println("��ȣȭ �� : " + plain);
		System.out.print("��ȣȭ �� : ");
		for (int i=0; i<encryption.size(); i++) {
			System.out.print(encryption.get(i));
		}
		
		System.out.println();
	}

	/* ��ȣȭ �޼ҵ� */
	public void decrypt() {
		//��ȣȭ 
		System.out.print("��ȣȭ �� : ");
		for (int i=0; i<plain.length(); i++) {
			for (int j=0; j<26; j++) {
				if (encryption.get(i) == board[1][j]) {
					System.out.print(board[0][j]);
				}
			}
		}
		System.out.println();
	}	
	
}
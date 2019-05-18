import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonoalphabeticSubstitution {
	public String key;  //입력된 암회키가 저장될 변수
	public String plain;  //입력된 평문이 저장될 변수
	List<Character> encryption = new ArrayList<Character>(); //암호화된 문자열이 저장될 변수

	char[][] board = new char[2][26];  //암호판에 해당하는 2차원 배열
	
	/* 기본생성자 */
	public MonoalphabeticSubstitution() {
		input_data();  //데이터 입력 메소드 실행
	}

	/* 암호키, 평문 입력 메소드*/
	public void input_data() {
		Scanner sc = new Scanner(System.in); 	//입력을 위한 Scanner 정의

		System.out.print("\n         암호키 입력 : ");
		key = sc.nextLine().toUpperCase();  //암호키 입력 및 대문자로 변환
		System.out.print(" 암호화할 문장 입력 : ");
		plain =  sc.nextLine().toUpperCase();  //평문 입력 및 대문자로 변환
		System.out.println();		
	}
	
	/* 암호판 생성 메소드 */
	public void create_board() {
		String key_for_board = "";  //중복및 공백을 제거한 암호키를 담는 변수 
		char last;
		
		//암호판 0번째 행에 알파벳 A부터 Z까지 차례대로 입력 
		for (int i=0; i<26; i++) {
			board[0][i] = (char)('A' + i);
		}

		//암호키 공백 제거
		key = key.replaceAll(" ", "");
		
		//암호키의 중복을 제거한 문자열을 key_for_board에 저장
		for(int i=0; i < key.length(); i++) {  //사용자가 입력한 문자열(key)의 길이만큼 반복
			if(!key_for_board.contains(String.valueOf(key.charAt(i)))) {  //str.contains(string): str에 string이 포함되어있는지 체크
				key_for_board += String.valueOf(key.charAt(i));  //포함되어있지 않다면 key_for_board에 해당 문자 추가
			}
		}
		
		//key_for_board에 저장된 마지막 문자를 저장
		last = key_for_board.charAt(key_for_board.length()-1);
		
		//key_for_board에 나머지 알파벳 삽입
		for (char i=(char)(last+1); i<='Z'; i++) {
			if(!key_for_board.contains(String.valueOf(i))) {  //str.contains(string): str에 string이 포함되어있는지 체크
				key_for_board += i;  //포함되어있지 않다면 key_for_board에 해당 문자 추가
			}
		}
		for (char i='A'; i<last; i++) {
			if(!key_for_board.contains(String.valueOf(i))) {  //str.contains(string): str에 string이 포함되어있는지 체크
				key_for_board += i;  //포함되어있지 않다면 key_for_board에 해당 문자 추가
			}
		}
		
		//key_for_board의 값을 암호판(board[1])에 저장
		for (int i=0; i<26; i++) {
			board[1][i] = key_for_board.charAt(i);
		}
		
		//출력
		System.out.print("생성된 암호판 : ");
		for (int j=0; j<26; j++) {
			System.out.print((char)(board[0][j] + 'a' - 'A') + " ");  //소문자로 출력
		}
		System.out.println();
		System.out.print("            ");
		for (int j=0; j<26; j++) {
			System.out.print(board[1][j]+ " ");
		}
		System.out.println();
	}

	/* 암호화 메소드 */
	public void encrypt() {
		//입력한 평문 공백 제거
		plain = plain.replaceAll(" ", "");
		
		//암호화 
		for (int i=0; i<plain.length(); i++) {
			for (int j=0; j<26; j++) {
				if (plain.charAt(i) == board[0][j]) {  //암호판 0번째 행에서 해당 알파벳을 검색
					encryption.add(board[1][j]);  //일치하는 알파벳의 인덱스로 1번째 행에서 암호화된 값 대 
					break;
				}
			}
		}
		
		//출력
		System.out.println("암호화 전 : " + plain);
		System.out.print("암호화 후 : ");
		for (int i=0; i<encryption.size(); i++) {
			System.out.print(encryption.get(i));
		}
		
		System.out.println();
	}

	/* 복호화 메소드 */
	public void decrypt() {
		//복호화 
		System.out.print("복호화 후 : ");
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
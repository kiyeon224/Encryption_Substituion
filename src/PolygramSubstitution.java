import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolygramSubstitution {

	public String key;  //입력된 암회키가 저장될 변수
	public String plain;  //입력된 평문이 저장될 변수
	List<Character> encryption = new ArrayList<Character>(); //암호화된 문자열이 저장될 변수
	
	public static char board[][] = new char[5][5];  //암호판에 해당하는 2차원 배열
	 
	/* 기본생성자 */	
	public PolygramSubstitution() {
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
		key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //key 변수에 알파벳 A부터 Z까지 추가
		String key_for_board = "";  //중복및 공백을 제거한 암호키를 담는 변수 
		
		//공백 제거
		key = key.replaceAll(" ", "");
		
		//중복처리 
		for (int i = 0; i < key.length(); i++) {  //사용자가 입력한 문자열(key)의 길이만큼 반복
			if (!key_for_board.contains(String.valueOf(key.charAt(i)))) {  //str.contains(string): str에 string이 포함되어있는지 체크
				key_for_board += String.valueOf(key.charAt(i));  //포함되어있지 않다면 key_for_board에 해당 문자 추가
			}
		}
		
		//암호판 생성 
		for (int i=0, cnt=0; i<5; i++) { 
			for (int j=0; j<5; j++) {
				board[i][j] = key_for_board.charAt(cnt++);
			}
		}
		
		//생성된 암호판 출력
		System.out.print("생성된 암호판 : ");
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
			System.out.print("            ");
		}
		System.out.println();
	}

	/* 암호화 메소드 */
	public void encrypt() {
		List<Character> plain_for_encrypt = new ArrayList<Character>();  //입력받은 평문을 한글자씩 저장할 동적배열
		
		//입력한 평문 공백 제거
		plain = plain.replaceAll(" ", "");

		//z를 Q로 바꿔서 처리
		plain = plain.replaceAll("Z", "Q");
		
		//연속되는 글자가 중복시 X 추가
		for (int i=0; i<plain.length(); i+=2) {
			try {
				if (String.valueOf(plain.charAt(i)).equals((String.valueOf(plain.charAt(i+1))))) {  //연속되는 글자가 같으면
					plain_for_encrypt.add(plain.charAt(i));
					plain_for_encrypt.add('X');
					i--;
				} else {
					plain_for_encrypt.add(plain.charAt(i));
					plain_for_encrypt.add(plain.charAt(i+1));
				}
			} catch(IndexOutOfBoundsException e) { //plain의 문자 개수가 홀수일 때 예외 발생
				plain_for_encrypt.add(plain.charAt(i));
				plain_for_encrypt.add('X');
			}
		}
		
		//X가 추가된 평문 2글자씩 출력
		System.out.print("암호화 전 : ");
		for (int i=0; i<plain_for_encrypt.size(); i+=2) {
			System.out.print(plain_for_encrypt.get(i) + "" + plain_for_encrypt.get(i+1) + " ");
		}
		System.out.println();
		
		//암호화 
		System.out.print("암호화 후 : ");
		int x1 = 0, y1 = 0;  //첫번째 문자의 행과 열
		int x2 = 0, y2 = 0;  //두번째 문자의 행과 열
		for (int i=0; i<plain_for_encrypt.size(); i+=2) {
			
			//첫번째, 두번째 문자 각각의 행과 열 검색
			for (int j=0; j<5; j++) {
				for (int k=0; k<5; k++) {
					if (board[j][k] == plain_for_encrypt.get(i)) {  //첫번째 글자 검색
						x1 = j;
						y1 = k;
					}
					if (board[j][k] == plain_for_encrypt.get(i+1)) {  //두번째 글자 검색
						x2 = j;
						y2 = k;
					}
				}
			}

			if (y1 == y2) {  //같은 행에 있을 때
				encryption.add(board[isLast(x1)][y1]);
				encryption.add(board[isLast(x2)][y2]);
			} else if (x1 == x2) {  //같은 열에 있을 때	
				encryption.add(board[x1][isLast(y1)]);
				encryption.add(board[x2][isLast(y2)]);
			} else {  //다른 행, 다른 열에 있을 때
				encryption.add(board[x2][y1]);
				encryption.add(board[x1][y2]);
			}			
			
			//암호화된 문자열 출력
			System.out.print((encryption.get(i)) + (encryption.get(i+1) + " "));
		}			
		System.out.println();
	}
	
	/* 복호화 메소드 */
	public void decrypt() {
		List<Character> descryption = new ArrayList<Character>();

		//암호화 
		System.out.print("복호화 후 : ");
		int x1 = 0, y1 = 0;  //첫번째 문자의 행과 열
		int x2 = 0, y2 = 0;  //두번째 문자의 행과 열
		for (int i=0; i<encryption.size(); i+=2) {
			
			//첫번째, 두번째 문자 각각의 행과 열 검색
			for (int j=0; j<5; j++) {
				for (int k=0; k<5; k++) {
					if (board[j][k] == encryption.get(i)) {  //첫번째 글자 검색
						x1 = j;
						y1 = k;
					}
					if (board[j][k] == encryption.get(i+1)) {  //두번째 글자 검색
						x2 = j;
						y2 = k;
					}
				}
			}

			if (y1 == y2) {  //같은 행에 있을 때
				descryption.add(board[isFirst(x1)][y1]);
				descryption.add(board[isFirst(x2)][y2]);
			} else if (x1 == x2) {  //같은 열에 있을 때	
				descryption.add(board[x1][isFirst(y1)]);
				descryption.add(board[x2][isFirst(y2)]);
			} else {  //다른 행, 다른 열에 있을 때
				descryption.add(board[x2][y1]);
				descryption.add(board[x1][y2]);
			}			
			
			//복호화된 문자열 출력
			System.out.print((descryption.get(i)) + (descryption.get(i+1) + " "));
		}			
		System.out.println();
	}

	/* 암호화 - 행이나 열의 마지막 값일 때 */
	public int isLast(int xy) {
		if (xy >= 4) return 0;
		else return xy+1;
	}

	/* 복호화 - 행이나 열의 첫번째 값일 때 */
	public int isFirst(int xy) {
		if (xy <= 0) return 4;
		else return xy-1;
	}
}
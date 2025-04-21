package Case2;

import java.util.*;

public class GuestBookMain {
	
	private static List<Guestbook> guestbooks = new ArrayList<>();
	
		
	public static void main(String[] args) {
		// 建立留言
		Guestbook g1 = new Guestbook("Hello");
		Guestbook g2 = new Guestbook("Gonna rain.");
		
		// 存入留言
		guestbooks.add(g1); 
		guestbooks.add(g2);
		
		System.out.println(guestbooks);
	}

}

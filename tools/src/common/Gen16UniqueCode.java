package common;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Gen16UniqueCode {
	public String genCode(){
		String p = new Date().getTime() + "";
		String l = new Random().nextInt(1000) + "";
		if (l.length() == 1){
			l = "00" + l;
		} else if (l.length() == 2){
			l = "0" + l;
		}
		String s = p + l;
		String code = genCodeWithUniqueString(s);
		return code;
	}

	private String genCodeWithUniqueString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i ++){
			Character c = s.charAt(i);
			int a = Integer.parseInt("" + c);
			char x = 'A';
			if (i > 11){
				x = 'P';
			} else if (i > 6){
				x = 'K';
			}
			sb.append((char)(x + a));
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 2000000; i++){
			boolean idAdd = false;
			idAdd = set.add(UUID.randomUUID().toString().replace("-", "").substring(16).toUpperCase());
			if (!idAdd){
				System.out.println("重复");
			}
			if ((i+1) % 10000 == 0 ){
				System.out.println((i + 1) /10000 + "w");
			}
		}
	}
}

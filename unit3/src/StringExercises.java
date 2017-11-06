public class StringExercises {
	public static void main(String[] args) {
		String str1 = "Bugs Bunny";
		String str2 = "mickey mouse";
		String str3 = "dONald dUCK";
		String str4 = "minnie mouse";
		String str5 = "babs^bunny";
		String str6 = "Bugs";
		String str7 = "play";
		String str8 = "playing";

		System.out.println(str2.compareTo(str3));
		System.out.println(str8.compareTo(str7));
		System.out.println(str5.compareTo(str1));
		System.out.println(str1.compareTo(str2));
		System.out.println(str2.compareTo(str4));
		System.out.println(str6.compareTo(str1));
		System.out.println(str5.charAt(5));
		System.out.println(str3.charAt(0));
		System.out.println(str4.charAt(str4.length() - 3));
//		System.out.println(str1.charAt(10));
		System.out.println(str5.charAt(str6.length()));
//		System.out.println(str3.charAt(str5.length() + 1));
	}
}

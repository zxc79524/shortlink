package idv.blake.shortlink.util;

public class ConvertUtil {
	private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static int scale = 62;
//    private static int minLength = 5;

	/**
	 * 
	 * 10進制轉62進制
	 * 
	 * @param num
	 * @return
	 */
	public static String hex10To62(long num) {
		StringBuilder sb = new StringBuilder();
		int remainder;
		while (num > scale - 1) {
			remainder = Long.valueOf(num % scale).intValue();
			sb.append(chars.charAt(remainder));
			num = num / scale;
		}
		sb.append(chars.charAt(Long.valueOf(num).intValue()));
		String value = sb.reverse().toString();
		return value;
	}

}

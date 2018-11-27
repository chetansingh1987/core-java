
public class StringBehavior
{
	public static void main(String[] args) {
		String s = new String("abc");
		String s2="abc";
		System.out.println(s==s2);
		s=s.intern();
		System.out.println(s==s2);
	}
}

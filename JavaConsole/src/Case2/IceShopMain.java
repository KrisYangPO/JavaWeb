package Case2;

public class IceShopMain {

	public static void main(String[] args) {
		String[] dress = new String[] {"jss", "skksk","skskks","ssksksksks", "skksk"};
		IceShop is = new IceShop("Douhua", dress);
		
		System.out.println("totalPrice: " + is.getTotalPrice());

	}

}

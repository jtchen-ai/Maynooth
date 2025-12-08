/*
 * Test different brands of plugs.
 */
public final class TestPlugs {

	public static void main(String args[]) {
		System.out.println("--- Testing German to UK Adapter ---");
		testGermanToUKAdapter();

		System.out.println("\n--- Testing UK to German Adapter ---");
		testUKToGermanAdapter();
	}


	/*
	 *	Test Method for testing GermanToUK Adapter.
	 */
	public static void testGermanToUKAdapter() {
		/* create a germany plug connector (brand: zest) */
		GermanPlugConnector plug = new ZestPlug();
		/* create a UK socket */
		UKElectricalSocket socket = new UKElectricalSocket();
		/* create an adapter */
		UKPlugConnector ukAdapter = new GermanToUKPlugConnectorAdapter(plug);
		/* use this adapter in a UK socket */
		socket.plugIn(ukAdapter);
	}

	/*
	 *	Test Method for testing UKToGerman Adapter.
	 */
	public static void testUKToGermanAdapter() {
		/* 1. create a UK plug connector (brand: Furutech) */
		UKPlugConnector plug = new Furutech();
		/* 2. create a German socket */
		GermanElectricalSocket socket = new GermanElectricalSocket();
		/* 3. create an adapter */
		GermanPlugConnector germanAdapter = new UKToGermanPlugConnectorAdapter(plug);
		/* 4. use this adapter in a German socket */
		socket.plugIn(germanAdapter);
	}
}
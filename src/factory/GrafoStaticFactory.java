package factory;

public class GrafoStaticFactory {

	private static final GrafosFactory factory = new GrafosFactoryImpl();
	public static GrafosFactory criaFactory(){
		return factory;
	}
	
}

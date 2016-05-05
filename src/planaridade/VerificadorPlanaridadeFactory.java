package planaridade;

import planaridade.impl.VerificadorPlanaridadeImpl;

public class VerificadorPlanaridadeFactory {

	public static VerificadorPlanaridade create() {
		return new VerificadorPlanaridadeImpl();
	}

}

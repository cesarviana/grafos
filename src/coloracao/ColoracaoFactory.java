package coloracao;

import coloracao.impl.ColoracaoImpl;

public class ColoracaoFactory {

	public static Coloracao cria() {
		return new ColoracaoImpl();
	}

}

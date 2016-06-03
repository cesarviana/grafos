package coloracao;

import model.Grafo;

public interface Coloracao {

	public Grafo colorir(Grafo grafo);

	public Grafo executaPasso(Grafo grafo);

	public void addListener(ColoracaoListener listener);

}

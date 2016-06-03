package model.impl;

import model.Aresta;
import model.Vertice;

public class ArestaPadrao implements Aresta {

	private double peso;
	private Vertice verticeA = Vertice.NULO;
	private Vertice verticeB = Vertice.NULO;

	private ArestaPadrao(double peso) {
		this.peso = peso;
	}

	public ArestaPadrao() {
		this(1);
	}

	@Override
	public Vertice getVerticeLigadoA(Vertice vertice) {
		return vertice == this.verticeA ? this.verticeB : this.verticeA;
	}

	@Override
	public void addVertice(Vertice vertice) {
		if (verticeA != Vertice.NULO && verticeB != Vertice.NULO)
			throw new IllegalStateException(
					"A aresta já está ligada a dois vértices.");
		if (verticeA == Vertice.NULO)
			verticeA = vertice;
		else
			verticeB = vertice;
	}

	@Override
	public double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return verticeA + " - " + verticeB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((verticeA == null) ? 0 : verticeA.hashCode());
		result = prime * result
				+ ((verticeB == null) ? 0 : verticeB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArestaPadrao other = (ArestaPadrao) obj;
		if (Double.doubleToLongBits(peso) != Double
				.doubleToLongBits(other.peso))
			return false;
		if (verticeA == null && verticeB == null) {
			if (other.verticeA != null || other.verticeB != null)
				return false;
		}
		return (aabb(other) || abba(other));
	}

	private boolean abba(ArestaPadrao other) {
		if (verticeA == null && other.verticeB != null)
			return false;
		if (verticeB == null && other.verticeA != null)
			return false;
		return verticeA.equals(other.verticeB)
				&& verticeB.equals(other.verticeA);
	}

	private boolean aabb(ArestaPadrao other) {
		if (verticeA == null && other.verticeA != null)
			return false;
		if (verticeB == null && other.verticeB != null)
			return false;
		return !verticeA.equals(other.verticeA)
				&& verticeB.equals(other.verticeB);
	}

}

package model.factory;

import model.factory.impl.ModelFactoryPadrao;

public class ModelSimpleFactory {

    private static ModelFactory instance;
    
    public static ModelFactory factory() {
	if(instance == null)
	    instance = new ModelFactoryPadrao();
	return instance;
    }

}

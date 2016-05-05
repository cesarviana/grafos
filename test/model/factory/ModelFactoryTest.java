package model.factory;

import org.junit.Test;

public class ModelFactoryTest {

    @Test
    public void test() {
	ModelFactory mf = ModelSimpleFactory.factory();
	mf.criaGrafo();
    }

}

package leitorxml;

import java.io.File;

public interface LeitorXml<E> {
    
    public E ler(File xml);
    
}

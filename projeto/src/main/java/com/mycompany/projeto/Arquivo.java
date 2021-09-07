import java.io.File;

public interface Arquivo{
  <T> void escreverArquivo(T objeto);
  <T> T lerArquivo(File file);
}
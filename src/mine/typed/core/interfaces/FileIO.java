package mine.typed.core.interfaces;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public interface FileIO {

    /**
     * Asset 으로 부터 파일을 읽어 옵니다.
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public InputStream readAsset(String fileName) throws IOException;

    /**
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public InputStream readFile(String fileName) throws IOException;

    public FileOutputStream writeFile(String fileName) throws IOException;

}

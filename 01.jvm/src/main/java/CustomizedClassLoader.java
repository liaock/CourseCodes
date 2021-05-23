import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by liaock on 2021/5/1
 **/
public class CustomizedClassLoader extends ClassLoader{

    /**
     * 需要被加载的类的路径
     */
    private String needToLoadClassPath;


    public CustomizedClassLoader(String needToLoadClassPath){
        this.needToLoadClassPath = needToLoadClassPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = this.getClassBytes();
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] getClassBytes() throws ClassNotFoundException {
        byte [] bytes;
        try {
            File file = new File(needToLoadClassPath);
            bytes = Files.readAllBytes(file.toPath());
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (IOException e) {
            throw new ClassNotFoundException("指定加载文件路径不存在！", e);
        }
        return bytes;
    }
}
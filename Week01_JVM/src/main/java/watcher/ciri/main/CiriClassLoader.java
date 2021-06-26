package watcher.ciri.main;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class CiriClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Object hello = new CiriClassLoader().findClass("Hello").newInstance();
        Class<?> helloClass = hello.getClass();
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        helloMethod.invoke(hello);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassBytes();
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassBytes() {
        byte[] resultBytes = null;

        try (InputStream clzInputStream = ClassLoader.getSystemResourceAsStream("Hello.xlass");
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            if (clzInputStream == null) {
                throw new NullPointerException("InputStream is null!");
            }
            byte[] bytes = new byte[1024];
            int read;
            while ((read = clzInputStream.read(bytes, 0, bytes.length)) != -1) {
                byteArrayOutputStream.write(bytes, 0, read);
            }
            byteArrayOutputStream.flush();
            resultBytes = decodeBytes(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultBytes;
    }

    private byte[] decodeBytes(byte[] bytes) {
        byte[] resultBytes = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            resultBytes[i] = (byte) (255 - bytes[i]);
        }

        return resultBytes;
    }

}

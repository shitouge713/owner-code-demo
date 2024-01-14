package owner.code.demo.netty.myrpc;

import java.io.*;

/**
 * 序列化和反序列
 */
public class SerializeUtils {
    /**
     * 序列化
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[]  serializer(Object obj) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(ba);
        os.writeObject(obj);
        os.flush();
        byte [] bytes = ba.toByteArray();
        ba.close();
        os.close();
        return bytes;
    }
    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object doSerializer(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream os = new ObjectInputStream(bi);
        Object obj = os.readObject();
        os.close();
        bi.close();
        return obj;
    }
}

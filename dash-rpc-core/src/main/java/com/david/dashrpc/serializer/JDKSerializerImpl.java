package com.david.dashrpc.serializer;

import java.io.*;

public class JDKSerializerImpl implements Serializer{
    /**
     * 序列化
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */

    @Override
    public <T> byte[] serialize(T object) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close(); // 不關爆內存哦
        return bos.toByteArray();
    }

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException{
        ByteArrayInputStream bais= new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        try{
            return (T) ois.readObject();

        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }finally{
            ois.close();
        }
    }
}


package com.dys.serial;

import java.nio.ByteBuffer;

public class Test02 {

        public static void main(String[] args) throws Exception {
            int id = 101;
            int age = 21;

            ByteBuffer byteBuffer = ByteBuffer.allocate(8);
            byteBuffer.putInt(id);
            byteBuffer.putInt(age);
            byte[] bytes = byteBuffer.array();

            ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes);
            int idInt = byteBuffer1.getInt();
            int ageInt = byteBuffer1.getInt();
            System.out.println(idInt + ", " + ageInt);
        }

}

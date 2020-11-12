package com.dys.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) throws Exception {
        int id = 101;
        int age = 21;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(int2bytes(id));
        byteArrayOutputStream.write(int2bytes(age));

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idBytes = new byte[4];
        byteArrayInputStream.read(idBytes);
        System.out.println("id: " + bytes2int(idBytes));

        byte[] ageBytes = new byte[4];
        byteArrayInputStream.read(ageBytes);
        System.out.println("id: " + bytes2int(ageBytes));
    }
    public static byte[] int2bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(i >> 3 * 8);
        bytes[1] = (byte)(i >> 2 * 8);
        bytes[2] = (byte)(i >> 1 * 8);
        bytes[3] = (byte)(i >> 0 * 8);

        return bytes;
    }
    public static int bytes2int(byte[] bytes) {
        int result = (bytes[0] << 3 * 8) | (bytes[1] >> 2 * 8) | (bytes[2] << 1 * 8) | (bytes[3] << 0 * 8);
        return result;
    }
}

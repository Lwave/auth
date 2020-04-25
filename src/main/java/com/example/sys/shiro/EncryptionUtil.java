package com.example.sys.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class EncryptionUtil {

    public static String MD5Pwd(String password) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = password;//密码原值
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("ss" + salt);
        ByteSource byteSource = ByteSource.Util.bytes(salt);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        return new SimpleHash(hashAlgorithmName, crdentials, byteSource, hashIterations).toString();

    }


    public static void main(String[] args) {
        String password = MD5Pwd("123456");
        System.out.println(password);

    }
}
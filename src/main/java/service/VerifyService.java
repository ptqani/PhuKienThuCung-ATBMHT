package service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


// Xác minh chữ kí
public class VerifyService {
    public static boolean verifySignature(String data, String signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data.getBytes("UTF-8"));
        return sig.verify(Base64.getDecoder().decode(signature));
    }

    public static void main(String[] args) throws Exception {
        String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmezgLAf7yqp6W70+EWcUdYn5gL/U08S8dhb6puZkV/ddQdQnmjkTFxGyM2LStAZ9HNJXCwPCL7RFHniWmSOQpJdi21hXtk2bOsCJsfndmrCb5vhrYcq1ArO+eJR8Rnb5HItCA7iWk2na7N6RAmDm1is/FhP+VvEYM6y4mmUgb1v5S/+TCgDLQbNdaFpNoM2eikuKsRKG0r3aE9ZEfO5DMJScmgf7U4lmU8gOHkw/tbtnDoJb7FbGPyv7orPsnuzHCuSyBuaooN1K42I4JkOi1FNhuPIMuGWpd6ZT5TcYLNykWW2aADfCSQHQvDEe6HGHu7KSmqcSonrCFOHvjT5BxwIDAQAB";
        String data = "usaGfKNv4oN2nYhsGlcIt6jH0Y6TGrH0hbXQc9xq1jU=";
        String signature = "dzFnHxTF6Vi1gQX56dhgERGOXWYVb0nN3HUkR5zJ89fGXnXLF6LrIawmDqtxcJbUuyRSlfaZjUJPiLqZTHxhOz/0NVY3F1UT4VjFf/gIkZ0szLH8Lxw2yT95dFIkHMJkX8pg7EAtZgUX+xrMnkAWCAx52qGwZiWziBude2/vU1Oq2uId7Fd2gxr+1Hmg4P1hL7joG+3c3SKxpb69DdDweEMhuI27jJAsnktGXGT9msGO+Fi2xlY9isae58ferMItEWhp03DEY/ZDoCIHdeDOSHa7snkWQU0aqJ/mkrDcdLLDGWp6SI/bbleNVVVq+gmMVmG9fPmKHNpiKPpOyDr4Kw==";
        // Giải mã chuỗi Base64 thành mảng byte
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        // Tạo đối tượng X509EncodedKeySpec từ mảng byte
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // Sử dụng KeyFactory để tạo PublicKey từ keySpec
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        boolean isVerified = verifySignature(data, signature, publicKey);
        System.out.println("Signature Verified: " + isVerified);
    }
}

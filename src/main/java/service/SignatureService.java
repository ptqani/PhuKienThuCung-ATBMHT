package service;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class SignatureService {
    public static String signData(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }


}


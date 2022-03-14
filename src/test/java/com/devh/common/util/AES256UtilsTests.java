package com.devh.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AES256UtilsTests {
    @Autowired
    private AES256Utils aes256Utils;

    @Test
    public void test_encrypt_decrypt() throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidParameterSpecException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        String s = "root";
        String enc = aes256Utils.encrypt(s);
        // bK5m/i6cms4hSHSronOsqKhqOUyqxTnjU8txYVbneWcPWVu1hOJV65BIAz6GPIIhNXrS+Q==
        System.out.println(enc);

        String dec = aes256Utils.decrypt(enc);
        System.out.println(dec);

        assertEquals(s, dec);
    }
}

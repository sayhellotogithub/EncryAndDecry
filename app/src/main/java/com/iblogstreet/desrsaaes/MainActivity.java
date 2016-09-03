package com.iblogstreet.desrsaaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.iblogstreet.desrsaaes.utils.Aes;
import com.iblogstreet.desrsaaes.utils.Des;
import com.iblogstreet.desrsaaes.utils.MD5Utils;
import com.iblogstreet.desrsaaes.utils.RSACrypt;
import com.iblogstreet.desrsaaes.utils.Sha1;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class MainActivity
        extends AppCompatActivity
{
    EditText etContent;
    EditText etResult;
    String   result;
    private boolean isDes;
    private boolean isAes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = (EditText) findViewById(R.id.et_content);
        etResult = (EditText) findViewById(R.id.et_result);
    }

    public void btnMd5(View view)

    {
        try {
            result = MD5Utils.Md5Digest(getData());
            etResult.setText(result + "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public void btnSha1(View view) {
        try {
            result = Sha1.SHA1(getData());
            etResult.setText(result + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String desEncrypt;
    String desKey = "123456233";

    /**
     * des加密/解密
     *
     * @param v
     */
    public void des(View v) {
        try {

            if (!isDes) {//加密
                desEncrypt = Des.encrypt(getData(), desKey);
                etResult.setText("DES加密：" + desEncrypt);
            } else {//解密
                String desEecrypt = Des.decrypt(desEncrypt, desKey);
                etResult.setText("DES解密：" + desEecrypt);
            }
            isDes = !isDes;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String aesEncrypt;
    String aesKey = "1234567890";

    public void aes(View v) {
        if (!isAes) {//加密
            aesEncrypt = Aes.encrypt(getData(), aesKey);
            etResult.setText("AES加密：" + aesEncrypt);
        } else {//解密
            String aesDecrypt = Aes.decrypt(aesEncrypt, aesKey);
            etResult.setText("AES解密：" + aesDecrypt);
        }
        isAes = !isAes;
    }

    byte[]  encryptByPrivateKey;
    boolean isRsa;

    public void rsa(View v) {
        if(TextUtils.isEmpty(privateKey)){
            initKeyPair();
        }
        try {
            if (!isRsa) {//加密
                encryptByPrivateKey = RSACrypt.encryptByPrivateKey(getData().getBytes(),
                                                                   privateKey);
                etResult.setText("RSA加密：" + RSACrypt.encode(encryptByPrivateKey));
            } else {//解密
                byte[] decryptByPublicKey = RSACrypt.decryptByPublicKey(encryptByPrivateKey,
                                                                        publicKey);
                etResult.setText("RSA解密：" + new String(decryptByPublicKey));
            }
            isRsa = !isRsa;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genKeyPair(View view) {
        initKeyPair();
        etResult.setText("成功生成密钥对");
    }

    public void getPrivateKey(View view) {
        etResult.setText("RSA私钥:"+privateKey);
    }
    public void getPublicKey(View view) {
        etResult.setText("RSA公钥:"+publicKey);
    }


    public String privateKey;
    public String publicKey;

    private void initKeyPair() {
        try {
            Map<String, Object> keyPair = RSACrypt.genKeyPair();
            privateKey = RSACrypt.getPrivateKey(keyPair);
            publicKey = RSACrypt.getPublicKey(keyPair);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getData() {
        return etContent.getText()
                        .toString()
                        .trim();
    }

}

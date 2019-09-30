package com.leeue.rsa;

import com.leeue.utils.RSAUtils;

import javax.crypto.Cipher;

/**
 * RSA非对称加密案例
 *
 * @author liyue
 * @date 2019-08-13 16:09
 */
public class Rsa {

    public static  String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0hcRbnVZzFMohJM/qsY3WSJATH1QT+nm\n" +
            "qJlzHizLxwQYPdxdXUzZrRBcwZAZPk3JyYw+2RW/wBrtF9xY4KEseALAm+CnbkcWEFYMQxRCzAye\n" +
            "1tZvA1VfG31CXLCwWsx07PugyjaVXq49eEN303H9YCP28noLmK00raRIE3MD3eWj3Zvwe3CX6Ckf\n" +
            "8jbileMTaR6sTiPGxpclMQsIRc8dqXZjPTxB6GeiVeY0b0PUJ3tQBV8PU59/4EQHmpEs8HT9ZP5H\n" +
            "wNz3KMOafqQxAt7hEnZ37nomzLNtdGvZ06KQPcLdrEWiGJ6I0MPrmbRBn/VNQhcKLobcsuYyfMN4\n" +
            "Vy0+kQIDAQAB";//公钥

    //可以看出这里私钥是有特殊字符的，所以有时候在RPC远程调用的时候需要进行url 编码
    public static  String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDSFxFudVnMUyiEkz+qxjdZIkBM\n" +
            "fVBP6eaomXMeLMvHBBg93F1dTNmtEFzBkBk+TcnJjD7ZFb/AGu0X3FjgoSx4AsCb4KduRxYQVgxD\n" +
            "FELMDJ7W1m8DVV8bfUJcsLBazHTs+6DKNpVerj14Q3fTcf1gI/byeguYrTStpEgTcwPd5aPdm/B7\n" +
            "cJfoKR/yNuKV4xNpHqxOI8bGlyUxCwhFzx2pdmM9PEHoZ6JV5jRvQ9Qne1AFXw9Tn3/gRAeakSzw\n" +
            "dP1k/kfA3Pcow5p+pDEC3uESdnfueibMs210a9nTopA9wt2sRaIYnojQw+uZtEGf9U1CFwouhtyy\n" +
            "5jJ8w3hXLT6RAgMBAAECggEALCOOlP9wu13pUFlDchStAjwQ+EEHw5Jn1JpQtlFBCvIQScbnkreX\n" +
            "sCWU51HoC8/6Ci898QwBulGt6Gpx/de+6QPjBZzgcGnc99esQdea5RXttUNeFXw1PChH7gNtOdZk\n" +
            "5nlDYdiv7zF6Vq8OGM2FJ1LHClCb6RrSy3yNbBCpClBYI3byj2dOeNUO1zvwZs1Js/ig2nPVDZU0\n" +
            "DN5xaj4cHUj0i7NoTs9qbzmyvdQ/D2mzj1PCN0Vcpsu2wEX5lp//g2sw2lKj5bdqp/RcEx1BJYur\n" +
            "vShnscXj0cL/tKiIBwFe2z8pSK4OPDky6Dw1MzMvgJB5ormLbqPqr9KhNBLZ8QKBgQD7upyV+pAP\n" +
            "qfqSlhXboN6CD+HxR9y/OFL033zYw+HFHY1P4BgqwGsz6kBWewHrDB+dF7qnwSQ0KNm7iAGpH0q6\n" +
            "sD47pS2rxz7Q2D+6wyI7nqULLfUJ9gwXypmLf+UM3hZBXJ9kqusceQSZQ8o745dnaQEu3ksGEupz\n" +
            "2sZruvztXQKBgQDVp5j211WqzcME8PwNsUEFaqu7InNwBOs7AgayYsslzN8OtBlTQvzMcwuWTrXM\n" +
            "tDFiCCYwS9HFWHmk9qPIhhPkJ85XAfsSx1zOkfC94ITFYSLRRCbxxmSYXHFkqb6nNccpyduEI4Kx\n" +
            "obzFo15KsBHqdqz9x7ltf+LhYxDLQ1WOxQKBgAWBEhVxch71j4YPBBFWVicv47fb42bG+4FhNaCj\n" +
            "v9cIi2N4ue6NYIq1D40UiXkzPam4sBsk7P66+e/QzIB/L6ofWm0Bcpsz8xqr79DMnX4CPQeejKIY\n" +
            "IDGQ+4SCWmbKoF/zf4S6/AMWXlRfmBdxJYlcUWtN7wQF+9xmpzoJz6f9AoGBAIvSH6fC6lxaRftQ\n" +
            "3hLz4lJyKarQ8+zmOy8lt048myfEuGE9+ajkmN4PmBCouA8cCYlXPe6zX7efnBQaGLtdC11vs3h7\n" +
            "Yl06vQCuv3bznNAzLn728+mb5S9UqByxfYSeDBXL9W23c4teBlTtAgMs0Ly5J6L5dt8zJz5K/62W\n" +
            "BkLlAoGBAMxkPQcvB1XS7cV339xi9Hj5EasumdKPsLh8g6C49FENDCsG9z5htFpqPX7aOFWY9H8T\n" +
            "vEE271W3ee9uZmUZhjrKhTB7G5IfYLTNkZSPI0MduRUziycfKEtBbic54Q/YXPFd/6cQQSF2U2Iq\n" +
            "G4xg3xKYMPQwEY/4aB8DVka0dpLM";//私钥

    public static void main(String[] args) {

        String str = "wobushizhengzhengdekuaile";


        //公钥进行加密
        String encryptStr = RSAUtils.encryptByPublicKey(str,publicKey, Cipher.ENCRYPT_MODE);

        System.out.println("加密后的字符:"+encryptStr);

        //私钥进行解密
        String decryptStr = RSAUtils.encryptByprivateKey(encryptStr,privateKey,Cipher.DECRYPT_MODE);

        //私钥进行解密
        System.out.println(decryptStr);


    }
}

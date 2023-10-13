import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class solve {
    public static final long a(byte[] bArr) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return crc32.getValue();
    }

    public static final byte[] b(String str, byte[] bArr, SecretKeySpec secretKeySpec, IvParameterSpec ivParameterSpec) {
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(bArr);
            return doFinal;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle exceptions appropriately in your application
        }
    }

    public static void Decode_and_save_file() {
        byte[] e2 = readFileToBytes("iv.png");
        byte[] bytes = "4508305374508305".getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes,"AES");
        String string = "AES/CBC/PKCS5Padding";
        String string2 = "abcdefghijklmnop";
        byte[] bytes2 = string2.getBytes(StandardCharsets.UTF_8);
        byte[] b2 = b(string, e2, secretKeySpec, new IvParameterSpec(bytes2));
        String fileName = "flag.png";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(b2);
            outputStream.close();
            System.out.println("File saved as : " + fileName + " God I hope it works !!!!!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String key = "4508305374508305";

    public static byte[] readFileToBytes(String filePath) {
    try {
        Path path = Path.of(filePath);
        return Files.readAllBytes(path);
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception appropriately in your application
        return null;
    }
}

    public static void main(String[] args) {
        Decode_and_save_file();
    }
}

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHasher {
    public static String hashFile(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(filePath);
             DigestInputStream dis = new DigestInputStream(fis, md)) {

            byte[] buffer = new byte[32768];
            while (dis.read(buffer) != -1) {

            }

            byte[] digestBytes = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte digestByte : digestBytes) {
                String hex = Integer.toHexString(0xff & digestByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        }
    }

    public static void main(String[] args) {
        /*try {
            String fileHash = hashFile(filePath);
            System.out.println("SHA-256 hash of the file: " + fileHash);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileHasherUI ui = new FileHasherUI();
                ui.setVisible(true);
            }
        });
    }
}

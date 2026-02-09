package genDoc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    public static byte[] generateQRCode(String data, int size) throws Exception {

        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix matrix = qrWriter.encode(
                data,
                BarcodeFormat.QR_CODE,
                size,
                size
        );

        BufferedImage image = new BufferedImage(
                size,
                size,
                BufferedImage.TYPE_INT_RGB
        );

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                image.setRGB(x, y,
                        matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF
                );
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return baos.toByteArray(); 
    }
}

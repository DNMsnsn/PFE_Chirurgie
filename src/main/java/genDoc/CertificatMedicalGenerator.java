package genDoc;

import java.time.LocalDate;
import java.time.Period;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CertificatMedicalGenerator {

    public static void genererCertificat(
            String cheminPdf,
            String nomHopital,
            String nomMedecin,
            String prenomMedecin,
            String nomPatient,
            String prenomPatient,
            String dateEmission,
            LocalDate dateNaissance,
            String in_apte,
            String urlCertificat
    ) throws Exception {

        int age = Period.between(dateNaissance, LocalDate.now()).getYears();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();

        float marginLeft = 70;
        float marginRight = pageWidth - 70;

        PDPageContentStream content = new PDPageContentStream(document, page);

        /* ========= ENTETE ========= */

        float headerY = pageHeight - 60;

        content.beginText();
        content.setFont(PDType1Font.TIMES_BOLD, 14);
        content.newLineAtOffset(marginLeft, headerY);
        content.showText(nomHopital);
        content.endText();

        content.beginText();
        content.setFont(PDType1Font.TIMES_ROMAN, 10);
        content.newLineAtOffset(marginLeft, headerY - 20);
        content.showText("Adresse : Tizi-Ouzou");
        content.newLineAtOffset(0, -14);
        content.showText("Tel : 026 xx xx xx    Fax : 026 xx xx xx");
        content.newLineAtOffset(0, -14);
        content.showText("Email : contact@hopital.dz    Site : www.hopital.dz");
        content.endText();

        content.moveTo(marginLeft, headerY - 70);
        content.lineTo(marginRight, headerY - 70);
        content.stroke();

        /* ========= TITRE ========= */

        content.beginText();
        content.setFont(PDType1Font.TIMES_BOLD, 18);
        content.newLineAtOffset((pageWidth / 2) - 120, headerY - 120);
        content.showText("CERTIFICAT MEDICAL");
        content.endText();

        /* ========= CORPS ========= */

        float bodyY = headerY - 170;

        content.beginText();
        content.setFont(PDType1Font.TIMES_ROMAN, 12);
        content.newLineAtOffset(marginLeft, bodyY);

        content.showText(
                "Je soussigné Dr " + nomMedecin + " " + prenomMedecin +
                " certifie avoir examiné ce jour " + dateEmission + " " + "Mr/Mme " + nomPatient
        );
        content.newLineAtOffset(0, -22);

        content.showText(
        		 prenomPatient + ", âgé(e) de " + age + " ans, et le/la déclare indemne de toute affection cliniquement décelable."
        );
        content.newLineAtOffset(0, -22);
        
        

        content.showText(
                "Le/la sus-nommé(e) est physiquement : " + in_apte
        );
        content.newLineAtOffset(0, -40);
        
        content.showText(
                "Dont certificat."
        );
        content.newLineAtOffset(0, -40);

        content.showText(
                "Fait à Tizi-Ouzou, le " + LocalDate.now()
        );

        content.endText();

        /* ========= QR CODE (EN MEMOIRE) ========= */

        byte[] qrBytes = QRCodeGenerator.generateQRCode(
        		urlCertificat,
                200
        );

        PDImageXObject qrImage = PDImageXObject.createFromByteArray(
                document,
                qrBytes,
                "qr"
        );

        content.drawImage(qrImage, 400, 400, 80, 80);

        content.close();
        document.save(cheminPdf);
        document.close();
    }
    
    /*
    
    public static void main(String[] args) {
        try {
            CertificatMedicalGenerator.genererCertificat(
                "D:/Certificat_Medical.pdf",
                "HOPITAL CENTRAL D'AZAZGA",
                "Bir Rabah",
                "KASSOURI Youcef",
                LocalDate.of(1970, 12, 3),
                "Inapte"
                
            );

            System.out.println("✅ PDF généré avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
}

package Controller;

import genDoc.QRCodeGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/DownloadPatientQrPDF")
public class DownloadPatientQrPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ================= DONNÉES =================
            String idPatient = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String tel = request.getParameter("tel");
            String mail = request.getParameter("mail");
            String adr = request.getParameter("adr");
            String dt = request.getParameter("dt");
            String grp = request.getParameter("grp");

            String nomHopital = "HÔPITAL NATIONAL";
            
            // ================= QR =================
            String qrData = "https://azazmed.net/InterfaceUtilisateur?id=" + idPatient;
            byte[] qrBytes = QRCodeGenerator.generateQRCode(qrData, 180);

            // ================= PDF =================
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float marginLeft = 50;
            float marginRight = pageWidth - 50;

            PDPageContentStream content = new PDPageContentStream(document, page);

            /* ================= ENTÊTE ================= */

            float headerY = pageHeight - 60;

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 20);
            content.newLineAtOffset(marginLeft, headerY);
            content.showText(nomHopital);
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 10);
            content.newLineAtOffset(marginLeft, headerY - 18);
            content.showText("Adresse : Tizi-Ouzou");
            content.newLineAtOffset(0, -14);
            content.showText("Tel : 026 11 00 64 | Email : contact@hcm-dz.com");
            content.endText();

            content.moveTo(marginLeft, headerY - 40);
            content.lineTo(marginRight, headerY - 40);
            content.stroke();

            /* ================= TITRE ================= */

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 18);
            content.newLineAtOffset((pageWidth / 2) - 90, headerY - 80);
            content.showText("CARTE PATIENT");
            content.endText();

            /* ================= CADRE ================= */

            float boxY = headerY - 120;
            content.addRect(marginLeft, boxY - 300, pageWidth - 100, 300);
            content.stroke();

            /* ================= INFOS PATIENT ================= */

            float textX = marginLeft + 20;
            float textY = boxY - 30;

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(textX, textY);

            content.showText("ID Patient : " + idPatient);
            content.newLineAtOffset(0, -18);
            content.showText("Nom : " + nom);
            content.newLineAtOffset(0, -18);
            content.showText("Prénom : " + prenom);
            content.newLineAtOffset(0, -18);
            content.showText("Téléphone : " + tel);
            content.newLineAtOffset(0, -18);
            content.showText("Email : " + mail);
            content.newLineAtOffset(0, -18);
            content.showText("Adresse : " + adr);
            content.newLineAtOffset(0, -18);
            content.showText("Groupe sanguin : " + grp);
            content.newLineAtOffset(0, -18);
            content.showText("Date : " + dt);

            content.endText();

            /* ================= QR ================= */

            PDImageXObject qrImage = PDImageXObject.createFromByteArray(
                    document, qrBytes, "qrcode");

            content.drawImage(qrImage, pageWidth - 220, boxY - 220, 160, 160);

            content.close();

            /* ================= ENVOI ================= */

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();

            byte[] pdfBytes = baos.toByteArray();

            response.setContentType("application/pdf");
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=Carte_Patient_" + idPatient + ".pdf"
            );
            response.setContentLength(pdfBytes.length);
            response.getOutputStream().write(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

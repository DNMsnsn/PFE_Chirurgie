package Controller;

import genDoc.QRCodeGenerator;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/DownloadCrhQrPDF")
public class DownloadCrhQrPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* ===== RÉCUP DONNÉES ===== */
        String id = request.getParameter("id");
        String idPatient = request.getParameter("id_patient");
        String nPatient = request.getParameter("n_patient");
        String pPatient = request.getParameter("p_patient");

        String nOperateur = request.getParameter("n_medecin");
        String pOperateur = request.getParameter("p_medecin");

        String dte = request.getParameter("dte");
        String dts = request.getParameter("dts");

        String content = request.getParameter("content");

        /* ===== PDF ===== */
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        PDPageContentStream c = new PDPageContentStream(doc, page);

        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float margin = 50;
        float cursorY;

        /* ================= ENTÊTE FIXE ================= */
        cursorY = pageHeight - 60;
        c.beginText();
        c.setFont(PDType1Font.TIMES_BOLD, 20);
        c.newLineAtOffset(margin, cursorY);
        c.showText("HOPITAL UNIVERSITAIRE");
        c.endText();

        c.beginText();
        c.setFont(PDType1Font.TIMES_ROMAN, 10);
        c.newLineAtOffset(margin, cursorY - 18);
        c.showText("Adresse : Tizi-Ouzou");
        c.newLineAtOffset(0, -14);
        c.showText("Tel : 026 xx xx xx | Email : contact@hopital.dz");
        c.endText();

        c.moveTo(margin, cursorY - 40);
        c.lineTo(pageWidth - margin, cursorY - 40);
        c.stroke();

        /* ================= TITRE FIXE ================= */
        cursorY -= 70;
        c.beginText();
        c.setFont(PDType1Font.TIMES_BOLD, 16);
        c.newLineAtOffset(margin + 100, cursorY);
        c.showText("COMPTE RENDU D'HOSPITALISATION");
        c.endText();

        /* ========= BLOC IDENTITÉ ========= */
        cursorY -= 40;
        drawBox(c, margin, cursorY - 55, pageWidth - 2 * margin, 55);
        writeText(c, margin + 10, cursorY - 20, "ID : " + id);
        writeText(c, margin + 10, cursorY - 40, "Patient : " + nPatient + " " + pPatient);

        /* ========= BLOC ÉQUIPE ========= */
        cursorY -= 70;
        drawBox(c, margin, cursorY - 80, pageWidth - 2 * margin, 80);
        writeText(c, margin + 10, cursorY - 20, "Medecin : " + nOperateur + " " + pOperateur);

        /* ========= BLOC OPÉRATION ========= */
        cursorY -= 95;
        drawBox(c, margin, cursorY - 45, pageWidth - 2 * margin, 45);
        writeText(c, margin + 10, cursorY - 20, "Date d'entree : " + dte);
        writeText(c, margin + 250, cursorY - 20, "Date de sortie : " + dts);

        /* ========= BLOC PROTOCOLE ILLIMITÉ ========= */
        cursorY -= 60;
        float lineHeight = 14;
        float boxMargin = 10;
        //float availableHeight = cursorY - 60; // laisser espace bas pour QR
        String[] lignes = content.replace("\r", "").split("\n");

        float protoX = margin;
        float protoWidth = pageWidth - 2 * margin;
        float protoY = cursorY;

        drawBox(c, protoX, protoY - (lignes.length + 2) * lineHeight, protoWidth, (lignes.length + 2) * lineHeight);
        writeText(c, protoX + boxMargin, protoY - lineHeight, "Protocole Opératoire :");

        float textY = protoY - 2 * lineHeight;
        for (String l : lignes) {
            if (!l.trim().isEmpty()) {
                if (textY < 60) { // créer nouvelle page si bas atteint
                    c.close();
                    page = new PDPage(PDRectangle.A4);
                    doc.addPage(page);
                    c = new PDPageContentStream(doc, page);
                    textY = pageHeight - 60;
                }
                writeText(c, protoX + 2 * boxMargin, textY, "- " + l.trim());
                textY -= lineHeight;
            }
        }

        /* ========= QR CODE ========= */
        try {
            byte[] qr = QRCodeGenerator.generateQRCode(
                    "http://localhost:8080/revisionControle/InterfaceUtilisateur?id=" + idPatient,
                    120
            );
            PDImageXObject qrImg = PDImageXObject.createFromByteArray(doc, qr, "qr");
            c.drawImage(qrImg, pageWidth - margin - 120, 60, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        c.close();

        /* ===== STREAM ===== */
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=protocole_operatoire.pdf");
        doc.save(response.getOutputStream());
        doc.close();
    }

    /* ===== UTIL ===== */
    private void drawBox(PDPageContentStream c, float x, float y, float w, float h) throws IOException {
        c.addRect(x, y, w, h);
        c.stroke();
    }

    private void writeText(PDPageContentStream c, float x, float y, String txt) throws IOException {
        c.beginText();
        c.setFont(PDType1Font.TIMES_ROMAN, 11);
        c.newLineAtOffset(x, y);
        c.showText(txt);
        c.endText();
    }
}

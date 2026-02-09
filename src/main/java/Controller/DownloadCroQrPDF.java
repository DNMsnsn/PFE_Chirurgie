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

@WebServlet("/DownloadCroQrPDF")
public class DownloadCroQrPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* ===== RÉCUP DONNÉES ===== */
        String id = request.getParameter("id");
        String idPatient = request.getParameter("id_patient");
        String nPatient = request.getParameter("n_patient");
        String pPatient = request.getParameter("p_patient");

        String nOperateur = request.getParameter("n_operateur");
        String pOperateur = request.getParameter("p_operateur");

        String nAide = request.getParameter("n_aide");
        String pAide = request.getParameter("p_aide");

        String nRea = request.getParameter("n_reanimateur");
        String pRea = request.getParameter("p_reanimateur");

        String nAnes = request.getParameter("n_anesthesiste");
        String pAnes = request.getParameter("p_anesthesiste");

        String date = request.getParameter("dt");
        String bloc = request.getParameter("bloc");

        String diag = request.getParameter("diag");
        String intervention = request.getParameter("intervention");
        String protocole = request.getParameter("protocole");

        /* ===== PDF ===== */
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        PDPageContentStream c = new PDPageContentStream(doc, page);

        float pageWidth = page.getMediaBox().getWidth();
        float y = page.getMediaBox().getHeight() - 60;
        float margin = 50;
        float width = pageWidth - 2 * margin;

        /* ================= ENTÊTE ================= */
        c.beginText();
        c.setFont(PDType1Font.TIMES_BOLD, 20);
        c.newLineAtOffset(margin, y);
        c.showText("HOPITAL UNIVERSITAIRE");
        c.endText();

        c.beginText();
        c.setFont(PDType1Font.TIMES_ROMAN, 10);
        c.newLineAtOffset(margin, y - 18);
        c.showText("Adresse : Tizi-Ouzou");
        c.newLineAtOffset(0, -14);
        c.showText("Tel : 026 xx xx xx | Email : contact@hopital.dz");
        c.endText();

        c.moveTo(margin, y - 40);
        c.lineTo(pageWidth - margin, y - 40);
        c.stroke();

        y -= 70;

        /* ================= TITRE ================= */
        c.beginText();
        c.setFont(PDType1Font.TIMES_BOLD, 16);
        c.newLineAtOffset(margin + 150, y);
        c.showText("PROTOCOLE OPERATOIRE");
        c.endText();

        y -= 40;

        /* ========= BLOC IDENTITÉ ========= */
        drawBox(c, margin, y, width, 55);
        writeText(c, margin + 10, y + 35, "ID : " + id);
        writeText(c, margin + 10, y + 15,
                "Patient : " + nPatient + " " + pPatient);
        y -= 70;

        /* ========= BLOC ÉQUIPE ========= */
        drawBox(c, margin, y, width, 80);
        writeText(c, margin + 10, y + 60,
                "Operateur : " + nOperateur + " " + pOperateur);
        writeText(c, margin + 10, y + 45,
                "Aide : " + nAide + " " + pAide);
        writeText(c, margin + 10, y + 30,
                "Anesthesiste : " + nAnes + " " + pAnes);
        writeText(c, margin + 10, y + 15,
                "Reanimateur : " + nRea + " " + pRea);
        y -= 95;

        /* ========= BLOC OPÉRATION ========= */
        drawBox(c, margin, y, width, 45);
        writeText(c, margin + 10, y + 25, "Date : " + date);
        writeText(c, margin + 250, y + 25, "Bloc : " + bloc);
        y -= 60;

        /* ========= BLOC MÉDICAL ========= */
        drawBox(c, margin, y, width, 70);
        writeText(c, margin + 10, y + 45,
                "Diagnostique Lésionnel : " + diag);
        writeText(c, margin + 10, y + 20,
                "Intervention Pratiquée : " + intervention);
        y -= 85;

        /* ========= BLOC PROTOCOLE ========= */
        String[] lignes = protocole.replace("\r", "").split("\n");
        float protoHeight = 30 + lignes.length * 14;

        drawBox(c, margin, y, width, protoHeight);
        writeText(c, margin + 10, y + protoHeight - 20,
                "Protocole Opératoire :");

        float ly = y + protoHeight - 35;
        for (String l : lignes) {
            if (!l.trim().isEmpty()) {
                writeText(c, margin + 25, ly, "- " + l.trim());
                ly -= 14;
            }
        }

        /* ========= QR CODE ========= */
        try {
            byte[] qr = QRCodeGenerator.generateQRCode(
            		"http://localhost:8080/revisionControle/InterfaceUtilisateur?id=" + idPatient,
                    120
            );

            PDImageXObject qrImg =
                    PDImageXObject.createFromByteArray(doc, qr, "qr");

            c.drawImage(qrImg,
                    pageWidth - margin - 120,
                    60,
                    100,
                    100);

        } catch (Exception e) {
            e.printStackTrace();
        }

        c.close();

        /* ===== STREAM ===== */
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "inline; filename=protocole_operatoire.pdf");

        doc.save(response.getOutputStream());
        doc.close();
    }

    /* ===== UTIL ===== */
    private void drawBox(PDPageContentStream c,
                         float x, float y, float w, float h) throws IOException {
        c.addRect(x, y, w, h);
        c.stroke();
    }

    private void writeText(PDPageContentStream c,
                           float x, float y, String txt) throws IOException {
        c.beginText();
        c.setFont(PDType1Font.TIMES_ROMAN, 11);
        c.newLineAtOffset(x, y);
        c.showText(txt);
        c.endText();
    }
}

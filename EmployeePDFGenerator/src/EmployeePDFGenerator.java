import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class EmployeePDFGenerator {

    public static void main(String[] args) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream content =
                new PDPageContentStream(document, page);

        // TITLE
        content.beginText();
        content.setFont(
                new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 18);
        content.newLineAtOffset(180, 750);
        content.showText("EMPLOYEE REPORT");
        content.endText();

        int y = 700;

        content.setFont(
                new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

// TABLE HEADER
        content.setFont(
                new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);

        content.beginText();
        content.newLineAtOffset(80, y);
        content.showText("ID     NAME     DEPARTMENT     SALARY");
        content.endText();

        y -= 20;

// TABLE DATA
        content.setFont(
                new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

        List<String> lines = Files.readAllLines(Paths.get("employees.csv"));

        for (int i = 1; i < lines.size(); i++) {

            String[] data = lines.get(i).split(",");

            content.beginText();
            content.newLineAtOffset(80, y);

            content.showText(
                    String.format("%-6s %-10s %-15s %-10s",
                            data[0], data[1], data[2], data[3])
            );

            content.endText();

            y -= 20;
        }

        content.close();

        document.save("EmployeeReport.pdf");
        document.close();

        System.out.println("Dynamic Employee PDF Generated!");
    }
}
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class BatchEmployeePDF {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("employees.csv"));

        for (int i = 1; i < lines.size(); i++) {

            String[] data = lines.get(i).split(",");

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content =
                    new PDPageContentStream(document, page);

            // COMPANY NAME
            content.beginText();
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
            content.newLineAtOffset(160, 750);
            content.showText("ABC COMPANY");
            content.endText();

            // DATE
            content.beginText();
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
            content.newLineAtOffset(400, 770);
            content.showText("Date: " + LocalDate.now());
            content.endText();

            // TITLE
            content.beginText();
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            content.newLineAtOffset(170, 700);
            content.showText("EMPLOYEE REPORT");
            content.endText();

            // EMPLOYEE DETAILS
            content.beginText();
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            content.newLineAtOffset(100, 650);
            content.showText("ID: " + data[0]);
            content.newLineAtOffset(0, -20);
            content.showText("Name: " + data[1]);
            content.newLineAtOffset(0, -20);
            content.showText("Department: " + data[2]);
            content.newLineAtOffset(0, -20);
            content.showText("Salary: " + data[3]);
            content.endText();

            content.close();

            document.save("reports/Employee_" + data[0] + ".pdf");
            document.close();
        }

        System.out.println("Batch PDFs Generated!");
    }
}
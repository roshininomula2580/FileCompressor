import java.io.*;
import java.util.Scanner;
import java.util.zip.*;

public class FileCompressor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== File Compressor & Decompressor =====");
        System.out.println("1. Compress File");
        System.out.println("2. Decompress File");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        try {
            if (choice == 1) {
                System.out.print("Enter file path to compress: ");
                String path = sc.nextLine();
                compressFile(path);
            } 
            else if (choice == 2) {
                System.out.print("Enter zip file path to decompress: ");
                String path = sc.nextLine();
                decompressFile(path);
            } 
            else {
                System.out.println("Invalid choice");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // COMPRESS METHOD
    public static void compressFile(String filePath) throws IOException {

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        String zipFileName = file.getName() + ".zip";
        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }

        zos.close();
        fis.close();

        System.out.println("File compressed successfully: " + zipFileName);
    }

    // DECOMPRESS METHOD
    public static void decompressFile(String zipFilePath) throws IOException {

        FileInputStream fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);

        ZipEntry entry = zis.getNextEntry();

        FileOutputStream fos = new FileOutputStream(entry.getName());

        byte[] buffer = new byte[1024];
        int length;

        while ((length = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }

        fos.close();
        zis.closeEntry();
        zis.close();

        System.out.println("File decompressed successfully.");
    }
}

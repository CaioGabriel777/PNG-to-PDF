package com.conversor.png.to.pdf.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class conversorUtils {

    public static byte[] convertImageToPdf(MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Carregar a imagem
        ImageData imageData = ImageDataFactory.create(file.getBytes());
        Image image = new Image(imageData);

        // Centralizar no PDF
        image.setAutoScale(true);

        document.add(image);
        document.close();

        return outputStream.toByteArray();
    }
}

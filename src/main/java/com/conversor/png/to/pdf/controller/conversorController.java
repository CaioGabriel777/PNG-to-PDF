package com.conversor.png.to.pdf.controller;

import com.conversor.png.to.pdf.utils.conversorUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class conversorController  {

    @PostMapping("/conversor")
    public ResponseEntity<byte[]> convertImageToPdf(@RequestParam("file") MultipartFile file){

        try{
            if (file.isEmpty()){
                return ResponseEntity.badRequest().body("Nenhuma imagem enviada!".getBytes());
            }

            //Chama o metodo todo do Utils para Converter a imagem em PDF
            byte[] pdfBytes = conversorUtils.convertImageToPdf(file);

            //Retorna o PDF gerado como resposta (no formato de array de bytes)
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=imagem_convertida.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao converter a imagem.".getBytes());
        }


    }

}

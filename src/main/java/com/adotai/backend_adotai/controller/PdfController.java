package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Pdf.PdfDto;
import com.adotai.backend_adotai.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    PdfGeneratorService pdfGeneratorService;


    @GetMapping
    public ResponseEntity<byte[]> gerarPdf(@RequestBody PdfDto dto) {
        ResponseApi<byte[]> response = pdfGeneratorService.create(dto);

        if (response.status() == 200) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=certificado.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(response.data());
        } else {
            return ResponseEntity.status(response.status()).build();
        }
    }

}

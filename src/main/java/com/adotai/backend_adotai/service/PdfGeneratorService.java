package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Pdf.PdfDto;
import com.adotai.backend_adotai.entity.Animal;
import com.adotai.backend_adotai.entity.Ong;
import com.adotai.backend_adotai.entity.User;
import com.adotai.backend_adotai.repository.AnimalRepository;
import com.adotai.backend_adotai.repository.OngRepository;
import com.adotai.backend_adotai.repository.UserRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {

    private final OngRepository ongRepository;
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    public PdfGeneratorService(OngRepository ongRepository,
                               AnimalRepository animalRepository,
                               UserRepository userRepository) {
        this.ongRepository = ongRepository;
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    public ResponseApi<byte[]> create(PdfDto dto) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Animal animal = animalRepository.findById(dto.idAnimal())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado."));

            Ong ong = animal.getOng();

            User user = userRepository.findById(dto.idUser())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

            Document document = new Document(PageSize.A4, 50, 50, 80, 50);
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 26, Font.BOLD, new Color(0, 102, 204));
            Font subtitleFont = new Font(Font.HELVETICA, 18, Font.ITALIC, new Color(0, 0, 0));
            Font normalFont = new Font(Font.HELVETICA, 14, Font.NORMAL, Color.BLACK);

            Paragraph titulo = new Paragraph("Certificado de Adoção", titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(40);
            document.add(titulo);

            String texto = String.format(
                    "Certificamos que %s realizou a adoção responsável de %s,\n" +
                            "sob os cuidados da ONG %s.\n\nDesejamos uma vida repleta de amor, carinho e companheirismo!",
                    user.getName(), animal.getName(), ong.getName()
            );

            Paragraph corpo = new Paragraph(texto, normalFont);
            corpo.setAlignment(Element.ALIGN_CENTER);
            corpo.setSpacingAfter(30);
            document.add(corpo);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
            Paragraph data = new Paragraph("Emitido em: " + LocalDate.now().format(formatter), subtitleFont);
            data.setAlignment(Element.ALIGN_CENTER);
            data.setSpacingAfter(50);
            document.add(data);

            PdfPTable assinaturas = new PdfPTable(2);
            assinaturas.setWidthPercentage(80);
            assinaturas.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cellUser = new PdfPCell(new Phrase("_____________________________\nAdotante: " + user.getName(), normalFont));
            cellUser.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUser.setBorder(Rectangle.NO_BORDER);

            PdfPCell cellOng = new PdfPCell(new Phrase("_____________________________\nONG: " + ong.getName(), normalFont));
            cellOng.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellOng.setBorder(Rectangle.NO_BORDER);

            assinaturas.addCell(cellUser);
            assinaturas.addCell(cellOng);

            document.add(assinaturas);
            document.close();

            return ResponseApi.success("Certificado gerado com sucesso!", out.toByteArray());

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return ResponseApi.error(500, "Erro ao gerar PDF: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseApi.error(404, e.getMessage());
        }
    }
}

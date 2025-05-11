package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Color.Request.RequestColorDto;
import com.adotai.backend_adotai.dto.Color.Response.ResponseColorDto;
import com.adotai.backend_adotai.entity.Color;
import com.adotai.backend_adotai.mapper.ColorMapper;
import com.adotai.backend_adotai.repository.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<ResponseColorDto> findAll(){
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(ColorMapper::toDto).toList();
    }

   public ResponseColorDto findById(Integer id){
        Optional<Color> color = colorRepository.findById(id);
        if(color.isEmpty()){
            throw new EntityNotFoundException("Specie not found with id: " + id);
        }

        return ColorMapper.toDto(color.get());
   }

   public ResponseColorDto findByName(String name){
        Optional<Color> color = colorRepository.findByName(name);

        if(color.isEmpty()){
            throw new EntityNotFoundException("Specie not found with name: " + name);
        }
        return ColorMapper.toDto(color.get());
   }

   public ResponseColorDto save(RequestColorDto requestColorDto){
        Color color = colorRepository.save(ColorMapper.toEntity(requestColorDto));
        return ColorMapper.toDto(color);
   }
}

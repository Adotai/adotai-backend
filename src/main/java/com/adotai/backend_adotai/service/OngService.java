package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Address.UpdateAddressDto;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngPhotosDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngDTO;
import com.adotai.backend_adotai.dto.Ong.UpdateOngDto;
import com.adotai.backend_adotai.dto.Ong.UpdateOngPhotosDTO;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.Ong;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.mapper.OngMapper;
import com.adotai.backend_adotai.mapper.PhotosMapper.OngPhotosMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.repository.OngRepository;
import com.adotai.backend_adotai.repository.PhotosRepository.OngPhotosRepository;
import com.adotai.backend_adotai.util.ValidationUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngService {

    private final OngRepository ongRepository;
    private final OngPhotosRepository ongPhotosRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final BCryptPasswordEncoder passwordEncoder;

    public OngService(OngRepository ongRepository, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder, OngPhotosRepository ongPhotosRepository, AddressService addressService) {
        this.ongRepository = ongRepository;
        this.ongPhotosRepository = ongPhotosRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.addressService = addressService;
    }

    public ResponseApi<ResponseOngDTO> create(RequestOngDTO dto){
       if (!ValidationUtils.isValidCnpj(dto.cnpj())){
           return ResponseApi.error(404,"Missing or invalid cnpj.");
       }

       if(!ValidationUtils.isValidPhone(dto.phone())){
           return ResponseApi.error(404,"Missing or invalid phone number.");
       }

       if(!ValidationUtils.isValidEmail(dto.email()) || (ongRepository.existsByEmailIgnoreCase(dto.email()))){
           return ResponseApi.error(404,"Email inválido ou Inexistente.");
       }

       Optional<Address> address = addressRepository.findById(dto.addressId());
       if(address.isEmpty()){
           return ResponseApi.error(404,"Address not found.");
       }

       if(dto.password().isBlank())
            return ResponseApi.error(404,"Missing or invalid password.");

        if (dto.documents() == null || dto.documents().getSocialStatute().isBlank() || dto.documents().getBoardMeeting().isBlank()) {
            return ResponseApi.error(404, "Documentos faltando ou inválidos (socialStatute or boardMeeting).");
        }

        if (dto.photos() == null || dto.photos().isEmpty()) {
            return ResponseApi.error(404, "Fotos faltando ou inválidas.");
        }


        String encodedPassword = passwordEncoder.encode(dto.password());

        try{
            Ong ong = OngMapper.toEntity(dto,address.get(),encodedPassword);
            ongRepository.save(ong);

//            dto.photos().forEach(photoDto -> {
//                OngPhotos ongPhoto = new OngPhotos();
//                ongPhoto.setPhotoUrl(photoDto.getPhotoUrl());
//                ongPhoto.setOng(ong); // Associa a foto à ONG recém-criada
//                ongPhotosRepository.save(ongPhoto);
//            });

            return ResponseApi.success("Success",OngMapper.toDto(ong));
        } catch (Exception e) {
            return ResponseApi.error(500,"Error: " + e.getMessage());
        }
    }

    public ResponseApi<ResponseOngDTO> updateOng(UpdateOngDto dto) {
        Optional<Ong> existingOngOpt = ongRepository.findById(dto.id());

        if (existingOngOpt.isEmpty()) {
            return ResponseApi.error(404, "ONG not found");
        }

        Ong existingOng = existingOngOpt.get();

        if (dto.name() != null && !dto.name().isBlank()) {
            existingOng.setName(dto.name());
        }

        if (dto.phone() != null && !dto.phone().isBlank()) {
            if (!ValidationUtils.isValidPhone(dto.phone())) {
                return ResponseApi.error(400, "Invalid phone number");
            } else {
                existingOng.setPhone(dto.phone());
            }
        }

        if (dto.cnpj() != null && !dto.cnpj().isBlank()) {
            if (!ValidationUtils.isValidCnpj(dto.cnpj())) {
                return ResponseApi.error(400, "Invalid CNPJ");
            } else if (ongRepository.existsByCnpj(ValidationUtils.formatStrNumber(dto.cnpj()))) {
                return ResponseApi.error(400, "CNPJ already exists");
            } else {
                existingOng.setCnpj(ValidationUtils.formatStrNumber(dto.cnpj()));
            }
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            if (!ValidationUtils.isValidEmail(dto.email())) {
                return ResponseApi.error(400, "Invalid email");
            } else if (ongRepository.existsByEmailIgnoreCase(dto.email())) {
                return ResponseApi.error(400, "Email already exists");
            } else {
                existingOng.setEmail(dto.email());
            }
        }

        if (dto.description() != null && !dto.description().isBlank()) {
            existingOng.setDescription(dto.description());
        }

        if (dto.photos() != null) {
            Map<Integer, OngPhotos> existingPhotosMap = existingOng.getPhotos().stream()
                    .collect(Collectors.toMap(OngPhotos::getId, photo -> photo));

            List<OngPhotos> updatedPhotos = new ArrayList<>();

            for (UpdateOngPhotosDTO updatedPhotoDto : dto.photos()) {
                if (updatedPhotoDto.id() != null && existingPhotosMap.containsKey(updatedPhotoDto.id())) {
                    // Atualiza foto existente
                    OngPhotos existingPhoto = existingPhotosMap.get(updatedPhotoDto.id());
                    existingPhoto.setPhotoUrl(updatedPhotoDto.photoUrl());
                    updatedPhotos.add(existingPhoto);
                } else {
                    // Nova foto
                    OngPhotos newPhoto = OngPhotosMapper.toEntity(updatedPhotoDto);
                    newPhoto.setOng(existingOng);
                    updatedPhotos.add(newPhoto);
                }
            }
            existingOng.getPhotos().addAll(updatedPhotos);
        }

        if (dto.address() != null) {
            UpdateAddressDto updateAddressDto = AddressMapper.toUpdateDto(dto.address());
            ResponseApi<ResponseAddressDTO> addressUpdateResponse = addressService.update(updateAddressDto);

            if (addressUpdateResponse.status() >= 400 || addressUpdateResponse.data() == null) {
                return ResponseApi.error(addressUpdateResponse.status(), addressUpdateResponse.message());
            }

            Address updatedAddress = AddressMapper.toEntity(addressUpdateResponse.data());
            existingOng.setAddress(updatedAddress);
        }

        if (dto.password() != null && !dto.password().isBlank()) {
            String encodedPassword = passwordEncoder.encode(dto.password());
            existingOng.setPassword(encodedPassword);
        }

        try {
            Ong updatedOng = ongRepository.save(existingOng);
            return ResponseApi.success("ONG updated successfully", OngMapper.toDto(updatedOng));
        } catch (Exception e) {
            return ResponseApi.error(500, e.getMessage());
        }
    }



    public ResponseApi<String> deleteOngPhotos(Integer ongId, List<Integer> photoIdsToDelete) {
        if (photoIdsToDelete == null || photoIdsToDelete.isEmpty()) {
            return ResponseApi.error(400, "Nenhuma foto selecionada para exclusão.");
        }

        if (photoIdsToDelete.size() > 3) {
            return ResponseApi.error(400, "Você só pode excluir até 3 fotos por vez.");
        }

        Optional<Ong> ongOpt = ongRepository.findById(ongId);
        if (ongOpt.isEmpty()) {
            return ResponseApi.error(404, "ONG não encontrada.");
        }

        Ong ong = ongOpt.get();

        // Remove as fotos que estão na lista de exclusão
        List<OngPhotos> remainingPhotos = ong.getPhotos().stream()
                .filter(photo -> !photoIdsToDelete.contains(photo.getId()))
                .collect(Collectors.toList());

        // Verifica se todas as fotos a excluir pertencem mesmo à ONG
        long matchedCount = ong.getPhotos().stream()
                .filter(photo -> photoIdsToDelete.contains(photo.getId()))
                .count();

        if (matchedCount != photoIdsToDelete.size()) {
            return ResponseApi.error(400, "Uma ou mais fotos não pertencem a essa ONG.");
        }

        ong.getPhotos().clear();
        ong.getPhotos().addAll(remainingPhotos);

        ongRepository.save(ong);

        return ResponseApi.success("Fotos excluídas com sucesso.", null);
    }


    public ResponseApi<List<ResponseOngDTO>> findAll(){
        List<Ong> ongs = ongRepository.findAll();
        List<ResponseOngDTO> ongDto = ongs.stream().map(OngMapper::toDto).toList();
        return ResponseApi.success("Success",ongDto);
    }

    public ResponseApi<ResponseOngDTO> findById(int id){
        Optional<Ong> ong = ongRepository.findById(id);

        if(ong.isEmpty())
            return ResponseApi.error(404,"Ong not found.");

        ResponseOngDTO dto = OngMapper.toDto(ong.get());
        return ResponseApi.success("Success",dto);
    }

    public ResponseApi<ResponseOngDTO> delete(int id){
        Optional<Ong> ong = ongRepository.findById(id);

        if(ong.isEmpty())
            return ResponseApi.error(404,"Ong not found.");

        try{
            ongRepository.deleteById(id);
            ResponseOngDTO dto = OngMapper.toDto(ong.get());

            return ResponseApi.success("Success",dto);
        } catch (Exception e) {
            return ResponseApi.error(500,"Error: " + e.getMessage());
        }
    }

    public ResponseApi<?> updateStatusById(int id){
        int rowsAffected = ongRepository.toggleStatusById(id);
        if (rowsAffected == 0){
            return ResponseApi.error(404,"Not Found");
        }
        return ResponseApi.success("Success",null);
    }
}

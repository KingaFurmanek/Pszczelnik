package org.beeapp.backend.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beeapp.backend.api.external.HiveDTO;
import org.beeapp.backend.api.internal.Hive;
import org.beeapp.backend.api.internal.Task;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.mapper.HiveMapper;
import org.beeapp.backend.repository.HiveRepository;
import org.beeapp.backend.service.FileStorageService;
import org.beeapp.backend.service.HiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HiveServiceImpl implements HiveService {

    private final HiveRepository hiveRepository;
    private final HiveMapper hiveMapper;
    private final FileStorageService fileStorageService;

    @Override
    public List<HiveDTO> getUsersHives(Users user) {
        return hiveRepository.findByUser(user).stream()
                .map(hiveMapper::toDTO)
                .toList();
    }

    @Override
    public void addHive(HiveDTO addHiveRequestDTO, Users user, MultipartFile file) throws ParseException {
        String filePath = null;
        if (file != null && !file.isEmpty()) {
            filePath = fileStorageService.saveFile(file);
        }
        Hive hive = hiveMapper.toEntity(addHiveRequestDTO, user, filePath);
        hiveRepository.save(hive);
    }

    @Override
    public HiveDTO getHiveInfo(Integer hiveId) {
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new EntityNotFoundException("Ul o ID " + hiveId + " nie istnieje"));

        return hiveMapper.toDTO(hive);
    }

    @Override
    public void editHive(Integer hiveId, HiveDTO addHiveRequestDTO, MultipartFile file) throws ParseException {
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new EntityNotFoundException("Ul o ID: " + hiveId + " nie istnieje"));

        if (file != null && !file.isEmpty()) {
            hive.setImage(fileStorageService.saveFile(file));
        }

        hive.setName(addHiveRequestDTO.getName());
        hive.setFramesCount(addHiveRequestDTO.getFramesCount());
        hiveRepository.save(hive);
    }

    @Override
    public void deleteHive(Integer hiveId) {
        Hive hive = hiveRepository.findById(hiveId)
                .orElseThrow(() -> new EntityNotFoundException("Ul o ID: " + hiveId + " nie istnieje"));

        hiveRepository.delete(hive);
    }
}

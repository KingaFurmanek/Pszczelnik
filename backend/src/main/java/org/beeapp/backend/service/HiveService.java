package org.beeapp.backend.service;

import org.beeapp.backend.api.external.HiveDTO;
import org.beeapp.backend.api.internal.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface HiveService {
    List<HiveDTO> getUsersHives(Users user);
    void addHive(HiveDTO addHiveRequestDTO, Users user, MultipartFile file) throws ParseException;
    HiveDTO getHiveInfo(Integer hiveId);
    void editHive(Integer hiveId, HiveDTO addHiveRequestDTO, MultipartFile file) throws ParseException;
    void deleteHive(Integer hiveId);
}

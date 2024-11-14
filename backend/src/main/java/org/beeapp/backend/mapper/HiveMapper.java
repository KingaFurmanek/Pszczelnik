package org.beeapp.backend.mapper;

import org.beeapp.backend.api.external.HiveDTO;
import org.beeapp.backend.api.internal.Hive;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.service.implementations.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class HiveMapper {

    public Hive toEntity(HiveDTO hiveDTO, Users user, String filePath) {
        Hive hive = new Hive();
        hive.setId(hiveDTO.getId());
        hive.setName(hiveDTO.getName());
        hive.setFramesCount(hiveDTO.getFramesCount());
        hive.setLocationLatitude(hiveDTO.getLocationLatitude());
        hive.setLocationLongitude(hiveDTO.getLocationLongitude());
        hive.setImage(filePath);
        hive.setUser(user);

        return hive;
    }

    public HiveDTO toDTO(Hive hive) {
        HiveDTO hiveDTO = new HiveDTO();
        hiveDTO.setId(hive.getId());
        hiveDTO.setName(hive.getName());
        hiveDTO.setFramesCount(hive.getFramesCount());
        hiveDTO.setLocationLatitude(hive.getLocationLatitude());
        hiveDTO.setLocationLongitude(hive.getLocationLongitude());
        hiveDTO.setImage(FileUtils.readFileFromLocation(hive.getImage()));

        return hiveDTO;
    }
}

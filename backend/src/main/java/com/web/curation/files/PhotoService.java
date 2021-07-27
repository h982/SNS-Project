package com.web.curation.files;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PhotoService {
    private PhotoDao photoDao;

    public PhotoDto addPhoto(PhotoDto photoDto){
        return photoDao.save(photoDto);
    }

    public Optional<PhotoDto> findByImageName(String uploadedImageName) {
        return photoDao.findByImageName(uploadedImageName);
    }

    public void delete(Long id) {
        Optional<PhotoDto> uploadedImage = photoDao.findById(id);
        if (uploadedImage.isPresent()) {
            photoDao.delete(uploadedImage.get());
        }
    }
}

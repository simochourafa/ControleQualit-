package com.group.azura.maraissa.controleQualite.repository.postgres;

import com.group.azura.maraissa.controleQualite.entities.postgres.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Long> {
}

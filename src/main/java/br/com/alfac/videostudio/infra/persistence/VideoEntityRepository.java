package br.com.alfac.videostudio.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoEntityRepository extends JpaRepository<VideoEntity, Long> {

    Optional<VideoEntity> findByUuid(UUID uuid);

    List<VideoEntity> findByUsuarioId(Long usuarioId);

}

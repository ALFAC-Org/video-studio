package br.com.alfac.videostudio.infra.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LogRepository extends JpaRepository<LogEntity, UUID> {
}
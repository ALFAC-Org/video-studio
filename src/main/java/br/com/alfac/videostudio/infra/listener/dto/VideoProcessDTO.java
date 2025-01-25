package br.com.alfac.videostudio.infra.listener.dto;

import java.util.UUID;

public record VideoProcessDTO(UUID videoName, String processingStatus) {
}

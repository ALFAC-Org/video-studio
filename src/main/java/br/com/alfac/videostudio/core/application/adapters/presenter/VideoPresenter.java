package br.com.alfac.videostudio.core.application.adapters.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Video;

public final class VideoPresenter {

    private VideoPresenter() {
    }

    public static VideoDTO mapearParaVideoDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setNome(video.getNome());
        videoDTO.setStatus(video.getStatus());
        videoDTO.setId(video.getId());
        videoDTO.setUuId(video.getUuid());
        return videoDTO;
    }

    public static List<VideoDTO> mapearParaVideoDTOList(List<Video> videoList) {
        List<VideoDTO> videoDTOList = new ArrayList<>();
        
        if (videoList == null) {
            return videoDTOList;
        }

        for (Video video : videoList) {
            videoDTOList.add(mapearParaVideoDTO(video));
        }

        return videoDTOList;
    }

}

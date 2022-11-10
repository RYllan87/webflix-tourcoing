package fr.formation.webflix.controllers.api;


import fr.formation.webflix.entities.VideoEntity;
import fr.formation.webflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiVideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping(value = "/api/videos")
    public List<VideoEntity> indexVideo() {
        return (List<VideoEntity>) videoService.findAll();

    }
    @PostMapping(value = "/api/videos")
    public VideoEntity addVideo(@RequestBody VideoEntity videoEntity){

        return videoService.save(videoEntity);
    }

    @GetMapping(value = "/api/videos/{id}")
    public VideoEntity findOne(@PathVariable Long id) throws Exception {
        return videoService.findById(id).orElseThrow(() -> new Exception("vidéo non trouvée"));

    }

    @PutMapping("/api/videos/{id}")
    public VideoEntity update(@PathVariable Long id, @RequestBody VideoEntity videoEntity){
        return  videoService.findById(id)
                .map(video -> {
                    video.setName(videoEntity.getName());
                    video.setDuration(videoEntity.getDuration());
                    video.setOriginCountry(videoEntity.getOriginCountry());
                    video.setSynopsis(videoEntity.getSynopsis());
                    video.setUrlVideo(videoEntity.getUrlVideo());
                    video.setCover(video.getCover());
                    video.setProductYear(videoEntity.getProductYear());
                    video.setDatePublished(videoEntity.getDatePublished());
                    video.setCategory(videoEntity.getCategory());

                    return videoService.save(video);
                })
                .orElseGet(() -> {
                    videoEntity.setId(id);

                    return videoService.save(videoEntity);

                });

    }

    @DeleteMapping("/api/videos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        videoService.deleteById(id);
        String msg = """
                            {
                            "message": "Vidéo supprimée"
                            }
                            """;

        return ResponseEntity.ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(msg);
    }

//    class VideoException extends Exception {
//        public VideoException(){
//            super("Vidéo non trouvée");
//        }
    }

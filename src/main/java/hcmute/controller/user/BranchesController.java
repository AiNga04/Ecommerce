package hcmute.controller.user;

import hcmute.entity.CityEntity;
import hcmute.service.ICityService;
import hcmute.service.IStorageService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("branches")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class BranchesController {
    ICityService cityService;

    IStorageService storageService;

    @GetMapping("")
    public String listDefault(ModelMap model) {
        List<CityEntity> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        System.out.println(cities.size());
        if (cities.size() > 0) {
            model.addAttribute("idCity", cities.get(0).getIdCity());
        }
        return "user/branches";
    }

    @GetMapping("/{idCity}")
    public String listByCity(ModelMap model, @PathVariable("idCity") String idCity) {
        List<CityEntity> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        model.addAttribute("idCity", idCity);
        return "user/branches";
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                             .header(
                                     HttpHeaders.CONTENT_DISPOSITION,
                                     "attachment;filename=\"" + file.getFilename() + "\""
                             )
                             .body(file);
    }
}

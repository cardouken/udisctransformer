package ee.uustal.udisctransformer.controller;

import ee.uustal.udisctransformer.service.FileService;
import ee.uustal.udisctransformer.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private final ImportService importService;
    private final FileService fileService;

    @Autowired
    public UploadController(ImportService importService, FileService fileService) {
        this.importService = importService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/add-file")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}

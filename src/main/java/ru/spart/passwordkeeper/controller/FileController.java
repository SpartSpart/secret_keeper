package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.spart.passwordkeeper.controller.model.FileModel;
import ru.spart.passwordkeeper.repository.model.FileData;
import ru.spart.passwordkeeper.service.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/files/{doc_id}")
    public ResponseEntity<List<String>> addFiles(@PathVariable("doc_id") long doc_id, @RequestParam("files") @RequestBody MultipartFile[] file) {
        return ResponseEntity
                .ok()
                .body(fileService.addFiles(file,doc_id));
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/files/info/{doc_id}")
    public ResponseEntity<List<FileModel>> getAllFileInfo(@PathVariable("doc_id") long doc_id) {
        return ResponseEntity
                .ok()
                .body(fileService.getAllFileInfo(doc_id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/files/{file_id}")
    public ResponseEntity<Resource> getFile (@PathVariable("file_id") long file_id) throws IOException {

      File file = fileService.getFile(file_id);
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/files/android/{file_id}")
    public ResponseEntity<byte[]> getFileAndroid (@PathVariable("file_id") long file_id) throws IOException {


        File file = fileService.getFile(file_id);
        byte[] array = {1,2,3};
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
               .body(array);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping (value = "/files/delete")
    public ResponseEntity<Void> deleteListDocs(@RequestBody List<Long> idList) throws FileNotFoundException {
        fileService.deleteListFiles(idList);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/upload/file/{doc_id}")
    public ResponseEntity<FileModel> addFile(@PathVariable("doc_id") long doc_id, @RequestParam("file") @RequestBody MultipartFile file) {
        return ResponseEntity
                .ok()
                .body(fileService.addFile(file,doc_id));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/files/update/{file_id}")
    public ResponseEntity<Boolean> updateFileName(@PathVariable("file_id") long file_id, @RequestBody String newFileName) {
        return ResponseEntity
                .ok()
                .body(fileService.updateFileName(file_id,newFileName));
    }


}

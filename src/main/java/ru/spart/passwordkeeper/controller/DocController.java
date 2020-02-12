package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.spart.passwordkeeper.controller.model.Doc;
import ru.spart.passwordkeeper.service.exception.DocNotFound;
import ru.spart.passwordkeeper.service.DocService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DocController {

    private final DocService docService;

    @Autowired
    public DocController(DocService secretService) {
        this.docService = secretService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/docs")
    public ResponseEntity<Long> addDoc(@RequestBody Doc doc) {
        return ResponseEntity
                .ok()
                .body(docService.addDoc(doc));
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/docs/{id}")
    public ResponseEntity<Void> updateDoc(@PathVariable("id") long id, @RequestBody Doc doc) {
        try {
            docService.update(id, doc);
        } catch (DocNotFound docNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/docs")
    public ResponseEntity<List<Doc>> getAllDocs() {
        return ResponseEntity
                .ok()
                .body(docService.getAllDocs());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping (value = "/docs/delete")
    public ResponseEntity<Void> deleteListDocs(@RequestBody List<Long> idList) throws FileNotFoundException {
        try {
            docService.deleteListDocs(idList);
        } catch (DocNotFound docNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }

}

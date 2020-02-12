package ru.spart.passwordkeeper.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spart.passwordkeeper.controller.model.Doc;
import ru.spart.passwordkeeper.repository.DocDataRepository;
import ru.spart.passwordkeeper.repository.UserDataRepository;
import ru.spart.passwordkeeper.repository.model.DocData;
import ru.spart.passwordkeeper.repository.model.UserData;
import ru.spart.passwordkeeper.service.exception.DocNotFound;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocService {
    private final DocDataRepository docDataRepository;
    private final UserDataRepository userDataRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final FileService fileService;

    public DocService(DocDataRepository docDataRepository, UserDataRepository userDataRepository, UserDetailsServiceImpl userDetailsServiceImpl, FileService fileService) {
        this.docDataRepository = docDataRepository;
        this.userDataRepository = userDataRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.fileService = fileService;
    }

    @Transactional
    public Long addDoc(Doc doc) {
        DocData docData = new DocData();
        docData.setDocument(doc.getDocument());
        docData.setDescription(doc.getDescription());
        docData.setUserData(getUserData());
        docDataRepository.saveAndFlush(docData);
        return docData.getId();
    }


    @Transactional
    public void deleteListDocs(List<Long> idList) throws DocNotFound, FileNotFoundException {
        for (Long id: idList) {
            fileService.deleteListFilesOfDeletingDoc(id);
            docDataRepository.deleteById(id);
        }
    }

    @Transactional
    public void update(long id, Doc doc) throws DocNotFound {
        DocData docData = docDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(DocNotFound::new);

        docData.setDocument(doc.getDocument());
        docData.setDescription(doc.getDescription());

        docDataRepository.saveAndFlush(docData);
    }

    @Transactional
    public List<Doc> getAllDocs() {
        List<Doc> allDocs = new ArrayList<>();
        List<DocData> allDocData = docDataRepository.findAllByUserData(getUserData());

        for (DocData docData : allDocData) {
            Doc scanDoc = new Doc();
            scanDoc.setId(docData.getId());
            scanDoc.setDocument(docData.getDocument());
            scanDoc.setDescription(docData.getDescription());

            allDocs.add(scanDoc);
        }

        return allDocs;
    }

    private UserData getUserData() {
        UserDetails userDetails = userDetailsServiceImpl.getCurrent();
        return userDataRepository.findByUserLogin(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);
    }
}


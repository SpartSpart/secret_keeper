package ru.spart.passwordkeeper.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.spart.passwordkeeper.configuration.yaml.YamlConfig;
import ru.spart.passwordkeeper.controller.model.FileModel;
import ru.spart.passwordkeeper.repository.FileDataRepository;
import ru.spart.passwordkeeper.repository.model.FileData;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {
    private final FileDataRepository fileDataRepository;
    private YamlConfig yamlConfig;
    private String fileDirectory;

    private String separator = "/";

    public FileService(FileDataRepository fileDataRepository, YamlConfig yamlConfig) {
        this.fileDataRepository = fileDataRepository;
        this.yamlConfig = yamlConfig;
        fileDirectory = yamlConfig.getDirectory();
    }

    @Transactional
    public List<String> addFile(MultipartFile[] files, long doc_id) {
        List <String> unUploadedFiles = new ArrayList<>();
        String dateNow = getDate();
        String fileBaseName;
        String fileExtention;
              for (MultipartFile bytefile : files) {
                  fileBaseName = FilenameUtils.getBaseName(bytefile.getOriginalFilename());
                  fileExtention = FilenameUtils.getExtension(bytefile.getOriginalFilename());
                  File file = new File(fileDirectory
                          + separator
                          + fileBaseName
                          +"_"
                          +dateNow
                          +"."
                          +fileExtention);

                  if (file.exists())
                          unUploadedFiles.add(bytefile.getOriginalFilename());
                  else{
                  try {
                      FileOutputStream fos = new FileOutputStream(file);
                      fos.write(bytefile.getBytes());
                      fos.close();

                      FileData fileData = new FileData();
                      fileData.setDocId(doc_id);
                      fileData.setFileName(bytefile.getOriginalFilename());
                      fileData.setFilePath(file.getAbsolutePath());
                      fileDataRepository.saveAndFlush(fileData);
                        } catch (FileNotFoundException e) {
                      e.printStackTrace();
                        } catch (IOException e) {
                      e.printStackTrace();
                    }
                  }
              }
        return unUploadedFiles;
    }

    @Transactional
    public List<FileModel> getAllFileInfo(long doc_id) {
        List<FileModel> allFileNames = new ArrayList<>();
        List<FileData> allFileData = fileDataRepository.findAll();

        for (FileData file : allFileData) {
            if (doc_id == file.getDocId()) {
                FileModel fileModel = new FileModel();

                fileModel.setId(file.getId());
                fileModel.setFileName(file.getFileName());
                fileModel.setDoc_id(file.getDocId());
                fileModel.setFilePath(file.getFilePath());

                allFileNames.add(fileModel);
            }
        }
        return allFileNames;
    }


    @Transactional
    public void deleteListFiles(List<Long> idList) throws FileNotFoundException {
        for (Long id: idList) {
            FileData fileData = fileDataRepository.findById(id)
                                .orElseThrow(FileNotFoundException::new);
            String filePath = fileData.getFilePath();

            File fileToDelete = new File(filePath);
            fileToDelete.delete();

            fileDataRepository.deleteById(id);
        }
   }

   @Transactional
    public File getFile(long file_id) throws FileNotFoundException {
        FileData fileData = fileDataRepository.findById(file_id)
                .orElseThrow(FileNotFoundException::new);
        String path = fileData.getFilePath();
        File file = new File(path);

        return file;
    }

    @Transactional
    public void deleteListFilesOfDeletingDoc(Long id_Doc) throws FileNotFoundException {
        List<FileData> allFiles = fileDataRepository.findAll();
        List<Long> id_filesToDelete;
            id_filesToDelete = getFilesOfDocToDelte(allFiles, id_Doc);
            deleteListFiles(id_filesToDelete);
    }

    private List<Long> getFilesOfDocToDelte(List<FileData> allFiles, long doc_id) {
        List<Long> filesToDelete = new ArrayList<>();
        for (FileData file : allFiles) {
            if (file.getDocId() == doc_id)
                filesToDelete.add(file.getId());
        }
        return filesToDelete;
    }

    private String getDate(){
        String pattern = "yyyy-MM-dd HH-mm-ss";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        return date;
    }
}

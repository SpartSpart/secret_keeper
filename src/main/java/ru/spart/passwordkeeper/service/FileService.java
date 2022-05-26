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
    private static final int MAX_FILE_NAME_LENGTH = 50;
    private final FileDataRepository fileDataRepository;
    private YamlConfig yamlConfig;
    private String fileDirectory;

    private String separator = "/";

    public FileService(FileDataRepository fileDataRepository, YamlConfig yamlConfig) {
        this.fileDataRepository = fileDataRepository;
        this.yamlConfig = yamlConfig;
        fileDirectory = yamlConfig.getDirectory();
        checkFileDirectoryExists();
    }

    private void checkFileDirectoryExists(){
        File fileDirectory = new File(this.fileDirectory);
        if (!fileDirectory.exists())
            fileDirectory.mkdirs();
    }

    @Transactional
    public List<String> addFiles(MultipartFile[] files, long doc_id) {
        List <String> unUploadedFiles = new ArrayList<>();
              for (MultipartFile byteFile : files) {
                  String fileBaseName = FilenameUtils.getBaseName(byteFile.getOriginalFilename());
                  if (fileBaseName.length()>MAX_FILE_NAME_LENGTH)
                      unUploadedFiles.add(fileBaseName);
                  else
                      saveFile(byteFile,doc_id);
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
        //String path = fileData.getFilePath();///////////////////////////////////////////////////////
        String path = fileDirectory+File.separator+fileData.getFilePath();
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

    @Transactional
    public FileModel addFile(MultipartFile byteFile, long doc_id) {

        FileData fileData = saveFile(byteFile,doc_id);

        FileModel fileModel = new FileModel();
        if (fileData!=null){
            fileModel.setId(fileData.getId());
            fileModel.setFileName(fileData.getFileName());
            fileModel.setDoc_id(fileData.getDocId());
            fileModel.setFilePath(fileData.getFilePath());
        }

        return fileModel;
    }

    private FileData saveFile(MultipartFile byteFile, long doc_id){
        String dateNow = getDate();
        String fileBaseName;
        String fileExtention;
        FileData fileData = null;

        fileBaseName = FilenameUtils.getBaseName(byteFile.getOriginalFilename());
        fileExtention = FilenameUtils.getExtension(byteFile.getOriginalFilename());
        File tempFile = new File(fileDirectory
                + separator
                + fileBaseName
                +"_"
                +dateNow
                +"."
                +fileExtention);

        if (tempFile.exists())
            return null;
        else{
            try {
                String fileName = byteFile.getOriginalFilename();
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(byteFile.getBytes());
                fos.close();

                fileName = createFileName(fileName,doc_id);

                fileData = new FileData();
                fileData.setDocId(doc_id);
                fileData.setFileName(fileName);
                fileData.setFilePath(tempFile.getName());//getAbsolutePath());/////////////////////
                fileDataRepository.saveAndFlush(fileData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return fileData;
    }

    private String createFileName(String curentFileName,long doc_id){
        int increment = 1;
            while (isFileNameExists(curentFileName, doc_id)) {
                int dot = curentFileName.lastIndexOf(".");
                if (increment>1){
                    int firstBrace = curentFileName.lastIndexOf("(");
                    if (dot != -1) {
                        String name = curentFileName.substring(0, firstBrace);
                        String extention = curentFileName.substring(dot + 1, curentFileName.length());
                        curentFileName = name + "(" + increment + ")." + extention;

                    } else {
                        String name = curentFileName.substring(0, firstBrace);
                        curentFileName = name + "(" + increment + ")";
                    }
                }
                else {
                    if (dot != -1) {
                        String name = curentFileName.substring(0, dot);
                        String extention = curentFileName.substring(dot + 1, curentFileName.length());
                        curentFileName = name + "(" + increment + ")." + extention;

                    } else {
                        curentFileName = curentFileName + "(" + increment + ")";
                    }
                }
                increment++;
        }
            return curentFileName;
    }

   private boolean isFileNameExists(String fileName, long doc_id){
        List<FileData> fileDataList = fileDataRepository.findAll();
        for(FileData fileData : fileDataList){
            if (fileData.getFileName().equals(fileName)
                    && fileData.getDocId()==doc_id){
                return true;
            }
        }
        return false;
    }

    public boolean updateFileName(long file_id, String newFileName) {
        Optional<FileData> fileData = fileDataRepository.findById(file_id);
        newFileName = newFileName.substring(1,newFileName.length()-1);
        if(isFileNameExists(newFileName, fileData.get().getDocId()))
            return false;
        else{
            fileData.get().setFileName(newFileName);
            fileDataRepository.saveAndFlush(fileData.get());
        }
        return true;
    }
}

package Controller;

import Entity.Product;
import Entity.ProductNumberInfor;
import Service.FileService;

import java.util.List;
import java.util.Map;

public class FileController {
    private FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    public List<ProductNumberInfor> readFile(String path){
        return fileService.readFile(path);
    }
    public void writeFile(String path){
        fileService.writeFile(path);
    }
}

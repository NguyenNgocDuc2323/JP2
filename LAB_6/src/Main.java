import Controller.FileController;
import Entity.Product;
import Entity.ProductNumberInfor;
import Service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/","\\") + "/Data/ProductCenter.in.txt";
        String fileOutPath = sysPath.replace("/","\\") + "/Data/ProductCenter.out.txt";
        List<Product> products = new ArrayList<Product>();
        List<ProductNumberInfor> productNumberInfors = new ArrayList<>();
        FileService fileService = new FileService(productNumberInfors,products);
        FileController fileController = new FileController(fileService);
        products.add(new Product(1,"Iphone",2500));
        products.add(new Product(2,"Sam Sung",1500));
        fileController.readFile(fileInPath);
        fileController.writeFile(fileOutPath);
    }
}
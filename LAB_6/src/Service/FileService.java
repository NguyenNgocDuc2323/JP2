package Service;

import Entity.Product;
import Entity.ProductNumberInfor;
import IGereral.IGeneric;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileService implements IGeneric<ProductNumberInfor> {
    private List<ProductNumberInfor> productNumberInfors;
    private List<Product> products;
    public FileService(List<ProductNumberInfor> productNumberInfors, List<Product> products) {
        this.productNumberInfors = productNumberInfors;
        this.products = products;
    }
    @Override
    public List<ProductNumberInfor> readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] data = line.split(",");
                    int ProductNumberInforId = Integer.parseInt(data[0]);
                    int productId = Integer.parseInt(data[1]);
                    if (productId > 0 && productId <= products.size()) {
                        Product foundProduct = products.get(productId - 1);
                        int clickNumber = Integer.parseInt(data[2]);
                        int addToCartNumber = Integer.parseInt(data[3]);
                        int checkOutNumber = Integer.parseInt(data[4]);
                        LocalDate dateTime = LocalDate.parse(data[5]);
                        ProductNumberInfor p = new ProductNumberInfor(ProductNumberInforId, foundProduct, clickNumber, addToCartNumber, checkOutNumber, dateTime);
                        productNumberInfors.add(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productNumberInfors;
    }
    @Override
    public void writeFile(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            productNumberInfors.stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getDateTime().getMonth().toString().toUpperCase(),
                            Collectors.groupingBy(p -> p.getProduct().getName())
                    ))
                    .forEach((month, productDataMap) -> {
                        StringBuilder result = new StringBuilder();
                        result.append("Total for Month: ").append(month).append("\n");

                        AtomicInteger totalClicksForMonth = new AtomicInteger();
                        AtomicInteger totalAddToCartForMonth = new AtomicInteger();
                        AtomicInteger totalCheckOutForMonth = new AtomicInteger();

                        productDataMap.forEach((productName, productData) -> {
                            totalClicksForMonth.addAndGet(productData.stream().mapToInt(ProductNumberInfor::getClickNumber).sum());
                            totalAddToCartForMonth.addAndGet(productData.stream().mapToInt(ProductNumberInfor::getAddToCartNumber).sum());
                            totalCheckOutForMonth.addAndGet(productData.stream().mapToInt(ProductNumberInfor::getCheckOutNumber).sum());
                        });
                        productDataMap.forEach((productName, productData) -> {
                            int totalClicksForProduct = productData.stream().mapToInt(ProductNumberInfor::getClickNumber).sum();
                            int totalAddToCartForProduct = productData.stream().mapToInt(ProductNumberInfor::getAddToCartNumber).sum();
                            int totalCheckOutForProduct = productData.stream().mapToInt(ProductNumberInfor::getCheckOutNumber).sum();
                            double clickPercentage = totalClicksForMonth.get() > 0 ? (totalClicksForProduct * 100.0 / totalClicksForMonth.get()) : 0;
                            double addToCartPercentage = totalAddToCartForMonth.get() > 0 ? (totalAddToCartForProduct * 100.0 / totalAddToCartForMonth.get()) : 0;
                            double checkOutPercentage = totalCheckOutForMonth.get() > 0 ? (totalCheckOutForProduct * 100.0 / totalCheckOutForMonth.get()) : 0;
                            result.append(String.format("Product: %s Click %%: %.2f%% Add to Cart %%: %.2f%% Check Out %%: %.2f%%\n",
                                    productName, clickPercentage, addToCartPercentage, checkOutPercentage));
                        });
                        result.append("------------");
                        try {
                            bw.write(result.toString());
                            bw.newLine();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}

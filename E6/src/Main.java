import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

    public class Main {
        public static void main(String[] args) {
            String sysPath = System.getProperty("user.dir");
            String fileInPath = sysPath.replace("/", "\\") + "/Data/orderDetail.in.txt";
            String fileOutPath = sysPath.replace("/", "\\") + "/Data/orderCustomer.out.txt";
            String fileCusOutPath = sysPath.replace("/", "\\") + "/Data/customer.out.txt";

            List<Product> products = new ArrayList<>();
            List<Customer> customers = new ArrayList<>();
            Map<Integer, Order> orders = new HashMap<>();
            Map<Customer, Double> customerBills = new HashMap<>();
            Map<Customer, Set<Integer>> customerProductIds = new HashMap<>();
            products.add(new Product(121, "Iphone 15", 1500, 150));
            products.add(new Product(122, "SamSung Galaxy", 1200, 120));
            products.add(new Product(125, "Nokia 1280", 500, 120));

            // read customer
            try (BufferedReader br = new BufferedReader(new FileReader(fileCusOutPath))) {
                String lineData;
                while ((lineData = br.readLine()) != null) {
                    if (!lineData.isEmpty()) {
                        String[] data = lineData.split("~");
                        Customer customer = new Customer(Integer.parseInt(data[0]), data[1], data[2]);
                        customers.add(customer);
                        customerProductIds.put(customer, new HashSet<>());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading customer file: " + e.getMessage());
            }
            orders.put(1, new Order(1, customers.get(0), LocalDateTime.of(2024, 11, 8, 8, 15)));
            orders.put(2, new Order(2, customers.get(1), LocalDateTime.of(2024, 11, 8, 11, 10)));
            List<String> orderDetails = new ArrayList<>();
            //read detail
            try (BufferedReader brDetail = new BufferedReader(new FileReader(fileInPath))) {
                String lineData;
                while ((lineData = brDetail.readLine()) != null) {
                    if (!lineData.isEmpty()) {
                        orderDetails.add(lineData);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading order details: " + e.getMessage());
            }
            //write file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutPath))) {
                orderDetails.stream()
                        .map(lineData -> {
                            StringBuilder result = new StringBuilder();
                            String[] dataDetail = lineData.split(",");
                            if (dataDetail.length == 4) {
                                int orderId = Integer.parseInt(dataDetail[1]);
                                int productId = Integer.parseInt(dataDetail[2]);
                                int quantity = Integer.parseInt(dataDetail[3]);

                                Product product = products.stream()
                                        .filter(p -> p.getId() == productId)
                                        .findFirst()
                                        .orElse(null);

                                if (product != null) {
                                    double price = product.getPrice() * quantity;

                                    Order order = orders.get(orderId);

                                    if (order != null) {
                                        Customer customer = order.getCustomer();
                                        customerBills.put(customer, customerBills.getOrDefault(customer, 0.0) + price);
                                        result.append("Customer: ").append(customer.getName()).append("\n");
                                        result.append("Product: ").append(product.getName()).append("\n");
                                        result.append("Price: ").append(price).append("\n");
                                        result.append("Quantity: ").append(quantity).append("\n");
                                        result.append("Time: ").append(order.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
                                        result.append("-------------------------\n");

                                        System.out.println(result.toString());
                                    }
                                } else {
                                    result.append("Product with ID ").append(productId).append(" not found.\n");
                                }
                            } else {
                                result.append("Invalid data in line: ").append(lineData).append("\n");
                            }
                            return result.toString();
                        })
                        .forEach(line -> {
                            try {
                                writer.write(line);
                            } catch (IOException e) {
                                System.out.println("Error writing to output file: " + e.getMessage());
                            }
                        });

                customers.stream()
                        .map(customer -> "Total bill for " + customer.getName() + ": " + customerBills.getOrDefault(customer, 0.0))
                        .forEach(line -> {
                            try {
                                writer.write(line + "\n");
                                System.out.println(line);
                            } catch (IOException e) {
                                System.out.println("Error writing total bill: " + e.getMessage());
                            }
                        });

            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
            }

        }

    }

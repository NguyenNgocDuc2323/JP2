package Service;

import Entity.CRStatistic;
import Entity.Statistic;
import IGeneral.IFileGeneric;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService implements IFileGeneric<Statistic> {
    public FileService() {}

    @Override
    public List<Statistic> readFileStatistics(String fileName) {
        List<Statistic> statistics = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String lineData;
            while ((lineData = br.readLine()) != null) {
                Statistic statistic = new Statistic();
                if(!lineData.isEmpty()){
                    String[] data = lineData.split(";");
                    statistic.setId(Integer.parseInt(String.valueOf(data[0])));
                    statistic.setView(Integer.parseInt(String.valueOf(data[1])));
                    statistic.setAddToCart(Integer.parseInt(String.valueOf(data[2])));
                    statistic.setCheckOut(Integer.parseInt(String.valueOf(data[3])));
                    statistic.setCreateAtDate(LocalDate.parse(data[4]));
                    statistics.add(statistic);
                }
            }

        }catch (IOException e){
            e.getCause();
        }
        return statistics;
    }
    @Override
    public void writeFileStatistics(String fileName, Map<CRStatistic, CRStatistic> statisticMap) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            statisticMap.forEach((key, value) -> {
                try {
                    int totalView = value.getTotalView();
                    int totalAddToCart = value.getTotalAddToCart();
                    int totalCheckOut = value.getTotalCheckOut();

                    double addToCartPercent = totalView == 0 ? 0 : (totalAddToCart / (double) totalView) * 100;
                    double checkOutPercent = totalView == 0 ? 0 : (totalCheckOut / (double) totalView) * 100;

                    String line2Write = String.format(
                            "ID: %d ,Month: %d, Year: %d - AddToCart: %.2f%%, CheckOut: %.2f%%",
                            value.getId(),
                            value.getMonth(),
                            value.getYear(),
                            addToCartPercent,
                            checkOutPercent
                    );

                    bw.write(line2Write);
                    bw.newLine();
                } catch (IOException e) {
                   e.getCause();
                }
            });
        } catch (IOException e) {
            e.getCause();
        }
    }

    public Map<CRStatistic, CRStatistic> getDataStatistics(List<Statistic> statistics) {
        Map<CRStatistic, CRStatistic> dataCrStatistics = statistics.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatistic(cr.getId(), cr.getMonthOFDate(), cr.getYearOFDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listCr -> {
                                    if (listCr.isEmpty()) {
                                        return null;
                                    }
                                    Statistic firstStatistic = listCr.get(0);
                                    CRStatistic crStatistic = new CRStatistic(
                                            firstStatistic.getId(),
                                            firstStatistic.getMonthOFDate(),
                                            firstStatistic.getYearOFDate()
                                    );

                                    int totalView = listCr.stream().mapToInt(Statistic::getView).sum();
                                    int totalAddToCart = listCr.stream().mapToInt(Statistic::getAddToCart).sum();
                                    int totalCheckOut = listCr.stream().mapToInt(Statistic::getCheckOut).sum();

                                    crStatistic.setTotalView(totalView);
                                    crStatistic.setTotalAddToCart(totalAddToCart);
                                    crStatistic.setTotalCheckOut(totalCheckOut);

                                    return crStatistic;
                                }
                        )
                ));

        return dataCrStatistics;
    }

}

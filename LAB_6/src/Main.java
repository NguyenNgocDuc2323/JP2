import Controller.FileController;
import Entity.CRStatistic;
import Entity.Statistic;
import Service.FileService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/", "\\") + "/Data/statistic.in.txt";
        String fileOutPath = sysPath.replace("/", "\\") + "/Data/statistic.out.txt";
        FileService fileService = new FileService();
        FileController fileController = new FileController(fileService);
        List<Statistic> statistics = fileController.readFileStatistics(fileInPath);
        Map<CRStatistic, CRStatistic> statisticStatisticMap = fileController.getDataStatistics(statistics);
        fileController.writeFileStatistics(fileOutPath,statisticStatisticMap);
    }
}
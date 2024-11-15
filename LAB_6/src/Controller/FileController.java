package Controller;

import Entity.CRStatistic;
import Entity.Statistic;
import Service.FileService;

import java.util.List;
import java.util.Map;

public class FileController {
    private FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    public List<Statistic> readFileStatistics(String fileName){
        return fileService.readFileStatistics(fileName);
    }
    public void writeFileStatistics(String fileName, Map<CRStatistic, CRStatistic> statisticMap){
        fileService.writeFileStatistics(fileName, statisticMap);
    }
    public Map<CRStatistic, CRStatistic> getDataStatistics(List<Statistic> statistics){
        return fileService.getDataStatistics(statistics);
    }
}

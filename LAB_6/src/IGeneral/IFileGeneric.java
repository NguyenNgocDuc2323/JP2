package IGeneral;

import Entity.CRStatistic;
import Entity.Statistic;

import java.util.List;
import java.util.Map;

public interface IFileGeneric<T>{
    public List<T> readFileStatistics(String fileName);
    public void writeFileStatistics(String fileName, Map<CRStatistic,CRStatistic> statisticMap);
}

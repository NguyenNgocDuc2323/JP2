package IGereral;

import Entity.Product;
import Entity.ProductNumberInfor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IGeneric<T> {
    public List<T> readFile(String path);
    public void writeFile(String path);
}

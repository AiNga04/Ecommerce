package hcmute.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hcmute.model.MilkTeaModel;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductRedisService {
    void saveProductById(int id, String product);

    String getProductById(int id);

    void deleteProductById(int id);

    void deleteAllProduct();

    List<MilkTeaModel> getAllProduct(
            String key, Long categoryId, PageRequest pageRequest
    ) throws JsonProcessingException;

    void saveAllProduct(String key, Long categoryId, PageRequest pageRequest, List<MilkTeaModel> productResponses);
}

package hcmute.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.model.MilkTeaModel;
import hcmute.service.IProductRedisService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ProductRedisService implements IProductRedisService {
    RedisTemplate<String, Object> redisTemplate;
    ObjectMapper objectMapper;

    private String getKeyForm(String key, Long categoryId, PageRequest pageRequest) {
        int page = pageRequest.getPageNumber();
        int size = pageRequest.getPageSize();
        Sort sort = pageRequest.getSort();
        String sortDirection = Objects.requireNonNull(sort.getOrderFor("idMilkTea"))
                                      .getDirection() == Sort.Direction.ASC ? "asc" : "desc";

        String ans = String.format("all_product:%d:%d:%s", page, size, sortDirection);
        return ans;
    }

    @Override
    public void saveProductById(int id, String product) {
        redisTemplate.opsForValue().set("product:" + id, product, 1, TimeUnit.DAYS);
    }

    @Override
    public String getProductById(int id) {
        return (String) redisTemplate.opsForValue().get("product:" + id);
    }

    @Override
    public void deleteProductById(int id) {
        redisTemplate.delete("product:" + id);
    }

    @Override
    public void deleteAllProduct() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public List<MilkTeaModel> getAllProduct(
            String keyword, Long categoryId, PageRequest pageRequest
    ) throws JsonProcessingException {
        // Implement logic to retrieve all products based on key and categoryId
        String key = this.getKeyForm(keyword, categoryId, pageRequest);
        String json = (String) redisTemplate.opsForValue().get(key);
        List<MilkTeaModel> productResponses = json == null
                ? null :
                objectMapper.readValue(json, new TypeReference<List<MilkTeaModel>>() {});
        return productResponses;
    }

    @Override
    public void saveAllProduct(
            String keyword, Long categoryId, PageRequest pageRequest, List<MilkTeaModel> productResponses
    ) {
        // Implement logic to save all products
        try {
            redisTemplate.opsForValue()
                         .set(
                                 this.getKeyForm(keyword, categoryId, pageRequest),
                                 objectMapper.writeValueAsString(productResponses)
                         );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
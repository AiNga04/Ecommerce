package hcmute.entity;

import hcmute.service.IProductRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@RequiredArgsConstructor
public class MilkTeaEntityListener {
    private final IProductRedisService productRedisService;

    @PrePersist
    public void prePersist(MilkTeaEntity productEntity) {
        log.info("PrePersist: {}", productEntity);
    }

    @PostPersist
    public void postPersist(MilkTeaEntity productEntity) {
        log.info("PostPersist: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

    @PreUpdate
    public void preUpdate(MilkTeaEntity productEntity) {
        log.info("PreUpdate: {}", productEntity);
    }

    @PostUpdate
    public void postUpdate(MilkTeaEntity productEntity) {
        log.info("PostUpdate: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

    @PreRemove
    public void preRemove(MilkTeaEntity productEntity) {
        log.info("PreRemove: {}", productEntity);
    }

    @PostRemove
    public void postRemove(MilkTeaEntity productEntity) {
        log.info("PostRemove: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

}

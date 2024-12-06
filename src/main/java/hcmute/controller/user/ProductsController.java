package hcmute.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import hcmute.entity.MilkTeaCategoryEntity;
import hcmute.entity.MilkTeaEntity;
import hcmute.entity.MilkTeaTypeEntity;
import hcmute.model.MilkTeaModel;
import hcmute.service.IMilkTeaCategoryService;
import hcmute.service.IMilkTeaService;
import hcmute.service.IMilkTeaTypeService;
import hcmute.service.IProductRedisService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ProductsController {
    IMilkTeaCategoryService milkTeaCategoryService;
    IMilkTeaTypeService milkTeaTypeService;
    IMilkTeaService milkTeaService;
    IProductRedisService productRedisService;

    @GetMapping("")
    public String showCategory(
            Model model,
            @RequestParam(defaultValue = "1", name = "page", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(defaultValue = "1", name = "category_id", required = false) Long categoryId,
            @RequestParam(defaultValue = "", required = false) String search
    ) throws JsonProcessingException {
        int totalPage = 0;
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("idMilkTea").descending());

        List<MilkTeaModel> milkTeaModels = productRedisService.getAllProduct(search, categoryId, pageRequest);

        List<MilkTeaCategoryEntity> categories = milkTeaCategoryService.findAll();
        List<List<MilkTeaTypeEntity>> types = new ArrayList<List<MilkTeaTypeEntity>>();
        model.addAttribute("categories", categories);
        for (MilkTeaCategoryEntity category : categories) {
            List<MilkTeaTypeEntity> categoriesWithTypes = milkTeaTypeService
                    .findAllByCategoryId(category.getIdCategory());
            types.add(categoriesWithTypes);
        }
        model.addAttribute("types", types);

        int count = (int) milkTeaService.count();
//        int currentPage = page.orElse(1);
        int currentPage = page;
        int pageSize = 6;

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("idMilkTea"));
        Page<MilkTeaEntity> resultpaPage = null;
        if (milkTeaModels == null) {

            resultpaPage = milkTeaService.findAll(pageable);
            productRedisService.saveAllProduct(
                    search, categoryId, pageRequest,
                    resultpaPage.getContent()
                                .stream()
                                .map(milkTeaEntity -> {
                                    MilkTeaModel
                                            milkTeaModel =
                                            new MilkTeaModel();
                                    milkTeaModel.setIdMilkTea(
                                            milkTeaEntity.getIdMilkTea());
                                    milkTeaModel.setName(
                                            milkTeaEntity.getName());
                                    milkTeaModel.setCost(
                                            milkTeaEntity.getCost());
                                    milkTeaModel.setDescription(
                                            milkTeaEntity.getDescription());
                                    milkTeaModel.setImage(
                                            milkTeaEntity.getImage());
                                    return milkTeaModel;
                                })
                                .collect(Collectors.toList())
            );
            int totalPages = resultpaPage.getTotalPages();
            if (totalPages > 0) {
                int start = Math.max(1, currentPage - 2);
                int end = Math.min(currentPage + 2, totalPages);
                if (totalPages > count) {
                    if (end == totalPages)
                        start = end - count;
                    else if (start == 1)
                        end = start + count;
                }
                List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        } else {
            List<MilkTeaEntity> milkTeaEntities = milkTeaModels.stream().map(milkTeaModel -> {
                MilkTeaEntity milkTeaEntity = new MilkTeaEntity();
                milkTeaEntity.setIdMilkTea(milkTeaModel.getIdMilkTea());
                milkTeaEntity.setName(milkTeaModel.getName());
                milkTeaEntity.setCost(milkTeaModel.getCost());
                milkTeaEntity.setDescription(milkTeaModel.getDescription());
                milkTeaEntity.setImage(milkTeaModel.getImage());
                return milkTeaEntity;
            }).collect(Collectors.toList());

            resultpaPage = new PageImpl<>(milkTeaEntities, pageable, milkTeaEntities.size());
        }

        model.addAttribute("milkTeas", resultpaPage);
        return "user/products";

    }

    @RequestMapping("type/{id}")
    public String getMilkTeaByType(
            Model model, @PathVariable("id") int typeId,
            @RequestParam("page") Optional<Integer> page
    ) {
        List<MilkTeaCategoryEntity> categories = milkTeaCategoryService.findAll();
        List<List<MilkTeaTypeEntity>> types = new ArrayList<List<MilkTeaTypeEntity>>();
        model.addAttribute("categories", categories);
        for (MilkTeaCategoryEntity category : categories) {
            List<MilkTeaTypeEntity> categoriesWithTypes = milkTeaTypeService
                    .findAllByCategoryId(category.getIdCategory());
            types.add(categoriesWithTypes);
        }
        model.addAttribute("types", types);

        int count = milkTeaService.countByTypeId(typeId);
        int currentPage = page.orElse(1);
        int pageSize = 6;

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("idMilkTea"));
        Page<MilkTeaEntity> resultpaPage = null;

        resultpaPage = milkTeaService.findAllByTypeId(typeId, pageable);

        int totalPages = resultpaPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > count) {
                if (end == totalPages)
                    start = end - count;
                else if (start == 1)
                    end = start + count;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }
        model.addAttribute("milkTeaByTypes", resultpaPage);
        model.addAttribute("idActive", typeId);
        return "user/products";
    }

}

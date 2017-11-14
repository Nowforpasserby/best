package com.logictech.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logictech.entity.dto.CountryDTO;
import com.logictech.entity.so.AppException;
import com.logictech.json.JSON;
import com.logictech.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:33
 */
@RestController
public class CountryController {

    @Autowired
    private CountryMapper countryMapper;

    @RequestMapping("/countries")
    @JSON(type = CountryDTO.class, include = "countryCode,countryName")
    public List<CountryDTO> getCities() {
        List<CountryDTO> countries = countryMapper.selectAll();
        return countries;
    }

    @RequestMapping("/countries/{pageNum}/{size}")
    @JSON(type = PageInfo.class, filter = "navigatepageNums,navigateFirstPage,navigateLastPage")
    @JSON(type = CountryDTO.class, include = "countryCode,countryName")
    public PageInfo getCitiesPage(@PathVariable Integer pageNum, @PathVariable Integer size) throws AppException {
        PageHelper.startPage(pageNum, size);
        List<CountryDTO> countries = countryMapper.selectAll();
        return new PageInfo<>(countries);
    }
}
    
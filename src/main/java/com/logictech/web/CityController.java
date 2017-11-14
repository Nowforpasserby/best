package com.logictech.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.logictech.entity.dto.CityDTO;
import com.logictech.mapper.CityMapper;
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
public class CityController {

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping("/cities")
    public List<CityDTO> getCities() {
        List<CityDTO> cities = cityMapper.selectAll();
        return cities;
    }
}
    
package com.jie.repository;

import com.jie.entity.Menu;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MenuRepository {

    public List<Menu> findMenu( int page,  int limit);

    public int count();

    public void save(@RequestBody Menu menu);

    public Menu findById( long id);

    public void update(@RequestBody Menu menu);

    public void deleteById(long id);
}

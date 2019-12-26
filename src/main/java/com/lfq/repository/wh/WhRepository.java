package com.lfq.repository.wh;

import com.lfq.entity.Wh;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: 𝓛.𝓕.𝓠
 */
public interface WhRepository extends CrudRepository<Wh,String> {
    List findAll();
}

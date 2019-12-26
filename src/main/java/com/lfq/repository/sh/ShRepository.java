package com.lfq.repository.sh;

import com.lfq.entity.Sh;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: 𝓛.𝓕.𝓠
 */
public interface ShRepository extends CrudRepository<Sh,String> {
    List findAll();
}

package com.concretepage;

import com.ocs.entity.SampleEntity;
import com.ocs.spring.service.SampleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Goodarzi on 02/16/2015.
 */
@Service
public class RunSample {
    @Autowired
    SampleEntityService sampleEntityService;

    public  void run(SampleEntity sampleEntity) {

        sampleEntityService.addSampleEntity(sampleEntity);
        System.out.println("inserted ..");


    }
}

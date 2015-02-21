package com.concretepage;

import com.ocs.entity.SampleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Goodarzi on 02/16/2015.
 */
@RestController
@RequestMapping("/mySample")
public class SampleController {
    @Autowired
    RunSample runSample;

    @RequestMapping("/testSampleEntity")
    public void saveSampleEntity(@RequestParam(value = "description", required = true) String description){

        System.out.println("Started from here..");
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setDescription(description);

        runSample.run(sampleEntity);
        System.out.println("Ends here..");

    }
}

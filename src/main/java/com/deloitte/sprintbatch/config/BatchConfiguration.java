package com.deloitte.sprintbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.deloitte.sprintbatch.model.Incident;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public FlatFileItemReader<Incident> reader() {
        FlatFileItemReader<Incident> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(
                "Crime_Reports.csv"));
        reader.setLineMapper(new DefaultLineMapper<Incident>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "incidentNumber", "highestOffenseDescription", "highestOffenseCode",
                                "familyViolence", "occurredDateTime", "occurredDate", "occurredTime",
                                "reportDateTime", "reportDate", "reportTime", "locationType", "address", "zipCode",
                                "councilDistrict", "apdSector", "apdDistrict", "pra", "censusTract",
                                "clearanceStatus", "clearanceDate", "ucrCategory", "categoryDescription",
                                "xCoordinate", "yCoordinate", "latitude", "longitude", "location" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Incident>() {
                    {
                        setTargetType(Incident.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public ItemProcessor<Incident, Incident> processor() {
        return incident -> incident;
    }

    @Bean
    public MongoItemWriter<Incident> writer() {
        MongoItemWriter<Incident> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("incidents");
        return writer;
    }

    @Bean
    public Step csvToMongoStep() {
        return stepBuilderFactory.get("csvToMongoStep")
                .<Incident, Incident>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job csvToMongoJob() {
        return jobBuilderFactory.get("csvToMongoJob")
                .start(csvToMongoStep())
                .build();
    }

}

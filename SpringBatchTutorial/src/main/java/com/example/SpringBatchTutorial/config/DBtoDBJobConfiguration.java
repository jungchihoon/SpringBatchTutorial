package com.example.SpringBatchTutorial.config;

import com.example.SpringBatchTutorial.PersonItemProcessor;
import com.example.SpringBatchTutorial.entity.SourcePerson;
import com.example.SpringBatchTutorial.entity.TargetPerson;
import com.example.SpringBatchTutorial.repository.SourcePersonRepository;
import com.example.SpringBatchTutorial.repository.TargetPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
public class DBtoDBJobConfiguration {
    @Autowired
    private SourcePersonRepository sourcePersonRepo;

    @Autowired
    private TargetPersonRepository targetPersonRepo;

    @Bean
    public Job db2dbJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("db2dbJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, RepositoryItemReader<SourcePerson> reader, RepositoryItemWriter<TargetPerson> writer, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step1", jobRepository)
                .<SourcePerson,TargetPerson>chunk(5, platformTransactionManager)
                .reader(reader)
                .processor(new PersonItemProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public RepositoryItemReader<SourcePerson> repositoryItemReader() {
        return new RepositoryItemReaderBuilder<SourcePerson>()
                .name("repositoryItemReader")
                .repository(sourcePersonRepo)
                .methodName("findAll")
                .pageSize(5)
                .sorts(Map.of("firstName", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<TargetPerson> writer() {
        return new RepositoryItemWriterBuilder<TargetPerson>()
                .methodName("save")
                .repository(targetPersonRepo)
                .build();
    }


}
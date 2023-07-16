package com.wordpress.antonio.newcarshop.batchap.configuration;

import com.wordpress.antonio.newcarshop.batchap.configuration.chuncklet.CarroItemProcessor;
import com.wordpress.antonio.newcarshop.batchap.configuration.chuncklet.CarroItemReader;
import com.wordpress.antonio.newcarshop.batchap.configuration.chuncklet.CarroItemWriter;
import com.wordpress.antonio.newcarshop.batchap.configuration.tasklet.CarroValidateTasklet;
import com.wordpress.antonio.newcarshop.batchap.dto.CarroDto;
import com.wordpress.antonio.newcarshop.batchap.model.Carro;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job job(){
        return jobBuilderFactory
                .get("carrosJob")
                .start(carroValidateTaskletStep())
                .next(carroEnriquecimentoChunckletStep(carroItemReader(),
                        carroItemProcessor(),
                        carroItemWriter()))
                .build();
    }

    @Bean
    public Step carroValidateTaskletStep(){
        return stepBuilderFactory
                .get("carroValidateTaskletStep")
                .tasklet(new CarroValidateTasklet("carros-import"))
                .build();
    }

    @Bean
    public Step carroEnriquecimentoChunckletStep(ItemReader<CarroDto> carroItemReader,
                                                 ItemProcessor<CarroDto, Carro> carroItemProcessor,
                                                 ItemWriter<Carro> carroItemWriter) {
        return stepBuilderFactory
                .get("carroEnriquecimentoChunckletStep")
                .<CarroDto, Carro>chunk(5)
                .reader(carroItemReader)
                .processor(carroItemProcessor)
                .writer(carroItemWriter)
                .build();
    }

    @Bean
    public ItemReader<CarroDto> carroItemReader(){
        return new CarroItemReader();
    }

    @Bean
    public ItemProcessor<CarroDto, Carro> carroItemProcessor(){
        return new CarroItemProcessor();
    }

    @Bean
    public ItemWriter<Carro> carroItemWriter(){
        return new CarroItemWriter();
    }
}

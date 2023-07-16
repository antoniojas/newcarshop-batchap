package com.wordpress.antonio.newcarshop.batchap.configuration.chuncklet;

import com.wordpress.antonio.newcarshop.batchap.converter.CarroConverter;
import com.wordpress.antonio.newcarshop.batchap.dto.CarroDto;
import com.wordpress.antonio.newcarshop.batchap.model.Carro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.Iterator;

public class CarroItemProcessor implements ItemProcessor<CarroDto, Carro>, StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroItemProcessor.class);


    @Override
    public void beforeStep(StepExecution stepExecution) {
        //não usar no dia a dia
        LOGGER.info("Iniciando o PROCESSOR...");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.info("Finalizando o PROCESSOR...");
        return ExitStatus.COMPLETED;
    }

    @Override
    public Carro process(CarroDto carroDto) throws Exception {
        return CarroConverter.getCarro(carroDto);
    }
}

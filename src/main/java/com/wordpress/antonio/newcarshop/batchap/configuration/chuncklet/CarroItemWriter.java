package com.wordpress.antonio.newcarshop.batchap.configuration.chuncklet;

import com.wordpress.antonio.newcarshop.batchap.model.Carro;
import com.wordpress.antonio.newcarshop.batchap.repository.CarroRepository;
import com.wordpress.antonio.newcarshop.batchap.utils.CsvFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


public class CarroItemWriter implements ItemWriter<Carro>, StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroItemReader.class);
    private CsvFileUtils csvSavedCars;

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.csvSavedCars = new CsvFileUtils("savedCars", false);

        LOGGER.info("Iniciando o Writer...");
    }

    @Override
    public void write(List<? extends Carro> carroOutList) throws Exception {
        List<? extends Carro> savedCarroList = this.carroRepository.saveAll(carroOutList);

        savedCarroList.stream().forEach(carro -> {
            try {
                this.csvSavedCars.writer(carro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try {
            this.csvSavedCars.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Finalizando o Writer...");
        return ExitStatus.COMPLETED;
    }

}

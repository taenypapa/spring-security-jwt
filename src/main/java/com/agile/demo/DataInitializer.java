package com.agile.demo;

import com.agile.demo.api.sample.SampleEntity;
import com.agile.demo.api.sample.SampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component 
@AllArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {

	private SampleRepository sampleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		sampleRepository.save(SampleEntity.builder()
						.title("sample - title")
				.build());

	}

}

package com.deloitte.sprintbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SprintBatchApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job csvToMongoJob;

	@Value("${batch.job.enabled}")
	private boolean isJobEnabled;

	public static void main(String[] args) {
		SpringApplication.run(SprintBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("===========================  @@@@ Job Enabled: " + isJobEnabled
				+ " @@@@ ============================================)");
		if (isJobEnabled) {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters();

			jobLauncher.run(csvToMongoJob, jobParameters);
		}
	}

}

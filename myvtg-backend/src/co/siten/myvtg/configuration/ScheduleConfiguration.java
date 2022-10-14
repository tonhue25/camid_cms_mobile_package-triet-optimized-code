package co.siten.myvtg.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import co.siten.myvtg.service.FakeMoSchedule;

@Configuration
@EnableScheduling
public class ScheduleConfiguration {
//	@Bean
//	public FakeMoSchedule runFakeMoSchedule() {
//		return new FakeMoSchedule();
//	}

	// @Bean
	// public SyncSubscriberScheduler runSyncSubscriber() {
	// return new SyncSubscriberScheduler();
	// }
}

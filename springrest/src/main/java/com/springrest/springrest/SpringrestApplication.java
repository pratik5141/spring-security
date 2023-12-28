package com.springrest.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redis.Authorization;
/*
@Configuration
@AutoConfigureBefore({ CompositeMeterRegistryAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class })
@AutoConfigureAfter(MetricsAutoConfiguration.class)
@ConditionalOnClass(NewRelicRegistry.class)
class MicrometerConfig {
	@Bean
	public NewRelicRegistryConfig newRelicConfig() {
		return new NewRelicRegistryConfig() {
			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public String apiKey() {
				return "your_api_key"; // for production purposes take it from config file
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(10);
			}

			@Override
			public String serviceName() {
				return "springrest"; // take it from config file
			}

		};
	}

	@Bean
	public NewRelicRegistry newRelicMeterRegistry(NewRelicRegistryConfig config) throws UnknownHostException {
		NewRelicRegistry newRelicRegistry = NewRelicRegistry.builder(config).build();
				//commonAttributes(new Attributes().put("host", InetAddress.getLocalHost().getHostName())).build());
				//(new Attributes().put("host", InetAddress.getLocalHost().getHostName())).build();
		newRelicRegistry.config().meterFilter(MeterFilter.ignoreTags("plz_ignore_me"));
		newRelicRegistry.config().meterFilter(MeterFilter.denyNameStartsWith("jvm.threads"));
		newRelicRegistry.start(new NamedThreadFactory("newrelic.micrometer.registry"));
		return newRelicRegistry;
	}
}*/

@SpringBootApplication
public class SpringrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
		/*
		 * Authorization auth = new Authorization(); //auth.getAuthToken();
		 * auth.getToken();
		 */
	}

}

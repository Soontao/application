package org.fornever.base.security;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditConfiguration {

	@Bean
	public AuditorAware<String> auditorProvider() {

		return () -> {
			try {
				return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
			} catch (Exception e) {
				return Optional.of("SYSTEM");
			}
			
		};
		
	}
}

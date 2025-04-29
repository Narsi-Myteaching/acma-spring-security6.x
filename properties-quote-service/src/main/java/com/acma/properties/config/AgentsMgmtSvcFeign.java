/**
 * 
 */
package com.acma.properties.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 
 */
@FeignClient(name = "properties-agent-service", url = "${acma.properties.agents.base-uri}")
public interface AgentsMgmtSvcFeign {

	@GetMapping(value = {"/agent-leads/zipCode"})
	List<String> fetchAgentsByZipCode(@RequestHeader("zipCode") String zipCode);
}

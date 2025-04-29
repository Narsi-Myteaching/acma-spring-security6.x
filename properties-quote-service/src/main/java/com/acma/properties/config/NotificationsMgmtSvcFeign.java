/**
 * 
 */
package com.acma.properties.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.acma.properties.beans.EmailRequestBean;
import com.acma.properties.beans.NotificationsBean;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 
 */
@FeignClient(name = "acma-notifications-service", url = "${acma.properties.notifications.base-uri}")
public interface NotificationsMgmtSvcFeign {

	@PostMapping(value = "/notifications/lead")
	List<EmailRequestBean> sendNotifications(@RequestBody NotificationsBean notificationsBean,
			@RequestHeader("leadId") int leadId);
}

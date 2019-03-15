package com.example.demo.config;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class ExtendedInfo {
    private final List<InfoContributor> infoContributors;

    /**
     * Create a new {@link InfoEndpoint} instance.
     * @param infoContributors the info contributors to use
     */
    public ExtendedInfo(List<InfoContributor> infoContributors) {
        Assert.notNull(infoContributors, "Info contributors must not be null");
        this.infoContributors = infoContributors;
    }

    @ReadOperation
    public WebEndpointResponse<Map<String, Object>> info() {
        Info.Builder builder = new Info.Builder();
        for (InfoContributor contributor : this.infoContributors) {
            contributor.contribute(builder);
        }
        builder.withDetail("status","abc");
        Info build = builder.build();
        Map<String, Object> info = build.getDetails();
        Integer status = getStatus(info);
        return new WebEndpointResponse<>(info, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        return 200;
    }
}

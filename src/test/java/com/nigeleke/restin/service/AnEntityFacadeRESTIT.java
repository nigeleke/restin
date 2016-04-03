package com.nigeleke.restin.service;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.*;
import static com.nigeleke.tuttle.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(HttpJUnitRunner.class)
public class AnEntityFacadeRESTIT {

    @Rule
    public Destination destination = new Destination(this, "http://localhost:21957/restin/rest");

    @Context
    private Response response;

    @HttpTest(method = Method.POST, path = "/anentity", type = MediaType.APPLICATION_JSON, content = "{ \"name\":\"Arthur C Clarke\" }")
    public void testCreateJson() throws Exception {
        assertCreated(response);
        assertThat(response.getType(), equalTo(MediaType.APPLICATION_JSON));
        assertThat(response.getBody(), matchesRegex("^\\{\\\"id\\\":\\d+,\\\"name\\\":\\\"Arthur C Clarke\\\"}$"));
        assertThat(response.getUrl(), matchesRegex("^http://localhost:21957/restin/rest/anentity$"));

        List<String> contentLocations = response.getHeaders().get(HttpHeaders.CONTENT_LOCATION);
        assertNotNull(contentLocations);
        assertEquals(1, contentLocations.size());
        assertThat(contentLocations.iterator().next(), matchesRegex("^http://localhost:21957/restin/rest/anentity/\\d+$"));
    }
}

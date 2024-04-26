package org.assessment.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WebConfigTest {
    @Mock
    CorsRegistry corsRegistry;

    @Mock
    CorsRegistration corsRegistration;

    @InjectMocks
    WebConfig webConfig;

    @Test
    public void addCorsMappingsTest() {
        when(corsRegistry.addMapping(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedOriginPatterns("http://localhost:*", "https://*.amplifyapp.com", "http://*.amazonaws.com")).thenReturn(corsRegistration);
        when(corsRegistration.allowedMethods("GET", "POST", "OPTIONS")).thenReturn(corsRegistration);
        when(corsRegistration.allowedHeaders(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowCredentials(anyBoolean())).thenReturn(corsRegistration);
        when(corsRegistration.maxAge(anyLong())).thenReturn(corsRegistration);

        webConfig.addCorsMappings(corsRegistry);

        verify(corsRegistry, times(1)).addMapping("/**");
        verify(corsRegistration, times(1)).allowedMethods("GET", "POST", "OPTIONS");
        verify(corsRegistration, times(1)).allowedHeaders("*");
        verify(corsRegistration, times(1)).allowCredentials(true);
        verify(corsRegistration, times(1)).maxAge(3600);
    }
}

package com.example.gpcdemoapp.config;

import com.example.gpcdemoapp.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

/**
 * @author aleksandr on 05.08.2023
 * @projectName GPCdemoApp
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class InitDBTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private InitDB initDB;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        initDB = new InitDB(resourceLoader, paymentService);
    }
    @Test
    public void testRunWithEmptyCSV() throws Exception {
        String emptyCsvData = "header\n";

        InputStream inputStream = new ByteArrayInputStream(emptyCsvData.getBytes(StandardCharsets.UTF_8));
        Resource resource = mock(Resource.class);

        when(resourceLoader.getResource("classpath:gpc.csv")).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);

        initDB.run(null);

        // Verify that no interactions with paymentService occurred
        verify(paymentService, never()).existByAccount(anyString(), anyString());
        verify(paymentService, never()).saveAndFlush(any());

        // Add more assertions if needed
    }


    @Test
    public void testRunWithDuplicateData() throws Exception {
        String csvData = "header,sender1,recipient1,100.0\n" +
                "data,sender2,recipient2,200.0\n" +
                "data,sender1,recipient1,150.0\n";

        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));
        Resource resource = mock(Resource.class);

        when(resourceLoader.getResource("classpath:gpc.csv")).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);
        when(paymentService.existByAccount(eq("sender1"), eq("recipient1"))).thenReturn(true);
        when(paymentService.existByAccount(eq("sender2"), eq("recipient2"))).thenReturn(true);

        initDB.run(null);

        // Verify the number of interactions with paymentService
        verify(paymentService, times(2)).existByAccount(anyString(), anyString());
        verify(paymentService, never()).saveAndFlush(any());

    }

    @Test
    public void testRunWithIOException() throws Exception {
        Resource resource = mock(Resource.class);

        when(resourceLoader.getResource("classpath:gpc.csv")).thenReturn(resource);
        when(resource.getInputStream()).thenThrow(new IOException());

        initDB.run(null);

        // Verify that no interactions with paymentService occurred
        verify(paymentService, never()).existByAccount(anyString(), anyString());
        verify(paymentService, never()).saveAndFlush(any());
    }
}
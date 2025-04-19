package com.learning.springhealth.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CustomMetricService {

    private final MeterRegistry meterRegistry;


    public void recordExecutionTime(String methodName, Duration duration) {
        Timer.builder("service_execution_time")
                .tag("method", methodName)
                .register(meterRegistry)
                .record(duration);
    }

    public void incrementExceptionCount(String methodName, String exceptionType) {
        Counter.builder("service_exception_count")
                .tag("method", methodName)
                .tag("exception", exceptionType)
                .register(meterRegistry)
                .increment();
    }
}

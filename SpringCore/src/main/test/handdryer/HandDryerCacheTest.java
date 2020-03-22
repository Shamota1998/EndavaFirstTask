package handdryer;

import com.endava.internship.s2020.springcoretask.handryerclasses.HandDryer;
import com.endava.internship.s2020.springcoretask.handryerclasses.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
class HandDryerCacheTest {
    Producer producer = new Producer("Panasonic", LocalDate.of(2012, 12, 2));
    @Autowired
    HandDryer dryer;

    @Configuration
    @EnableCaching
    public static class TestConfiguration {
        @Bean
        public CacheManager cacheManager() {
            SimpleCacheManager cacheManager = new SimpleCacheManager();
            cacheManager.setCaches(Collections.singletonList(
                    new ConcurrentMapCache("validatedInfo")
            ));
            return cacheManager;
        }

        @Bean
        public HandDryer handDryer() {
            return new HandDryer(null, null, mock(Validator.class, Answers.RETURNS_SMART_NULLS));
        }
    }

    @Test
    public void testCache(@Autowired CacheManager cacheManager) {
        dryer.getProducerDetails(producer);
        dryer.getProducerDetails(producer);
        ConcurrentMap cache = (ConcurrentMap) cacheManager.getCache("validatedInfo").getNativeCache();
        assertThat(cache).hasSize(1);
    }
}

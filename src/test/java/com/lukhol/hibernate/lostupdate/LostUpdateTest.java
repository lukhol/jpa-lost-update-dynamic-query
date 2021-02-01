package com.lukhol.hibernate.lostupdate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LostUpdateTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LostUpdateService lostUpdateService;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void withoutDynamicUpdate_weHaveLostUpdate() throws InterruptedException {
        Long id = lostUpdateService.without_create();

        Thread.sleep(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> lostUpdateService.without_changeFirstFieldAndWait("abc", 1000, id));
        Thread.sleep(100);
        executorService.execute(() -> lostUpdateService.without_changeSecondFieldAndWait("def", 250, id));
        executorService.awaitTermination(3, TimeUnit.SECONDS);

        WithoutDynamicUpdate fromDb = entityManager.find(WithoutDynamicUpdate.class, id);
        assertThat(fromDb.getFirstField()).isEqualTo("abc");
        assertThat(fromDb.getSecondField()).isEqualTo("second"); // Lost update
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void withDynamicUpdate_thereIsNoLostUpdate() throws InterruptedException {
        Long id = lostUpdateService.with_create();

        Thread.sleep(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> lostUpdateService.with_changeFirstFieldAndWait("abc", 1000, id));
        Thread.sleep(100);
        executorService.execute(() -> lostUpdateService.with_changeSecondFieldAndWait("def", 250, id));
        executorService.awaitTermination(3, TimeUnit.SECONDS);

        WithDynamicUpdate fromDb = entityManager.find(WithDynamicUpdate.class, id);
        assertThat(fromDb.getFirstField()).isEqualTo("abc");
        assertThat(fromDb.getSecondField()).isEqualTo("def"); // There is NO lost update
    }
}

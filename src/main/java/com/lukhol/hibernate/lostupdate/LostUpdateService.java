package com.lukhol.hibernate.lostupdate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LostUpdateService {

    private final WithoutDynamicUpdateRepository withoutDynamicUpdateRepository;
    private final WithDynamicUpdateRepository withDynamicUpdateRepository;

    @Transactional
    public Long without_create() {
        WithoutDynamicUpdate withoutDynamicUpdate = new WithoutDynamicUpdate("first", "second");
        return withoutDynamicUpdateRepository.saveAndFlush(withoutDynamicUpdate).getId();
    }

    @Transactional
    public Long with_create() {
        WithDynamicUpdate withoutDynamicUpdate = new WithDynamicUpdate("first", "second");
        return withDynamicUpdateRepository.saveAndFlush(withoutDynamicUpdate).getId();
    }

    @Transactional
    public void without_changeFirstFieldAndWait(String value, int ms, Long id) {
        WithoutDynamicUpdate entity = withoutDynamicUpdateRepository.getOne(id);
        entity.setFirstField(value);

        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional
    public void without_changeSecondFieldAndWait(String value, int ms, Long id) {
        WithoutDynamicUpdate entity = withoutDynamicUpdateRepository.getOne(id);
        entity.setSecondField(value);

        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional
    public void with_changeFirstFieldAndWait(String value, int ms, Long id) {
        WithDynamicUpdate entity = withDynamicUpdateRepository.getOne(id);
        entity.setFirstField(value);

        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional
    public void with_changeSecondFieldAndWait(String value, int ms, Long id) {
        WithDynamicUpdate entity = withDynamicUpdateRepository.getOne(id);
        entity.setSecondField(value);

        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

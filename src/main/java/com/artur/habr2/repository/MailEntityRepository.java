package com.artur.habr2.repository;

import com.artur.habr2.model.MailEntity;
import com.artur.habr2.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailEntityRepository extends JpaRepository<MailEntity, Long> {
    List<MailEntity> findAllByTargetOrderByDateTimeDesc(UserEntity target, Pageable pageable);
    List<MailEntity> findAllByAuthorOrderByDateTimeDesc(UserEntity author,Pageable pageable);
}
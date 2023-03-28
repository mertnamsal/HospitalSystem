package com.mert.utility;


import com.mert.repository.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Tüm sevisler için Spring DataJPA kullanımı için kalıp servis yapısı
 * @param <T> Entity
 * @param <ID> Entity ye ait @id ile işeretlenmiş değerin datatype ı
 */
@RequiredArgsConstructor
@Getter
public class ServiceManager<T extends BaseEntity,ID> implements IService<T,ID>{

    private final JpaRepository<T,ID> repository;

    @Override
    public T save(T t) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        t.setCreatedate(dtf.format(now));
        t.setUpdatedate(dtf.format(now));
        t.setState(true);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x->{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            x.setCreatedate(dtf.format(now));
            x.setUpdatedate(dtf.format(now));
            x.setState(true);
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        t.setUpdatedate(dtf.format(now));
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}

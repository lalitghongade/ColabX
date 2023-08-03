package com.colabx.soundx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.colabx.soundx.model.Record;


@Repository
public interface RecordRepo extends JpaRepository<Record,String> {

    void deleteById(Long id);

    Record findRecordById(Long id);
}

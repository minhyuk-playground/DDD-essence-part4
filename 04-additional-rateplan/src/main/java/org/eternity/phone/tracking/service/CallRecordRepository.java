package org.eternity.phone.tracking.service;

import org.eternity.phone.tracking.domain.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface CallRecordRepository extends JpaRepository<CallRecord, UUID> {
    @Query("""
            select
                s, c 
            from 
                CallRecord s
            join
                CallRecord c            
                on s.callingNumber = :phoneNumber
                   and s.calledNumber = c.calledNumber
                   and s.sessionId = c.sessionId 
                   and s.phase = 'STARTED'
                   and c.phase = 'COMPLETED'
            """)
    Collection<CallRecord[]> findCallRecordsToBill(@Param("phoneNumber") String phoneNumber);
}

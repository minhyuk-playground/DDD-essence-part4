package org.eternity.phone.service;

import org.eternity.phone.domain.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface CallRecordsBillingRepository extends JpaRepository<CallRecord, UUID> {
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
                   and s.status = 'STARTED'
                   and c.status = 'COMPLETED'
            """)
    Collection<CallRecord[]> findCallRecordsToBill(@Param("phoneNumber") String phoneNumber);
}

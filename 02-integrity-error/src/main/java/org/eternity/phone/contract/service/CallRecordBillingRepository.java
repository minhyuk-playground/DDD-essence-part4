package org.eternity.phone.contract.service;

import org.eternity.phone.tracking.domain.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface CallRecordBillingRepository extends JpaRepository<CallRecord, UUID> {
    @Query("""
            select
                s, c 
            from 
                CallRecord s
            join
                CallRecord c            
                on s.callingNumber = :phoneNumber
                   and s.sessionId = c.sessionId 
                   and s.status = 'STARTED'
                   and c.status = 'COMPLETED'                     
            """)
    Collection<CallRecord[]> findCallRecordsToBill(@Param("phoneNumber") String phoneNumber);
}

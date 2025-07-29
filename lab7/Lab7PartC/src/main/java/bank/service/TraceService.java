package bank.service;

import bank.domain.TraceRecord;
import bank.repositories.TraceRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class TraceService {
    @Autowired
    private TraceRepository traceRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTraceRecord(String message) {
        traceRepository.save(new TraceRecord(message));
    }
}

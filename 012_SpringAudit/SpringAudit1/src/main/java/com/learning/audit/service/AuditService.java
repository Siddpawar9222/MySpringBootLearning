package com.learning.audit.service;

import com.learning.audit.entity.AuditLog;
import com.learning.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void saveAllAudit(List<AuditLog> auditLogList) {
        try{
            auditLogRepository.saveAll(auditLogList);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public AuditLog createAuditLogObject(String entityName, String entityId, String fieldName,
                                          String oldValue, String newValue, String modifiedBy) {

        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entityName);
        auditLog.setEntityId(entityId);
        auditLog.setFieldName(fieldName);
        auditLog.setOldValue(oldValue);
        auditLog.setNewValue(newValue);
        auditLog.setModifiedBy(modifiedBy);
        auditLog.setModifiedAt(LocalDateTime.now());

        return auditLog;
    }
}

package com.learning.audit.config;

import com.learning.audit.entity.AuditLog;
import com.learning.audit.service.AuditService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuditListener {

    private final AuditService auditService;

    @Autowired
    public AuditListener(@Lazy AuditService auditService) {
        this.auditService = auditService;
    }

    @PreUpdate
    public void onPreUpdate(Object entity) throws IllegalAccessException {
        if (!(entity instanceof Cloneable)) return; // only work on cloneable entities

        Object oldEntity = getOriginalState(entity);
        if (oldEntity == null) return;

        Class<?> clazz = entity.getClass();
        String entityName = clazz.getSimpleName();
        String entityId = getEntityId(entity);

        List<AuditLog> auditLogList = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(AuditableField.class)) {
                Object newValue = field.get(entity);
                Object oldValue = field.get(oldEntity);

                if (!Objects.equals(oldValue, newValue)) {
                    AuditLog auditLogObject = auditService.createAuditLogObject(
                            entityName,
                            entityId,
                            field.getName(),
                            String.valueOf(oldValue),
                            String.valueOf(newValue),
                            getCurrentUsername()
                    );
                    auditLogList.add(auditLogObject);
                }
            }
        }

        auditService.saveAllAudit(auditLogList);
    }

    private Object getOriginalState(Object entity) {
        try {
            Method method = entity.getClass().getMethod("getOriginalState");
            return method.invoke(entity);
        } catch (Exception e) {
            return null;
        }
    }

    private String getEntityId(Object entity) {
        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return String.valueOf(idField.get(entity));
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    private String getCurrentUsername() {
        return "SYSTEM"; // You can integrate Spring Security here
    }
}

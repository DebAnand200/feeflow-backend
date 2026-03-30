package auth.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TenantContextTest {

    @AfterEach
    void tearDown() {
        TenantContext.clear();
    }

    @Test
    void setAndGetInstituteId_Success() {
        TenantContext.setInstituteId(101);
        
        Integer instituteId = TenantContext.getInstituteId();
        
        assertEquals(101, instituteId);
    }

    @Test
    void clear_RemovesInstituteId() {
        TenantContext.setInstituteId(101);
        TenantContext.clear();
        
        Integer instituteId = TenantContext.getInstituteId();
        
        assertNull(instituteId);
    }
}

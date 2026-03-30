package auth.security;

public class TenantContext {

    private static final ThreadLocal<Integer> currentTenant = new ThreadLocal<>();

    public static void setInstituteId(Integer instituteId) {
        currentTenant.set(instituteId);
    }

    public static Integer getInstituteId() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}

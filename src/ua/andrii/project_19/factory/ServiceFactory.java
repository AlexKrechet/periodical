package ua.andrii.project_19.factory;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.enums.ServiceType;
import ua.andrii.project_19.service.AdminService;
import ua.andrii.project_19.service.ClientService;

public abstract class ServiceFactory {

    public abstract AdminService getAdminService();

    public abstract ClientService getClientService();

    public static ServiceFactory getFactory(@NotNull DaoFactory daoFactory, ServiceType type) {
        if (type == ServiceType.SIMPLE) {
            return SimpleServiceFactory.getInstance(daoFactory);
        }
        return null;
    }
}

package ua.andrii.project_19.factory;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.service.AdminService;
import ua.andrii.project_19.service.AdminServiceImpl;
import ua.andrii.project_19.service.ClientService;
import ua.andrii.project_19.service.ClientServiceImpl;


public class SimpleServiceFactory extends ServiceFactory {

    private static SimpleServiceFactory instance;
    private DaoFactory daoFactory;
    private AdminService adminService;
    private ClientService clientService;

    private SimpleServiceFactory(@NotNull DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        adminService = new AdminServiceImpl(daoFactory.getUserDao(), daoFactory.getPeriodicalDao(), daoFactory.getPublisherDao(), daoFactory.getOrderDao());
        clientService = new ClientServiceImpl(daoFactory.getUserDao(), daoFactory.getPeriodicalDao(), daoFactory.getPublisherDao(), daoFactory.getOrderDao());
    }

    public static SimpleServiceFactory getInstance(@NotNull DaoFactory daoFactory) {
        synchronized (SimpleServiceFactory.class) {
            if (instance == null) {
                instance = new SimpleServiceFactory(daoFactory);
            }
        }
        return instance;
    }

    @Override
    public AdminService getAdminService() {
        return adminService;
    }

    @Override
    public ClientService getClientService() {
        return clientService;
    }

}
